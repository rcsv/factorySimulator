package org.rcsvp.factory.impl ;

import java.util.Iterator ;
import java.util.Map ;
import java.util.HashMap ;
import java.util.concurrent.Executor ;
import java.util.concurrent.Executors ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.IAgv ;
import org.rcsvp.factory.IExport ;
import org.rcsvp.factory.IFactory ;
import org.rcsvp.factory.ILabor ;
import org.rcsvp.factory.IMonitorRoom ;
import org.rcsvp.factory.IProductionLine ;
import org.rcsvp.factory.IWarehouse ;
import org.rcsvp.factory.Status ;
import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * Factory class is a simple implementation of IFactory interface.
 * 
 * @author Rcsvp.org
 * @date Jul 12, 2013
 * 
 */
public class Factory extends AbstFacilities implements IFactory {

	/**
	 * factory personnel data
	 */
	private Map<String, ILabor> labors ;

	/**
	 * production lines
	 */
	private Map<String, IProductionLine> lines ;

	/**
	 * AGVs
	 */
	private Map<String, IAgv> AGVs ;

	/**
	 * Warehouse
	 */
	private IWarehouse warehouse ;

	/**
	 * Export area.
	 */
	private IExport export ;

	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------

	/**
	 * Construct Factory instance with two arguments: unique name and tact time.
	 * 
	 * @param name
	 *            a name of factory. it required unique.
	 * @param tactTime
	 *            a tact time of this factory surveillance interval.
	 */
	public Factory(String name, long tactTime) {
		super(name, tactTime) ;

		initialize() ;
	}

	/**
	 * Construct Factory instance with one argument: a unique name of factory.
	 * Set tact time 60 when you use this constructor.
	 * 
	 * @param name
	 *            a name of factory. It required unique.
	 */
	public Factory(String name) {
		super(name, ControlCenter.getInstance().getDefaultCycleTime()) ;

		initialize() ;
	}

	/**
	 * Initialize.
	 */
	private void initialize() {

		this.labors = new HashMap<String, ILabor>() ;
		this.lines = new HashMap<String, IProductionLine>() ;
		this.AGVs = new HashMap<String, IAgv>() ;

		this.mr = MonitorRoom.getInstance(this.name) ;

		this.warehouse = new Warehouse("warehouse of " + this.name) ;
		this.export = new Export("export of " + this.name) ;

	}

	// -----------------------------------------------------------------------
	// override / abstract method.
	// -----------------------------------------------------------------------

	@Override
	public boolean register(IRegistrable target) {

		target.setMonitorRoom(mr) ;

		// -------------------------------------------------------------------
		// switch target name and store each hashmaps.
		// -------------------------------------------------------------------

		String targetName = target.getClass().getSimpleName() ;

		switch (targetName) {

		case "Labor":
			//
			// --- Labor --- ILabor interface
			//
			labors.put(target.toString(), (ILabor) target) ;
			break ;

		case "ProductionLine":
			//
			// --- ProductionLine --- IProductionLine interface
			//
			lines.put(target.toString(), (IProductionLine) target) ;
			break ;

		case "Agv":
			//
			// --- Agv --- IAgv interface
			//
			AGVs.put(target.toString(), (IAgv) target) ;
			break ;

		case "Warehouse":
		case "Export":
			Logger.error("You try to register facilities beyond necessity. STOP.") ;
		default:

			//
			// RuntimeException?
			// return false ;
			throw new RuntimeException() ;

			// break ;
		}

		return true ;
	}

	/**
	 * Check facility is sufficient.
	 * - ILabor, IProductionLine, IWarehouse, and IExport must be registered.
	 * 
	 * @return
	 */
	private boolean facilityCheck() {
		return (labors.size() != 0 && lines.size() != 0 && this.warehouse != null) ? true
				: false ;
	}

	@Override
	protected void bootUp() {

		Logger.debug(this.name + " : start bootUp().......................") ;

		if (!facilityCheck()) {
			throw new RuntimeException("Insufficient facilities.") ;
		}

		//
		// display information into logging.
		//
		Logger.info(this.name + " : has " + labors.size() + " labors.") ;
		Logger.info(this.name + " : has " + lines.size() + " production lines.") ;
		Logger.info(this.name + " : has " + this.AGVs.size() + " AGVs.") ;
		Logger.info(this.name + " : has a warehouse "
				+ this.warehouse.toString()) ;
		Logger.info(this.name + " : has a export " + this.export.toString()) ;

		//
		// kick start labors
		// it already checked whether labors exists at facilityCheck().

		Iterator<ILabor> iL = labors.values().iterator() ;
		Executor exeLabors = Executors.newFixedThreadPool(labors.size()) ;

		while (iL.hasNext()) {

			exeLabors.execute(iL.next()) ;
		}

		//
		// kick start production lines
		// it already checked whether labors exists at facilityCheck().
		//
		Iterator<IProductionLine> iP = lines.values().iterator() ;
		Executor exeLines = Executors.newFixedThreadPool(lines.size()) ;

		while (iP.hasNext()) {

			exeLines.execute(iP.next()) ;

		}

		//
		// kick start AGV
		//
		if (AGVs.size() != 0) {
			Iterator<IAgv> iA = AGVs.values().iterator() ;
			Executor exeAgv = Executors.newFixedThreadPool(AGVs.size()) ;

			while (iA.hasNext()) {

				exeAgv.execute(iA.next()) ;

			}
		}

		Logger.debug(this.name + " : finish bootUp().....................") ;
	}

	@Override
	protected boolean otherCheck() {
		return true ;
	}

	@Override
	protected void routines() {

		Logger.debug(this.name + " : working.") ;

	}

	@Override
	protected void finishProcess() {

		//
		// Shutdown labors.
		//

		ILabor xL = null ;
		Iterator<ILabor> iL = labors.values().iterator() ;

		while (iL.hasNext()) {

			xL = iL.next() ;
			xL.shutdown(Status.ShutdownNormally) ;

		}

		//
		// Shutdown Production Line
		//
		IProductionLine xP = null ;
		Iterator<IProductionLine> iP = lines.values().iterator() ;

		while (iP.hasNext()) {

			xP = iP.next() ;
			xP.shutdown(Status.ShutdownNormally) ;
		}

		//
		// Shutdown AGV.
		//

		IAgv xA = null ;
		Iterator<IAgv> iA = AGVs.values().iterator() ;

		while (iA.hasNext()) {

			xA = iA.next() ;
			xA.shutdown(Status.ShutdownNormally) ;
		}

		//
		// Shutdown? 
		// Export / Warehouse
		//

		this.warehouse.shutdown(Status.ShutdownNormally) ;

		// EXPORT SHUTDOWN ?
	}
}

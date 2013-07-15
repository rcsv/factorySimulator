package org.rcsvp.factory.impl ;

import java.util.Iterator ;
import java.util.LinkedList ;
import java.util.List ;
import java.util.concurrent.Executor ;
import java.util.concurrent.Executors ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.IProcedure ;
import org.rcsvp.factory.IProductionLine ;
import org.rcsvp.factory.attributes.IRegistrable ;

public class ProductionLine extends AbstFacilities implements IProductionLine {

	/**
	 * daily norm.
	 */
	private long norm ;

	private List<IProcedure> procs ;

	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------

	/**
	 * Construct production line instance with two arguments, name and tacttime.
	 * 
	 * @param name
	 *            a unique name for ProductionLine.
	 * @param tactTime
	 *            a tact time of product target.
	 */
	public ProductionLine(String name, long tactTime) {
		super(name, tactTime) ;

		initialize() ;
	}

	public ProductionLine(String name) {
		super(name, ControlCenter.getInstance().getDefaultCycleTime()) ;

		initialize() ;
	}

	private void initialize() {

		procs = new LinkedList<IProcedure>() ;
		norm = 0 ;

	}

	// -----------------------------------------------------------------------
	// override / abstract method.
	// -----------------------------------------------------------------------

	@Override
	public boolean register(IRegistrable target) {

		target.setMonitorRoom(mr) ;

		// -------------------------------------------------------------------
		// switch target name and store each linkedlist.
		// -------------------------------------------------------------------

		String targetName = target.getClass().getSimpleName() ;

		switch (targetName) {

		case "Procedure":

			//
			// --- procedure --- IProcedure implementation
			//

			procs.add((IProcedure) target) ;

			break ;

		default:
			Logger.error("Unintended register call.") ;

			throw new RuntimeException() ;

		}

		return true ;

	}

	/**
	 * Check facility is sufficient. IProcedure must be registered one or more.
	 * Norm must positive number or zero.
	 * 
	 * @return
	 */
	private boolean facilityCheck() {

		return (procs.size() != 0 && this.norm < 0) ? true : false ;

	}

	@Override
	protected void bootUp() {

		Logger.debug(this.name + " : start bootUp()........................") ;

		if (!facilityCheck()) {

			throw new RuntimeException("Insufficient facilities.") ;

		}

		//
		// Kick start procedures.
		// it already checked whether labors exists at facilityCheck().

		
		Iterator<IProcedure> iP = procs.iterator() ;
		Executor exeProc = Executors.newFixedThreadPool(procs.size()) ;

		while (iP.hasNext()) {

			exeProc.execute(iP.next()) ;

		}

		Logger.debug(this.name + " : finish bootUp().......................") ;
	}

	@Override
	protected boolean otherCheck() {
		// TODO Auto-generated method stub
		return false ;
	}

	@Override
	protected void routines() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void finishProcess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNorm(long norm) {
		
		this.norm = norm ;
		
	}

}

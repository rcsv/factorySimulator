package org.rcsvp.factory.impl ;

import java.util.* ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.IFactory ;
import org.rcsvp.factory.ILabor ;
import org.rcsvp.factory.IProductionLine ;
import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * Factory class is a simple implementation of IFactory interface.
 * 
 * @author Rcsvp.org
 * 
 */
public class Factory extends AbstFacilities implements IFactory {

	private Map<String, ILabor> labors ;
	private Map<String, IProductionLine> lines ;

	public Factory(String name, long tactTime) {
		super(name, tactTime) ;

		labors = new HashMap<String, ILabor>() ;
		lines = new HashMap<String, IProductionLine>() ;

		cc = ControlCenter.getInstance(name) ;
		cc.setFactory(this) ;
	}

	@Override
	public boolean register(IRegistrable target) {
		target.setControlCenter(cc) ;

		String targetName = target.getClass().getSimpleName() ;

		switch (targetName) {
		case "Labor":
			labors.put(target.toString(), (ILabor) target) ;
			break ;
		case "ProductionLine":
			lines.put(target.toString(), (IProductionLine) target) ;
		default:
			break ;
		}
		return true ;
	}

	/*
	 * from now, AbstFacility AREA --------------------------------------------
	 */
	@Override
	protected void bootUp() {

		Logger.infoWrite(this.name + " : start working today's job.") ;
		Logger.infoWrite(this.name + " : has " + labors.size() + " labors.") ;
		Logger.infoWrite(this.name + " : has " + lines.size()
				+ " production lines.") ;
		Logger.debugWrite(this.name + " : labor comming...") ;

		// start labors

		ThreadGroup thLabors = new ThreadGroup(this.name + "Labors") ;

		Iterator<ILabor> iL = labors.values().iterator() ;

		while (iL.hasNext()) {

			new Thread(thLabors, iL.next()).start() ;

		}

		Logger.infoWrite(this.name + " : call staff into working.") ;

		// start production line

		ThreadGroup thLines = new ThreadGroup(this.name + "Lines") ;

		Iterator<IProductionLine> iP = lines.values().iterator() ;

		while (iP.hasNext()) {

			new Thread(thLines, iP.next()).start() ;

		}

		Logger.infoWrite(this.name
				+ " : Production lines power-on and all green.") ;

	}

	@Override
	protected void routines() {
		Logger.debugWrite(this.name + " : working...") ;
	}

	@Override
	protected void finishProcess() {
		Iterator<ILabor> iL = labors.values().iterator() ;
		ILabor x = null ;
		while (iL.hasNext()) {
			x = iL.next() ;
			x.shutdown(GeneralStatus.NormallyShutdown) ;
		}
		Iterator<IProductionLine> iP = lines.values().iterator() ;
		IProductionLine pl = null ;
		while (iP.hasNext()) {
			pl = iP.next() ;
			pl.shutdown(GeneralStatus.NormallyShutdown) ;
		}
	}

	@Override
	protected boolean otherCheck() {
		return true ;
	}

}

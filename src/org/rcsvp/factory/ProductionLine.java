package org.rcsvp.factory ;

import java.util.* ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.common.* ;

public class ProductionLine extends AbstFacilities implements IProductionLine {

	private long norm ;

	private volatile long count ;

	private List<IProcedure> procs ;

	public ProductionLine(String name, long tactTime, long normA) {
		super(name, tactTime) ;
		norm = normA ;
		procs = new LinkedList<IProcedure>() ;
	}

	public boolean register(IRegistrable target) {
		target.setControlCenter(cc) ;

		String targetName = target.getClass().getSimpleName() ;

		switch (targetName) {
		case "Procedure":
			((IProcedure) target).setTactTime(this.tactTime) ;
			if (procs.size() != 0) {
				((IProcedure) target).register(procs.get(procs.size() - 1)) ;
			}
			procs.add((IProcedure) target) ;
			break ;
		}
		return true ;
	}

	@Override
	protected void bootUp() {

		Logger.infoWrite(this.name + " : today's NORMA = " + this.norm) ;
		Logger.infoWrite(this.name + " : my tact time  = " + this.tactTime) ;
		Logger.infoWrite(this.name + " : procedure num = " + this.procs.size()) ;
		Logger.debugWrite(this.name + " : start Procedures...") ;
		ThreadGroup thProcs = new ThreadGroup(this.name + "Procs") ;
		Iterator<IProcedure> iP = procs.iterator() ;
		while (iP.hasNext()) {
			new Thread(thProcs, iP.next()).start() ;
		}
		Logger.infoWrite(this.name + " : start all procedures successfully.") ;
		count = 0 ;

	}

	@Override
	protected void finishProcess() {

		/* 
                 * 
                 */
		Logger.debugWrite(this.name + " : finish process start.") ;
		Iterator<IProcedure> iP = procs.iterator() ;
		while (iP.hasNext()) {
			iP.next().shutdown(GeneralStatus.NormallyShutdown) ;
		}

	}

	@Override
	protected void routines() {
		// TODO Auto-generated method stub

		IProcedure temp = procs.get(procs.size() - 1) ;
		count = temp.getOutputCount() ;
		// count++ ;
		Logger.infoWrite(this.name + " : current output is " + count) ;
	}

	@Override
	protected boolean otherCheck() {
		return count <= norm ? true : false ;
	}
}
package org.rcsvp.factory.impl ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.IAlertBox ;
import org.rcsvp.factory.IControlCenter ;
import org.rcsvp.factory.IParent ;
import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * AbstFacility class represent a workflow of this simulation object. there are
 * initialize, register child node and Thread start. After starting as a thread,
 * it step following step: 1. bootUp, 2. routine, 3. finish process. it'll
 * finish after all method are passed completely.
 * 
 * This pattern will be going to apply IFactory, IProductionLine, ILabor, and
 * IProcedure implementation classes.
 * 
 * @author Rcsvp.org
 * @date Jul 5, 2013
 * 
 */
public abstract class AbstFacilities implements IRegistrable, IParent {

	/**
	 * A instance of IControlCenter it must be call method
	 * {@link IControlCenter#notify(org.rcsvp.factory.IControlCenter.IAlertBox)}
	 * when condition of each objects are changed.
	 */
	protected IControlCenter cc ;

	/**
	 * Stored object name for Map's key of parent node.
	 */
	protected String name ;

	/**
	 * Trigger of thread loop.
	 * A thread will stop when it values true.
	 */
	protected volatile boolean powerOff = false ;

	protected GeneralStatus status ;

	protected long tactTime ;

	public AbstFacilities(String name, long tactTime) {
		this.name = name ;
		this.tactTime = tactTime ;
		this.status = GeneralStatus.Ready ;
	}

	@Override
	public IStatusEnum getStatus() {
		return this.status ;
	}

	@Override
	public void run() {
		Logger.debugWrite(this.name + " : start running.") ;
		this.status = GeneralStatus.Working ;
		cc.notify(new AlertBox(this)) ;

		bootUp() ;

		while (!powerOff && otherCheck()) {

			try {
				Thread.sleep(this.tactTime * cc.getTimeScale()) ;
			} catch (InterruptedException e) {
				e.printStackTrace() ;
			}

			Logger.debugWrite(this.name + " : working job...") ;
			routines() ;

		}

		this.status = GeneralStatus.NormallyShutdown ;
		cc.notify(new AlertBox(this)) ;
		finishProcess() ;
		Logger.debugWrite(this.name + " : finish work.") ;
	}

	protected abstract void bootUp() ;

	protected abstract boolean otherCheck() ;

	protected abstract void routines() ;

	protected abstract void finishProcess() ;

	@Override
	public void setControlCenter(IControlCenter cc) {
		this.cc = cc ;
		cc.notify(new AlertBox(this)) ;
	}

	@Override
	public void shutdown(IStatusEnum status) {
		this.powerOff = true ;
	}

	@Override
	public String toString() {
		return this.name ;
	}
}

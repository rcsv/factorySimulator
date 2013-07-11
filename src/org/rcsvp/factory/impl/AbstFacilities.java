package org.rcsvp.factory.impl ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.IMonitorRoom ;
import org.rcsvp.factory.IStatus ;
import org.rcsvp.factory.Status ;
import org.rcsvp.factory.attributes.IParent ;
import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * AbstFacility class represent a workflow of this simulation object. there are
 * initialize, register child node and Thread start. After starting as a thread,
 * it step following step: 1. bootUp, 2. routine, 3. finish process. it'll
 * finish after all method are passed completely.
 * 
 * This pattern will be going to apply IFactory. IProductionLine, ILabor, and
 * IProcedure implementation classes.
 * 
 * @author Rcsvp.org
 * @date Jul 5, 2013
 * 
 */
public abstract class AbstFacilities implements IRegistrable, IParent {

	/**
	 * Set boot strap process when Thread running.
	 */
	protected abstract void bootUp() ;

	/**
	 * When pushed PowerOff button, but is it really shutdown? facility have to
	 * check whether shutdown itself.
	 * 
	 * @return OK is true, NG is false.
	 */
	protected abstract boolean otherCheck() ;

	/**
	 * Routines.
	 */
	protected abstract void routines() ;

	/**
	 * finish process after power off.
	 */
	protected abstract void finishProcess() ;

	/**
	 * a reference of MonitorRoom. It must be call method register( IAlertBox )
	 * initially.
	 */
	protected IMonitorRoom mr ;

	/**
	 * a key name for register into parent node, and MonitorRoom.
	 */
	protected String name ;

	/**
	 * Shutdown trigger of thread loop. A thead will stop when its values change
	 * true.
	 */
	protected volatile boolean powerOff = false ;

	/**
	 * status.
	 */
	protected IStatus status ;

	/**
	 * IProductionLine, IProcedure need its time.
	 */
	protected long tactTime ;

	public AbstFacilities(String name, long tactTime) {
		this.name = name ;
		this.tactTime = tactTime ;
		this.status = Status.Ready ;
	}

	@Override
	public IStatus getStatus() {
		return this.status ;
	}

	@Override
	public void setMonitorRoom(IMonitorRoom mr) {
		this.mr = mr ;
		mr.register(new AlertBox(this)) ;
	}

	@Override
	public void shutdown(IStatus status) {
		this.powerOff = true ;
	}

	@Override
	public String toString() {
		return this.name ;
	}

	@Override
	// ------------------------------------------------------------------------
	// MAIN LOGIC
	// ------------------------------------------------------------------------
	public void run() {
		Logger.debug(this.name + " : THREAD START..........................") ;

		//
		// start working.
		//
		this.status = Status.Working ;

		//
		// --- phase 1: boot strap process start.
		//
		bootUp() ;

		//
		// --- phase 2: working routine process.
		//

		while (!powerOff && otherCheck()) {

			try {
				Thread.sleep(this.tactTime
						* ControlCenter.getInstance().getTimeScale()) ;
			} catch (InterruptedException e) {
				e.printStackTrace() ;
			}
			Logger.debug(this.name
					+ " : WHILE LOOP............................") ;
			
			routines () ;
		}

		Logger.debug(this.name + " : THREAD FINISH.........................") ;
		
	}
	
	protected void setStatus ( IStatus stat ) {
		this.status = stat ;
		//
		// send MonitorRoom "update"
		//
	}
}
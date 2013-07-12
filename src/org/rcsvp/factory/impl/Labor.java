package org.rcsvp.factory.impl ;

import java.util.Iterator ;
import java.util.Map ;
import java.util.Map.Entry ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.IAlertBox ;
import org.rcsvp.factory.IDisposable ;
import org.rcsvp.factory.IFactory ;
import org.rcsvp.factory.ILabor ;
import org.rcsvp.factory.IStatus ;
import org.rcsvp.factory.IWarehouse ;
import org.rcsvp.factory.Status ;
import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * Labor class a simple implementation of ILabor.
 * 
 * @author Rcsvp.org
 * @date Jul 11, 2013
 */
public class Labor extends AbstFacilities implements ILabor {

	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------

	/**
	 * Construct instance Labor with two arguments: 1. Labor's name. It must be
	 * unique. 2. tactTime. it must be positive number.
	 * 
	 * @param name
	 *            a unique name for Labor instances.
	 * @param tactTime
	 *            a tact time for labor.
	 */
	public Labor(String name, long tactTime) {
		super(name, tactTime) ;
	}

	/**
	 * Construct instance Labor with a argument: Labor's name.
	 * 
	 * @param name
	 *            a unique name for Labor instances.
	 */
	public Labor(String name) {
		super(name, ControlCenter.getInstance().getDefaultCycleTime()) ;
	}

	// -----------------------------------------------------------------------
	// implement override / abstract methods
	// -----------------------------------------------------------------------

	@Override
	/**
	 * In current specification, Labor class does not have any other instances
	 * as a child node.
	 */
	public boolean register(IRegistrable target) {
		throw new RuntimeException() ;
	}

	@Override
	protected void bootUp() {
		Logger.debug(this.name + " : come office. 'Good morning everyone.'") ;
	}

	@Override
	protected boolean otherCheck() {
		return true ;
	}

	@Override
	protected void routines() {

		Logger.debug(this.name + " : check at Monitor Room................") ;

		if (canGoHome()) {

			//
			// TGI(F?)!
			//
			mr.powerOff() ;

			this.status = Status.ShutdownNormally ;

		}
	}

	private boolean canGoHome() {

		//
		// check at MonitorRoom.
		//
		boolean goHome = true ;

		//
		// prepare to get MonitorRoom Console information
		//
		Entry<String, IAlertBox> temp = null ;

		Map<String, IAlertBox> console = mr.getConsole() ;
		Iterator<Entry<String, IAlertBox>> iC = console.entrySet().iterator() ;

		while (iC.hasNext()) {

			//
			// get check item and pick up a current status from IAlertBox.
			//
			temp = iC.next() ;

			IStatus cStatus = temp.getValue().getTarget().getStatus() ;

			Logger.debug(this.name + " : check " + temp.getValue().getTarget()
					+ " - " + temp.getValue().getStatus()) ;

			if (cStatus == Status.Problem) {

				//
				// A facility have a problem. you have to repair, reload
				// something.
				//
				goHome = false ;

			} else if (cStatus == Status.Working) {

				//
				// We must ignore labors and factory as a surveillance target.
				// Otherwise we never go home!
				//
				if (!checkSurveillanceTarget(temp.getValue().getTarget())) {
					goHome = false ;
				}

			} else if (cStatus == Status.Ready) {

				//
				// It doesn't start work this day. As a matter of course, you
				// cannot go home for the moment.
				//
				goHome = false ;
			}

		}

		return goHome ;
	}

	/**
	 * It is Working, but Labor, Factory, Disposable, Warehouse cannot shutdown
	 * Labor start shutdown. So Labor instances never go home. check if these
	 * instances status is just "Shutdown".
	 * 
	 * @param x
	 * @return
	 */
	private boolean checkSurveillanceTarget(IRegistrable x) {
		return (x instanceof ILabor || x instanceof IFactory
				|| x instanceof IDisposable || x instanceof IWarehouse)

		? true : false ;
	}

	@Override
	protected void finishProcess() {
		//
		// nothing to do especially.
		//
	}

}

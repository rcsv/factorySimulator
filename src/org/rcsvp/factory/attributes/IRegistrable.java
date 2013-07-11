package org.rcsvp.factory.attributes ;

import org.rcsvp.factory.IMonitorRoom ;
import org.rcsvp.factory.IStatus ;

/**
 * IRegistrable intefrace represents set information into a MonitorRoom
 * implements instance. This interface requires multi-thread, obtain status and
 * register.
 * 
 * @author Rcsvp.org
 * @date Jul 11, 2013
 * 
 */
public interface IRegistrable extends Runnable {

	/**
	 * IRegistrable instance must return current status.
	 * 
	 * @return
	 */
	IStatus getStatus() ;

	/**
	 * IParent call it when registering IRegistrable.
	 * 
	 * @param mr
	 */
	void setMonitorRoom(IMonitorRoom mr) ;

	/**
	 * IRegistrable have to implement shutdown in order to follow the parent
	 * command.
	 * 
	 * @param status
	 */
	void shutdown(IStatus status) ;

}
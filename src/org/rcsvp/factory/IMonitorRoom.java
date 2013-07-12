package org.rcsvp.factory ;

import java.util.Map ;

/**
 * IMonitorRoom (ex IControlCenter) represents a management-room of a factory.
 * It can be checked all status in a factory's situation. Worker instance that
 * implemented ILabor interface check and action triggered by it.
 * 
 * @author Rcsvp.org
 * @date Jul 6, 2013
 * 
 */
public interface IMonitorRoom {

	/**
	 * get Console Information and a reference of facility that have a problem.
	 * 
	 * @return a name and reference of facility.
	 */
	Map<String, IAlertBox> getConsole() ;

	/**
	 * set factory.
	 * 
	 * @param factory
	 */
	void setFactory(IFactory factory) ;

	/**
	 * set reference and status into MonitorRoom.
	 * 
	 * @return
	 */
	boolean register(IAlertBox alertbox) ;
	
	/**
	 * When Labor check all tasks done, Labor try to shutdown whole Factory.
	 * @return
	 */
	boolean powerOff () ;

}
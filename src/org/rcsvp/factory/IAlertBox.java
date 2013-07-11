package org.rcsvp.factory ;

import org.rcsvp.factory.attributes.* ;

/**
 * IAlertBox represents a status notification unit in MonitorRoom. IMonitorRoom
 * use it when it notify each unit status to labor.
 * 
 * @author Rcsvp.org
 * @date Jul 11, 2013
 * 
 */
public interface IAlertBox {

	/**
	 * Labor need to know the address of facilities when it warned problem via
	 * AlertBox.
	 * 
	 * @return a reference of facility address that have a problem
	 */
	IRegistrable getTarget() ;

	/**
	 * get status of factory objects.
	 * 
	 * @return a status of reference.
	 */
	IStatus getStatus() ;

}

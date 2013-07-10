package org.rcsvp.factory ;

import java.util.Map ;

import org.rcsvp.factory.IRegistrable.* ;

/**
 * IControlCenter represents a management-room of a factory. It can be checked
 * all status in a factory's situation. Worker instance that implemented ILabor
 * interface check and action triggered by it.
 * 
 * {$Id$}
 * 
 * @see ILabor
 * @author Rcsvp.org
 * @date Jul 6, 2013
 * 
 */
public interface IControlCenter {

	Map<String, IAlertBox> getConsole() ;

	boolean powerOff() ;

	void setFactory(IFactory factory) ;

	long getTimeScale() ;

	void setTimeScale(long timeScale) ;

	boolean notify(IAlertBox alertbox) ;
}

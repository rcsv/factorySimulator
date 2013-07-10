package org.rcsvp.factory.attributes ;

import org.rcsvp.factory.IControlCenter ;

/**
 * IRegistrable interface represents set information into a IControlCenter
 * implements instance. This interface requires multi-thread, obtain status and
 * register.
 * 
 * @author Rcsvp.org
 * 
 */
public interface IRegistrable extends Runnable {

	void setControlCenter(IControlCenter cc) ;

	void shutdown(IStatusEnum status) ;

	IStatusEnum getStatus() ;

	interface IStatusEnum {
	}

}
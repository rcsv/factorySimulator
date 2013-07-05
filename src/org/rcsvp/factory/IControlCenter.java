package org.rcsvp.factory ;

import java.util.Map ;

import org.rcsvp.factory.IRegistrable.* ;

public interface IControlCenter {

	interface IAlertBox {
		IRegistrable getTarget() ;

		IStatusEnum getStatus() ;
	}

	Map<String, IAlertBox> getConsole() ;

	boolean powerOff() ;

	void setFactory(IFactory factory) ;

	long getTimeScale() ;

	void setTimeScale(long timeScale) ;

	boolean notify(IAlertBox alertbox) ;
}
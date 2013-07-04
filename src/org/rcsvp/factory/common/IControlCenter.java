package org.rcsvp.factory.common;

import java.util.Map;

import org.rcsvp.factory.common.IRegistrable.*;

public interface IControlCenter {

	interface IAlertBox {
		IRegistrable getTarget();

		IStatusEnum getStatus();
	}

	Map<String, IAlertBox> getConsole();

	boolean powerOff();

	void setFactory(IFactory factory);

	long getTimeScale();

	void setTimeScale(long timeScale);

	boolean notify(IAlertBox alertbox);
}
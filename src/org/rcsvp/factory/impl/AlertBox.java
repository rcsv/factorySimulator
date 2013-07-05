package org.rcsvp.factory.impl ;

import org.rcsvp.factory.IRegistrable ;
import org.rcsvp.factory.IControlCenter.IAlertBox ;
import org.rcsvp.factory.IRegistrable.* ;

public class AlertBox implements IAlertBox {

	private IStatusEnum status ;
	private IRegistrable object ;

	public AlertBox(IStatusEnum stat, IRegistrable target) {
		status = stat ;
		object = target ;
	}

	public AlertBox(IRegistrable target) {
		status = target.getStatus() ;
		object = target ;
	}

	@Override
	public IRegistrable getTarget() {
		return object ;
	}

	public IStatusEnum getStatus() {
		return status ;
	}

}
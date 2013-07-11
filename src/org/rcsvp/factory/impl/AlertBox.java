package org.rcsvp.factory.impl ;

import org.rcsvp.factory.* ;
import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * AlertBox is a simple implementation class of IAlertBox. AlertBox stores two
 * properties 1. a reference to a facilities, 2. the status of the facilities.
 * 
 * @author Rcsvp.org
 * @date Jul 11, 2013
 */
public class AlertBox implements IAlertBox {

	/**
	 * a status of a facilities.
	 */
	private IStatus status ;

	/**
	 * IRegistrable object.
	 */
	private IRegistrable object ;

	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------

	/**
	 * Constructor with two arguments. status, and target.
	 * 
	 * @param status
	 *            a status of target
	 * @param target
	 *            a IRegistrable instance
	 */
	public AlertBox(IStatus status, IRegistrable target) {
		this.status = status ;
		this.object = target ;
	}

	/**
	 * Constructor with one argument. get status from a specified target.
	 * 
	 * @param target
	 */
	public AlertBox(IRegistrable target) {
		this.status = target.getStatus() ;
		this.object = target ;
	}

	// -----------------------------------------------------------------------
	// Implemented methods
	// -----------------------------------------------------------------------

	@Override
	public IRegistrable getTarget() {
		return object ;
	}

	@Override
	public IStatus getStatus() {
		return status ;
	}

	@Override
	public void update() {
		this.status = object.getStatus() ;
	}

}
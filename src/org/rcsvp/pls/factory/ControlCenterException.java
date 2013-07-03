package org.rcsvp.pls.factory;

/**
 * ControlCenterException is system exception of Production-Line Simulator. In
 * fact, this system requires making hierarchy top-down. When it creates
 * bottom-down, this system throws it.
 * 
 * @author Rcsvp.org
 * @date Jul 3, 2013
 * 
 */
public class ControlCenterException extends RuntimeException {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = -5633740647959452306L;

	public ControlCenterException() {
		super();
	}

	public ControlCenterException(String message) {
		super(message);
	}

}
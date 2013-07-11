package org.rcsvp.factory ;

/**
 * IControlCenter interface represents a controller of whole factory simulator.
 * At least, it is be able to change running speed of simulator.
 * 
 * @author Rcsvp.org
 * @date Jul 11, 2013
 * 
 */
public interface IControlCenter {

	/**
	 * set simulator speed. 1~
	 * 
	 * @param timeScale
	 *            a integer value greater than one.
	 */
	void setTimeScale(long timeScale) ;

	/**
	 * get simulator speed. It values must be positive.
	 * 
	 * @return a positive integer value. It is greater than one.
	 */
	long getTimeScale() ;
}

package org.rcsvp.factory ;

/**
 * IControlCenter upgrade feature from version INITIAL. It specializes related
 * SIMULATOR features. At first, this interface have a timeScale ---decide
 * simulator speed--- feature.
 * 
 * @author Rcsvp.org
 * @date Jul 11, 2013
 * 
 */
public interface IControlCenter {

	/**
	 * Utility #1 set time scale of factory simulator speed.
	 * 
	 * @param timeScale
	 *            speed range of factory simulator.
	 */
	void setTimeScale(long timeScale) ;

	/**
	 * factory Simulator have to use this range when call Thread#sleep(), wait()
	 * methods.
	 * 
	 * @return zero over integer value.
	 */
	long getTimeScale() ;

}
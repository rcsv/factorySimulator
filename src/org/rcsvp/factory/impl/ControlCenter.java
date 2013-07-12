package org.rcsvp.factory.impl ;

import org.rcsvp.factory.IControlCenter ;

/**
 * ControlCenter is the Dashboard of factory Simulator.
 * 
 * @author Rcsvp.org
 * @date Jul 11, 2013
 */
public class ControlCenter implements IControlCenter {

	// ------------------------------------------------------------------------
	// SINGLETON
	// ------------------------------------------------------------------------

	private static IControlCenter cc = new ControlCenter() ;

	public static IControlCenter getInstance() {
		return ControlCenter.cc ;
	}

	// ------------------------------------------------------------------------
	// MAIN LOGIC
	// ------------------------------------------------------------------------
	/**
	 * time scale can change speed for running simulator.
	 */
	private long timeScale ;

	/**
	 * set default cycle time for factory, warehouse, shelf, labor.
	 */
	private long cycleTime ;

	/**
	 * private constructor.
	 */
	private ControlCenter() {
		timeScale = 1000 ;
		cycleTime = 60 ;
	}

	@Override
	public void setTimeScale(long timeScale) {

		if (timeScale <= 0) {

			throw new RuntimeException(
					"Simulator detect being about to set negative"
							+ "value into ControlCenter#timeScale") ;

		}

		this.timeScale = timeScale ;

	}

	@Override
	public long getTimeScale() {
		return this.timeScale ;
	}

	@Override
	public void setDefaultCycleTime(long cycleTime) {

		if (cycleTime <= 0) {
			throw new RuntimeException(
					"factory simulator detect illegal values from cycleTime setting.") ;
		}
		
		this.cycleTime = cycleTime ;
	}

	@Override
	public long getDefaultCycleTime() {
		return this.cycleTime ;
	}

}
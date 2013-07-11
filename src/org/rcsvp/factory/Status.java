package org.rcsvp.factory ;

/**
 * Enumeration Status represents objects' status for factory simulator. Labor
 * and other several instances check if related object status is fine.
 * 
 * @author Rcsvp.org
 * @date Jul 9, 2013
 * 
 */
public enum Status implements IStatus {

	/**
	 * Ready status represents finished initialized process completely. All
	 * classes working in the factory simulator have to implement this status
	 * after construct itself.
	 */
	Ready,

	/**
	 * Working status represents working all instances with no problem.
	 */
	Working,

	/**
	 * Problem status represents working but some facilities have problems. A
	 * registered labor have to check it as soon as possible.
	 */
	Problem,

	/**
	 * Shutdown Normally status represents finish all tasks in the day. All
	 * facilities must be shutdown and labors can go home immediately.
	 */
	ShutdownNormally,

	WaitPreviousMaterial
}

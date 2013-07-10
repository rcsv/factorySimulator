package org.rcsvp.factory.impl ;

import org.rcsvp.factory.attributes.IRegistrable.IStatusEnum ;

public enum GeneralStatus implements IStatusEnum {

	/**
	 * Ready status represents finished initialize process completely.
	 */
	Ready,

	/**
	 * Working status represents working all production line with no problem.
	 */
	Working,

	/**
	 * Problem status represents working but some facilities have problem,
	 * personnel have to check it.
	 */
	Problem,

	/**
	 * NormallyShutdown status represents finish all tasks in the day. All
	 * facilities must be shutdown and labors should go home right now.
	 */
	NormallyShutdown,

	InsufficientMaterials,

	WaitingPreviousMaterial,

	VerifyFine,

}
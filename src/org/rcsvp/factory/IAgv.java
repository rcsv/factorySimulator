package org.rcsvp.factory ;

import org.rcsvp.factory.attributes.IRegistrable ;
import org.rcsvp.factory.attributes.IStorable ;

/**
 * An **automated guided vehicle** or **automatic guided vehicle** (AGV) is a
 * mobile robot that follows makers or wires in the floor, or uses vision or
 * lasers. They are most often used in industrial applications to move materials
 * around a manufacturing facility or a warehouse. Application of the automatic
 * guided vehicle has broadened during the late 20th century(from Automated
 * Guided Vechile, Wikipedia.org).
 * 
 * @author Rcsvp.org
 * @date Jul 12, 2013
 * 
 */
public interface IAgv extends IRegistrable {

	/**
	 * IAgv can move storage station in the factory. set Destination can
	 * register the auto stop point for reload materials.
	 * 
	 * @param target
	 */
	void setDestination(IStorable target) ;
}

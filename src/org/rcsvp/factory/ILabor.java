package org.rcsvp.factory ;

import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * ILabor interface represents a worker belonging to a department related
 * "factory control" in a factory.
 * 
 * ILabor's schedule:
 * - He/She come here to work at 08:00.
 * - He/She start working at ControlCenter implemented IControlCenter.
 * - He/She check several status via ControlCenter.
 * - He/She go to other location when he/she detect situation they have to care.
 * One case, wreck tools, another case, few materials in the shelf.
 * - He/She check all production lines whether finish norm distributed at this
 * morning.
 * - He/She goes home, after turn off the factory's power.
 * 
 * finally I implemented at {@link org.rcsvp.factory.impl.Labor} as a simple implementation.
 * 
 * @author Rcsvp.org
 * @version $Date$ $Id$
 * @date Jul 6, 2013
 * @see {@link org.rcsvp.factory.impl.Labor}
 * 
 * 
 */
public interface ILabor extends IRegistrable {

}
/*
 * ----------------------------------------------------------------------------
 * 
 * Copyright &copy; 2013 Rcsvp.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations and
 * limitations under the License.
 * 
 * 
 * 
 * 
 * 
 * ----------------------------------------------------------------------------
 */
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
public interface IAgv extends IRegistrable, IStorable {

	/**
	 * IAgv can move storage station in the factory. set Destination can
	 * register the auto stop point for reload materials.
	 * 
	 * @param target
	 */
	void setDestination(IStorable target) ;
}

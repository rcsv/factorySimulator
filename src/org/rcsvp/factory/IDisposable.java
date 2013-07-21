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

import org.rcsvp.factory.attributes.ICare ;
import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * IDisposable interface represents a disposable tool equipped into procedures.
 * In fabricate materials to product, tool got galls more and more. It called
 * <code>#use()</code> method from a IProcedure interface, or a IMaterial
 * interface. It must be implemented calculation about relation about gall and
 * durability.
 * 
 * @author Rcsvp.org
 * @date Jul 21, 2013
 * 
 */
public interface IDisposable extends IRegistrable, ICare {

	/**
	 * It've prepared that configure use limit of tools. So, same name differ
	 * thing of IStorable method. Take care.
	 * 
	 * @param capcity
	 *            a long number of capacity.
	 */
	void setCapacity(long capacity) ;

	/**
	 * IDisposable implemented class have a capacity as a constraint. Default
	 * capacity threshold that display a limit to tool broken. It should send
	 * KANBAN to previous procedure when through a threshold.
	 */
	float defaultCapacityThreshold = 0.125f ;
}

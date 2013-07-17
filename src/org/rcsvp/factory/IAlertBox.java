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

import org.rcsvp.factory.attributes.* ;

/**
 * IAlertBox represents a status notification unit in MonitorRoom. IMonitorRoom
 * use it when it notify each unit status to labor.
 * 
 * @author Rcsvp.org
 * @date Jul 11, 2013
 * 
 */
public interface IAlertBox {

	/**
	 * Labor need to know the address of facilities when it warned problem via
	 * AlertBox.
	 * 
	 * @return a reference of facility address that have a problem
	 */
	IRegistrable getTarget() ;

	/**
	 * get status of factory objects.
	 * 
	 * @return a status of reference.
	 */
	IStatus getStatus() ;

	/**
	 * update information. I concerned that confuse me, IDisposable differ from
	 * other IRegistrable interface about holding status.
	 */
	void update() ;

}

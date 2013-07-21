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
package org.rcsvp.factory.impl ;

import org.rcsvp.factory.IMonitorRoom ;
import org.rcsvp.factory.ITolerance ;
import org.rcsvp.factory.IVerify ;

/**
 * @author Rcsvp.org
 * @date Jul 21, 2013
 * 
 */
public class Verify implements IVerify {

	// -----------------------------------------------------------------------
	// Private members
	// -----------------------------------------------------------------------

	/**
	 * MonitorRoom.
	 */
	private IMonitorRoom mr ;

	/**
	 * Verification system name.
	 */
	private String name ;

	/**
	 * A tolerance set.
	 */
	private ITolerance tolerance ;

	// -----------------------------------------------------------------------
	// CONSTRUCTOR
	// -----------------------------------------------------------------------

	public Verify(String name, ITolerance tolerance) {

		this.name = name ;
		this.tolerance = tolerance ;

	}

	public Verify(String name) {
		this.name = name ;
	}

	// -----------------------------------------------------------------------
	// Override methods
	// -----------------------------------------------------------------------

	@Override
	public String toString() {
		return this.name ;
	}
}

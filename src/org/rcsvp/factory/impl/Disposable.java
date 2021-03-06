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

import org.rcsvp.factory.IDisposable ;
import org.rcsvp.factory.IMonitorRoom ;
import org.rcsvp.factory.IStatus ;
import org.rcsvp.factory.Status ;

/**
 * A implementation of Disposable tools.
 * 
 * @author Rcsvp.org
 * @date Jul 19, 2013
 * 
 */
public class Disposable implements IDisposable {

	// -----------------------------------------------------------------------
	// Private member fields
	// -----------------------------------------------------------------------
	
	/**
	 * disposable name
	 */
	private String name ;

	/**
	 * Access code to the Monitor Room.
	 */
	private IMonitorRoom mr ;

	/**
	 * Status Code.
	 */
	private IStatus status ;

	/**
	 * limit.
	 */
	private long limit ;

	/**
	 * count.
	 */
	private volatile long count ;

	// -----------------------------------------------------------------------
	// CONSTRUCTORs
	// -----------------------------------------------------------------------

	/**
	 * Construct Disposable tool with two arguments: name and limit.
	 * 
	 * @param name
	 * 		a name of disposable tools that equipped into IProcedure.
	 * 		It should be unique in the factory.
	 * @param limit
	 * 		a use limit of dsposable tool. You must set unsigned integer
	 * 		value.
	 */
	public Disposable ( String name, long limit ) {
		
		this.name = name ;
		this.limit = limit ;
		
		initialize() ;
		
	}
	
	/**
	 * Construct disposable tool with a argument: name. So you must send method
	 * #setCapacity() before start fabrication.
	 * 
	 * @param name
	 * 		a name of disposable tools that equipped into Procedure. It
	 * 		should be unique in the factory.
	 */
	public Disposable ( String name ) {
		
		this.name = name ;
		this.limit = 0 ;
		
		initialize() ;
	}
	
	/**
	 * common setting of constructors.
	 */
	private void initialize() {
		
		this.status = Status.Ready ;
		this.count = 0 ;
		
	}
	
	// -----------------------------------------------------------------------
	// Override methods
	// -----------------------------------------------------------------------

	@Override
	public IStatus getStatus() {
		return this.status ;
	}

	@Override
	public void setMonitorRoom(IMonitorRoom mr) {
		this.mr = mr ;
		mr.register(new AlertBox(this)) ;
	}

	@Override
	public void shutdown(IStatus status) {
		// Nothing to do.
	}

	@Override
	public void run() {
		// It doesn't running as a thread.
		throw new RuntimeException() ;
	}

	@Override
	public String toString() {
		return this.name ;
	}

	@Override
	public boolean care() {

		this.count = 0 ;
		this.status = Status.Ready ;
		return true ;
	}

	@Override
	public void setCapacity(long capacity) {
		this.limit = capacity ;
	}

}

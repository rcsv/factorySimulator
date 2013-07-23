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

import java.util.List ;

import org.rcsvp.factory.ICompany ;
import org.rcsvp.factory.IFactory ;
import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * A simple implementation of ICompany.
 * @author Rcsvp.org
 * @date Jul 23, 2013
 * @see org.rcsvp.factory.ICompany
 */
public class Company implements ICompany {

	// -----------------------------------------------------------------------
	// public methods
	// -----------------------------------------------------------------------

	public void setFactory(IFactory factory) {

	}

	public void setFactories(List<IFactory> factories) {
		this.factory = factories ;
	}

	// -----------------------------------------------------------------------
	// private member
	// -----------------------------------------------------------------------

	/**
	 * factory list. implemented by java.util.List.
	 */
	private List<IFactory> factory ;

	/**
	 * Company name
	 */
	private String name ;

	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------

	/**
	 * Constructs with a arguments: name.
	 * 
	 * @param name
	 *            a company name.
	 */
	public Company(String name) {
		this.name = name ;
	}

}

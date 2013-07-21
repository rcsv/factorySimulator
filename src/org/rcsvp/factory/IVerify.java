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

/**
 * IVerify interface represents a set of check item and methodology of verify
 * itself. It has one and more couples of tolerance. IVerify has just ONE
 * tolerance set.
 * 
 * @author Rcsvp.org
 * @date Jul 22, 2013
 * 
 */
public interface IVerify {

	/**
	 * verify.
	 * 
	 * @return a result of verification.
	 */
	boolean verify() ;

	/**
	 * update new tolerance condition.
	 * 
	 * @param tolerance
	 *            a new set of Tolerance.
	 */
	void setTolerance(ITolerance tolerance) ;

	/**
	 * obtain current tolerance set detail.
	 * 
	 * @return a set of tolerance.
	 */
	ITolerance getTolerance() ;
}

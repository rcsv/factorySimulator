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
 * ITolerance interface represents a set of tolerance using Verification
 * process.
 * 
 * .........Tolerance Lower Range |<- -|- -> Tolerance Upper - Range
 * start[0]............................|.END[0]
 * |- - - - - - - - - - - - - - - - - >|
 * 
 * 
 * @author Rcsvp.org
 * @date Jul 21, 2013
 * 
 */
public interface ITolerance {

	/**
	 * To get tolerance range.
	 * 
	 * @return a float value of tolerance.
	 */
	double getToleranceRange() ;

	/**
	 * To get offset value.
	 * 
	 * @return
	 */
	double getOffset() ;

	/**
	 * Enumeration type, Tolerance Type.
	 * 
	 * @author Rcsvp.org
	 * @date Jul 21, 2013
	 * 
	 */
	enum Type {

		/**
		 * Scantling tolerance type.
		 */
		Scantling,

		/**
		 * Geometrics tolerance type.
		 */
		Geometric,

		/**
		 * Electrical,
		 */
		Electrical,

		/**
		 * Pressure.
		 */
		Pressure,

		/**
		 * Other tolerance type.
		 */
		Others,
	}

	/**
	 * get Tolerance Type.
	 * 
	 * @return Enumeration Type
	 */
	Type getType() ;
	
	
}

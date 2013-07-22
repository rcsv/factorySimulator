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

import org.rcsvp.factory.ITolerance ;
import org.rcsvp.factory.ITolerance.Type ;

/**
 * A simple implementation of ITolerance.
 *
 * @author Rcsvp.org
 * @date Jul 22, 2013
 *
 */
public class Tolerance implements ITolerance {

  // -----------------------------------------------------------------------
	// Private members
	// -----------------------------------------------------------------------
  
  /**
   * Tolerance Type
   */
  private Type type ;
  
  /**
   * offset.
   */
  private double offset ;
  
  /**
   * tolerance range.
   */
  private double tolerance ;
  
  // -----------------------------------------------------------------------
  // CONSTRUCTOR
  // -----------------------------------------------------------------------
  
  /**
   * Construct with three arguments: ToleranceType, offset and range.
   *
   * @param type 
   *    a Type of Tolerance category. Scantling, Geometric, etc.
   * @param offset
   *    a floating value of offet
   * @param tolerance
   *    a floating value of tolerance range
   */
  public Tolerance ( Type type, double offset, double tolerance ) {
  
    this.type = type ;
    this.offset = offset ;
    this.tolerance = tolerance ;
    
  }
  
  // -----------------------------------------------------------------------
  // Override methods
  // -----------------------------------------------------------------------
  
  @Override
  public double getToleranceRange () {
    return tolerance ;
  }
  
  @Override
  public double getOffset () {
    return offset ;
  }
  
  @Override
  public Type getType () {
    return type ;
  }
  
}

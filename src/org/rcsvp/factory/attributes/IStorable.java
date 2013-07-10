package org.rcsvp.factory.attributes ;

import org.rcsvp.factory.IMaterial ;

/**
 * IStorable interface represents that are able to store Materials into itself.
 * Its interface belongs to Shelf, Procedure, AGV, Warehouse and Export area.
 * 
 * @author Rcsvp.org
 * @date Jul 10, 2013
 * 
 */
public interface IStorable {

	/**
	 * getMaterial method provide a material from storage itself.
	 * 
	 * @return a reference implemented IMaterial
	 */
	IMaterial getMaterial() ;

	/**
	 * IStorable implemented class have a capacity as a constraint. default
	 * capacity threshold display a limit to the empty / full with material.
	 */
	float defaultCapacityThreshold = 0.125f ;
}

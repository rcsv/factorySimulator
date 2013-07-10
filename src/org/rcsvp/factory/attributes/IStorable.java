package org.rcsvp.factory.attributes ;

import org.rcsvp.factory.IMaterial ;

/**
 * IStorable interface represents that are able to store Materials into itself.
 * It is going to implement to Shelf, Procedure, AGV, Warehouse and Export
 * classes.
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
	 * It should send KANBAN to previous procedure when thru a threshold.
	 */
	float defaultCapacityThreshold = 0.125f ;

}

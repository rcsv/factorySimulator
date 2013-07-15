package org.rcsvp.factory.attributes ;

import org.rcsvp.factory.IMaterial ;

/**
 * IStorable interface represents that are able to store Materials into itself.
 * It is going to implement to Shelf, Procedure, AGV, Warehouse and Export
 * classes.
 * 
 * @author Rcsvp.org
 * @date Jul 10, 2013
 */
public interface IStorable extends Runnable, IRegistrable {

	/**
	 * Storable interface can set capacity.
	 * 
	 * @param capacity
	 *            a long number of capacity.
	 */
	void setCapacity(long capacity) ;

	/**
	 * getCapacity method can check a capacity value of IStorable implementation
	 * instances.
	 * 
	 * @return a capacity value.
	 */
	long getCapacity() ;

	/**
	 * getMaterial method provide a material from storage itself.
	 * 
	 * @return a reference implemented IMaterial
	 */
	IMaterial getMaterial() ;

	/**
	 * setMaterial be able to set Material into IStorable.
	 * 
	 * @param volume
	 *            size of material number.
	 */
	long setMaterials(long volume) ;

	/**
	 * IStorable implemented class have a capacity as a constraint. default
	 * capacity threshold display a limit to the empty / full with material. It
	 * should send KANBAN to previous procedure when thru a threshold.
	 */
	float defaultCapacityThreshold = 0.125f ;

}

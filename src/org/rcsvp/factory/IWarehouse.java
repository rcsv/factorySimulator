package org.rcsvp.factory ;

import org.rcsvp.factory.attributes.IRegistrable ;
import org.rcsvp.factory.attributes.IStorable ;

/**
 * IWarehouse interface represents a warehouse of a factory.
 * 
 * @author Rcsvp.org
 */
public interface IWarehouse extends IRegistrable, IStorable {

	/**
	 * Supplier put down from payload.
	 * 
	 * @param number
	 *            a count of Materials.
	 */
	void setLotsMaterials(long number) ;

	/**
	 * AGV, Labor take huge number of materials at once from warehouse.
	 * 
	 * @param number
	 * @return
	 */
	long getLotsMaterials(long number) ;
}

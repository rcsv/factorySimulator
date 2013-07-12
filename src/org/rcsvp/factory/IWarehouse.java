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
	 * @param type
	 *            a string of Material Type.
	 * @param number
	 *            a count of Materials.
	 */
	void setLotsMaterials(String type, long number) ;

	/**
	 * Shorthand of setLotsmaterials( String type, long number ). When omit type
	 * they treat materials as "DEFAULT".
	 * 
	 * @param number
	 *            a number of materials conveyed from other factories.
	 */
	void setLotsMaterials(long number) ;

	/**
	 * AGV, Labor take huge number of materials at once from warehouse.
	 * 
	 * @param number
	 *            a number of materials they take away from Warehouse.
	 * @return a number facility can go away with.
	 */
	long getLotsMaterials(String type, long number) ;

	/**
	 * Shorthand of getLotsMaterials ( String type, long number ). When omit
	 * type they treat materials as "Default".
	 * 
	 * @param number
	 *            a number of materials they take away from Warehouse.
	 * @return a number facility can go away with.
	 */
	long getLotsMaterials(long number) ;
}

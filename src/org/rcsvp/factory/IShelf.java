package org.rcsvp.factory ;

import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * IShelf represents a material shelf.
 * @author Rcsvp.org
 * @date   Jul 6, 2013
 *
 */
public interface IShelf extends IRegistrable, ICare {

	IMaterial getMaterial() ;

	// void reload() ;

	float threshold = 0.125f ;
}
package org.rcsvp.factory ;

public interface IShelf extends IRegistrable, ICare {

	IMaterial getMaterial() ;

	// void reload() ;

	float threshold = 0.125f ;
}
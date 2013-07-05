package org.rcsvp.factory.common ;

public interface IShelf extends IRegistrable, ICare {

	IMaterial getMaterial() ;

	// void reload() ;

	float threshold = 0.125f ;
}
package org.rcsvp.factory.common;

public interface IShelf extends IRegistrable {

	IMaterial getMaterial() ;

	void reload() ;
	
	float threshold = 0.125f ;
}
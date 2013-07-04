package org.rcsvp.factory;

import org.rcsvp.factory.common.IMaterial;

public class Material implements IMaterial {

	private String name ;
	
	public Material( String name ) {
		this.name = name ;
	}
	
	@Override
	public String toString() { return this.name ; }
}
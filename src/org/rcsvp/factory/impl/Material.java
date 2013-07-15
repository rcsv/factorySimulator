package org.rcsvp.factory.impl ;

import org.rcsvp.factory.IDisposable ;
import org.rcsvp.factory.IMaterial ;
import org.rcsvp.factory.IVerify ;

public class Material implements IMaterial {

	private String name ;

	public Material(String name) {
		this.name = name ;
	}

	@Override
	public void append(IMaterial other) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean fabricate(IDisposable tools) {
		// TODO Auto-generated method stub
		return false ;
	}

	@Override
	public boolean validate(IVerify verification) {
		// TODO Auto-generated method stub
		return false ;
	}

}

package org.rcsvp.factory.common ;

public interface IMaterial {

	void append(IMaterial other) ;

	boolean fabricate(IDisposable tools) ;

	boolean validate(IVerify verification) ;
}
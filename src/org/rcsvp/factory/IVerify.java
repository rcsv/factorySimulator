package org.rcsvp.factory ;

public interface IVerify extends IRegistrable {

	boolean check(IMaterial material) ;

	ITolerance getTolerance() ;

}
package org.rcsvp.factory.common ;

public interface IVerify extends IRegistrable {

	boolean check(IMaterial material) ;

	ITolerance getTolerance() ;

}
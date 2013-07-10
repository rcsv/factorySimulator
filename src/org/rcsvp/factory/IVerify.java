package org.rcsvp.factory ;

import org.rcsvp.factory.attributes.IRegistrable ;

public interface IVerify extends IRegistrable {

	boolean check(IMaterial material) ;

	ITolerance getTolerance() ;

}
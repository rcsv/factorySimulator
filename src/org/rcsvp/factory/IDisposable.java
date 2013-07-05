package org.rcsvp.factory ;

public interface IDisposable extends IRegistrable, ICare {

	float threshold = 0.125f ;

	boolean use() ;

}
package org.rcsvp.factory.common;

/**
 * 
 * @author Rcsvp.org
 * 
 */
public interface IProcedure extends IRegistrable, IParent {

	void setTactTime(long tactTime);

	int getOutputCount();
}
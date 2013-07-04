package org.rcsvp.factory.common;

/**
 * IParent interface represent parent node. target instance must implemented
 * IRegistrable interface.
 * 
 * @author Rcsvp.org
 * 
 */
public interface IParent {

	boolean register(IRegistrable target);
}
package org.rcsvp.factory ;

import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * IParent interface represent parent node. target instance must implemented
 * IRegistrable interface.
 * 
 * @author Rcsvp.org
 * 
 */
public interface IParent {

	boolean register(IRegistrable target) ;
}
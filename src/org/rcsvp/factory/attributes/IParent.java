package org.rcsvp.factory.attributes ;

/**
 * IParent interface represent parent node. Target instance must implemented
 * IRegistrable interface.
 * 
 * @author Rcsvp.org
 * 
 */
public interface IParent {

	/**
	 * register child node.
	 * 
	 * @param target
	 *            child node that implemented interface IRegistrable.
	 * @return success or failure.
	 */
	boolean register(IRegistrable target) ;
}
package org.rcsvp.factory.attributes ;

/**
 * ICare interface represents a "need care" by ILabor. To repair broken tool, to
 * reload empty material shelves, Labor send <code>care()</code> method of this
 * interface.
 * 
 * @see ILabor
 * 
 * @author Rcsvp.org
 * @date Jul 6, 2013
 * 
 */
public interface ICare {

	/**
	 * Labor have to care the facility. 
	 * 
	 * @return 
	 */
	boolean care() ;
}

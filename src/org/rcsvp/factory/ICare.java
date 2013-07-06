package org.rcsvp.factory ;

/**
 * ICare interface represents a "need care" to ILabor. To repair broken tool, to
 * reload empty material shelves, ILabor implemented instance send
 * <code>care ()</code> method of this interface.
 * 
 * @see ILabor
 * 
 * @author Rcsvp.org
 * @date Jul 6, 2013
 * 
 */
public interface ICare {

	boolean care() ;

}
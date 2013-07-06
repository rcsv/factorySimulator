package org.rcsvp.factory ;

/**
 * IDisposable interface represents a disposable tools for fabrication. In
 * fabricate materials to product, tool got galls more and more. It called
 * <code>use()</code> method from a IProcedure interface, or a IMaterial
 * interface. It must be implemented calculation about relation about gall and
 * durability.
 * 
 * @author Rcsvp.org
 * @date Jul 6, 2013
 * 
 */
public interface IDisposable extends IRegistrable, ICare {

	float threshold = 0.125f ;

	boolean use() ;

}
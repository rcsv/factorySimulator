package org.rcsvp.factory ;

/**
 * Interface IMaterial represents a material using in a factory. Material maybe
 * Cupper, Rare-Metal, sub-assy, or all. Factory must create product from these
 * materials.
 * 
 * IMaterial prepare several method in it.
 * 
 * 1. Append other material when create sub assembly parts.
 * 2. fabricate with disposable tools.
 * 3. validate result of fabrication.
 * 
 * @author Rcsvp.org
 * @date Jul 6, 2013
 * 
 */
public interface IMaterial {

	void append(IMaterial other) ;

	boolean fabricate(IDisposable tools) ;

	boolean validate(IVerify verification) ;
}
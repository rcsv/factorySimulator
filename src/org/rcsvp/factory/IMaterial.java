package org.rcsvp.factory ;

/**
 * Interface IMaterial represents a material used in a factory. Material maybe
 * Cupper, Rare metal, sub-assembly or full production. Factory must create a
 * product from these materials.
 * 
 * IMaterial prepare several method on it.
 * 
 * 1. Append itself to other material when create sub assembly parts. 2.
 * Fabricate with disposable tools. 3. Validate result of fabrication.
 * 
 * @author Rcsvp.org
 * @date Jul, 6, 2013
 * 
 */
public interface IMaterial {

	/**
	 * Append other Material.
	 * 
	 * @param other
	 */
	void append(IMaterial other) ;

	/**
	 * fabricate by Procedure using Disposable tool.
	 * 
	 * @param tools
	 *            Disposable tool to use
	 * @return success or failure fabrication by disposable tool.
	 */
	boolean fabricate(IDisposable tools) ;

	/**
	 * Verification by check content.
	 * 
	 * @param verification
	 *            a test.
	 * @return true when a test succeed or return false when cannot pass the
	 *         exam.
	 */
	boolean validate(IVerify verification) ;
}

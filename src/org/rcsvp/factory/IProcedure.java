package org.rcsvp.factory ;

import org.rcsvp.factory.attributes.* ;

/**
 * IProcedure interface represents a procedure of a production line in the
 * factory Fabricate materials with plenty of methods including press, bending,
 * slicing, punching and burning. Each procedure belongs to same production line
 * take same time to fabrication. It is tact time. In factory simulator, its
 * value will be set via IProductionLine instance.
 * In factory simulator, a procedure has some IShelf for supply materials, some
 * disposable tools, and lots of verification contents.
 * 
 * @author Rcsvp.org
 * @date Jul 6, 2013
 */
public interface IProcedure extends IRegistrable, IStorable, IParent, ICare {

	/**
	 * set tact time.
	 * 
	 * @param tactTime
	 */
	void setTactTime(long tactTime) ;

	/**
	 * get output count.
	 * 
	 * @return
	 */
	int getOutputCount() ;

}

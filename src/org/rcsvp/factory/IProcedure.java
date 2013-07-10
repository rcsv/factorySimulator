package org.rcsvp.factory ;

import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * IProcedure interface represents a procedure of a Production Line. Fabricate
 * material with plenty of method including press, bend, slice, punch and burn.
 * Each procedure belongs to same production line take same time to fabrication.
 * it is tact time. In factory simulator its value is given to its instances
 * from every IProductionLine instance.
 * 
 * In Factory Simulator, a procedure has some IShelf for supply materials, some
 * disposable tools, and lots of verification items.
 * 
 * @author Rcsvp.org
 * @date Jul 6, 2013
 * 
 */
public interface IProcedure extends IRegistrable, IParent {

	void setTactTime(long tactTime) ;

	int getOutputCount() ;
}
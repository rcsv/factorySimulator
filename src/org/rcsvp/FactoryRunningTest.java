package org.rcsvp ;

import org.rcsvp.Logger.Level ;
import org.rcsvp.factory.IFactory ;
import org.rcsvp.factory.ILabor ;
import org.rcsvp.factory.impl.Factory ;
import org.rcsvp.factory.impl.Labor ;

/**
 * Factory Simulator Running Test class. it is entry point.
 * 
 * @author Rcsvp.org
 * @date Jul 12, 2013
 * 
 */
public class FactoryRunningTest {

	/**
	 * --- ENTRY POINT ---
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Logger.setLevel(Level.Info) ;

		//
		// Generate Factory
		//
		IFactory factory = new Factory("Handa Denshi Factory") ;

		//
		// first level instances. I have to create instances a labor, a
		// warehouse, a production line, an export and
		// an AGV.
		//
		ILabor JoeKato = new Labor("Katsutoshi Kato") ;
	}
}
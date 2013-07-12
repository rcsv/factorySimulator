package org.rcsvp ;

import org.rcsvp.Logger.Level ;
import org.rcsvp.factory.IFactory ;
import org.rcsvp.factory.ILabor ;
import org.rcsvp.factory.IWarehouse ;
import org.rcsvp.factory.impl.Factory ;
import org.rcsvp.factory.impl.Labor ;
import org.rcsvp.factory.impl.Warehouse ;

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
		ILabor YamadaTaro = new Labor("Yamada Taro") ;
		factory.register( YamadaTaro ) ;
		
		IWarehouse warehouse = new Warehouse( "Warehouse 13" ) ;
	}
}
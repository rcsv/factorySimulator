package org.rcsvp ;

import org.rcsvp.Logger.Level ;
import org.rcsvp.factory.IFactory ;
import org.rcsvp.factory.ILabor ;
import org.rcsvp.factory.IProductionLine ;
import org.rcsvp.factory.impl.Factory ;
import org.rcsvp.factory.impl.Labor ;
import org.rcsvp.factory.impl.ProductionLine ;

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
		
		// 1. Labor
		ILabor YamadaTaro = new Labor("Yamada Taro") ;
		factory.register( YamadaTaro ) ;
		
		// 3. ProductionLine
		IProductionLine line1 = new ProductionLine( "Motor", 44 ); 
		line1.setNorm( 20000 ) ;
		factory.register(line1) ;
		
		// 4. Export ( generate at factory instance)
		// 2. Warehouse ( generate at factory instance)
		
		// 5. AGV
		IAgv agv1 = new Agv( "AGV1" ) ;
		factory.register( agv1 ) ;
		
		// -------------------------------------------------------------------
		// Start Factory's morning.
		// -------------------------------------------------------------------
		new Thread( factory ).start();
		
		Logger.debug("finish");
	}
}
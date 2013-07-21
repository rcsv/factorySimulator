package org.rcsvp ;

import org.rcsvp.Logger.Level ;
import org.rcsvp.factory.IAgv ;
import org.rcsvp.factory.IDisposable ;
import org.rcsvp.factory.IFactory ;
import org.rcsvp.factory.ILabor ;
import org.rcsvp.factory.IProcedure ;
import org.rcsvp.factory.IProductionLine ;
import org.rcsvp.factory.IShelf ;
import org.rcsvp.factory.IVerify ;
import org.rcsvp.factory.IWarehouse ;
import org.rcsvp.factory.impl.Agv ;
import org.rcsvp.factory.impl.Disposable ;
import org.rcsvp.factory.impl.Factory ;
import org.rcsvp.factory.impl.Labor ;
import org.rcsvp.factory.impl.Procedure ;
import org.rcsvp.factory.impl.ProductionLine ;
import org.rcsvp.factory.impl.Shelf ;
import org.rcsvp.factory.impl.Warehouse ;

/**
 * Factory Simulator Running Test class. it is entry point.
 * 
 * @author Rcsvp.org
 * @date Jul 12, 2013
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
		IFactory factory = new Factory("Sample Factory") ;

		//
		// first level instances. I have to create instances a labor, a
		// warehouse, a production line, an export and
		// an AGV.
		//

		// 1. Labor
		ILabor YamadaTaro = new Labor("Yamada Taro") ;
		factory.register(YamadaTaro) ;

		// 2. Warehouse ( generate at factory instance)
		IWarehouse warehouse = new Warehouse("Sample Factory's Warehouse") ;
		warehouse.setCapacity(20000) ;
		factory.register(warehouse) ;

		// 3. ProductionLine
		IProductionLine line1 = new ProductionLine("Motor", 44) ;
		line1.setNorm(20000) ;
		factory.register(line1) ;

		// 3.1. Procedures
		IProcedure proc1 = new Procedure("MotorProc1") ;
		line1.register(proc1) ;
		IProcedure proc2 = new Procedure("MotorProc2") ;
		line1.register(proc2);
		
		// 3.1.1. Shelf.
		IShelf shelf1 = new Shelf("MotorShelf1");
		shelf1.setCapacity( 200 );
		proc1.register(shelf1);
		
		// 3.1.2. Disposable tools
		IDisposable dispo1 = new Disposable("PunchTools") ;
		dispo1.setCapacity(20000);
		
		// 3.1.4. IVerify
		IVerify verify1 = new Verify( "Verify 1" ) ;
		verify1.setTolerance( new Tolerance( Type.Scantling, 0.1, 0.1 ) ) ;
		proc1.register( verify1 ) ;

		// 4. Export ( generate at factory instance)

		// 5. AGV
		IAgv agv1 = new Agv("AGV1") ;
		agv1.setCapacity(200);
		agv1.setDestination(warehouse) ;
		agv1.setDestination(shelf1) ;
		factory.register(agv1) ;

		// -------------------------------------------------------------------
		// Start Factory's morning.
		// -------------------------------------------------------------------
		new Thread(factory).start() ;

		Logger.debug("finish") ;
	}
}
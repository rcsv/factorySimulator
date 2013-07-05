package org.rcsvp ;

import org.rcsvp.factory.* ;
import org.rcsvp.factory.impl.ControlCenter ;
import org.rcsvp.factory.impl.Disposable ;
import org.rcsvp.factory.impl.Factory ;
import org.rcsvp.factory.impl.Labor ;
import org.rcsvp.factory.impl.Procedure ;
import org.rcsvp.factory.impl.ProductionLine ;
import org.rcsvp.factory.impl.Shelf ;
import org.rcsvp.factory.impl.Tolerance ;
import org.rcsvp.factory.impl.Verify ;

public class FactoryRunningTest {

	public static void main(String[] args) {
		Logger.lv = Logger.VerboseLevel.Info ;

		Logger.debugWrite(" - - - START  - - - ") ;

		//
		// Generate Factory
		//
		IFactory factory = new Factory("FABRIC", 2) ;

		IControlCenter cc = ControlCenter.getInstance("FABRIC") ;
		cc.setTimeScale(1) ;

		//
		// Create labor and register
		//
		factory.register(new Labor("Kazuto UEHARA", 1)) ;

		// tactTime 1 second, norm = 20.
		IProductionLine pl1 = new ProductionLine("Line 1", 1, 20000) ;

		factory.register(pl1) ;

		IProcedure proc11 = new Procedure("Proc11") ;
		IProcedure proc12 = new Procedure("Proc12") ;
		IProcedure proc13 = new Procedure("Proc13") ;
		IProcedure proc14 = new Procedure("Proc14") ;
		IProcedure proc15 = new Procedure("Proc15") ;

		pl1.register(proc11) ;
		pl1.register(proc12) ;
		pl1.register(proc13) ;
		pl1.register(proc14) ;
		pl1.register(proc15) ;

		IShelf sh11 = new Shelf("Shelf for Proc11", 60) ;
		proc11.register(sh11) ;

		IShelf sh12_1 = new Shelf("Shelf for Proc12#1", 20) ;
		IShelf sh12_2 = new Shelf("Shelf for Proc12#2", 20) ;
		proc12.register(sh12_1) ;
		proc12.register(sh12_2) ;

		//
		// Create Disposable Tools and Register.
		//
		IDisposable d1 = new Disposable("Punch Tool", 100) ;
		IDisposable d3 = new Disposable("some  Tool", 20) ;

		proc11.register(d1) ;
		proc13.register(d3) ;

		IVerify check1 = new Verify("Length Check", new Tolerance(
				ITolerance.ToleranceType.Scantling, 10, 10)) ;

		proc11.register(check1) ;

		Thread x = new Thread(factory) ;
		x.setName("Factory Thread") ;
		x.start() ;
		Logger.debugWrite(" - - - FINISH - - - ") ;
	}

}
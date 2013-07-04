package org.rcsvp;

import org.rcsvp.factory.*;
import org.rcsvp.factory.common.*;

public class FactoryRunningTest {

	public static void main(String[] args) {
		Logger.lv = Logger.VerboseLevel.Debug;

		Logger.debugWrite(" - - - START  - - - ");

		//
		// Generate Factory
		//
		IFactory factory = new Factory("Factory_1", 2);

		//
		// Create labor and register
		//
		factory.register(new Labor("Kazuto UEHARA", 1));

		// tactTime 1 sec, norma = 20.
		IProductionLine pl1 = new ProductionLine("Line 1", 1, 20);

		factory.register(pl1);

		IProcedure proc11 = new Procedure("Proc11");
		IProcedure proc12 = new Procedure("Proc12");
		IProcedure proc13 = new Procedure("Proc13");

		pl1.register(proc11);
		pl1.register(proc12);
		pl1.register(proc13);
		
		IShelf sh11 = new Shelf( "Shelf for Proc11", 100 ) ;
		proc11.register ( sh11 ) ;

		/*
		 * 
		 * 
		 * // // Create Procedure and Register into Production Line //
		 * IProcedure w11 = new Procedure("Procedure 1-1") ; IProcedure w12 =
		 * new Procedure("Procedure 1-2") ; IProcedure w13 = new
		 * Procedure("Procedure 1-3") ; IProcedure w14 = new
		 * Procedure("Procedure 1-4") ;
		 * 
		 * pl1.register(w11); pl1.register(w12); pl1.register(w13);
		 * pl1.register(w14);
		 * 
		 * IProcedure w21 = new Procedure("Procedure 2-1") ; IProcedure w22 =
		 * new Procedure("Procedure 2-2") ; IProcedure w23 = new
		 * Procedure("Procedure 2-3") ; IProcedure w24 = new
		 * Procedure("Procedure 2-4") ;
		 * 
		 * pl1.register(w21); pl1.register(w22); pl1.register(w23);
		 * pl1.register(w24);
		 */
		Thread x = new Thread(factory); 
		x.setName("Factory Thread");
		x.start();
		Logger.debugWrite(" - - - FINISH - - - ");
	}

}
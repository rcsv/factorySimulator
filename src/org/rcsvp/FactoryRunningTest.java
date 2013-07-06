package org.rcsvp ;

import org.rcsvp.factory.* ;
import org.rcsvp.factory.impl.* ;

public class FactoryRunningTest {

	public static void main(String[] args) {
		Logger.lv = Logger.LogLevel.Info ;

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

		IProductionLine line2 = new ProductionLine("Line 2", 2, 8000) ;
		factory.register(line2) ;

		IProcedure proc21 = new Procedure("Proc21") ;
		line2.register(proc21) ;
		IProcedure proc22 = new Procedure("Proc22") ;
		line2.register(proc22) ;
		IProcedure proc23 = new Procedure("Proc23") ;
		line2.register(proc23) ;

		IShelf sh21 = new Shelf("Shelf for Proc21", 50) ;
		proc21.register(sh21) ;

		IDisposable d22 = new Disposable("any tool", 200) ;
		IDisposable d23 = new Disposable("someting", 300) ;
		proc22.register(d22) ;
		proc23.register(d23) ;

		IVerify check211 = new Verify("A", new Tolerance(
				ITolerance.ToleranceType.Scantling, 10, 10)) ;
		IVerify check212 = new Verify("B", new Tolerance(
				ITolerance.ToleranceType.Scantling, 10, 10)) ;
		IVerify check213 = new Verify("C", new Tolerance(
				ITolerance.ToleranceType.Scantling, 10, 10)) ;
		proc21.register(check211) ;
		proc21.register(check212) ;
		proc21.register(check213) ;

		IVerify check221 = new Verify("D", new Tolerance(
				ITolerance.ToleranceType.Scantling, 10, 10)) ;
		IVerify check222 = new Verify("E", new Tolerance(
				ITolerance.ToleranceType.Scantling, 10, 10)) ;
		IVerify check223 = new Verify("F", new Tolerance(
				ITolerance.ToleranceType.Scantling, 10, 10)) ;
		proc22.register(check221) ;
		proc22.register(check222) ;
		proc22.register(check223) ;

		IVerify check231 = new Verify("G", new Tolerance(
				ITolerance.ToleranceType.Scantling, 10, 10)) ;
		IVerify check232 = new Verify("H", new Tolerance(
				ITolerance.ToleranceType.Scantling, 10, 10)) ;
		IVerify check233 = new Verify("I", new Tolerance(
				ITolerance.ToleranceType.Scantling, 10, 10)) ;
		proc23.register(check231) ;
		proc23.register(check232) ;
		proc23.register(check233) ;

		Thread x = new Thread(factory) ;
		x.setName("Factory Thread") ;
		x.start() ;
		Logger.debugWrite(" - - - FINISH - - - ") ;
	}

}
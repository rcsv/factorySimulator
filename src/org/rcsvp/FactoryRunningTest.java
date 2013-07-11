package org.rcsvp ;

import org.rcsvp.Logger.Level ;
import org.rcsvp.factory.IFactory ;

public class FactoryRunningTestV2 {

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
	}
}
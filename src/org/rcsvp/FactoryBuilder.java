package org.rcsvp ;

import java.io.* ;
import java.util.* ;

import org.rcsvp.factory.*;
/**
 * Factory Builder helps generate factory hierarchy about this factory
 * simulator.
 * 
 * @author Rcsvp.org
 * @date Jul 8, 2013
 * 
 */
public class FactoryBuilder {
	
	private final String defaultTarget = "config.properties" ;
	
	private List<IFactory> company ;

	public FactoryBuilder() {
		init( defaultTarget ) ;
	}
	
	public FactoryBuilder( String specifiedTarget ) {
		init( specifiedTarget );
	}
	
	private void init ( String filepath ) {
		Properties x = new Properties() ;
		
		try {
			InputStream is = this.getClass().getResourceAsStream(filepath) ;
			x.load( is );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		
		//
		// At first, obtain company name -- but stab.
		//
		this.company = new ArrayList<IFactory>() ;
		
		//
		// foreach, register factories
		//
		int counter = 1 ;
		String ret  = null ;

		do {
			
			ret = x.getProperty("rcsvp.f" + counter ) ;
			
			if ( ret != null ) {
				
			}
			
		} while ( true ) ;
		
	}
	
	public static void maim( String[] args) {
		
	}
}

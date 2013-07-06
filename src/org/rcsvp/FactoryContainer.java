package org.rcsvp ;

import java.io.IOException ;
import java.io.InputStream ;
import java.util.* ;

/**
 * Factory Container provide a feature that generate factory structure as a
 * novice mode.
 * 
 * @author Rcsvp.org
 * @date Jul 6, 2013
 * 
 */
public class FactoryContainer {

	final private static String defaultDI = "factory.properties" ;
	
	private Properties p ;
	
	property String a ;
	
	public FactoryContainer( String filename ) {
		initialize( filename ) ;
	}
	
	public FactoryContainer() {
		initialize( defaultDI ) ;
	}
	
	private void initialize( String filename ) {
		InputStream is = this.getClass().getResourceAsStream( defaultDI ) ;
		p = new Properties() ;
		
		try {
			
			p.load( is ) ;
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		p.getProperty("factory.name");
		
	}
	
	public static void main( String[] args ) {
		
		FactoryContainer x = new FactoryContainer() ;
	}
}

package org.rcsvp ;

import java.io.* ;
import java.util.* ;

import org.rcsvp.factory.* ;
import org.rcsvp.factory.impl.Factory ;

/**
 * Factory Builder helps generate factory hierarchy about this factory
 * simulator.
 * 
 * @author Rcsvp.org
 * @date Jul 8, 2013
 * 
 */
public class FactoryBuilder {

	/**
	 * It've prepared that default path to the configuration file and its
	 * filename, "config.properties".
	 */
	private final String defaultTarget = "config.properties" ;

	private Properties x ;

	/**
	 * This class instances are going to return this
	 * java.util.ArrayList<IFactory>.
	 */
	private List<IFactory> company ;

	/**
	 * Default constructor generate with default configuratio file.
	 */
	public FactoryBuilder() {
		init(defaultTarget) ;
	}

	/**
	 * Constructor with filepath generate from user-generated config.properties.
	 * 
	 * @param specifiedTarget
	 *            config.properties created with user.
	 */
	public FactoryBuilder(String specifiedTarget) {
		init(specifiedTarget) ;
	}

	private void init(String filepath) {
		this.x = new Properties() ;

		try {
			
			FileInputStream fis = new FileInputStream ( filepath ) ;
			fis.read();
			x.load( fis ) ;
			
		} catch (IOException e) {
			e.printStackTrace() ;
		}

		// --- 1. parse Company layer -----------------------------------------
		this.company = new ArrayList<IFactory>() ;

		// --- 2. parse Factory layer -----------------------------------------
		int counter = 1 ;
		String ret = null ;
		do {
			ret = x.getProperty("rcsvp.f" + counter) ;
			if (ret != null) {
				// parse -> Warabi,1
				// Factory Name: Warabi
				// Factory TactTime: 1
				StringTokenizer tkn = new StringTokenizer(ret, ",") ;
				String factoryName = tkn.nextToken() ;
				String factoryTactTime = tkn.nextToken() ;

				this.company.add(new Factory(factoryName, Integer
						.parseInt(factoryTactTime))) ;
				Logger.warnWrite( this.company.get(counter-1).toString()  );
				counter++ ;	

			}

		} while (true) ;

	}

	public static void main(String[] args) {

		FactoryBuilder x = new FactoryBuilder() ;
	}
}

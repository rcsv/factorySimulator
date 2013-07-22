/*
 * ----------------------------------------------------------------------------
 * 
 * Copyright &copy; 2013 Rcsvp.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations and
 * limitations under the License.
 * 
 * 
 * 
 * 
 * 
 * ----------------------------------------------------------------------------
 */
package org.rcsvp ;

import java.io.File ;
import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.io.IOException ;
import java.io.InputStream ;
import java.util.ArrayList ;
import java.util.List ;
import java.util.Properties ;
import java.util.StringTokenizer ;

import org.rcsvp.factory.IFactory ;
import org.rcsvp.factory.ILabor ;
import org.rcsvp.factory.IProductionLine ;
import org.rcsvp.factory.IWarehouse ;
import org.rcsvp.factory.impl.Factory ;
import org.rcsvp.factory.impl.Labor ;
import org.rcsvp.factory.impl.ProductionLine ;
import org.rcsvp.factory.impl.Warehouse ;

/**
 * Factory Builder class build factory simulator's complex configuration.
 * 
 * @author Rcsvp.org
 * @date Jul 22, 2013
 * 
 */
public class FactoryBuilder {

	// -----------------------------------------------------------------------
	// Private members
	// -----------------------------------------------------------------------

	/**
	 * factories list.
	 */
	private List<IFactory> factory ;

	/**
	 * reference to the Properties file
	 */
	private Properties config = new Properties() ;

	// -----------------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------------

	/**
	 * 
	 * @param filepath
	 */
	public FactoryBuilder(String filepath) {

		//
		// loading property file.
		//
		InputStream iS = null ;
		try {
			iS = new FileInputStream(new File(filepath)) ;
		} catch (FileNotFoundException e) {
			e.printStackTrace() ;
		}

		try {
			config.load(iS) ;
		} catch (IOException e) {
			e.printStackTrace() ;
		}

		generateFactory() ;

	}

	/**
	 * 
	 */
	private void generateFactory() {

		//
		// obtain company name
		//
		String companyName = config.getProperty("company") ;

		//
		// create factory list
		//
		int iF = Integer.parseInt(config.getProperty("factory")) ;

		this.factory = new ArrayList<IFactory>(iF) ;

		for (int i = 1; i <= iF; i++) {

			String baseKey = "factory." + i ;
			IFactory x = parseFactory(config.getProperty(baseKey)) ;

			//
			// TODO
			//
			setLabors(x, baseKey) ;

			setProductionLines(x, baseKey) ;
			this.factory.add(x) ;

		}
	}

	/**
	 * @param x
	 * @param baseKey
	 */
	private void setProductionLines(IFactory x, String key) {

		String baseKey = key + ".lines" ;

		int nL = Integer.parseInt(config.getProperty(baseKey)) ;

		for (int i = 1; i <= nL; i++) {
			IProductionLine pl = new ProductionLine(config.getProperty(baseKey
					+ "." + i)) ;
			x.register(pl) ;

			setProcedures(x, pl.toString()) ;
		}

	}

	/**
	 * @param x
	 * @param string
	 */
	private void setProcedures(IFactory x, String string) {
		// TODO Auto-generated method stub

	}

	private IFactory parseFactory(String key) {

		StringTokenizer token = new StringTokenizer(key, ",") ;

		String name = token.nextToken() ;
		int capacity = Integer.parseInt(token.nextToken()) ;

		IFactory x = new Factory(name) ;
		IWarehouse w = new Warehouse(x + "'s warehouse") ;
		w.setCapacity(capacity) ;
		x.register(w) ;

		return x ;
	}

	private void setLabors(IFactory x, String key) {

		String baseKey = key + ".labor" ;

		int nL = Integer.parseInt(config.getProperty(baseKey)) ;

		for (int i = 1; i <= nL; i++) {
			String uniqueKey = baseKey + "." + i ;
			ILabor labor = new Labor(uniqueKey) ;
			x.register(labor) ;
		}
	}

	// -----------------------------------------------------------------------
	// Single test
	// -----------------------------------------------------------------------
	public static void main(String[] args) {

		new FactoryBuilder("config.properties") ;

	}
}

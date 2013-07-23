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

import org.rcsvp.factory.IDisposable ;
import org.rcsvp.factory.IFactory ;
import org.rcsvp.factory.ILabor ;
import org.rcsvp.factory.IProcedure ;
import org.rcsvp.factory.IProductionLine ;
import org.rcsvp.factory.IShelf ;
import org.rcsvp.factory.ITolerance.Type ;
import org.rcsvp.factory.IVerify ;
import org.rcsvp.factory.IWarehouse ;
import org.rcsvp.factory.impl.Disposable ;
import org.rcsvp.factory.impl.Factory ;
import org.rcsvp.factory.impl.Labor ;
import org.rcsvp.factory.impl.Procedure ;
import org.rcsvp.factory.impl.ProductionLine ;
import org.rcsvp.factory.impl.Shelf ;
import org.rcsvp.factory.impl.Tolerance ;
import org.rcsvp.factory.impl.Verify ;
import org.rcsvp.factory.impl.Warehouse ;

/**
 * Factory Builder class builds configuration of factory simulator's
 * environment. it is factory simulator's configuration sampled by
 * <code>config.properties</code>
 * 
 * <pre>
 * # - Company --- Factory --- Line --- Proc --- Shelf
 * #                        |                |-- Verify
 * #                        |                |-- Disposable
 * #                        |- Labor
 * #                        |- Warehouse
 * #                        |- Export
 * #                        |- AGV
 * </pre>
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
	 * factory list. It will be return as a root node.
	 */
	private List<IFactory> factory ;

	/**
	 * a reference to the Properties file.
	 */
	private Properties config = new Properties() ;

	// -----------------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------------

	/**
	 * Construct with a parameter: string of filepath. Constructor build
	 * factory.
	 * 
	 * @param filepath
	 *            a file path of configuration file. It should be formatted by
	 *            .properties.
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

	// -----------------------------------------------------------------------
	// 1. FACTORY
	// -----------------------------------------------------------------------

	/**
	 * Build factory.
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

			// ---> GOTO LABOR
			setLabors(x, baseKey) ;

			// ---> GOTO LINES
			setProductionLines(x, baseKey) ;

			this.factory.add(x) ;

		}
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

	// -----------------------------------------------------------------------
	// 2. LABORs
	// -----------------------------------------------------------------------

	/**
	 * set labors into IFactory.
	 * 
	 * @param x
	 *            a reference of IFactory.
	 * @param key
	 *            key string of configuration
	 */
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
	// 3. PRODUCTION LINES
	// -----------------------------------------------------------------------

	/**
	 * Build production lines
	 * 
	 * @param x
	 *            a reference of IFactory.
	 * @param key
	 *            key string of configuration
	 */
	private void setProductionLines(IFactory x, String key) {

		String baseKey = key + ".lines" ;

		int nL = Integer.parseInt(config.getProperty(baseKey)) ;

		for (int i = 1; i <= nL; i++) {
			IProductionLine pl = new ProductionLine(config.getProperty(baseKey
					+ "." + i)) ;
			x.register(pl) ;

			// ---> GOTO PROCS
			setProcedures(pl, pl.toString()) ;
		}

	}

	// -----------------------------------------------------------------------
	// 4. PROCEDURES
	// -----------------------------------------------------------------------

	/**
	 * Build procedures.
	 * 
	 * @param x
	 *            a reference of IProductionLine.
	 * @param key
	 *            key string of configuration
	 */
	private void setProcedures(IProductionLine x, String key) {

		String baseKey = key + ".procs" ;

		int nP = Integer.parseInt(config.getProperty(baseKey)) ;

		for (int i = 1; i <= nP; i++) {

			IProcedure tP = new Procedure(config.getProperty(baseKey + "." + i)) ;
			x.register(tP) ;

			// ---> GOTO SHELF
			setShelves(tP, baseKey) ;

			// ---> GOTO Disposable Tools
			setDispos(tP, baseKey) ;

			// ---> GOTO Verify
			setVerify(tP, baseKey) ;
		}

	}

	// -----------------------------------------------------------------------
	// 5. SHELF
	// -----------------------------------------------------------------------

	/**
	 * @param tP
	 * @param baseKey
	 */
	private void setShelves(IProcedure tP, String key) {

		String baseKey = key + ".shelf" ;
		int nS = Integer.parseInt(config.getProperty(baseKey)) ;

		for (int i = 1; i <= nS; i++) {

			String tS = baseKey + "." + i ;

			IShelf x = parseShelf(config.getProperty(tS)) ;

			tP.register(x) ;
		}

	}

	/**
	 * 
	 * @param configValue
	 * @return
	 */
	private IShelf parseShelf(String configValue) {

		StringTokenizer token = new StringTokenizer(configValue, ",") ;
		String name = token.nextToken() ;
		long capacity = Long.parseLong(token.nextToken()) ;

		return new Shelf(name, capacity) ;
	}

	// -----------------------------------------------------------------------
	// 6. DISPOSABLE TOOLS
	// -----------------------------------------------------------------------

	/**
	 * set disposable tools.
	 * 
	 * @param tP
	 * @param baseKey
	 */
	private void setDispos(IProcedure tP, String key) {

		String baseKey = key + ".dispos" ;
		int nD = Integer.parseInt(config.getProperty(baseKey)) ;

		for (int i = 1; i <= nD; i++) {

			String tD = baseKey + "." + i ;
			IDisposable x = parseDispos(config.getProperty(tD)) ;
		}

	}

	/**
	 * 
	 * @param configValue
	 * @return
	 */
	private IDisposable parseDispos(String configValue) {

		StringTokenizer token = new StringTokenizer(configValue, ",") ;
		String name = token.nextToken() ;
		long useLimit = Long.parseLong(token.nextToken()) ;

		return new Disposable(name, useLimit) ;
	}

	// -----------------------------------------------------------------------
	// 6. DISPOSABLE TOOLS
	// -----------------------------------------------------------------------
	
	/**
	 * @param tP
	 * @param baseKey
	 */
	private void setVerify(IProcedure tP, String key) {

		String baseKey = key + ".verify" ;
		int nV = Integer.parseInt(config.getProperty(baseKey)) ;

		for (int i = 1; i <= nV; i++) {

			String tV = baseKey + "." + i ;

			IVerify verify = parseVerify(config.getProperty(tV)) ;

			// TODO
			// tP.register( verify ) ;
			//
		}
	}

	/**
	 * parse and lex Verification Class
	 * 
	 * @param configValue
	 *            It is not config Key string but configuration value string.
	 *            for example, "Head.procs.4.shelf.1" is a key string of
	 *            shelf#1. In this case, you should set
	 *            "edge#1,Scantling,0,0.0012" as a argument.
	 * 
	 * @return a IVerify instance
	 */
	private IVerify parseVerify(String configValue) {

		StringTokenizer token = new StringTokenizer(configValue, ",") ;
		String name = token.nextToken() ;
		String toleranceType = token.nextToken() ;
		double offset = Double.parseDouble(token.nextToken()) ;
		double range = Double.parseDouble(token.nextToken()) ;

		IVerify x = new Verify(name) ;
		x.setTolerance(new Tolerance(
				(toleranceType == "Scantling" ? Type.Scantling
						: toleranceType == "Geometric" ? Type.Geometric
								: toleranceType == "Electrical" ? Type.Electrical
										: toleranceType == "Pressure" ? Type.Pressure
												: Type.Others), offset, range)) ;

		return x ;
	}
	
	
	// -----------------------------------------------------------------------
	// Single test
	// -----------------------------------------------------------------------

	/**
	 * factory builder class single test.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		new FactoryBuilder("config.properties") ;

	}
}

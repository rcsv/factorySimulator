package org.rcsvp.factory.impl ;

import java.util.Map ;
import java.util.concurrent.ConcurrentHashMap ;

import javax.swing.Box ;

import org.rcsvp.factory.IAlertBox ;
import org.rcsvp.factory.IFactory ;
import org.rcsvp.factory.IMonitorRoom ;
import org.rcsvp.factory.Status ;

/**
 * MonitorRoom class is a simple implementation of IMonitorRoom
 * (ex-IControlCenter). MonitorRoom represents a control center room, and
 * integrated console. It implemented multi-singleton, it provides only one
 * instance each factory instances. So, getInstance method requires a factory
 * code name.
 * 
 * @author Rcsvp.org
 * @date Jul 12, 2013
 */
public class MonitorRoom implements IMonitorRoom {

	// -----------------------------------------------------------------------
	// MULTI SINGLETON
	// -----------------------------------------------------------------------

	/**
	 * MonitorRoom instance storage for each factory.
	 */
	private static Map<String, IMonitorRoom> mapMR = new ConcurrentHashMap<String, IMonitorRoom>() ;

	/**
	 * get instance each factory. if it does not generate yet, it is going to
	 * create a new instance.
	 * 
	 * @param factoryCode
	 *            same as factory name.
	 * @return a MonitorRoom instance.
	 */
	public static IMonitorRoom getInstance(String factoryCode) {

		IMonitorRoom x = mapMR.get(factoryCode) ;

		if (x == null) {
			x = new MonitorRoom() ;
			mapMR.put(factoryCode, x) ;
		}

		return x ;
	}

	// -----------------------------------------------------------------------
	// MULTI SINGLETON
	// -----------------------------------------------------------------------

	/**
	 * store a factory reference
	 */
	private IFactory factory ;

	/**
	 * console.
	 */
	private Map<String, IAlertBox> console ;

	/**
	 * Construct with no argument. initialize console.
	 */
	private MonitorRoom() {
		console = new ConcurrentHashMap<String, IAlertBox>() ;
	}

	@Override
	public Map<String, IAlertBox> getConsole() {
		return this.console ;
	}

	@Override
	public void setFactory(IFactory factory) {
		this.factory = factory ;
	}

	@Override
	public boolean register(IAlertBox alertbox) {
		console.put(alertbox.getTarget().toString(), alertbox) ;
		return true ;
	}

	@Override
	public boolean powerOff() {
		this.factory.shutdown(Status.ShutdownNormally) ;
		return true ;
	}

}

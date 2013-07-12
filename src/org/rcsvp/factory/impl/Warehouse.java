package org.rcsvp.factory.impl ;

import java.util.Map ;
import java.util.concurrent.ConcurrentHashMap ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.IMaterial ;
import org.rcsvp.factory.IWarehouse ;
import org.rcsvp.factory.attributes.IRegistrable ;

public class Warehouse extends AbstFacilities implements IWarehouse {

	/**
	 * several types materials can stock at a warehouse.
	 */
	private Map<String, Long> stocks ;

	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------

	/**
	 * Construct instance Warehouse with two arguments: 1. Warehouse's name. It
	 * must be
	 * unique. 2. tactTime. it must be positive number.
	 * 
	 * @param name
	 *            a unique name for Warehouse instances.
	 * @param tactTime
	 *            a tact time for warehouse.
	 */
	public Warehouse(String name, long tactTime) {
		super(name, tactTime) ;

		initialize() ;
	}

	/**
	 * Construct instance Labor with a argument: Warehouse's name.
	 * 
	 * @param name
	 *            a unique name for Warehouse instances.
	 */
	public Warehouse(String name) {
		super(name, ControlCenter.getInstance().getDefaultCycleTime()) ;

		initialize() ;
	}

	private void initialize() {
		this.stocks = new ConcurrentHashMap<String, Long>() ;
	}

	// -----------------------------------------------------------------------
	// implement override / abstract methods
	// -----------------------------------------------------------------------

	@Override
	/**
	 * In current specification, Warehouse class does not have any other
	 * instances as a child node.
	 */
	public boolean register(IRegistrable target) {
		throw new RuntimeException() ;
	}

	@Override
	protected void bootUp() {
		Logger.debug(this.name + " : open the shutter.") ;
	}

	@Override
	protected boolean otherCheck() {
		return true ;
	}

	protected void finishProcess() {

	}

	@Override
	public IMaterial getMaterial() {
		return null ;
	}

	@Override
	public void setLotsMaterials(String type, long number) {

		//
		// add materials into Warehouse after check volume of current stock.
		long current_size = this.stocks.get(type) ;

		this.stocks.put(type, number + current_size) ;
	}
	
	@Override
	public void setLotsMaterials( long number ) {
		setLotsMaterials( "Default", number ) ;
	}

	@Override
	public long getLotsMaterials(String type, long number) {
		
		long value = this.stocks.get(type);
		
		if ( value < number ) {
			
			//
			// reload...
			//
		} else {
			
			this.stocks.put(type, value - number ) ;
			
		}
		
		return 0 ;
	}
	
	@Override
	public long getLotsMaterials( long number ) {
		return getLotsMaterials( "Default", number ) ;
	}

	@Override
	protected void routines() {
		// TODO Auto-generated method stub

	}

}

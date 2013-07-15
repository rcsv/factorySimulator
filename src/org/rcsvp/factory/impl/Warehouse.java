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

	/**
	 * internal variable for IStorable interface
	 */
	private long capacity ; // getter -- setter.

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

		capacity = 0 ;
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
		
		if ( this.capacity == 0 ) {
			throw new RuntimeException() ;
		}
	}

	@Override
	protected boolean otherCheck() {
		return true ;
	}

	protected void finishProcess() {

	}

	@Override
	public void setLotsMaterials(String type, long number) {

		//
		// add materials into Warehouse after check volume of current stock.
		long current_size = this.stocks.get(type) ;

		this.stocks.put(type, number + current_size) ;
	}

	@Override
	public void setLotsMaterials(long number) {

		setLotsMaterials(defaultMaterialType, number) ;

	}

	@Override
	public long getLotsMaterials(String type, long number) {

		long value = this.stocks.get(type) ;

		if (value < number) {

			//
			// reload...
			//
		} else {

			this.stocks.put(type, value - number) ;

		}

		return 0 ;
	}

	@Override
	public long getLotsMaterials(long number) {
		return getLotsMaterials(defaultMaterialType, number) ;
	}

	@Override
	protected void routines() {
		// TODO Auto-generated method stub

	}

	// -----------------------------------------------------------------------
	// Implemented methods :: IStorable
	// -----------------------------------------------------------------------

	@Override
	public void setCapacity(long capacity) {
		this.capacity = capacity ;
	}

	@Override
	public long getCapacity() {
		return this.capacity ;
	}

	@Override
	public IMaterial getMaterial() {
		
		//
		// Maybe, I don't use this method...
		//
		return null ;
	}

	@Override
	public long setMaterials(long volume) {
		
		//
		// NOW PRINTING
		//
		
		return 0 ;
	}

}

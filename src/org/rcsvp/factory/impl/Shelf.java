package org.rcsvp.factory.impl ;

import org.rcsvp.factory.IMaterial ;
import org.rcsvp.factory.IShelf ;
import org.rcsvp.factory.Status ;
import org.rcsvp.factory.attributes.IRegistrable ;

public class Shelf extends AbstFacilities implements IShelf {

	/**
	 * rest count of materials.
	 */
	private volatile long restMaterials ;

	/**
	 * internal variable for IStorable inteface.
	 */
	private long capacity ; // getter and setter need.

	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------

	public Shelf(String name, long tactTime) {
		super(name, tactTime) ;

		restMaterials = 0 ;
	}
	
	public Shelf( String name ) {
		super( name, ControlCenter.getInstance().getDefaultCycleTime() ) ;
		
		restMaterials = 0 ;
	}

	// -----------------------------------------------------------------------
	// Override methods
	// -----------------------------------------------------------------------

	@Override
	public boolean register(IRegistrable target) {
		throw new RuntimeException() ;
	}

	@Override
	protected void bootUp() {

		if (this.capacity == 0) {
			throw new RuntimeException() ;
		}

	}

	@Override
	protected boolean otherCheck() {
		return true ;
	}

	@Override
	protected void finishProcess() {
	}

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

		if (this.restMaterials <= defaultCapacityThreshold * this.capacity) {

			if (restMaterials <= 0) {

				this.status = Status.WaitPreviousMaterial ;
				return null ;

			} else {

				this.status = Status.Problem ;

			}

		}
		
		restMaterials-- ;
		return new Material ( "from : " + this.name ) ;
	}

	@Override
	public long setMaterials(long volume) {
		// TODO Auto-generated method stub
		return 0 ;
	}

	@Override
	protected void routines() {
		// TODO Auto-generated method stub

	}

}

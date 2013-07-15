package org.rcsvp.factory.impl ;

import java.util.LinkedList ;
import java.util.List ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.IAgv ;
import org.rcsvp.factory.IMaterial ;
import org.rcsvp.factory.attributes.IRegistrable ;
import org.rcsvp.factory.attributes.IStorable ;

/**
 * AGV(Automated Guided Vehicle) implementation class.
 * 
 * @author Awane.Tomohiro
 */
public class Agv extends AbstFacilities implements IAgv {

	/**
	 * A list of STATION for AGV.
	 */
	private List<IStorable> destination ;

	/**
	 * Capacity.
	 */
	private long capacity ;

	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------

	/**
	 * Construct AGV instance with a argument: AGV ID.
	 * 
	 * @param name
	 *            a unique ID of AGV.
	 */
	public Agv(String name) {
		super(name, ControlCenter.getInstance().getDefaultCycleTime()) ;

		initialize() ;

	}

	/**
	 * Initialize method just called from Constructor excludely.
	 */
	private void initialize() {

		this.destination = new LinkedList<IStorable>() ;

		this.capacity = 0 ;

	}

	// -----------------------------------------------------------------------
	// implement override / abstract methods
	// -----------------------------------------------------------------------

	@Override
	/**
	 * In current specification, AGV class does not have any other instance as
	 * a child node.
	 */
	public boolean register(IRegistrable target) {
		throw new RuntimeException() ;
	}

	@Override
	public void setDestination(IStorable target) {
		this.destination.add(target) ;
	}

	private boolean facilityCheck() {
		return (destination.size() >= 2 && capacity > 0) ? true : false ;
	}

	@Override
	protected void bootUp() {

		Logger.debug(this.name + " : start bootUp().......................") ;

		if (!facilityCheck()) {

			throw new RuntimeException("Insufficient facilities at "
					+ this.name) ;

		}

		Logger.debug(this.name + " : finish bootUp().....................") ;
	}

	@Override
	protected void routines() {

	}

	@Override
	protected void finishProcess() {

	}

	@Override
	public IMaterial getMaterial() {
		//
		// AGV neber put a couple of material for other facilities. So, AGV
		// throw RuntimeException when it called.
		//

		throw new RuntimeException() ;

	}

	@Override
	protected boolean otherCheck() {
		return true ;
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
	public long setMaterials(long volume) {
		// TODO Auto-generated method stub
		return 0 ;
	}

}

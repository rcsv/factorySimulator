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

	private long capacity ;

	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------

	/**
	 * Construct AGV instance with two arguments: agv ID and speed.
	 * 
	 * @param name
	 *            a unique ID of AGV.
	 * @param tactTime
	 *            move speed.
	 */
	public Agv(String name, long tactTime, long capacity) {
		super(name, tactTime) ;
		this.capacity = capacity ;

		initialize() ;
	}

	/**
	 * Construct AGV instance with a argument: AGV ID.
	 * 
	 * @param name
	 *            a unique ID of AGV.
	 */
	public Agv(String name, long capacity) {
		super(name, ControlCenter.getInstance().getDefaultCycleTime()) ;
		this.capacity = capacity ;

		initialize() ;
	}

	/**
	 * Initialize method just called from Constructor excludely.
	 */
	private void initialize() {

		this.destination = new LinkedList<IStorable>() ;

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

	@Override
	protected void bootUp() {
		Logger.debug(this.name + " : initializing...") ;

		//
		// Check destination number. throw RuntimeException when it has few
		// numbers of destination.
		//
		// 0 : RuntimeException
		// 1 : RuntimeException
		//
		if (this.destination.size() < 2) {
			throw new RuntimeException() ;
		}

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
	public void setMaterial(IMaterial material) {
		// TODO Auto-generated method stub
		
	}

}

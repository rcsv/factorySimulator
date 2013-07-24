package org.rcsvp.factory.impl ;

import java.util.ArrayList ;
import java.util.Iterator ;
import java.util.LinkedList ;
import java.util.List ;
import java.util.Queue ;
import java.util.concurrent.ConcurrentLinkedQueue ;
import java.util.concurrent.Executor ;
import java.util.concurrent.Executors ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.IDisposable ;
import org.rcsvp.factory.IMaterial ;
import org.rcsvp.factory.IProcedure ;
import org.rcsvp.factory.IShelf ;
import org.rcsvp.factory.IVerify ;
import org.rcsvp.factory.Status ;
import org.rcsvp.factory.attributes.IRegistrable ;
import org.rcsvp.factory.attributes.IStorable ;

public class Procedure extends AbstFacilities implements IProcedure {

	/**
	 * Shelf List
	 */
	private List<IStorable> shelves ;

	/**
	 * Disposable tools list
	 */
	private List<IDisposable> dispos ;

	/**
	 * a checklist
	 */
	private List<IVerify> checklist ;

	/**
	 * output queue.
	 */
	private Queue<IMaterial> output ;

	/**
	 * output (failed) parts queue.
	 */
	private Queue<IMaterial> wreckedParts ;

	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------

	public Procedure(String name) {
		super(name, 0) ; // must set tact time after register.

		initialize() ;
	}

	private void initialize() {

		this.shelves = new ArrayList<IStorable>() ;
		this.dispos = new ArrayList<IDisposable>() ;
		this.checklist = new ArrayList<IVerify>() ;

		output = new ConcurrentLinkedQueue<IMaterial>() ;
		wreckedParts = new LinkedList<IMaterial>() ;

	}

	// -----------------------------------------------------------------------
	// Override / abstract methods
	// -----------------------------------------------------------------------

	@Override
	public boolean register(IRegistrable target) {

		target.setMonitorRoom(mr) ;

		// -------------------------------------------------------------------
		// switch target name and store each linkedlist.
		// -------------------------------------------------------------------

		String targetName = target.getClass().getSimpleName() ;

		switch (targetName) {

		case "Shelf":
		case "Procedure":

			shelves.add((IStorable) target) ;
			break ;

		case "Disposable":

			dispos.add((IDisposable) target) ;
			break ;

		case "Verify":

			checklist.add((IVerify) target) ;
			break ;

		default:

			throw new RuntimeException() ;

		}

		return true ;
	}

	/**
	 * 1+ shelves.
	 * 
	 * @return
	 */
	private boolean facilityCheck() {

		return (this.shelves.size() != 0) ? true : false ;

	}

	@Override
	protected void bootUp() {

		Logger.debug(this.name + " : start bootUp().......................") ;

		if (!facilityCheck()) {

			throw new RuntimeException() ;

		}

		//
		// Extract SHELVES list
		// patterns:
		// 1. shelf -> kick start
		// 2. another procedure -> skip
		//

		IStorable xS = null ;
		Iterator<IStorable> iS = shelves.iterator() ;

		Executor exeStorage = Executors.newCachedThreadPool() ;

		while (iS.hasNext()) {

			// set reference into "xS"
			xS = iS.next() ;

			if (xS instanceof IProcedure) {
				continue ;
			}

			exeStorage.execute(xS) ;

		}

		Logger.debug(this.name + " : finish bootUp()......................") ;

	}

	@Override
	protected boolean otherCheck() {
		return true ;
	}

	@Override
	protected void routines() {

	}

	@Override
	protected void finishProcess() {

		//
		// shutdown IStorable
		//
		Iterator<IStorable> iS = shelves.iterator() ;

		while (iS.hasNext()) {

			iS.next().shutdown(Status.ShutdownNormally) ;

		}

		//
		// shutdown IDisposable
		//
		Iterator<IDisposable> iD = dispos.iterator() ;

		while (iD.hasNext()) {

			iD.next().shutdown(Status.ShutdownNormally) ;
		}

	}

	@Override
	public void setTactTime(long tactTime) {
		this.tactTime = tactTime ;
	}

	@Override
	public int getOutputCount() {
		return this.output.size() ;
	}

	@Override
	public IMaterial getMaterial() {

		//
		// change status Insufficient Material, when cannot get any IMaterial
		//
		if (this.output.size() != 0) {
			return this.output.remove() ;
		}

		return null ;
	}

	@Override
	public boolean care() {

		this.status = Status.Ready ;
		return true ;
	}

	@Override
	public void setCapacity(long capacity) {
		return ;
	}

	@Override
	public long getCapacity() {
		return 0 ;
	}

	@Override
	public long setMaterials(long volume) {
		return 0 ;
	}

	/* (non-Javadoc)
	 * @see org.rcsvp.factory.IProcedure#register(org.rcsvp.factory.IVerify)
	 */
	@Override
	public boolean register(IVerify verify) {
		this.checklist.add(verify) ;
		return true ;
	}
}

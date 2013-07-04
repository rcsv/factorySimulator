package org.rcsvp.factory ;

import java.util.* ;

import org.rcsvp.factory.common.* ;

public class Procedure extends AbstFacilities implements IProcedure, IShelf {

	private List<IShelf> shelves ;

	private Queue<IMaterial> output ;

	public Procedure(String name) {
		super(name, 0) ;

		shelves = new ArrayList<IShelf>() ;
		output = new LinkedList<IMaterial>() ;

	}

	@Override
	public boolean register(IRegistrable target) {

		target.setControlCenter(cc) ;
		String targetName = target.getClass().getSimpleName() ;

		switch (targetName) {
		case "Shelf":
			shelves.add((IShelf) target) ;
			break ;
		case "Disposable":
			// dispos.add ( (IDisposable) target ) ;
			break ;
		case "Verify":
			// verify.add ( (IVerify) target ) ;
			break ;
		default:
			break ;
		}
		return true ;
	}

	@Override
	protected void bootUp() {

		ThreadGroup thShelves = new ThreadGroup(this.name + "Shelves") ;
		Iterator<IShelf> iS = shelves.iterator() ;
		while (iS.hasNext()) {
			new Thread(thShelves, iS.next()).start() ;
		}

	}

	@Override
	protected boolean otherCheck() {
		return true ;
	}

	@Override
	protected void routines() {

		//
		// 1. get Material from Shelves and/or previous Procedure.
		// 2. Assembly, press etc. using tools (IDisposable)
		// 3. Verify several check item.
		// 4. add output.
		//
		output.add(new Material("TODO")) ;

	}

	@Override
	protected void finishProcess() {
		Iterator<IShelf> iS = shelves.iterator() ;
		while (iS.hasNext()) {
			iS.next().shutdown(GeneralStatus.NormallyShutdown) ;
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
		return this.output.remove() ;
	}

	@Override
	public void reload() {
		// Procedure Stuck cannot reload.
	}

}
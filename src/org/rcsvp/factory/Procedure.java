package org.rcsvp.factory ;

import java.util.* ;

import org.rcsvp.factory.common.* ;

public class Procedure extends AbstFacilities implements IProcedure, IShelf {

	private List<IShelf> shelves ;

	private List<IDisposable> dispos ;

	private List<IVerify> checklist ;

	private Queue<IMaterial> output ;

	public Procedure(String name) {
		super(name, 0) ;

		shelves = new ArrayList<IShelf>() ;
		dispos = new ArrayList<IDisposable>() ;
		checklist = new ArrayList<IVerify>() ;
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
			dispos.add((IDisposable) target) ;
			break ;
		case "Verify":
			checklist.add((IVerify) target) ;
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
		//
		Iterator<IShelf> iS = shelves.iterator() ;
		IMaterial original = null ;
		IShelf temp = null ;
		
		while (iS.hasNext()) {
			temp = iS.next() ;

			if (original == null) {

				original = temp.getMaterial() ;
				if ( original == null ) {
					// material insufficient.
					//
					this.status = GeneralStatus.WaitingPreviousMaterial;
					cc.notify( new AlertBox( this ) ) ;
					return ;
				}

			} else {

				original.append(temp.getMaterial()) ;

			}
		}

		//
		// 2. Assembly, press etc. using tools (IDisposable)
		//
		Iterator<IDisposable> iD = dispos.iterator() ;
		while (iD.hasNext()) {
			original.fabricate(iD.next()) ;
		}

		//
		// 3. Verify several check item.
		//
		Iterator<IVerify> iV = checklist.iterator() ;
		while (iV.hasNext()) {
			original.validate(iV.next()) ;
		}

		// 4. add output.
		//
		output.add( original ) ;

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
		if ( this.output.size() != 0 ) {
			return this.output.remove() ;
		}
		return null ;
	}

	@Override
	public void reload() {
		// Procedure Stuck cannot reload.
	}

}
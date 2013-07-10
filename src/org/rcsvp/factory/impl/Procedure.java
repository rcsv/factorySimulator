package org.rcsvp.factory.impl ;

import java.util.* ;
import java.util.concurrent.* ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.IDisposable ;
import org.rcsvp.factory.IMaterial ;
import org.rcsvp.factory.IProcedure ;
import org.rcsvp.factory.IShelf ;
import org.rcsvp.factory.IVerify ;
import org.rcsvp.factory.attributes.IRegistrable ;

public class Procedure extends AbstFacilities implements IProcedure, IShelf {

	private List<IShelf> shelves ;

	private List<IDisposable> dispos ;

	private List<IVerify> checklist ;

	private Queue<IMaterial> output ;

	private Queue<IMaterial> wreckedParts ;

	public Procedure(String name) {
		super(name, 0) ;

		shelves = new ArrayList<IShelf>() ;

		dispos = new ArrayList<IDisposable>() ;

		checklist = new ArrayList<IVerify>() ;

		/*
		 * 
		 */
		output = new ConcurrentLinkedQueue<IMaterial>() ;

		wreckedParts = new LinkedList<IMaterial>() ;

	}

	@Override
	public boolean register(IRegistrable target) {

		target.setControlCenter(cc) ;
		String targetName = target.getClass().getSimpleName() ;

		switch (targetName) {
		case "Shelf":
		case "Procedure":
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

		Logger.infoWrite(this.name + " : has " + shelves.size() + " shelves.") ;
		Logger.infoWrite(this.name + " : has " + dispos.size()
				+ " disposable tools.") ;
		Logger.infoWrite(this.name + " : has " + checklist.size()
				+ " check list.") ;

		ThreadGroup thShelves = new ThreadGroup(this.name + "Shelves") ;
		Iterator<IShelf> iS = shelves.iterator() ;
		IShelf temp = null ;
		while (iS.hasNext()) {
			temp = iS.next() ;
			if (temp instanceof IProcedure) {
				Logger.debugWrite(this.name + " : assume " + temp.toString()
						+ " as a material shelf. but thread start ignorance.") ;
				continue ;
			} else {
				Logger.debugWrite(this.name + " : kick start "
						+ temp.toString() + " as a material shelf.") ;
			}
			new Thread(thShelves, temp).start() ;
		}
		Logger.infoWrite(this.name + " : all shelves reload initialized.") ;
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
				if (original == null) {
					// material insufficient.
					//
					this.status = GeneralStatus.WaitingPreviousMaterial ;
					cc.notify(new AlertBox(this)) ;
					return ;
				}

			} else {

				original.append(temp.getMaterial()) ;

			}
		}

		boolean success = false ;
		//
		// 2. Assembly, press etc. using tools (IDisposable)
		//
		Iterator<IDisposable> iD = dispos.iterator() ;

		while (iD.hasNext()) {

			success = original.fabricate(iD.next()) ;

			if (!success) {

				Logger.warnWrite(this.name + " : fabricate process failed. "
						+ original.toString()) ;
				this.wreckedParts.add(original) ;
				return ;
			}

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
		output.add(original) ;

	}

	@Override
	protected void finishProcess() {
		Iterator<IShelf> iS = shelves.iterator() ;
		while (iS.hasNext()) {
			iS.next().shutdown(GeneralStatus.NormallyShutdown) ;
		}

		Iterator<IDisposable> iD = dispos.iterator() ;
		while (iD.hasNext()) {
			iD.next().shutdown(GeneralStatus.NormallyShutdown) ;
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

		if (this.output.size() != 0) {
			return this.output.remove() ;
		}
		return null ;

	}

	@Override
	public boolean care() {
		throw new RuntimeException() ;
	}

}
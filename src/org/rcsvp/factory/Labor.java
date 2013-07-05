package org.rcsvp.factory ;

import java.util.* ;
import java.util.Map.Entry ;

import org.rcsvp.factory.common.ICare ;
import org.rcsvp.factory.common.IControlCenter.IAlertBox ;
import org.rcsvp.factory.common.IDisposable ;
import org.rcsvp.factory.common.IFactory ;
import org.rcsvp.factory.common.ILabor ;
import org.rcsvp.factory.common.IRegistrable ;
import org.rcsvp.factory.common.IShelf ;
import org.rcsvp.Logger ;

public class Labor extends AbstFacilities implements ILabor {

	public Labor(String name, long tactTime) {
		super(name, tactTime) ;
	}

	@Override
	public boolean register(IRegistrable target) {
		return false ;
	}

	@Override
	protected void bootUp() {
		Logger.debugWrite(this.name + " : said 'Good Morning, Everyone.'") ;
	}

	@Override
	protected void routines() {

		Logger.debugWrite(this.name + " : check at ControlCenter...") ;

		//
		// Check at ControlCenter...
		// Preparation of obtain ContolCenter console
		//
		Map<String, IAlertBox> console = cc.getConsole() ;
		Iterator<Entry<String, IAlertBox>> iC = console.entrySet().iterator() ;
		Entry<String, IAlertBox> temp = null ;

		// boolean: Can I go home?
		boolean canGoHome = true ;

		while (iC.hasNext()) {
			temp = iC.next() ;

			Logger.debugWrite(this.name + " check "
					+ temp.getValue().getTarget().toString() + " : "
					+ temp.getValue().getTarget().getStatus().toString()) ;

			IStatusEnum currentStatus = temp.getValue().getTarget().getStatus() ;

			//
			// switch にできない。
			//
			if (currentStatus == GeneralStatus.Problem) {
				//
				// You cannot go home. At first you work or repair, reload
				// something.
				//
				canGoHome = false ;

				Logger.warnWrite(this.name + " : detect warning at "
						+ temp.getValue().getTarget().toString()) ;

				((ICare) temp.getValue().getTarget()).care() ;

			} else if (currentStatus == GeneralStatus.InsufficientMaterials) {
				//
				// You cannot go home. Reload materials anyway.
				//
				canGoHome = false ;

				if (temp.getValue().getTarget() instanceof IShelf) {

					IShelf x = (IShelf) temp.getValue().getTarget() ;
					x.care() ;

				}

			} else if (currentStatus == GeneralStatus.Working) {
				//
				// If they are still working without yourself and factory
				// itself,
				// You cannot go home right now. Stay here.
				//

				if (temp.getValue().getTarget() instanceof ILabor
						|| temp.getValue().getTarget() instanceof IFactory
						|| temp.getValue().getTarget() instanceof IDisposable) {

				} else {
					canGoHome = false ;
				}
			} else if (currentStatus == GeneralStatus.Ready) {
				//
				// you didn't start working yet today!
				// So, You cannot go home for the moment.
				//
				canGoHome = false ;

			}
		}

		if (canGoHome == true) {
			// TGIF!
			cc.powerOff() ;

			// 電源をオフにして、全て終了したことを確認した上で帰るべきか。
			this.status = GeneralStatus.NormallyShutdown ;
			this.shutdown(GeneralStatus.NormallyShutdown) ;

			writeReport() ;
		}

	}

	private void writeReport() {

	}

	@Override
	protected void finishProcess() {
		Logger.debugWrite(this.name + " : said 'I gonna go home.'") ;
	}

	@Override
	protected boolean otherCheck() {
		return true ;
	}

}
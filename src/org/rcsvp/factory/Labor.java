package org.rcsvp.factory;

import java.util.*;
import java.util.Map.Entry;

import org.rcsvp.factory.common.IControlCenter.IAlertBox;
import org.rcsvp.factory.common.IFactory;
import org.rcsvp.factory.common.ILabor;
import org.rcsvp.factory.common.IRegistrable;
import org.rcsvp.Logger;

public class Labor extends AbstFacilities implements ILabor {

	public Labor(String name, long tactTime) {
		super(name, tactTime);
	}

	@Override
	public boolean register(IRegistrable target) {
		return false;
	}

	@Override
	protected void bootUp() {
		Logger.debugWrite(this.name + " : said 'Good Morning, Everyone.'");
	}

	@Override
	protected void routines() {

		Logger.debugWrite(this.name + " : check at ControlCenter...");

		//
		// Check at ControlCenter...
		// Preparation of obtain ContolCenter console
		//
		Map<String, IAlertBox> console = cc.getConsole();
		Iterator<Entry<String, IAlertBox>> iC = console.entrySet().iterator();
		Entry<String, IAlertBox> temp = null;

		// boolean: Can I go home?
		boolean canGoHome = true;

		while (iC.hasNext()) {
			temp = iC.next();

			Logger.debugWrite(this.name + " check "
					+ temp.getValue().getTarget().toString() + " : "
					+ temp.getValue().getTarget().getStatus().toString());

			if (temp.getValue().getTarget().getStatus() == GeneralStatus.Problem) {
				canGoHome = false;
				// I have to repair some facility...
				Logger.debugWrite(this.name + " : detect problem at "
						+ temp.getValue().getTarget().toString());
			} else if (temp.getValue().getTarget().getStatus() == GeneralStatus.Working) {
				if (temp.getValue().getTarget() instanceof ILabor
						|| temp.getValue().getTarget() instanceof IFactory) {
					//
					// Ignore labor's and Factory's status.
					//
				} else {
					canGoHome = false; // I can go home after finish all job.
				}
			} else if (temp.getValue().getTarget().getStatus() == GeneralStatus.Ready) {
				canGoHome = false; // It have not start working yet!
			}

		}

		if (canGoHome == true) {
			// TGIF!
			cc.powerOff();

			// 電源をオフにして、全て終了したことを確認した上で帰るべきか。
			this.status = GeneralStatus.NormallyShutdown;
			this.shutdown(GeneralStatus.NormallyShutdown);
		}

	}

	@Override
	protected void finishProcess() {
		Logger.debugWrite(this.name + " : said 'I gonna go home.'");
	}

	@Override
	protected boolean otherCheck() {
		return true;
	}

}
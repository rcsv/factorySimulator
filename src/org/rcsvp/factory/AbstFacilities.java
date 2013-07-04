package org.rcsvp.factory;

import org.rcsvp.Logger;
import org.rcsvp.factory.common.*;

public abstract class AbstFacilities implements IRegistrable, IParent {

	protected IControlCenter cc;
	protected String name;

	protected volatile boolean powerOff = false;

	protected GeneralStatus status;

	protected long tactTime;

	public AbstFacilities(String name, long tactTime) {
		this.name = name;
		this.tactTime = tactTime;
		this.status = GeneralStatus.Ready;
	}

	@Override
	public IStatusEnum getStatus() {
		return this.status;
	}

	@Override
	public void run() {
		Logger.debugWrite(this.name + " : start running.");
		this.status = GeneralStatus.Working;
		cc.notify(new AlertBox(this));

		bootUp();

		while (!powerOff && otherCheck()) {
			
			try {
				Thread.sleep(this.tactTime * cc.getTimeScale());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Logger.debugWrite( this.name + " : working job..." );
			routines();

		}

		this.status = GeneralStatus.NormallyShutdown;
		cc.notify(new AlertBox(this));
		finishProcess();
		Logger.debugWrite(this.name + " : finish work.");
	}

	protected abstract void bootUp();

	protected abstract boolean otherCheck();

	protected abstract void routines();

	protected abstract void finishProcess();

	@Override
	public void setControlCenter(IControlCenter cc) {
		this.cc = cc;
	}

	@Override
	public void shutdown(IStatusEnum status) {
		this.powerOff = true;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
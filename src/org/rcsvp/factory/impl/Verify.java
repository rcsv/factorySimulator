package org.rcsvp.factory.impl ;

import java.util.Random ;

import org.rcsvp.factory.IControlCenter ;
import org.rcsvp.factory.IMaterial ;
import org.rcsvp.factory.ITolerance ;
import org.rcsvp.factory.IVerify ;

public class Verify implements IVerify {

	private IControlCenter cc ;

	protected IStatusEnum status ;

	public volatile double actualMeasure = 0 ;

	private String name ;

	protected ITolerance tolerance ;

	public Verify(String name, ITolerance tolerance) {
		this.name = name ;
		this.tolerance = tolerance ;
		this.status = GeneralStatus.Ready ;
	}

	@Override
	public String toString() {
		return this.name ;
	}

	@Override
	public boolean check(IMaterial material) {
		this.status = GeneralStatus.Working ;

		actualMeasure = Math.random() ;

		if (this.actualMeasure < 0.98) {
			this.status = GeneralStatus.VerifyFine ;
			return true ;
		}

		this.status = GeneralStatus.Problem ;
		return true ;
	}

	@Override
	public void setControlCenter(IControlCenter cc) {

		this.cc = cc ;

		// it don't send GeneralStatus.Ready into ControlCenter.

	}

	@Override
	public void shutdown(IStatusEnum status) {
		// it don't work as a thread.
		throw new RuntimeException() ;
	}

	@Override
	public IStatusEnum getStatus() {
		return status ;
	}

	@Override
	public void run() {
		// it don't work as a thread.
		throw new RuntimeException() ;
	}

	@Override
	public ITolerance getTolerance() {
		return tolerance ;
	}
}
package org.rcsvp.factory.impl ;

import org.rcsvp.factory.IControlCenter ;
import org.rcsvp.factory.IDisposable ;

public class Disposable implements IDisposable {

	private String name ;

	@Override
	public String toString() {
		return this.name ;
	}

	private IControlCenter cc ;

	private IStatusEnum status ;

	private long limit ;

	private volatile long count ;

	public Disposable(String name, long limit) {

		this.name = name ;

		this.limit = limit ;

		this.count = 0 ;

		this.status = GeneralStatus.Ready ;

	}

	@Override
	public void setControlCenter(IControlCenter cc) {
		this.cc = cc ;
		cc.notify(new AlertBox(this)) ;
	}

	@Override
	public void shutdown(IStatusEnum status) {
		cc.notify(new AlertBox(this)) ;
	}

	@Override
	public IStatusEnum getStatus() {
		return this.status ;
	}

	@Override
	public void run() {
		throw new RuntimeException("this instance cannot kick start.") ;
	}

	@Override
	public boolean use() {
		this.status = GeneralStatus.Working ;
		cc.notify(new AlertBox(this)) ;

		this.count++ ;

		if (count >= (1 - threshold) * limit) {

			this.status = GeneralStatus.Problem ;
			cc.notify(new AlertBox(this)) ;

			if (count == limit) {
				return false ;
			}

		} else {
			// this.status = GeneralStatus.Working ;
		}

		return true ;
	}

	@Override
	public boolean care() {

		this.count = 0 ;

		this.status = GeneralStatus.Working ;

		return true ;

	}

}
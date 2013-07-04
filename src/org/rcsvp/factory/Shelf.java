package org.rcsvp.factory ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.common.IControlCenter ;
import org.rcsvp.factory.common.IMaterial ;
import org.rcsvp.factory.common.IShelf ;

public class Shelf implements IShelf {

	final private long tactTime = 1 ;
	
	private String name ;

	private long count ;

	private long stockSize ;

	private volatile boolean powerOff = false ;

	private IControlCenter cc ;

	private GeneralStatus status ;

	public Shelf(String name, long stockSize) {
		this.name = name ;
		this.count = this.stockSize = stockSize ;
		this.status = GeneralStatus.Ready ;
	}

	@Override
	public void setControlCenter(IControlCenter cc) {
		this.cc = cc ;
	}

	@Override
	public void shutdown(IStatusEnum status) {
		this.powerOff = true ;
	}

	@Override
	public IStatusEnum getStatus() {
		return this.status ;
	}

	@Override
	public void run() {
		this.status = GeneralStatus.Working ;
		cc.notify(new AlertBox(this));
		while (!powerOff) {

			try {
				Thread.sleep(tactTime * cc.getTimeScale()) ;
			} catch (InterruptedException e) {
				e.printStackTrace() ;
			}

			Logger.debugWrite ( this.name + " : material left : " + this.count ) ;
		}
		
		this.status = GeneralStatus.NormallyShutdown;
		cc.notify(new AlertBox(this));
	}

	@Override
	public IMaterial getMaterial() {
		count-- ;
		if (this.count <= threshold * stockSize) {
			if (count <= 0) {
				this.status = GeneralStatus.InsufficientMaterials ;
				cc.notify(new AlertBox(this)) ;
			}
		}
		return new Material("from : " + this.name) ;
	}

	@Override
	public void reload() { this.count += this.stockSize ; }

	@Override
	public String toString () { return this.name ; }
}

package org.rcsvp.factory.impl;

import org.rcsvp.factory.IProductionLine ;
import org.rcsvp.factory.attributes.IRegistrable ;

public class ProductionLine extends AbstFacilities implements IProductionLine {

	private long norm ;
		
	public ProductionLine(String name, long tactTime) {
		super(name, tactTime) ;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean register(IRegistrable target) {
		// TODO Auto-generated method stub
		return false ;
	}

	@Override
	protected void bootUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean otherCheck() {
		// TODO Auto-generated method stub
		return false ;
	}

	@Override
	protected void routines() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void finishProcess() {
		// TODO Auto-generated method stub
		
	}

}

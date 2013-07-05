package org.rcsvp.factory ;

import org.rcsvp.factory.common.ITolerance ;

public class Tolerance implements ITolerance {

	private ToleranceType type ;

	private double offset ;

	private double tolerance ;

	public Tolerance(ToleranceType type, double offset, double tolerance) {
		this.type = type ;
		this.offset = offset ;
		this.tolerance = tolerance ;
	}

	@Override
	public double getToleranceRange() {
		return tolerance ;
	}

	@Override
	public double getOffset() {
		return offset ;
	}

	@Override
	public ToleranceType getType() {
		return type ;
	}

}
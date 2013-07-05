package org.rcsvp.factory ;

/**
 * ITolerance interface represents range of tolerance about each checkpoint.
 * 
 * @author Rcsvp.org
 * 
 */
public interface ITolerance {

	double getToleranceRange() ;

	double getOffset() ;

	enum ToleranceType {
		Scantling, Geometric, Electrical
	}

	ToleranceType getType() ;
}
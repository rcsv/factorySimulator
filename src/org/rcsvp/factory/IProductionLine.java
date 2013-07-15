package org.rcsvp.factory;

import org.rcsvp.factory.attributes.IParent ;
import org.rcsvp.factory.attributes.IRegistrable ;

public interface IProductionLine extends IRegistrable, IParent {

	void setNorm( long norm ) ;
}

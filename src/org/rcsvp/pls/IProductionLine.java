package org.rcsvp.pls;

import org.rcsvp.pls.factory.ControlCenterException;
import org.rcsvp.pls.factory.IRegistrable;
import org.rcsvp.pls.factory.IControlCenter.IStatusEnum;

/**
 * Interface IProductionLine represents Manufacturing production line in the
 * factory. it products a type of merchandise or assembly, or sub-assembly. In
 * this system architecture, they have some sequential Procedures in the lines.
 * 
 * @author Rcsvp.org
 * 
 */
public interface IProductionLine extends IRegistrable, Runnable {

	/**
	 * Register child node, Procedures and Shelf.
	 * 
	 * @param lineObject
	 *            a childnode instance of implements IRegistrable interface.
	 * @return success or failure.
	 */
	boolean register(IRegistrable lineObject) throws ControlCenterException;

	enum ProductionLineStatus implements IStatusEnum {
		Ready, NormFinished, WreckSomeProcedures
	}
}
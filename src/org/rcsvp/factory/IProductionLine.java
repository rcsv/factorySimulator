package org.rcsvp.factory ;

/**
 * IProductionLine interface represents a production line of a factory
 * implemented interface IFactory. IProductionLine has many Procedures, linked
 * each other. Each procedure work same time, called tact time. IProductionLine
 * instance knows tact time myself. then set tact time to every procedure, in
 * factory simulator.
 * 
 * @author Rcsvp.org
 * @date Jul 6, 2013
 * 
 */
public interface IProductionLine extends IRegistrable, IParent {

}
package org.rcsvp.factory ;

/**
 * Interface IFactory represents Manufacturing factory. it products lots of
 * merchandises from several materials. In common case, a manufacturing company
 * has some factories and factories have some production lines.
 * 
 * In this case, Factory equipped next-generation infrastructure, named
 * ControlCenter. A control center can check all facilities by console. I
 * prepared simple implementation of this interface at
 * {@link org.rcsvp.factory.impl.Factory}.
 * 
 * @see org.rcsvp.factory.impl.Factory
 * @author Rcsvp.org
 * 
 */
public interface IFactory extends IRegistrable, IParent {

}
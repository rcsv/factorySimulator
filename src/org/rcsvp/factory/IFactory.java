package org.rcsvp.factory ;

import org.rcsvp.factory.attributes.IParent ;
import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * Interface IFactory represents Manufacturing factory. It products log of
 * merchandises from several materials. In common case, a manufacturing company
 * has some factories and factories have some production lines.
 * 
 * In this case, Factory equipped next-generation infrastructure, named Monitor.
 * (ex-name, ControlCenter). A MonitorRoom can check all facilities by integrated
 * console. I prepared simple implementation of this interface at Factory.
 * 
 * @author Rcsvp.org
 * @date Jul 6, 2013
 * 
 */
public interface IFactory extends IRegistrable, IParent {

}
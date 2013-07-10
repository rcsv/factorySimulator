package org.rcsvp.factory ;

import org.rcsvp.factory.IRegistrable.IStatusEnum ;

/**
 * IAlertBox represents a status notification unit in ControlCenter.
 * IControlCenter use it when it notify each unit status to labor.
 *
 * @author Rcsvp.org
 * @date Jul 10, 2013
 *
 */
public interface IAlertBox {

  /**
   * Labor need to know the addres of facilities when it warned problem via
   * AlertBox.
   *
   * @return a reference of facility address that have a problem.
   */
  IRegistrable getTarget () ;
  
  IStatusEnum getStatus () ;
  
}

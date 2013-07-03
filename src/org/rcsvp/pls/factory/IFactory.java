package org.rcsvp.pls.factory;

import org.rcsvp.pls.factory.IControlCenter.IStatusEnum;

/**
 * Interface IFactory represents Manufacturing factory. it products lots of
 * merchandises from several materials. In common case, a manufacturing company
 * has some factories.
 * 
 * @author Rcsvp.org
 * 
 */
public interface IFactory extends Runnable {

	/**
	 * Register child node, including a labors, managers, production lines and
	 * AGV.
	 * 
	 * @param factoryObject
	 *            a child node instance implemented IRegistrable interface.
	 * @return
	 */
	boolean register(IRegistrable factoryObject);

	/**
	 * Enumerable Factory Status represents status of factory.
	 * 
	 * @author Rcsvp.org
	 * @date Jul 3, 2013
	 * 
	 */
	enum FactoryStatus implements IStatusEnum {

		/**
		 * Ready は準備 OK を表現する、最初のステータスです。
		 */
		Ready,

		/**
		 * NormallyShutdown は通常終了を表現するステータスです。
		 */
		NormallyShutdown,

		/**
		 * AbnormallyShutdown ステータスは、工場が通常の状態ではない方式で終了していることを示します。
		 */
		AbnormallyShutdown
	}
}
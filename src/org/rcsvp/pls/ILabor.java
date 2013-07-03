package org.rcsvp.pls;

import org.rcsvp.pls.factory.IRegistrable;
import org.rcsvp.pls.factory.IControlCenter.IStatusEnum;


/**
 * ILabor interface represents a factory worker. it related with IFactory
 * interface. ILabor registered into Factory, and checks several status of all
 * production lines, material shelves, other worker procedures and wrecked
 * Assembly, subassembly.
 * 
 * In common case, each worker asynchronized running. start threads using
 * Thread.start ().
 * 
 * Currently, there are no methods in it. I knew.
 * 
 * @author Rcsvp.org
 * @date Jul 3, 2013
 * 
 */
public interface ILabor extends IRegistrable, Runnable {

	/**
	 * 作業従事者は勤務時間中、様々な業務内容が待ち受けています。別の領域に指示を出す「連携」や、
	 * コントロールセンターを参照している「コントロールセンター監視中」何かを修理している「修理中」
	 * 部品棚に部品を供給している「補給中」等、様々な状況が想定されます。
	 * 
	 * @author Rcsvp.org
	 * 
	 */
	enum LaborStatus implements IStatusEnum {
		
		/**
		 * 作業従事者が最初になる状態で準備万端であるという事。
		 */
		Ready,

		/**
		 * Checking ControlCenter
		 */
		CheckControlCenter,

		/**
		 * Repair something
		 */
		Repair,
		
		/**
		 * Reload to Shelves
		 */
		Reloading

	}
}
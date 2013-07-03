package org.rcsvp.pls.factory;

import org.rcsvp.pls.factory.IControlCenter.IStatusEnum;

/**
 * SimpleAlertBoxImpl クラスは、IAlertBox
 * インターフェイスの単純実装です。こんな名前をつける必要は多分なかったので、リファクタリングの後、名称を変更するつもりです.
 * 
 * @author Rcsvp.org
 * @date Jul 3, 2013
 * 
 */
public class SimpleAlertBoxImpl implements IAlertBox {

	/**
	 * 保有する情報を最初に格納するコンストラクタ。フィールドは全て public で実装しているため他にメソッドも必要ありません。
	 * 
	 * @param stat
	 * @param object
	 */
	public SimpleAlertBoxImpl(IStatusEnum stat, IRegistrable object) {
		status = stat;
		this.object = object;
	}

	public IStatusEnum status;
	public IRegistrable object;

}
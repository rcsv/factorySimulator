package org.rcsvp.pls;

import java.util.*;

import org.rcsvp.pls.factory.ControlCenterException;
import org.rcsvp.pls.factory.IControlCenter;
import org.rcsvp.pls.factory.IControlCenter.IStatusEnum;
import org.rcsvp.pls.factory.IRegistrable;
import org.rcsvp.pls.util.Logger;

/**
 * The class ProductionLine is a simple implementation of
 * org.rcsvp.pls.IProductionLine interface.
 * 
 * @author Rcsvp.org
 * 
 */
public class ProductionLine implements IProductionLine {

	@Override
	public void run() {
		Logger.debugWrite(this.name + " : start production.");

		long count = 0;

		// shutdown 要請があった場合か、ノルマが達成された場合かのどちらかがあった場合、操業停止。
		while (!poweroff && (count != norm)) {

			try {
				Thread.sleep(1 * cc.getTimeScale());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			count++;
			Logger.debugWrite(this.name + " : production merchandises " + count);
		}

		Logger.debugWrite(this.name + " : finish production.");
		// もしノルマに達していなければ
		if (count != norm) {
			Logger.debugWrite(this.name
					+ " : insufficient merchandise. Are you sure?");
		}
	}

	/**
	 * ProductionLine スレッドを停止するための停止要求を覚えておく boolean。
	 */
	private volatile boolean poweroff = false;

	/**
	 * ProductionLine に当日もとめられている製造数。ノルマ。
	 */
	// PRODUCTIONLINE 特有のフィールド
	private volatile long norm;

	/**
	 * ProductionLine の名称を登録するエリア。各工場で、ProductionLine の名称は Unique の必要があります。
	 */
	private String name;

	/**
	 * IControlCenter インターフェイス実装。
	 */
	private IControlCenter cc;

	/**
	 * ProductionLine の名称を求めるコンストラクタ。
	 * 
	 * @param name
	 *            ProductionLine を一意に設定する名称。
	 */
	public ProductionLine(String name, int norm) {
		this.name = name;
		this.norm = norm;
		this.procs = new LinkedList<IProcedure>();
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public void setup(IControlCenter cc) {
		this.cc = cc;
	}

	@Override
	public void shutdown(IStatusEnum status) {
		this.poweroff = true;
		Logger.debugWrite(this.name
				+ " : Requested shutdown by factory. reason : "
				+ status.toString());
	}

	@Override
	public boolean register(IRegistrable lineObject)
			throws ControlCenterException {

		// 工場への登録が済まないまま、事前に製造ラインに
		if (cc == null) {
			throw new ControlCenterException(
					"当該システムは工場から順番に下のノードを構築していく必要があります。下から構築しないでください。");
		}
		lineObject.setup(cc);
		String x = lineObject.getClass().getSimpleName();

		switch (x) {
		case "Procedure": // TODO: クラス名がハードコード。リファクタリングができる形態にしたい。
			procs.add((IProcedure) lineObject);
			break;
		default:
			break;
		}
		return true;
	}

	private List<IProcedure> procs;
}
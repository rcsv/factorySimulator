package org.rcsvp.factory;

import java.util.*;

import org.rcsvp.factory.common.IControlCenter;
import org.rcsvp.factory.common.IFactory;

/**
 * Singleton 複合で、Factory の中で一つ。getInstance には、FactoryCode となる String
 * 型の文字列が必要となる。
 * 
 * @author Rcsvp.org
 * 
 */
public class ControlCenter implements IControlCenter {

	private IFactory factory;

	private static Map<String, IControlCenter> mapCC = new HashMap<String, IControlCenter>();

	private Map<String, IAlertBox> console;

	/**
	 * シングルトンで実装するためコンストラクタを private にして封印。
	 */
	private ControlCenter() {
		console = new HashMap<String, IAlertBox>();
		this.timeScale = 1000;
	}

	/**
	 * static IControlCenter 型のインスタンスを提供します。各工場毎で使用するインスタンスは別。
	 * 
	 * @return 工場コード factoryCode で一意に割り当てられた IControlCenter インターフェイス実装のインスタンス。
	 */
	public static IControlCenter getInstance(String factoryCode) {

		IControlCenter x = mapCC.get(factoryCode);

		if (x == null) {
			x = new ControlCenter();
			mapCC.put(factoryCode, x);
		}

		return x;
	}

	@Override
	public boolean notify(IAlertBox box) {
		console.put(box.getTarget().toString(), box);
		return true;
	}

	@Override
	public Map<String, IAlertBox> getConsole() {
		return this.console;
	}

	@Override
	public long getTimeScale() {
		return timeScale;
	}

	private long timeScale = 1000;

	@Override
	public boolean powerOff() {
		this.factory.shutdown(GeneralStatus.NormallyShutdown);
		return false;
	}

	@Override
	public void setFactory(IFactory factory) {
		this.factory = factory;
	}

	@Override
	public void setTimeScale(long timeScale) {
		this.timeScale = timeScale;
	}

}
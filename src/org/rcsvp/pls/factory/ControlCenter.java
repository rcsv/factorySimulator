package org.rcsvp.pls.factory;

import java.util.*;

/**
 * Singleton 複合で、Factory の中で一つ。getInstance には、FactoryCode となる String
 * 型の文字列が必要となる。
 * 
 * @author Rcsvp.org
 * 
 */
public class ControlCenter implements IControlCenter {

	/**
	 * Map 型で保存する領域。
	 */
	private static Map<String, IControlCenter> mapCC = new HashMap<String, IControlCenter>();

	/**
	 * Factory オブジェクトへのポインタ
	 */
	private IFactory factory;

	/**
	 * シングルトンで実装するためコンストラクタを private にして封印。
	 */
	private ControlCenter() {
	}

	public IFactory getFactoryAccess() {
		return this.factory;
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
	public boolean notify(IStatusEnum status, String reason) {
		return false;
	}

	public class NotifyBox {
		public String reason;
		public IStatusEnum status;
		public IRegistrable facility;
	}

	@Override
	public Queue<IAlertBox> getConsole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTimeScale() {
		return defaultTimeScale;
	}

	final private static int defaultTimeScale = 1000;

	@Override
	public void register(IRegistrable target) {
		target.setup(this);
		
		String x = target.getClass().getSimpleName() ;
		
		switch ( x ) {
		case "Factory":
			this.factory = (IFactory)target ;
			break ;
		default:
			break ;
		}
	}
}
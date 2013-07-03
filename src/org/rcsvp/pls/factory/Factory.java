package org.rcsvp.pls.factory;

import java.util.*;

import org.rcsvp.pls.*;
import org.rcsvp.pls.disposable.*;
import org.rcsvp.pls.factory.IControlCenter.IStatusEnum;
import org.rcsvp.pls.util.Logger;

/**
 * It is simple implementation of IFactory.
 * 
 * @author Rcsvp.org
 * 
 */
public class Factory implements IFactory {

	private volatile boolean powerOff = false;

	public void shutdown(IStatusEnum status) {
		this.powerOff = true;
	}

	/**
	 * store area for Labors.
	 */
	private Map<String, ILabor> labors;

	/**
	 * Production Line storage area.
	 */
	private Map<String, IProductionLine> lines;

	/**
	 * a instance of IControlCenter. Each factory and child nodes using just one
	 * ControlCenter.
	 */
	private IControlCenter cc;

	/**
	 * Store area of a factory name.
	 */
	private String name;

	/**
	 * Constructor needs a unique factory name cause super wrapper instance
	 * manage using java.util.Map<String name, IFactory factory>.
	 * 
	 * @param name
	 *            a unique name for a factory.
	 */
	public Factory(String name) {
		this.name = name;
		cc = ControlCenter.getInstance(name);

		labors = new HashMap<String, ILabor>();
		lines = new HashMap<String, IProductionLine>();
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public boolean register(IRegistrable factoryObject) {

		factoryObject.setup(cc);
		String x = factoryObject.getClass().getSimpleName();

		switch (x) {
		case "Labor": // TODO: クラス名がハードコード。リファクタリングができる形態にしたい。
			labors.put(factoryObject.toString(), (ILabor) factoryObject);
			break;

		case "ProductionLine":
			lines.put(factoryObject.toString(), (IProductionLine) factoryObject);
			break;

		default:
			Logger.debugWrite("It is another.");
			break;
		}

		return true;
	}

	@Override
	public void run() {
		Logger.debugWrite("Start Factory    : " + this.name);

		//
		// 従業員が働き始めます。
		//
		ThreadGroup threadLabors = new ThreadGroup(this.name + "Labors");
		Iterator<ILabor> itLabor = labors.values().iterator();
		while (itLabor.hasNext()) {

			// あーたーらしーいあっさがきた、きぼーおおのあさーっだ。
			// よろこーびにむねをひーらっけ、あおぞーらあーおーげー。
			// らじおーの、こえにー

			new Thread(threadLabors, itLabor.next()).start();
		}

		//
		// 従業員が働き始めた後、製造ラインの電源を入れます。
		//
		ThreadGroup threadLine = new ThreadGroup(this.name + "ProductionLine");
		Iterator<IProductionLine> itLine = lines.values().iterator();
		while (itLine.hasNext()) {

			// ぶいーん。

			new Thread(threadLine, itLine.next()).start();

		}

		while (!powerOff) {
			try {
				Thread.sleep(30 * cc.getTimeScale());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}

		//
		// SHUTDOWN...
		//
		itLabor = labors.values().iterator();
		ILabor x = null;

		while (itLabor.hasNext()) {
			x = itLabor.next();
			x.shutdown(IFactory.FactoryStatus.NormallyShutdown);
		}

		itLine = lines.values().iterator();
		IProductionLine pl = null;

		while (itLine.hasNext()) {
			pl = itLine.next();
			pl.shutdown(IFactory.FactoryStatus.NormallyShutdown);
		}

		Logger.debugWrite("Shutdown Factory : " + this.name);
	}

	public static void main(String[] args) {

		Logger.lv = Logger.VerboseLevel.Debug;

		// 工場を構築して、従業員を登録する。

		IFactory factory = new Factory("Factory_1");

		factory.register(new Labor("Yamada Taro"));

		IProductionLine pl1 = new ProductionLine("LINE 1", 100);
		IProductionLine pl2 = new ProductionLine("LINE 2", 100);

		IProcedure proc1 = new Procedure("Procedure 1 ");
		IProcedure proc2 = new Procedure("Procedure 2 ");

		IDisposable dispo1 = new Disposable("Tool1", 100);
		IDisposable dispo2 = new Disposable("Tool 2", 200);

		factory.register(pl1);
		factory.register(pl2);

		pl1.register(proc1);
		pl1.register(proc2);

		new Thread(factory).start();

		Logger.debugWrite("Shutdown Main");
	}
}
package org.rcsvp.factory.impl ;

import java.util.Iterator ;
import java.util.Map ;
import java.util.HashMap ;
import java.util.concurrent.Executor ;
import java.util.concurrent.Executors ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.IAgv ;
import org.rcsvp.factory.IExport ;
import org.rcsvp.factory.IFactory ;
import org.rcsvp.factory.ILabor ;
import org.rcsvp.factory.IProductionLine ;
import org.rcsvp.factory.IWarehouse ;
import org.rcsvp.factory.attributes.IRegistrable ;

/**
 * Factory class is a simple implementation of IFactory interface.
 * 
 * http://itpro.nikkeibp.co.jp/article/COLUMN/20071001/283395/
 * 
 * Concurrency Utilities では、Thread
 * クラスを明示的に使用する事はありません。その代わりに登場するのが、java.util.concurrent.Executor
 * インターフェイス、もしくはその派生インターフェイスである java.util.concurrent.ExecutorService インターフェイスです。
 * Executor インターフェイスは Runnable インターフェイスを実装したクラスで記述されるタスクを実
 * 行する execute メソッドを定義します。しかし、Executor インターフェイスではタスクの実行方法を
 * 指定する事はありません。
 * 
 * タスク実行の方法は、Executor インターフェイスを実装したクラスによって決まります。例えば、
 * java.util.concurrent.ThreadPoolExecutor クラスであれば、複数のスレッドで構成されるス
 * レッドプールを用いてタスクを実行します。
 * 例えば、100 ミリ病間スリープしてその後、標準出力にメッセージを出力するという全く役に立たないタ
 * スクを Executor インターフェイスを使って実行してみましょう。
 * 
 * @author Rcsvp.org
 * @date Jul 12, 2013
 * 
 */
public class Factory extends AbstFacilities implements IFactory {

	/**
	 * factory personnel data
	 */
	private Map<String, ILabor> labors ;

	/**
	 * production lines
	 */
	private Map<String, IProductionLine> lines ;

	/**
	 * AGVs
	 */
	private Map<String, IAgv> AGVs ;

	/**
	 * Warehouse
	 */
	private IWarehouse warehouse ;

	/**
	 * Export area.
	 */
	private IExport export ;

	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------

	/**
	 * Construct Factory instance with two arguments: unique name and tact time.
	 * 
	 * @param name
	 *            a name of factory. it required unique.
	 * @param tactTime
	 *            a tact time of this factory surveillance interval.
	 */
	public Factory(String name, long tactTime) {
		super(name, tactTime) ;

		initialize() ;
	}

	/**
	 * Construct Factory instance with one argument: a unique name of factory.
	 * Set tact time 60 when you use this constructor.
	 * 
	 * @param name
	 *            a name of factory. It required unique.
	 */
	public Factory(String name) {
		super(name, ControlCenter.getInstance().getDefaultCycleTime()) ;

		initialize() ;
	}

	/**
	 * Initialize.
	 */
	private void initialize() {

		this.labors = new HashMap<String, ILabor>() ;
		this.lines = new HashMap<String, IProductionLine>() ;
		this.AGVs = new HashMap<String, IAgv>() ;

		this.mr = MonitorRoom.getInstance( this.name ) ;
	}

	// -----------------------------------------------------------------------
	// override / abstract method.
	// -----------------------------------------------------------------------

	@Override
	public boolean register(IRegistrable target) {

		target.setMonitorRoom(mr) ;

		// -------------------------------------------------------------------
		// switch target name and store each hashmaps.
		// -------------------------------------------------------------------

		String targetName = target.getClass().getSimpleName() ;

		switch (targetName) {

		case "Labor":
			//
			// --- Labor --- ILabor interface
			//
			labors.put(target.toString(), (ILabor) target) ;
			break ;

		case "ProductionLine":
			//
			// --- ProductionLine --- IProductionLine interface
			//
			lines.put(target.toString(), (IProductionLine) target) ;
			break ;

		case "Agv":
			//
			// --- Agv --- IAgv interface
			//
			AGVs.put(target.toString(), (IAgv) target) ;
			break ;

		case "Warehouse":
			//
			// --- Warehouse --- IWarehouse interface
			//
			this.warehouse = (IWarehouse) target ;
			break ;

		case "Export":
			//
			// --- Export --- IExport interface
			//
			this.export = (IExport) target ;
			break ;
		default:

			//
			// RuntimeException?
			// return false ;
			throw new RuntimeException() ;

			// break ;
		}

		return true ;
	}

	@Override
	protected void bootUp() {

		Logger.debug(this.name + " : start bootUp().......................") ;

		//
		// display information into logging.
		//
		Logger.info(this.name + " : has " + labors.size() + " labors.") ;
		Logger.info(this.name + " : has " + lines.size() + " production lines.") ;
		Logger.info(this.name + " : has " + this.AGVs.size() + " AGVs.") ;
		Logger.info(this.name + " : has a warehouse "
				+ this.warehouse.toString()) ;
		Logger.info(this.name + " : has a export " + this.export.toString()) ;

		//
		// kick start labors
		//
		if (labors.size() != 0) {
			Iterator<ILabor> iL = labors.values().iterator() ;
			Executor exeLabors = Executors.newFixedThreadPool(labors.size()) ;

			while (iL.hasNext()) {

				exeLabors.execute(iL.next()) ;
			}
		}

		//
		// kick start production lines
		//
		if (lines.size() != 0) {
			Iterator<IProductionLine> iP = lines.values().iterator() ;
			Executor exeLines = Executors.newFixedThreadPool(lines.size()) ;

			while (iP.hasNext()) {

				exeLines.execute(iP.next()) ;

			}
		}

		//
		// kick start AGV
		//
		if (AGVs.size() != 0) {
			Iterator<IAgv> iA = AGVs.values().iterator() ;
			Executor exeAgv = Executors.newFixedThreadPool(AGVs.size()) ;

			while (iA.hasNext()) {

				exeAgv.execute(iA.next()) ;

			}
		}

		Logger.debug(this.name + " : finish bootUp().....................") ;
	}

	@Override
	protected boolean otherCheck() {
		// TODO Auto-generated method stub
		return false ;
	}

	@Override
	protected void routines() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void finishProcess() {
		// TODO Auto-generated method stub

	}
}

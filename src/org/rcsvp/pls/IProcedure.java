package org.rcsvp.pls;

import org.rcsvp.pls.disposable.IDisposable;
import org.rcsvp.pls.material.IShelf;
import org.rcsvp.pls.validation.IVerify;

/**
 * IProcedure interface represents some types of procedures in a factory's
 * production lines. Each procedures take same time named "tact time". In this
 * case, they have to implement "Runnable".
 * 
 * IProcedure インターフェイスは、製造ラインに所属するそれぞれの工程を表現しています。
 * それぞれの工程は、タクトタイムという時間で協調して動作しますが、一連の作業は並列で行っていくため、マルチスレッドで動作させる必要があります。
 * 最前列の工程には少なくとも一つ、途中の工程にも一つ以上の在庫棚を持っていることが考えられます。
 * 
 * 各工程はその日のノルマがいくつなのか判断することはありません。
 * 
 * @author Rcsvpg.org
 * 
 */
public interface IProcedure extends Runnable {

	/**
	 * 在庫棚を登録します。 在庫棚は各製造工程で全く無い場合もあれば、複数存在している場合もあります。
	 * 在庫棚に在庫となる材料を補充するのは工場に勤務している従業員の仕事になります。
	 * 
	 * @param shelf
	 *            使用する在庫棚
	 */
	void register(IShelf shelf);

	/**
	 * 各工程で使用する消耗品を登録します。消耗品も在庫棚同様、一つも消耗品がない場合もあれば、複数の消耗品で構成されている工程も存在します。
	 * 
	 * @param dispo
	 *            IDisposable インターフェイスを実装したクラスのインスタンス。
	 */
	void register(IDisposable dispo);

	/**
	 * 各工程で加工が完了した後に検証する項目を登録します。
	 * 
	 * @param verify
	 *            IVerify インターフェイスを実装したクラスのインスタンス。
	 */
	void register(IVerify verify);

	/**
	 * ProcedureStatus は、各工程が持つであろう状態を表現しています。
	 * 前工程の材料待ち、工程での作業中、作業を失敗して復旧できない状態にある。 電源がオフである、といった状態が考えられます。
	 * 
	 * @author Rcsvpg.org
	 * 
	 */
	enum ProcedureStatus {

		/**
		 * 前工程からの材料待ちです。PowerOn されると、この状態にまず入ります。前工程から無事に材料を受け取ることが出来れば、Working
		 * の状態に入ります。
		 */
		WaitPreviousMaterial,

		/**
		 * 作業中です。作業中は他のことが出来ません。
		 */
		Working,

		/**
		 * 作業中に消耗品が破損したり、その他様々な理由で自分自身が止まってしまった場合 StopWrecking という状態になります。
		 * この状態に陥った場合、工場勤務の人は急いでラインを一旦停止し、復旧のための作業を行う必要があります。
		 */
		StopWrecking,

		/**
		 * 電源がオフの状態です。製造ラインがその日の生産ノルマを達成し、ラインを停止すると、この状態に入ります。
		 */
		PowerOff
	}
}
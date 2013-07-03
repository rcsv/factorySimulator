package org.rcsvp.pls;

import org.rcsvp.pls.factory.*;
import org.rcsvp.pls.factory.IControlCenter.IStatusEnum;
import org.rcsvp.pls.util.Logger;

/**
 * The class Labor is a simple implementation of org.rcsvp.pls.ILabor interface.
 * It's a short story. He is Taro Yamada, belonging to Example Manufacturing.,
 * Inc. Every morning, he goes a factory where he work --- Burn Takoyaki
 * Production Line, for example --- and greets his co-workers.
 * "Good morning, How does it going today?" everytime he ask to same buddies.
 * It's a routine.
 * 
 * ILabor の単純実装です。Labor は工場で勤務しています。年齢は不詳ですが、工場内では班長として立ち回る事が出来る程度には年を重ねています。
 * 
 * @author Rcsvp.org
 * 
 */
public class Labor implements ILabor {

	private volatile boolean poweroff = false;
	/**
	 * Labor's name. It should be unique for designing future architecture, for
	 * example name sets as a index of database table. 作業者の名前です。作業者の名称も、HashMap
	 * に登録したり、または DB で Primary Key への設計を発展させやすくする事を狙い、一意であることを求めます。
	 */
	private String name;

	/**
	 * IControlCenter インターフェイスの実装を取得します。
	 */
	private IControlCenter cc;

	/**
	 * 作業者の名前の入力を求めるコンストラクタです。
	 * 
	 * @param name
	 *            作業者に設定する名前。
	 */
	public Labor(String name) {
		this.name = name;
	}

	@Override
	public void setup(IControlCenter cc) {
		this.cc = cc;
	}

	@Override
	public void run() {

		Logger.debugWrite(this.name + " : said 'good morning.'");

		//
		// 朝来て、会社で同僚に挨拶。そこから彼の一日は始まる。コントロールセンターに立ち入り、
		// 電源が On されていることを確認する。初期起動チェックだ。初期起動チェックは
		// 1. 在庫が製造工程の横に充足された形で配備されているかどうか
		// 2. 全ての製造工程、製造ライン、その他に初期異常がでていないかどうか
		// といった点をチェックする。
		//

		while (!poweroff) {

			//
			// 集中管理盤を見る。
			//
			Logger.debugWrite(this.name + " : Checking at ControlCenter...");

			try {
				Thread.sleep(10 * cc.getTimeScale());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			cc.getConsole();

			Logger.debugWrite(this.name + " : didn't detect any problem.");
		}

		Logger.debugWrite(this.name + " : said 'I gonna go home.'");
	}

	@Override
	/*
	 * 通常 Override の方がコメントが必要だと思う、という論争を読んだ事があるんだが、その後どうなったのだろうか。
	 */
	public String toString() {
		return this.name;
	}

	@Override
	public void shutdown(IStatusEnum status) {
		this.poweroff = true;
		Logger.debugWrite(this.name + " : Requested shutdown by "
				+ status.toString());
	}

}
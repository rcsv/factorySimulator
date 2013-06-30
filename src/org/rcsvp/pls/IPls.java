package org.rcsvp.pls;

/**
 * スレッドとして動作する、今回のシミュレータの実装を若干横着するインターフェイスです。スレッドと
 * 子供ノードの登録、そして、全てに停止する事を送信する。
 * 
 * @author Tomohiro AWANE <Awane.Tomohiro@me.com>
 * @version 0.1
 * 
 */
public interface IPls extends Runnable {

  /**
	 * 状態を返す。
	 * @return 状態。
	 * @see Status
	 */
	Status getStatus() ;
	
	/**
	 * このインターフェイスを実装するオブジェクト達は、全てスレッド的に動作することになります。
	 * 用がすんだら停止してもらうため、シャットダウンを用意しておきます。戻り値はありません。
	 */
	void shutdown( Status status ) ;
	
	/**
	 * register が呼ばれた時に名称の登録をし直すためのメソッドです。
	 * 
	 * @param nameForInsert
	 */
	void updateName( String nameForInsert ) ;
}

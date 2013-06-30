package org.rcsvp.pls;

/**
 * スレッドとして動作する、今回のシミュレータの実装を若干横着するインターフェイスです。スレッドと
 * 子供ノードの登録、そして、全てに停止する事を送信する。
 * 
 * @author Tomohiro AWANE <Awane.Tomohiro@me.com>
 *
 */
public interface Ipls extends Runnable {

  /**
	 * 子供のノードを登録する。factory, production, shelf, procedure に対して行う横着。
   *
	 * @param childNode 
	 */
	void register( Ipls childNode ) ;
	
	/**
	 * 状態を返す。
	 * @return 状態。
	 */
	Status getStatus() ;
	
	/**
	 * スレッド的に動作するため、停止してもらいます。
	 */
	void shutdown() ;
}

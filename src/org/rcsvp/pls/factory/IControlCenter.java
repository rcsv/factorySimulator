package org.rcsvp.pls.factory;

import java.util.Queue;

/**
 * IControlCenter
 * インターフェイスは工場内部に設置されている「集中管理室」、または「集中管理コンソール」といったオブジェクトを表現したものです。
 * 見える化を担うのが集中管理コンソールであって、情報を一元的に保存し続けるという役割を担うのは、本来「データベース」と呼ばれるものであるべきで、
 * それが別途存在した上で
 * 、そちらがシングルトンで扱われるべきだと考えます。少しおかしな話なのですが、集中管理コンソールは唯一無二の工場内の一元データベースとなっています。
 * 
 * 
 * @author Rcsvp.org
 * 
 */
public interface IControlCenter {

	IFactory getFactoryAccess() ;
	
	void register ( IRegistrable target ) ;
	/**
	 * IControlCenter に向かって、製造ラインや製造工程が集中管理対象となるデータや、自分自身の活動の変化を伝えます。
	 * IControlCenter 自体がデータベースのような役割を今のところになってしまっており変な事になっています。
	 * 
	 * @param status
	 *            IStatusEnum インターフェイスを実装している列挙型。
	 * 
	 * @param reason
	 *            活動内容。
	 * 
	 * @return 通知に成功した場合は true, 通知に失敗したときは false を送信します。
	 */
	boolean notify(IStatusEnum status, String reason);

	Queue<IAlertBox> getConsole();

	/**
	 * obtain time scale. range = 1 ~ 1000+. if set 0 or minus, reset 1000.
	 * 
	 * @return
	 */
	int getTimeScale();

	/**
	 * IStatusEnum インターフェイスは、集中管理盤が取り扱う状態を示す列挙型が実装するインターフェイスです。
	 * 
	 * @author Rcsvp.org
	 * 
	 */
	interface IStatusEnum {

	}
}
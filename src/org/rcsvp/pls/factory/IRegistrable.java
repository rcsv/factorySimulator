package org.rcsvp.pls.factory;

import org.rcsvp.pls.factory.IControlCenter.IStatusEnum;

/**
 * IRegistrable インターフェイスは IFactory インターフェイスに登録することが出来る子ノードに必要なメソッドが用意されています。
 * 
 * @author Rcsvp.org
 * 
 */
public interface IRegistrable {

	/**
	 * ControlCenter へのポインタを用意します。
	 * 
	 * @param cc
	 */
	void setup(IControlCenter cc);

	/**
	 * IFactory インターフェイスに登録された別ノードは操業が停止したら作業を終わり、電源をオフにしていく。
	 * 
	 * @param status
	 */
	void shutdown(IStatusEnum status);

}
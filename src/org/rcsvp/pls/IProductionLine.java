package org.rcsvp.pls;

/**
 * 工場に登録する生産ラインを表現するためのインターフェイス。横着用インターフェイス Ipls を拡張し
 * ています。
 * 
 * @author Tomohiro AWANE <Awane.Tomohiro@me.com>
 *
 */
public interface IProductionLine extends IPls {

  void register ( IProcedure proc ) ;
}

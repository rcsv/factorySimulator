package org.rcsvp.pls;

/**
 * interface で分離しておかないと、全部 Ipls になっちゃうからね。そういえば、インターフェイスの
 * ダウンキャスト的な @Override ってどういう仕組みになるのだろうか。
 * 
 * @author Tomohiro AWANE <Awane.Tomohiro@me.com>
 *
 */
public interface IFactory extends IPls {

  /**
	 * IProductionLine を工場に登録するためのメソッドです。基本的に参照渡しです。生産ラインは
	 * 一つ一つが完結した者なので、登録の順序は特に関係ありません。内部も名称とマップで保有します。
	 * Map で保有するため、IProductionLine のコンストラクタで設定する名称は一意の必要がありま
	 * す。
	 * 
	 * @param line IProductionLine インスタンスのポインタ。
	 */
	void register( IProductionLine line ) ;
	
	/**
	 * ISurveillance を工場に登録するためのメソッドです。結局キックスタートしてしまうだけなので、
	 * 実際問題として IProductionLine との register を分類する必要は無かったような気がします。
	 * 折角なので分類しておいて、内部的に同じ事をしてしまえばいいかなと。
	 * 
	 * @param person 鼻くそをほじりながら監視モニターを眺めている人。班長かもしれないし、確認
	 * 作業をしっかりがんばっている作業者かもしれないし、あるいはたまたま来た新人さんかもしれない。
	 */
	void register( ISurveillance person ) ;

	/**
	 * 自動でパレット供給をするための工場内を走り回るロボットのようなものを登録します。これについては
	 * あまり詳細を理解していないため、適当です。
	 * 
	 * @param agv
	 */
	void register( IAgv agv ) ;
}

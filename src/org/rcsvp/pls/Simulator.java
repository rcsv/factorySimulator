/**
 * 
 */
package org.rcsvp.pls;

/**
 * 今回の Production Line Simulator 構想を練るための基本クラス。当面はこの操作順序をベースに
 * したオブジェクトの拡充、クラスの拡充がメインとなっていくと考えられる。
 * 
 * @author Tomohiro AWANE <Awane.Tomohiro@me.com>
 *
 */
public class Simulator {

  public static void main(String[] args) {
	  
		//
		// まず場所的な問題として「工場」というものをオブジェクト化してみます。
		// 工場には工場長初め、班長さんに掃除をしている人等、様々な人が勤務していますので、
		// 膨大な情報を持つ必要があるかもしれません。
		//
		IFactory factory = new Factory( "MyFactory" ) ;
		
		//
		// 次に「工場」が持つであろう「生産ライン」というものをオブジェクト化してみます。生産ライ
		// ンは、それぞれが自立的に稼働し、タクトタイム毎に製品を完成させていきます。生産ラインに
		// はそれぞれの組み付け工程や検査項目等がありますが、運転を止めなければならない場合、生産
		// ラインは停止してしまいます。
		// かといって、Java Thread 自体が止まる訳ではなさそうですな。生産ラインは、組み立てた
		// 時に既に一つの製品が出来上がるまでに必要な秒数、つまり、「タクトタイム」があり、これを
		// ベースに各工程が進んでいくような仕組みになる筈です。
		//
		// 今回のラインのタクトタイムは 45 秒、ということにします。
		//
		IProductionLine line1 = new ProductionLine( "鉄の箱", 45 ) ;
		
		//
		// さて、工場の製造ラインは一つではないハズです。同じ製品を作っている生産ラインを表現する
		// ため、同じ手法でオブジェクト化を試みます。同じラインなのですが、設備が古いため、タクト
		// タイムは 60 秒必要、ということにします。
		// 
		IProductionLine line2 = new ProductionLine( "鉄の塊", 60 ) ;
		
		//
		// 常に参照渡しだったと思うので、生産ラインを一旦工場の者として登録しておきます。この工場
		// には生産ラインが二つしかありません。しかも両方とも鉄の塊です。ある意味、ものすごいドル
		// 箱商品としてこの鉄の塊は扱われている事になります。
		//
		factory.register ( line1 ) ;
		factory.register ( line2 ) ;
		
		//
		// 生産ラインは、毎月、または毎日、取引先からの供給要望に応じて生産量を決定します。生産ラ
		// インはその要望に応じて、操業を継続していく事になります。タクトタイムに応じて、そのノル
		// マを設定していくのは自動設定ではなく、工場の管理部の仕事かもしれません。今回は、各々の
		// 生産ラインが 3:4 のタクトタイムになっているため、比率に応じた生産量としています。
		//
		line1.setNorm( 400 ) ; // line1 の今回（今日？今月？）の生産量は 400 です。
		line2.setNorm( 300 ) ; // line2 の今回（今日？今月？）の生産量は 300 となりました。

		//
		// 生産ラインの最初の工程の近くには、それぞれ加工を開始するための「最初の材料が置いてある
		// 棚」がある筈です。棚は製造工程から「材料頂戴！」と言われれば、自動で反応しなければなり
		// ませんが、棚自身が自分で足らなくなった材料を拡充するとか聞いた事がありませんので、
		// こいつ自体が材料を拡充する方式にはしたくありません。ここでは二つの棚を用意します。各々
		// の棚は、場所の関係上材料を確保しておく最大量が違います。
		//
		IShelf shelf4line1 = new Shelf( "鉄の塊の素", 100 ) ;
		IShelf shelf4line2 = new Shelf( "鉄の塊の素", 80  ) ;
		
		//
		// 材料棚を登録します。
		//
		line1.register ( shelf4line1 ) ;
		line2.register ( shelf4line2 ) ;

		//
		// 確認さんは、担当しているラインの状況を観察し、材料が不足していたら AVG を呼び寄せる、
		// 最後の検査工程で目視を担ったりしています。今回は全てこの人に担ってもらいます。名前は
		// しもはらひとりさん。
		//
		ISurveillance person = new Surveillance( "Hitori Shimohara" ) ;

		//
		// しもはらひとりさんに、今回の監視対象になるものをチェックしてもらいます。チェック対象は
		// 「鉄の塊の素」が置いてある二つの棚と全ラインです。普段は監視パネルを見ていますが材料が
		// 足らなくなったらこの人が動きます。
		//
		// person.addSurvey( shelf4line1 ) ;
		// person.addSurvey( shelf4line2 ) ;
		// person.addSurvey( line1 ) ;
		// person.addSurvey( line2 ) ;
		//

		// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		//
		// 今回の構成要素を紐解くと:
		// IFactory - IProductionLine - IShelf
		//							  - IProcedure - IDisposable
		//										   - ICheck
		//			- ISurveyllance
		//          - AGV?
		//
		// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		
		//
		// 工程を表現する IProcedure はそれぞれスレッドで動く事になりますね。
		// 多分、タクトタイムは IProductionLine からもらった方が良いでしょう。
		//
		IProcedure proc11 = new Procedure( "1.1. 棚から下ろして眺める工程" ) ;
		IProcedure proc12 = new Procedure( "1.2. 更に眺めて、悦に浸る工程" ) ;

		// 結局同じ工程。
		IProcedure proc21 = new Procedure( "1.1. 棚から下ろして眺める工程" ) ;
		IProcedure proc22 = new Procedure( "1.2. 更に眺めて、悦に浸る工程" ) ;

		//
		// 製造ライン１に対して、工程１と工程２を設定しておきます。
		//
		line1.register( proc11 ) ;
		line1.register( proc12 ) ;
		
		line2.register( proc21 ) ;
		line2.register( proc22 ) ;

		ICheck c11 = new CheckDesign( "悦に浸れるか否か" ) ;
		ICheck c12 = new CheckSize  ( "サイズが適合しているか否か", 10, 20 ) ;
		ICheck c13 = new CheckLength( "長さが許容範囲内か否か", 10, 20 ) ;
		ICheck c14 = new CheckWeight( "重さが許容範囲か否か", 100, 200 ) ;
		ICheck c15 = new CheckElec  ( "電圧に異常が無いかどうか", -0.8, 0.8 ) ;
		ICheck c16 = new CheckSlide ( "摩擦抵抗が一定以下か否か", -10 ) ;
		
		//
		// それぞれの工程でチェックしなければならない項目を設定しておきます。
		//
		proc11.register( c11 ) ;
		proc12.register( c11 ) ;
		proc11.register( c12 ) ;
		proc12.register( c13 ) ;
		proc12.register( c14 ) ;
		proc12.register( c15 ) ;
		proc12.register( c16 ) ;
		
		proc21.register( c11 ) ;
		proc22.register( c11 ) ;
		proc21.register( c12 ) ;
		proc22.register( c13 ) ;
		proc22.register( c14 ) ;
		proc22.register( c15 ) ;
		proc22.register( c16 ) ;

		//
		// それぞれの工程に消耗品をひとつづつ登録しておきます。
		//
		IDisposable dispo1 = new Disposable( "眺める目", 2500 ) ;
		IDisposable dispo2 = new Disposable( "眺め続ける心", 5000 ) ;
		
		proc11.register( dispo1 ) ;
		proc12.register( dispo2 ) ;

		//
		// これで Factory を Root にして、ツリー状態になっているので、ここから
		// factory.start() ;
		// とする事で、全て非同期で動作し始めると良いな。という事を目指しています。
		//
		//
		// ここまで適当にクラスや階層図を書いたところで、
		// Thread とそれなりな Interface 設計があればシンプルになるかもしれないね。
	}
}


package org.rcsvp.pls.material;

/**
 * IShelf インターフェイスは、各工程で使用する材料を補完しておく棚を表現しています。
 * 材料は一つずつ使い、段々無くなっていきますが、各工程でのタクトタイムをとめないよう、 工場の従業員は注意して見張っておく必要があります。
 * 
 * @author Rcsvpg.org
 * 
 */
public interface IShelf {

	/**
	 * 在庫を切らして、次の在庫を補充するための警告閾値を設定しています。 在庫保持上限が 10
	 * 個のようなあまりにも低い在庫保持状況の工程でこの数値を使用した場合、 0 になりやすくなります。
	 */
	float	threshold	= 0.125f;

	/**
	 * 在庫棚から、材料をひとつだけ取り出して使用します。
	 * 
	 * @return インスタンス化された IMaterial 実装オブジェクト
	 */
	IMaterial getMaterial ()
	// 実装の困難さがあるのでやめておきます。
	// throws InsufficientMaterialsException
	;

	/**
	 * 在庫棚に在庫を補充します。 材料がなくなる前に補充できれば良い状態。
	 */
	void appendMaterials ();

	/**
	 * 在庫棚の状況を取得する機構です。 これは流石に集中管理版でなんとかなるものではありません。 従業員が現認して、状況を確認しなければなりません。
	 * 
	 * @return ShelfStatus で定義された在庫棚の充足状況。
	 */
	ShelfStatus getStatus ();

	/**
	 * 材料の在庫状況を保持する列挙型です。IDisposable の消耗品の状態同様、大きく 3 段階に分類されています。 1. Sufficient
	 * - 十分な状態。 2. Insufficient - 閾値を越えて「足らないな」と目視でわかる状態。 3. Zero - なくなってしまった状態。
	 * この場合、ラインは動いていても全く材料は供給されません。
	 * 
	 * @author Rcsvpg.org
	 * 
	 */
	enum ShelfStatus {

		/**
		 * 在庫が十分にある状態。問題なく製造工程が運用できています。
		 */
		Sufficient,

		/**
		 * 閾値を越えて「足らないな」と目視でわかる状態。
		 */
		Insufficient,

		/**
		 * なくなってしまった状態。 この場合、ラインは動いていても全く材料は供給されません。
		 */
		Zero
	}
}
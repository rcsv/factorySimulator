package org.rcsvp.pls.disposable;

/**
 * IDisposable インターフェイスは、各工程で使用する道具、治具等を表現しています。
 * 特に磨耗、損耗、劣化、減衰の影響で本来の加工品質に耐えられなくなってくる道具が登録対象です。
 * 
 * @author Rcsvpg.org
 * 
 */
public interface IDisposable {

	/**
	 * 破損までの一般的な警告閾値を設定しています。 使用限界数が 10 回以下のような、 あまりにも低いツールの場合はこの閾値を使用した場合、
	 * 必ず破損まで使用してしまいます。
	 */
	float threshold = 0.125f;

	/**
	 * 道具は使われる運命にあります。 一時は破損した場合に何らかの例外を投げることを考えていましたが、
	 * 設計が複雑になるので止めにします...と思いましたが、やっぱり投げないと話がおかしくなりそうなので 投げることにします。
	 * 
	 * @throws ToolWreckedException
	 *             ツールが破損したことを伝える例外。
	 */
	void use() throws ToolWreckedException;

	/**
	 * 道具が損耗上限に達した際には、該当する消耗品は交換するか、修復するかをする必要があります。
	 * 道具自体はアラートを上げることは無いので、時々見に行くしかないのかもしれません。
	 */
	void repair();

	/**
	 * 消耗品の劣化具合を検査する機構です。 使用している工程が定期的に監視しているような状態になればいいと考えます。
	 * 
	 * @return DisposableStatus で定義された消耗品の状態。
	 */
	DisposableStatus getStatus();

	/**
	 * 消耗品の状態を保持する列挙型です。消耗品の状態は大きく３段階に分かれています。 1. Work -
	 * 通常使用に問題が無い状態。この状態を見た場合、関係者は誰も動きません。 2. Warn -
	 * 仕様上限となる閾値を越え、もうすぐ壊れる可能性があることを示唆している状態です。 3. Wrecked -
	 * 壊れた状態です。この状態になってしまった場合、消耗品は交換しなければなりません。 またこの状態で工程を動かしていた場合、その間加工した中間品は全て
	 * NG となってしまいます。
	 * 
	 * @author Rcsvpg.org
	 * 
	 */
	enum DisposableStatus {
		/**
		 * 通常使用に問題が無い状態。この状態を見た場合、関係者は誰も動きません。
		 */
		Work,

		/**
		 * 仕様上限となる閾値を越え、もうすぐ壊れる可能性があることを示唆している状態です。
		 */
		Warn,

		/**
		 * 壊れた状態です。この状態になってしまった場合、消耗品は交換しなければなりません。
		 * またこの状態で工程を動かしていた場合、その間加工した中間品は全て NG となってしまいます。
		 */
		Wrecked
	}
}
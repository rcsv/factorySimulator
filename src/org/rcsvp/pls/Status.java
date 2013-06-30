package org.rcsvp.pls;

/**
 * Production Line Simulator で使用する状態を、類型として扱います。
 * 
 * @author Tomohiro AWANE <Awane.Tomohiro@me.com>
 *
 */
public enum Status {

  /**
	 * あらゆる状態について、準備 OK な場合、このステータスが設定されます。一日の始めは Ready。
	 * ラジオ体操が終われば、Ready から一つ一つ生産に入ります。
	 */
	Ready,
	
	//
	// Stop 系のステータス
	//
	/**
	 * <strong>簡単に言うと通常停止。</strong>ノルマとして設定された生産量を既に生産したため、
	 * 操業を停止するような場合、この Stop Normally という状態が与えられ、shutdown() されま
	 * す。
	 */
	StopNormally,
	
	/**
	 * <strong>誰かの所為で、一旦止まっています</strong>。「えー。誰か知らんけどー。停まれって
	 * いうからー。」という状態。生産ラインでは、任意の Procedure が止まった場合、自分も停止して
	 * いるが「原因は自分ではない！」と表明している状態を指しています。
	 */
	StopToldAnother,
	
	/**
	 * <strong>「自分が原因で止まっている」</strong>ことを表現している状態。生産ラインの任意の
	 * 工程では、やれ治具が摩耗・損耗した、穴あけ加工したら穴の大きさが変わってきちゃった等。
	 * で、絡まって挟まって停止。なんてことになるんだと思うのです。そんな時、この状態が与えられます。
	 */
	StopOwnCase,
	
	//
	// Wait 系のステータス
	//
	/**
	 * Wait という状態は、操業を停止していないが、何らかの原因で自分の前の工程を待っている状態です。
	 * 前工程や前の部品棚に材料が置いてない場合、もらえないので「<strong>ショボーン</strong>」
	 * となっている状態は <code>WaitInsufficientMaterial</code> となります。
	 */
	WaitInsufficientMaterial,
	
	//
	// Process 系
	//
	/**
	 * 作業やってる最中。
	 */
	Process,
	
	/**
	 * Using Disposable item into material
	 */
	ProcessUseDisposable,
	
	/**
	 * Validate process against material.
	 */
	ProcessValidation,
}

package org.rcsvp.pls.validation;

import org.rcsvp.pls.IMaterial;

/**
 * IVerify は、製造の各工程の段階で保証すべきチェック項目をオブジェクト化するためのインターフェイスです。
 * 製造ラインで施された加工は長さ、重さ、摺動抵抗、圧入や目視でしか判らない項目などあらゆる観点から検証されます。
 * 各工程で様々な検証項目を設定できるように、インターフェイス化しています。
 * 
 * @author Rcsvpg.org
 * 
 */
public interface IVerify {

	/**
	 * インターフェイスを実装した内容に基いて、工程を回ってきた加工品を検証します。 検証項目に基いて、問題がなければ、VerifyResult.OK
	 * が、問題があれば VerifyResult.NG が戻り値として帰ってきます。IMaterial
	 * は受けた検証項目と結果を、全て内部で保持していることを想定しています。
	 * 
	 * @param material
	 *            検証を受ける加工品単体。
	 * @return 問題がなければ、VerifyResult.OK、問題があれば、VerifyResult.NG が戻ります。
	 * @see org.rcsvp.pls.validation.IVerify.VerifyResult
	 */
	VerifyResult verify(IMaterial material);

	/**
	 * 検証項目が保有する公差の情報を取得します。
	 * 
	 * @return ITolerance インターフェイスを実装した、公差に関する情報
	 */
	ITolerance getTolerance();

	/**
	 * VerifyResult は当該検査項目の結果を返します。OK / NG の 2 種類のみです。
	 * 
	 * @author rcsv
	 * 
	 */
	enum VerifyResult {
		/**
		 * 検証の結果、問題がなかったことを表現します。
		 */
		OK,

		/**
		 * 検証の結果、問題があったことを表現します。
		 */
		NG
	}
}
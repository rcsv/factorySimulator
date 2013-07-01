package org.rcsvp.pls.validation;

import org.rcsvp.pls.Logger;
import org.rcsvp.pls.material.IMaterial;

/**
 * 検証を行うオブジェクトは、かなりのレベルでやることは同じなので、 殆ど同じ場合はこの概要クラスを拡張すると実装しやすくなります。
 * 
 * @author Rcsvp.org
 * 
 */
public abstract class VerifyAbst implements IVerify {

	/**
	 * 実測値を持たせます。
	 */
	public volatile double actualMeasure = 0;

	/**
	 * 検証項目名を保持しておきます。
	 */
	private String name;

	/**
	 * 公差に関する情報を保持します。
	 */
	private ITolerance tolerance;

	/**
	 * コンストラクタでは検証項目を入力する必要があります。
	 * 
	 * @param name
	 */
	public VerifyAbst(String name, ITolerance tolerance) {
		this.name = name;
		this.tolerance = tolerance;
	}

	@Override
	public VerifyResult verify(IMaterial material) {
		Logger.debugWrite(this.name + "Start Verification.");

		VerifyResult stat = verifyDetails(material);

		//
		// / TODO: まだどのように材料内に情報を保存しておくべきか考えていません。
		// 材料側のスタックに検査条件を入れてしまいます。
		//
		material.addVerificationStack(this);

		Logger.debugWrite(this.name + "Finish Verification. Result : " + stat);

		return stat;
	}

	/**
	 * verify ( IMaterial material ) 内部で実施する詳細なチェックを実装します。
	 * 
	 * @param material
	 * @return
	 */
	protected abstract VerifyResult verifyDetails(IMaterial material);

	@Override
	public ITolerance getTolerance() {
		return this.tolerance;
	}
}
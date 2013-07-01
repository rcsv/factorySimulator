package org.rcsvp.pls.material;

import org.rcsvp.pls.validation.IVerify;

/**
 * 製造ラインを流れる材料を表現しています。金属、樹脂など様々な材料が各工程で加工されていきます。
 * 
 * @author Rcsvpg.org
 * 
 */
public interface IMaterial {

	/**
	 * 各工程で受けた検査項目と、その検査結果を保持しておきます。
	 * 
	 * @param verify
	 */
	void addVerificationStack(IVerify verify);

	/**
	 * 各製造工程で、追加で材料を付与することを表現します。
	 * 
	 * @param subMaterial
	 *            連結対象となる、別の材料。
	 */
	void addAnotherMaterial(IMaterial subMaterial);

}
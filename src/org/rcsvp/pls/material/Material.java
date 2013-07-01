package org.rcsvp.pls.material;

import org.rcsvp.pls.Logger;
import org.rcsvp.pls.validation.IVerify;

/**
 * Material は、製造ライン、製造工程、在庫棚に配置される IMaterial インターフェイスの単純実装です。
 * 
 * @author rcsv
 * 
 */
public class Material implements IMaterial {

	public Material() {
	}

	@Override
	public void addVerificationStack(IVerify verify) {
		Logger.debugWrite("call addVerificationStack");

		//
		// TODO: 現段階では綺麗な情報の渡し方ができないので、あとでロジックを組み立てることにします。
		//
	}

	@Override
	public void addAnotherMaterial(IMaterial subMaterial) {
	}

}
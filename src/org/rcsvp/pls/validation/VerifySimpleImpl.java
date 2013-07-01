package org.rcsvp.pls.validation;

import java.util.Random;

import org.rcsvp.pls.material.IMaterial;

/**
 * IVerify インターフェイスの単純実装です。 特に公差条件は見ておらず、乱数を発生させて 99% 程度の確立で検査が通る仕様になっています。
 * 
 * @author Rcsvpg.org
 * 
 */
public class VerifySimpleImpl extends VerifyAbst {

	public VerifySimpleImpl(String name, ITolerance tolerance) {
		super(name, tolerance);
	}

	@Override
	protected VerifyResult verifyDetails(IMaterial material) {

		Random rand = new Random();
		VerifyResult ret = null;
		actualMeasure = rand.nextDouble();

		if (this.actualMeasure < 0.98) {
			ret = VerifyResult.OK;
		} else {
			ret = VerifyResult.NG;
		}

		return ret;
	}

}
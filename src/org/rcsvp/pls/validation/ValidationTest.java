package org.rcsvp.pls.validation;

import org.rcsvp.pls.Logger;
import org.rcsvp.pls.Material;
import org.rcsvp.pls.validation.ITolerance.ToleranceType;

public class ValidationTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Logger.lv = Logger.VerboseLevel.Debug;

		Logger.debugWrite("this is debug test.");
		IVerify x = new VerifySimpleImpl("チェック項目", new ToleranceImpl(
				ToleranceType.Scantling, 40.0, 0.4));

		for (int i = 0; i < 200; i++) {
			x.verify(new Material());
		}

		Logger.debugWrite("test finished.");
	}
}
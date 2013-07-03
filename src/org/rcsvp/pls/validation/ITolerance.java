package org.rcsvp.pls.validation;

/**
 * ITolerance は IVerify で実装した検証項目に関しての許容される範囲を情報として保持するための
 * インターフェイスです。最大値、最小値となる長さ、重さ、荷重が入ります。 基準値と公差、いずれも double で表現しています。
 * 
 * @author Rcsvp.org
 * 
 */
public interface ITolerance {

	/**
	 * 許容値の範囲を返します。
	 * 
	 * @return 許容値の公差
	 */
	double getToleranceRange();

	/**
	 * 公差の基準となる数値を返します。
	 * 
	 * @return 許容値の基準値
	 */
	double getOffset();

	/**
	 * 公差のタイプによって、内部に保有している情報の扱い方が異なる場合があるため、列挙型で定義しています。 それぞれ、寸法、幾何、電気抵抗の種類です。
	 * 
	 * @author Rcsvp.org
	 * 
	 */
	enum ToleranceType {
		/**
		 * 寸法に関する公差。寸法のズレがどのぐらいまで許せるかどうかについての場合は Scantling を設定する。
		 */
		Scantling,

		/**
		 * 幾何公差。主軸に対して何度ずれているのかを表現する。
		 */
		Geometric,

		/**
		 * 電気抵抗の公差。任意のオームに対して、どのパーセンテージに収まっているのかどうかを表現する。
		 */
		Electrical,
	}

	/**
	 * 公差の種別を返します。
	 * 
	 * @return 公差の種別。寸法 (ToleranceType.Scantling)、幾何
	 *         (ToleranceType.Geometric)、電気抵抗（ToleranceType.Electrical）等。
	 * @see org.org.rcsvp.pls.validation.ITolerance.ToleranceType
	 */
	ToleranceType getType();
}
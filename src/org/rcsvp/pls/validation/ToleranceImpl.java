package org.rcsvp.pls.validation;

/**
 * 公差を保持する ITolerance インターフェイスの単純実装です。情報を入れる箱なので、getter/setter を持つだけのモデル扱いです。
 * 
 * @author Rcsvpg.org
 * 
 */
public class ToleranceImpl implements ITolerance {

	/**
	 * 公差タイプを保持する。
	 */
	private ToleranceType type;

	private double offset;

	private double tolerance;

	/**
	 * 当該コンストラクタは公差の種別、基準値と公差の３種類の情報を保持します。
	 * 
	 * @param type
	 *            ToleranceType で与えられる公差タイプ。寸法、幾何、電気抵抗の３種類。
	 * @param offset
	 *            基準値。
	 * @param tolerance
	 *            公差。
	 */
	public ToleranceImpl(ToleranceType type, double offset, double tolerance) {
		this.type = type;
		this.offset = offset;
		this.tolerance = tolerance;
	}

	@Override
	public double getToleranceRange() {
		return tolerance;
	}

	@Override
	public double getOffset() {
		return this.offset;
	}

	@Override
	public ToleranceType getType() {
		return this.type;
	}

}
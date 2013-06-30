package org.rcsvp.pls;

public class ControlCenter {

  private static ControlCenter me = new ControlCenter() ;

	public static final int defaultTimeScale = 1000 ;

	private int variable_timescale = 1000 ;
	
	/**
	 * コンストラクタは封印。
	 */
	private ControlCenter() {
	}

	/**
	 * シングルトン。
	 * 
	 * @return
	 */
	public static ControlCenter getInstance() {
		return me ;
	}

	/**
	 * シミュレーションの速度を統一するため、関連クラスは全てこのタイムスケールに従って運動する。
	 * 
	 * @return 倍率。なんの設定もしないと、1000 になる。
	 */
	public int getTimeScale() {
		return this.variable_timescale ;
	}

	/**
	 * シミュレーションの速度を統一するため、関連クラスは全てこのタイムスケールに従って稼働する。
	 * 値は整数値。デフォルトは 1000（等速）。小さくすれば速くなり、大きくすれば遅くなる。
	 * 
	 * @param timeScale 時間の尺度。
	 */
	public void setTimeScale(int timeScale) {
		this.variable_timescale = timeScale ;
	}
	


}

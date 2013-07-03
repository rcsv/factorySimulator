package org.rcsvp.pls.util;

import java.util.Date;

/**
 * おもいきり潔い車輪の再開発として Logger を作ります。デバッグレベルから、エラーレベルまで 6
 * 種類のレベルに応じて吐き出します。低いレベルの情報を抑制する場合は lv を操作します。
 * 
 * @author Rcsvp.org
 * 
 */
public class Logger {

	public static void debugWrite(String msg) {
		write(msg, VerboseLevel.Debug);
	}

	public static void infoWrite(String msg) {
		write(msg, VerboseLevel.Info);
	}

	public static void noticeWrite(String msg) {
		write(msg, VerboseLevel.Notice);
	}

	public static void warnWrite(String msg) {
		write(msg, VerboseLevel.Warn);
	}

	public static void errorWrite(String msg) {
		write(msg, VerboseLevel.Error);
	}

	public static void abendWrite(String msg) {
		write(msg, VerboseLevel.Abend);
	}

	/**
	 * コンストラクタ自重。
	 */
	private Logger() {
	}

	/**
	 * べらべら度合いを変更するスイッチ。デフォルトはご注意レベルを表示します。。
	 */
	public static VerboseLevel lv = VerboseLevel.Notice;

	/**
	 * 共通部品。
	 * 
	 * @param msg
	 */
	private static void write(String msg, VerboseLevel x) {
		if (lv.ordinal() <= x.ordinal()) {
			System.out.println("[" + new Date() + "] " + msg);
		}
	}

	/**
	 * Logger クラスで使用するレベル。
	 * 
	 * @author Tomohiro AWANE <Awane.Tomohiro@me.com>
	 * 
	 */
	public enum VerboseLevel {

		/**
		 * Debug レベルでは設計を構想している最中にしか興味がわかないレベルの情報を提供します。
		 * およそどうでもいい情報はさっさと切ってしまいましょう。どうしても興味がわく場合は、 Logger.lv =
		 * Logger.VerboseLevel.Debug とかにします。
		 */
		Debug,

		/**
		 * Info レベルは、特にこれを見てどのスレッドを状況判断、意思決定の類を変更しないレベルの
		 * 情報を表示する場合に使用します。通常の場合は表示されません。これもデバッグレベルとまで
		 * はいかないものの、かなりどうでもいいレベルの情報が流れてきますのであしからず。
		 */
		Info,

		/**
		 * Notice レベルになると、この情報を見た何らかの人、機械、その他のインスタンスが判断し、 行動を変更します。
		 * この情報を直接見て動くような仕組みにするつもりはないので、あくまでシミュレーターを眺めている人用の情報提供です。
		 */
		Notice,

		/**
		 * Warn レベルは、意思決定どころか、シミュレーター内で働く人たちが困った状況に陥っている
		 * 場合に使用されます。この表示を見たスレッドは何らかの行動を変更する必要があります。また
		 * シミュレーター内のあらゆるインスタンスはこれが発生したら「慌てる」様に振る舞う必要がありそうです。
		 */
		Warn,

		/**
		 * Debug レベルではありませんが、シミュレーターの不具合で、意図しない振る舞いが発生した場合に表示します。
		 */
		Error,

		/**
		 * Debug レベルではありませんが、シミュレーターに復旧できない不具合が生じた時、異常終了と同時に表示させるべきレベルのものです。
		 */
		Abend,

		/**
		 * 何が何でも、ログは表示しません。
		 */
		None
	}
}
package org.rcsvp ;

import java.text.DateFormat ;
import java.text.SimpleDateFormat ;
import java.util.Date ;

/**
 * Re-develop java.util.logging.Logger (via only Console). It provides 6
 * severity level in this implementation. I see, I didn't know the existence of
 * java.util.logging.*. I just hate call a method getGlobal().
 * 
 * <h2>How to use this logger</h2> Call six static method like:
 * <code>Logger.debugWrite( "something" ) ;</code> when use these methods. To
 * filtering logging by using LogLevel, set {@link #lv} directory.
 * 
 * おもいきり潔く、Logger という車輪の再開発を行っています。デバッグレベルから、エラーレベルまで 6
 * 種類のレベルに応じて吐き出します。低いレベルの情報を抑制する場合は lv を操作します。java.util.logging.
 * を知らない訳ではないんです。ただひたすら getGlobal() とか、Properties とか、DateFormat
 * のために設定ファイルとかは遠慮したいのです。
 * 
 * @author Rcsvp.org
 * 
 */
public class Logger {

	/**
	 * Logger クラスで使用するレベル。
	 * 
	 * @author Tomohiro AWANE <Awane.Tomohiro@me.com>
	 * 
	 */
	public enum LogLevel {

		/**
		 * Debug レベルではありませんが、シミュレーターに復旧できない不具合が生じた時、異常終了と同時に表示させるべきレベルのものです。
		 */
		Abend,

		/**
		 * Debug レベルでは設計を構想している最中にしか興味がわかないレベルの情報を提供します。
		 * およそどうでもいい情報はさっさと切ってしまいましょう。どうしても興味がわく場合は、 Logger.lv =
		 * Logger.VerboseLevel.Debug とかにします。
		 */
		Debug,

		/**
		 * Debug レベルではありませんが、シミュレーターの不具合で、意図しない振る舞いが発生した場合に表示します。
		 */
		Error,

		/**
		 * Info レベルは、特にこれを見てどのスレッドを状況判断、意思決定の類を変更しないレベルの
		 * 情報を表示する場合に使用します。通常の場合は表示されません。これもデバッグレベルとまで
		 * はいかないものの、かなりどうでもいいレベルの情報が流れてきますのであしからず。
		 */
		Info,

		/**
		 * 何が何でも、ログは表示しません。
		 */
		None,

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
		Warn
	}

	/**
	 * べらべら度合いを変更するスイッチ。デフォルトはご注意レベルを表示します。。
	 */
	public static LogLevel lv = LogLevel.Notice ;

	public static void abendWrite(String msg) {
		write("[ABEND] " + msg, LogLevel.Abend) ;
	}

	public static void debugWrite(String msg) {
		write("[DEBUG] " + msg, LogLevel.Debug) ;
	}

	public static void errorWrite(String msg) {
		write("[ERROR] " + msg, LogLevel.Error) ;
	}

	public static void infoWrite(String msg) {
		write("[FINE ] " + msg, LogLevel.Info) ;
	}

	public static void noticeWrite(String msg) {
		write("[NOTE ] " + msg, LogLevel.Notice) ;
	}

	public static void warnWrite(String msg) {
		write("[WARN ] " + msg, LogLevel.Warn) ;
	}

	/**
	 * 共通部品。
	 * 
	 * @param msg
	 */
	private static void write(String msg, LogLevel x) {
		if (lv.ordinal() <= x.ordinal()) {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") ;
			System.out.println("[" + df.format(new Date()) + "] " + msg) ;
		}
	}

	/**
	 * コンストラクタ自重。
	 */
	private Logger() {
	}
}
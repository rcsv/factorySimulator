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
 * @author Rcsvp.org
 * 
 */
public class Logger {

	/**
	 * LogLevel have 2 role. one case, a level of error priority
	 */
	public enum LogLevel {

		/**
		 * <h2>Level. 1</h2> Debug.
		 * <p>
		 * Static debugging / messaging class for factorySimulator. This Number
		 * defines a set of static methods that can be called to produce
		 * debugging messages. Messages have an associated "debug level" and
		 * messages below the current setting are not displayed.
		 * </p>
		 */
		Debug,

		/**
		 * <h2>Level. 2</h2> Information.
		 * <p>
		 * Static information / messaging class for Factory Simulator. This
		 * level defies a set of static methods that can be called to produce
		 * information level messages. Message have an associated
		 * "information level" and messages below the current setting are not
		 * displayed.
		 * </p>
		 */
		Info,

		/**
		 * <h2>Level. 3</h2> Notice, Notification.
		 * <p>
		 * Static notification / messaging class for Factory simulator. This
		 * number level defines a set of static methods that can be called to
		 * produce notification level messages. Message have an associated
		 * "notify level" and messages below the current setting are not
		 * displayed.
		 * </p>
		 * </p>
		 */
		Notice,

		/**
		 * <h2>Level. 4</h2> Warn.
		 * <p>
		 * Static warn / messaging instance for Factory Simulator. This number
		 * level defines a set of static methods that can be called to produce
		 * warning level messages. Message have an associated "warn level" and
		 * messages below the current setting are not displayed.
		 * </p>
		 */
		Warn,

		/**
		 * <h2>Level. 5</h2> Error.
		 * <p>
		 * Static Error / messaging instance for Factory Simulator. This number
		 * level defines a set of static methods that can be called to procedure
		 * error level messages. Message have an accosiated "error level" and
		 * message below the current setting are not displayed.
		 * </p>
		 */
		Error,

		/**
		 * <h2>Level. 6</h2> Abend, FINISH normally.
		 * <p>
		 * It may RuntimeException from Factory Simulator.
		 * </p>
		 * 
		 */
		Abend,

		/**
		 * <h2>Level. XX</h2> NONE. don't display.
		 * <p>
		 * This Level Surpress any message via Logger.
		 * </p>
		 */
		None
	}

	/**
	 * 
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
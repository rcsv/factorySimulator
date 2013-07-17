/*
 * Copyright &copy; ${year} Rcsvp.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.rcsvp ;

import java.io.PrintStream ;
import java.text.DateFormat ;
import java.text.SimpleDateFormat ;
import java.util.Date ;

/**
 * Re-develop java.util.logging.Logger (via only Console). It provides six
 * severity level in this implementation. I see, I didn't know the existence of
 * java.util.logging.*. I just hate call a method including getGlobal as far.
 * <h2>How to use this logger</h2> Call six static method like:
 * <code>Logger.debug( "something" ) ;</code> when use these methods. To
 * filtering logging level by call setLevel.
 * 
 * @author Rcsvp.org
 * @date Jul 6, 2013
 * @since 0.9
 */
public class Logger {

	/**
	 * default level set Notice.
	 */
	private static Level level = Level.Notice ;

	/**
	 * specify PrintStream. It is console default.
	 */
	private static PrintStream output = System.out ;

	/**
	 * Enumeration Level have two roles. Once case, a level of message priority,
	 * other hands, for message filtering level.
	 * 
	 * @author Rcsvp.org
	 * @date Jul 6, 2013
	 * @since 0.9
	 */
	public enum Level {
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
		 */
		Notice,

		/**
		 * <h2>Level. 4</h2> Warning.
		 */
		Warning,

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
		 */
		Abend,

		/**
		 * <h2>Level. XX</h2> NONE. don't display.
		 * <p>
		 * This Level means suppress any message via Logger.
		 * </p>
		 */
		None
	}

	/**
	 * print debug message.
	 * 
	 * @param msg
	 *            a message to display
	 */
	public static void debug(String msg) {
		write("[DEBUG] " + msg, Level.Debug) ;
	}

	/**
	 * print information message.
	 * 
	 * @param msg
	 *            a message to display
	 */
	public static void info(String msg) {
		write("[INFO ] " + msg, Level.Info) ;
	}

	/**
	 * print notice message.
	 * 
	 * @param msg
	 *            a message to display
	 */
	public static void notice(String msg) {
		write("[NOTE ] " + msg, Level.Notice) ;
	}

	/**
	 * print warning message
	 * 
	 * @param msg
	 *            a message to display
	 */
	public static void warning(String msg) {
		write("[WARN ] " + msg, Level.Warning) ;
	}

	/**
	 * print error message
	 * 
	 * @param msg
	 *            a message to display
	 */
	public static void error(String msg) {
		write("[ERROR] " + msg, Level.Error) ;
	}

	/**
	 * print abnormal finished message (Could you really display it?).
	 * 
	 * @param msg
	 *            a message to display
	 */
	public static void abend(String msg) {
		write("[ABEND] " + msg, Level.Abend) ;
	}

	/**
	 * Common method send string to java.io.PrintStream
	 * 
	 * @param msg
	 * @param x
	 */
	private static void write(String msg, Level x) {
		if (Logger.level.ordinal() <= x.ordinal()) {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") ;
			Logger.output.println("[" + df.format(new Date()) + "] " + msg) ;
		}
	}

	/**
	 * set level for message filter.
	 * 
	 * @param level
	 *            a level for message filtering.
	 */
	public static void setLevel(Level level) {
		Logger.level = level ;
	}

	/**
	 * Suppress constructor.
	 */
	private Logger() {
	}
}
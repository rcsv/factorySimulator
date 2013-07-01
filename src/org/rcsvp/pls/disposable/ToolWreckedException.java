package org.rcsvp.pls.disposable;

/**
 * IDisposable インターフェイスで実装した消耗品が破損したことを伝える例外です。
 * この例外を受け取った場合、製造工程に何らかの不備が発生しています。
 * 
 * @author rcsv
 * 
 */
public class ToolWreckedException extends Exception {

	public ToolWreckedException(String message) {
		super(message);
	}

	/**
	 * serializable なので。
	 */
	private static final long serialVersionUID = -2916458182732374805L;

}
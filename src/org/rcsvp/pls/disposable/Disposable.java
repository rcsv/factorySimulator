package org.rcsvp.pls.disposable;

import org.rcsvp.pls.IWrappable;
import org.rcsvp.pls.util.Logger;

public class Disposable implements IDisposable, IWrappable {

	/**
	 * ツールの使用限界を大体３段階で表現したものです。
	 */
	private DisposableStatus status;

	/**
	 * ツールの名称です。
	 */
	private String name;

	/**
	 * 使用できる限界の回数です。
	 */
	private int limit;

	/**
	 * 現時点で何度使用したのかを覚えておきます。
	 */
	private int count;

	/**
	 * Disposable コンストラクタでは、ツールの名称と使用できる回数を設定して開始します。
	 * 
	 * @param name
	 *            ツールの名称
	 * @param limit
	 *            使用限界数
	 */
	public Disposable(String name, int limit) {
		this.name = name;
		this.limit = limit;
		this.status = DisposableStatus.Work;
		this.count = 0;
	}

	@Override
	public void use() throws ToolWreckedException {
		Logger.debugWrite(this.name + " start using.");

		//
		// 一度、道具を使用して、カウントを一つまわすだけの簡単なお仕事です。
		//
		this.count++;
		if (count >= (1 - threshold) * limit) {

			//
			// 使いすぎた場合は ToolWreckedException 例外が送信されます。
			//
			if (count >= limit) {
				this.status = DisposableStatus.Wrecked;
				Logger.errorWrite(this.name + " Wrecked!");
				throw new ToolWreckedException(this.name);
			}

			//
			// 閾値を過ぎている場合は、状態が警告となりそろそろ怪しい状態となります。
			//
			this.status = DisposableStatus.Warn;
		}

		Logger.debugWrite(this.name + " finish using. rest: "
				+ (this.limit - this.count));
	}

	@Override
	public void repair() {
		this.count = 0;
		this.status = DisposableStatus.Work;
	}

	@Override
	public DisposableStatus getStatus() {
		return this.status;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public void updateName(String givenName) {
		this.name = givenName + ":" + this.name;
	}

	@Override
	public void register(IWrappable childNode) {
		//
	}
}

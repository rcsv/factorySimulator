package org.rcsvp.pls.material;

import org.rcsvp.pls.util.Logger;

public class Shelf implements IShelf {

	/**
	 * 在庫の状況を大体３段階で表現したものです。
	 */
	private ShelfStatus status;

	/**
	 * 在庫棚の名称です。なんとなく一意であるとうれしいです。
	 */
	private String name;

	/**
	 * 一度に積み上げておくことが出来る大体の在庫数です。
	 */
	private long limit;

	/**
	 * 現在、いくつの在庫が残っているのかを覚えておきます。
	 */
	private long count;

	/**
	 * 一意の名称と在庫の棚の大きさが必要なコンストラクタです。
	 * 
	 * @param name
	 *            在庫棚の名称。
	 * @param limit
	 *            在庫棚の大きさ。
	 */
	public Shelf(String name, long limit) {
		this.name = name;
		this.limit = this.count = limit;
		this.status = ShelfStatus.Sufficient;
	}

	@Override
	public IMaterial getMaterial() {
		Logger.debugWrite(this.name + " start supplying.");

		//
		// ただ Material を new するだけの簡単なお仕事です。
		//
		this.count--;

		if (this.count <= threshold * limit) {

			if (count <= 0) {
				this.status = ShelfStatus.Zero;
				Logger.errorWrite(this.name + " material zero!");
				//
				// throw?
				//
				// 在庫がない、と返事する。つまり return null ;
				return null;
			}
			//
			// 在庫が切れ始めてきたら、
			//
			this.status = ShelfStatus.Insufficient;
			return new Material();
		}

		return new Material();
	}

	@Override
	public void appendMaterials() {
		this.count += limit; // 在庫をいまある数から、更に積み上げ。
	}

	@Override
	public ShelfStatus getStatus() {
		return this.status;
	}

}
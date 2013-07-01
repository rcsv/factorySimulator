package org.rcsvp.pls;

import java.util.*;

import org.rcsvp.pls.disposable.IDisposable;
import org.rcsvp.pls.material.IMaterial;
import org.rcsvp.pls.material.IShelf;
import org.rcsvp.pls.validation.IVerify;

/**
 * Procedure クラスは IProcedure インターフェイスの単純実装です。
 * 
 * @author Rcsvpg.org
 */
public class Procedure implements IProcedure, IShelf {

	/**
	 * 一意の名前。
	 */
	private String name;

	/**
	 * 自分の名前を返します。
	 */
	public String toString() {
		return this.name;
	}

	/**
	 * 在庫棚を保持するための連想配列。 名前参照で一気に飛びやすくするための、Key Value ペア配列での実装。
	 */
	private Map<String, IShelf> shelves;

	/**
	 * 使用する消耗品を保持するための連想配列。
	 */
	private Map<String, IDisposable> dispos;

	/**
	 * 検証項目を保持するためのリスト。全て総当たりで検証する事になるため、Map ではなく List で実装をしています。
	 */
	private List<IVerify> verify;

	/**
	 * 前工程の見なし在庫棚シリーズ。
	 */
	private ShelfStatus shelfstat;

	/**
	 * 前工程の見なし在庫棚シリーズ。
	 */
	private Queue<IMaterial> materialQueue;

	/**
	 * 一意の名称を登録してもらうコンストラクタ。
	 * 
	 * @param name
	 *            工程のユニークな名前
	 */
	public Procedure(String name) {

		shelves = new HashMap<String, IShelf>();
		dispos = new HashMap<String, IDisposable>();

		verify = new ArrayList<IVerify>();

		//
		// 残念ながら、通常の在庫棚と違い、工程では在庫枯渇がデフォルトとなる。
		//
		this.shelfstat = ShelfStatus.Zero;

		this.materialQueue = new LinkedList<IMaterial>();
	}

	@Override
	public void run() {
		//
		// TODO: real to do list
		//
	}

	@Override
	public void register(IShelf shelf) {
		shelves.put(shelf.toString(), shelf);
	}

	@Override
	public void register(IDisposable dispo) {
		dispos.put(dispo.toString(), dispo);
	}

	@Override
	public void register(IVerify verify) {
		this.verify.add(verify);
	}

	@Override
	public IMaterial getMaterial() {
		return this.materialQueue.poll(); // IMaterial インターフェイスを実装した材料が。
	}

	@Override
	public void appendMaterials() {
		Logger.debugWrite(this.name + " uninteded method called.");
	}

	@Override
	public ShelfStatus getStatus() {
		return this.shelfstat;
	}

}

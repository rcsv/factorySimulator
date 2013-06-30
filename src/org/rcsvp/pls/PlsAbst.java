package org.rcsvp.pls;

public abstract class PlsAbst implements IPls {

  /**
	 * Runnable stop flag.
	 */
	protected volatile boolean done ;
	
	/**
	 * 名前。ユニークである。
	 */
	protected String name ;
	
	/**
	 * 一意の名前を返す。
	 */
	public String toString() { return this.name ; }

	/**
	 * 状態を保持する。
	 */
	protected Status status ;
	
	public PlsAbst ( String name ) {
		this.name = name ;
		this.status = Status.Ready ;
		this.done = false ;
	}
	
	@Override
	public Status getStatus() {
		return this.status ;
	}

	@Override
	public void shutdown(Status status) {
		this.done = true ;
	}

	@Override
	public void updateName(String nameForInsert) {
		this.name = nameForInsert + ":" + this.name ;
	}

}

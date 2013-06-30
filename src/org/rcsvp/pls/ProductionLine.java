package org.rcsvp.pls;

import java.util.* ;

public class ProductionLine extends PlsAbst implements IProductionLine {

  /**
	 * 生産ラインを構成する製造工程を保持するための hashmap MT-safe じゃないような気がする。
	 * よって後で構成買えなきゃダメなんだよね。multi-thread 書いた事無いから難しいね。
	 */
	private HashMap<String, IProcedure> procs ;
	
	private int tactTime ;
	
	public ProductionLine ( String name, int tactTime ) {
		super( name ) ;
		
		this.procs = new HashMap<String, IProcedure>() ;
		
		this.tactTime = tactTime ;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register(IProcedure proc) {
		procs.put( proc.toString(), proc ) ;
	}

}

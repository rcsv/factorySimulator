package org.rcsvp.pls;

import java.util.* ;

/**
 * 工場を表現しています。
 * 
 * @author Tomohiro AWANE <Awane.Tomohiro@me.com>
 *
 */
public class Factory extends PlsAbst implements IFactory {

  private HashMap<String,IProductionLine> lines ;
	private HashMap<String,ISurveillance> people ;
	private HashMap<String,IAgv> agvs ;
	
	/**
	 * 工場を表現するためのコンストラクタです。
	 * 
	 * @param name 工場を表現するための名称。一意である必要があります。今の所影響ないけど。
	 */
	public Factory( String name ) {
		super( name ) ;
		lines = new HashMap<String, IProductionLine>() ;
		people = new HashMap<String, ISurveillance>() ;
		agvs = new HashMap<String, IAgv>() ;
		
	}
	
	@Override
	public void shutdown( Status status ) {
		//
		// 理由が全うであれば、停止します。
		//
		if( status == Status.StopNormally ) {
			this.done = true ;
		} else {
			//
			// 理由が全うではない場合、どうすればいいんでしょう。
			//
			this.status = Status.StopToldAnother ;
			this.done = true ; // 現段階では一旦止めます。
		}
		
	}

	@Override
	public void updateName(String nameForInsert) {
		this.name = nameForInsert + ":" + this.name ;
	}

	@Override
	public void run() {
		
		//
		// Initialize
		//
		ThreadGroup threadprod = new ThreadGroup("productionLine");
		Iterator<IProductionLine> iLines = lines.values().iterator() ;
		while ( iLines.hasNext() ) {
			new Thread( threadprod, iLines.next() ).start() ;
		}
		/*
		ThreadGroup threadpoeple = new ThreadGroup( "people" ) ;
		Iterator<ISurveillance> iPeople = people.values().iterator() ;
		while ( iPeople.hasNext() ) {
			new Thread ( threadpoeple, iPeople.next() ) ;
		}
		
		ThreadGroup threadagv = new ThreadGroup( "agv" ) ;
		Iterator<IAgv> iAgv = agvs.values().iterator() ;
		while ( iAgv.hasNext() ) {
			new Thread ( threadagv, iAgv.next() ) ;
		}
		*/
		while ( !done ) {
			try {
				Thread.sleep( 500 ) ;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	


	@Override
	public void register(IProductionLine line) {
		lines.put( line.toString(), line ) ;
	}

	@Override
	public void register(ISurveillance person) {
		people.put( person.toString(), person ) ;
	}

	@Override
	public void register(IAgv agv) {
		agvs.put(agv.toString(), agv ) ;
	}

}

package org.rcsvp.factory ;

import java.util.* ;

import org.rcsvp.Logger ;
import org.rcsvp.factory.common.IDisposable ;
import org.rcsvp.factory.common.IMaterial ;
import org.rcsvp.factory.common.IVerify ;

public class Material implements IMaterial {

	private String name ;

	private Stack<IMaterial> bom ;
	
	private Queue<IDisposable> fabricHistory ;
	
	private Queue<IVerify> verifyHistory ;
	

	public Material(String name) {
		this.name = name ;
		this.bom = new Stack<IMaterial>() ;
		this.fabricHistory = new LinkedList<IDisposable>() ;
		this.verifyHistory = new LinkedList<IVerify>() ;
	}

	@Override
	public String toString() {
		return this.name ;
	}

	@Override
	public void append(IMaterial other) {
		Logger.debugWrite( this.name + " : append other material" + other.toString());
		this.bom.push(other) ;
	}

	@Override
	public boolean fabricate(IDisposable tools) {
		Logger.debugWrite( this.name + " : fabricated by " + tools.toString());
		this.fabricHistory.add(tools);
		return tools.use(this) ;
		
	}
	
	@Override
	public boolean validate( IVerify verification ) {
		Logger.debugWrite( this.name + " : verify about " + verification);
		this.verifyHistory.add(verification);
		return verification.check(this) ;
	}
}
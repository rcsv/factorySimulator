package org.rcsvp.factory.impl ;

import org.rcsvp.factory.IExport ;

public class Export implements IExport {

	private String name ;

	public Export(String name) {
		this.name = name ;
	}

	@Override
	public String toString() {
		return this.name ;
	}
}

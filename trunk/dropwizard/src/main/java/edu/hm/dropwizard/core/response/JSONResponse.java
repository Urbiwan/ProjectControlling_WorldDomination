package edu.hm.dropwizard.core.response;

public class JSONResponse {
	private String foo;
	
	public JSONResponse(String foo) {
		this.foo = foo;
	}
	
	public String getFoo() {
		return foo;
	}
}

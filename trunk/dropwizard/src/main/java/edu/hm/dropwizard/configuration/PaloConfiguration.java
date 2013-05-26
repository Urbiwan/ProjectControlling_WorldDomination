package edu.hm.dropwizard.configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaloConfiguration {
	@NotEmpty
	@JsonProperty
	private String location;
	
	@NotEmpty
	@JsonProperty
	private int port;
	
	@NotEmpty
	@JsonProperty
	private String loginName;
	
	@NotEmpty
	@JsonProperty
	private String loginPwd;
	
	public String getLocation() {
		return location;
	}
	
	public int getPort() {
		return port;
	}
	
	public String LoginName() {
		return loginName;
	}
	
	public String LoginPwd() {
		return loginPwd;
	}
}

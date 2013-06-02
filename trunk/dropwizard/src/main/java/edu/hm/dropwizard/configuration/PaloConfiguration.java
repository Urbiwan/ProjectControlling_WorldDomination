package edu.hm.dropwizard.configuration;

import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaloConfiguration extends Configuration {
	@NotEmpty
	@JsonProperty("location")
	private String location;
	
	@NotEmpty
	@JsonProperty("port")
	private int port;
	
	@NotEmpty
	@JsonProperty("loginName")
	private String loginName;
	
	@NotEmpty
	@JsonProperty("loginPwd")
	private String loginPwd;
	
	public String getLocation() {
		return location;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
	public String getLoginPwd() {
		return loginPwd;
	}

    public PaloConfiguration() {

    }

    public PaloConfiguration(String location, int port, String loginName, String loginPwd) {
        this.location = location;
        this.port = port;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
    }
}

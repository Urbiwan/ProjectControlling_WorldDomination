package edu.hm.dropwizard.configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class DropwizardConfiguration extends Configuration {
	@NotEmpty
	@JsonProperty
	private PaloConfiguration paloConf;
	
	public PaloConfiguration getPaloConfiguration() {
		return paloConf;
	}
}

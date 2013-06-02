package edu.hm.dropwizard.configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class DropwizardConfiguration extends Configuration {
	@JsonProperty("palo")
	private PaloConfiguration paloConf;
	
	public PaloConfiguration getPaloConfiguration() {
		return paloConf;
	}

    public DropwizardConfiguration() {

    }

    public DropwizardConfiguration(PaloConfiguration palo) {
        this.paloConf = palo;
    }
}

package edu.hm.dropwizard;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import edu.hm.dropwizard.configuration.DropwizardConfiguration;

public class ComputeService extends Service<DropwizardConfiguration>{
	public static void main(String[] args) throws Exception {
		new ComputeService().run(args);
	}
	
	
	@Override
	public void initialize(Bootstrap<DropwizardConfiguration> bootstrap) {
		bootstrap.setName("compute");
	}

	@Override
	public void run(DropwizardConfiguration configuration, Environment environment)
			throws Exception {
		environment.addResource(new edu.hm.dropwizard.resources.ComputeResource());
		//TODO ADD HEALTHCHECK!
		//environment.addHealthCheck(null);
	}

}

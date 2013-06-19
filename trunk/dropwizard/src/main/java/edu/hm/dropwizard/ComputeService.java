package edu.hm.dropwizard;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import com.yammer.metrics.core.HealthCheck;
import edu.hm.dropwizard.configuration.DropwizardConfiguration;
import edu.hm.dropwizard.configuration.PaloConfiguration;
import edu.hm.dropwizard.health.ComputeHealth;
import edu.hm.dropwizard.resources.simple.SimpleResource;
import edu.hm.palo.IPaloControl;
import edu.hm.palo.PaloMock;

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
        IPaloControl palo = new PaloMock();

		environment.addResource(new edu.hm.dropwizard.resources.ComputeResource(palo));
        environment.addResource(new SimpleResource());

		environment.addHealthCheck(new ComputeHealth(palo));
	}

}

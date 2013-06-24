package edu.hm.dropwizard;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import edu.hm.dropwizard.configuration.DropwizardConfiguration;
import edu.hm.dropwizard.health.PaloAvailable;
import edu.hm.dropwizard.resources.ComputeResource;
import edu.hm.dropwizard.resources.DownloadResource;
import edu.hm.dropwizard.resources.files.StaticResource;
import edu.hm.dropwizard.resources.UploadResource;
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

        //To supply index.html and other files needed.
        environment.addResource(new StaticResource());
        //To upload and download files from dropwizard.
        environment.addResource(new UploadResource(palo));
        environment.addResource(new DownloadResource(palo));

        //The logic.
		environment.addResource(new ComputeResource(palo));

		environment.addHealthCheck(new PaloAvailable(palo));
	}

}

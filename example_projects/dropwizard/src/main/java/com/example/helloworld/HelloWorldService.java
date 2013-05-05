package com.example.helloworld;

import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class HelloWorldService extends Service<HelloWorldConfiguration> {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		new HelloWorldService().run(args);
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		bootstrap.setName("hello-world");
	}

	@Override
	public void run(HelloWorldConfiguration configuration, Environment env)
			throws Exception {
		final String template = configuration.getTemplate();
		final String defaultName = configuration.getDefaultName();
		
		env.addResource(new HelloWorldResource(template, defaultName));
		env.addHealthCheck(new TemplateHealthCheck(template));
	}

}

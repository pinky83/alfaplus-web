package org.pinky83.alfaplus.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources(
		{ @PropertySource(value = "classpath:application.properties") })
public class ApplicationProperties {
	private final Environment env;

	@Autowired
	public ApplicationProperties(Environment env) {
		this.env = env;
	}

	public String getProperty(String propName) {
		return env.getProperty(propName);
	}
}

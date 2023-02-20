package uo.ri.cws.application.persistence.util;

import java.io.IOException;
import java.util.Properties;

public class Conf {
	private Properties props;
	private static Conf instance = null;
	private static final String FILE_CONF = "configuration.properties";

	private Conf() {
		this.props = new Properties();
		try {
			props.load(Conf.class.getClassLoader().getResourceAsStream(FILE_CONF));
		} catch (IOException e) {
			throw new RuntimeException("File properties cannot be loaded",e);
		}
		
	}
	public static Conf getInstance() {
		if (instance == null) {
			instance = new Conf();
		}
		return instance;
	}
	
	public String getProperty(String key) {
		String value = props.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Property not found in config file");
		}
		return value;
	}
	

}


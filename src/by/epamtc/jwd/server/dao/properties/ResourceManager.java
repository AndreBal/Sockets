package by.epamtc.jwd.server.dao.properties;

import java.util.ResourceBundle;


public class ResourceManager {
	
private final static ResourceManager instance = new ResourceManager();
	
	
	
	private ResourceBundle bundle = ResourceBundle.getBundle("resources.app");
	
	public static ResourceManager getInstance () {
		return instance;
	}
	
	public String getValue (String key) {
		return bundle.getString(key);
	}

}

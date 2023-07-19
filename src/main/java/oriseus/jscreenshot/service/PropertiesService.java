package oriseus.jscreenshot.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesService {
    private PropertiesService() {}
    
    public static String getProperties(String key) {
        String value = null;
        
        try (FileInputStream fis = new FileInputStream(".." + File.separator + "jscreenshot" + File.separator + "src" + File.separator + 
        		"main" + File.separator + "resources" + File.separator + "config" + File.separator + "config.properties")) {
            Properties property = new Properties();
            property.load(fis);
            value = property.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return value;
    }
    
}

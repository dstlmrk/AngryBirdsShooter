package cz.fit.dpo.mvcshooter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton pattern
 */
public class Config {
    
    private Properties prop = new Properties();
    private InputStream input = null;
    // singleton    
    private static Config instance = null;

    public Config() {
        try {
            input = new FileInputStream("config.properties");
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
	} finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    // e.printStackTrace();  
                }
            }
	} 
    }
    
    // vraci jedinacka    
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
     }
    
    public String getProperty(String key) {
        return prop.getProperty(key).trim();
    }
    
    public int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }
}
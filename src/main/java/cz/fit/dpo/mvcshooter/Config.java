package cz.fit.dpo.mvcshooter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Trida (singleton) reprezentujici config file.
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
            // ex.printStackTrace();
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
    
    /** Vraci jedinacka (singleton). */    
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
     }
    
    /** Vraci string z configu. */
    public String getProperty(String key) {
        return prop.getProperty(key).trim();
    }
    
    /** Vraci int z configu. */
    public int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Milos
 */
public class Util {
    private static Util instance;
    
    private Properties properties;
    
    private Util(){
        loadProperties();
    }
    
    public static Util getInstance(){
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }
    

    private void loadProperties(){
        try {
            FileInputStream fis = new FileInputStream("settings.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (Exception ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String getValue(String key) {
        return properties.getProperty(key, "n/a");
    }
}

package br.com.integracaoteiacard.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Bonifácio
 */
public abstract class SystemUtil {

    public static String getConfigurationProperty(String propertyName) {
        Properties properties = new Properties();
        String fileName = System.getProperty("jboss.server.config.dir") + File.separator + "teiacard" + File.separator + "configuracoes.properties";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            properties.load(fis);
        return properties.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}

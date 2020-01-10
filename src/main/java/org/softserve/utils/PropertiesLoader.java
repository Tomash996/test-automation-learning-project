package org.softserve.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final String CONFIG_PROPERTIES = "/config/config.properties";
    private static ThreadLocal<PropertiesLoader> providerThreadLocal = new ThreadLocal<>();
    private Properties props;

    private PropertiesLoader() {
    }

    public static PropertiesLoader loadProjectProperties() {
        if (providerThreadLocal.get() == null) {
            PropertiesLoader loader = new PropertiesLoader();
            loader.props = loadConfigProperties(PropertiesLoader.class, CONFIG_PROPERTIES);
            providerThreadLocal.set(loader);
        }
        return providerThreadLocal.get();
    }

    /**
     * load config properties into class
     * - usually it should be some library that has lots of configurations and class that uses it does not need to store it
     *
     * @param configClass
     * @param file
     * @return
     */
    private static Properties loadConfigProperties(Class<?> configClass, String file) {
        Properties props = new Properties();
        try (InputStream inputStream = configClass.getResourceAsStream(file)) {
            props.load(inputStream);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error loading configuration: " + e);
        }

        return props;
    }

    public String getMainUrl() {
        return props.getProperty("MAIN_URL");
    }
}

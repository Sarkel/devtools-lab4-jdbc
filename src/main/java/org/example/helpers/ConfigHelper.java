package org.example.helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/*
    @author Sebastian Kubalski
*/
public class ConfigHelper {
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
    private static ConfigHelper INSTANCE;

    private final Properties prop = new Properties();

    public static ConfigHelper getInstance() {
        return Optional.ofNullable(INSTANCE)
                .orElseGet(() -> {
                    INSTANCE = new ConfigHelper();
                    return INSTANCE;
                });
    }

    public void load() {
        try(FileInputStream propsInput = new FileInputStream(CONFIG_FILE_PATH)) {
            prop.load(propsInput);
        }
        catch (FileNotFoundException e) {
            System.out.println("Config file not found: " + CONFIG_FILE_PATH);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ConfigHelper() {}

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}

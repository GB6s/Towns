package com.gb6.towns.managers;

import org.bukkit.configuration.file.FileConfiguration;

import static com.gb6.towns.utils.Constants.INSTANCE;

public class ConfigurationManager {

    private FileConfiguration config;

    public ConfigurationManager() {
        config = INSTANCE.getConfig();
    }

    public int getInteger(String path) {
        return config.getInt(path);
    }

    public String getString(String path) {
        return config.getString(path);
    }

}

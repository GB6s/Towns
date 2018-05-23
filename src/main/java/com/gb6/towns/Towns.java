package com.gb6.towns;

import com.gb6.towns.commands.TownCommandManager;
import com.gb6.towns.listeners.PlayerEvents;
import com.gb6.towns.managers.CommandManager;
import com.gb6.towns.managers.FileManager;
import com.gb6.towns.objects.Resident;
import com.gb6.towns.objects.Town;
import com.gb6.towns.utils.ConfigWrapper;
import com.gb6.towns.utils.Lang;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

import static com.gb6.towns.managers.FileManager.createFile;
import static com.gb6.towns.utils.Constants.*;

public final class Towns extends JavaPlugin {

    @Getter private static Towns instance;
    @Getter private static CommandManager commandManager;
    @Getter private ConfigWrapper langFile = new ConfigWrapper(this, "", "lang.yml");

    @Override
    public void onEnable() {
        instance = this;
        commandManager = new TownCommandManager();

        saveDefaultConfig();
        loadLanguageFile();
        loadData();

        new PlayerEvents();
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        saveData();
        // Plugin shutdown logic
    }

    private void saveData() {
        FileManager.writeJSON("residents", RESIDENT_MAP);
        FileManager.writeJSON("towns", TOWN_MAP);
    }

    private void loadData() {
        createFile("residents");
        createFile("towns");

        RESIDENT_MAP.putAll(FileManager.readJSON("residents", new TypeToken<Map<UUID, Resident>>() {
        }));
        TOWN_MAP.putAll(FileManager.readJSON("towns", new TypeToken<Map<String, Town>>() {
        }));
    }

    private void loadLanguageFile() {
        Lang.setFile(langFile.getConfig());

        for (final Lang value : Lang.values()) {
            langFile.getConfig().addDefault(value.getNode(), value.getValue());
        }

        langFile.getConfig().options().copyDefaults(true);
        langFile.saveConfig();
    }
}

package com.gb6.towns.utils;

import com.gb6.towns.Towns;
import com.gb6.towns.managers.ConfigurationManager;
import com.gb6.towns.managers.FileManager;
import com.gb6.towns.objects.Resident;
import com.gb6.towns.objects.Town;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface Constants {

    /**
     * TownMap is a {@link Map} collection that contains {@link String} identifier as
     * key and {@link Town} as value.
     */
    Map<String, Town> TOWN_MAP = new HashMap<>();

    /**
     * TownMap is a {@link Map} collection that contains {@link UUID} identifier as
     * key and {@link Town} as value.
     */
    Map<UUID, Resident> RESIDENT_MAP = new HashMap<>();

    Towns INSTANCE = Towns.getInstance();

    ConfigurationManager CFM = new ConfigurationManager();

    FileManager FILE_MANAGER = new FileManager();

    Gson GSON = new GsonBuilder().setPrettyPrinting().create();
}

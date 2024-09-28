package com.github.jorepong.flexibleProperty;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PropertyDatabase {
    final private File propertyFile = new File("plugins/FlexibleProperty/properties.yml");
    final private YamlConfiguration config = YamlConfiguration.loadConfiguration(propertyFile);

    public void saveProperty(Location firstPosition, Location secondPosition) {
        UUID propertyUUID = UUID.randomUUID();
        String propertyId = propertyUUID.toString();

        config.set("properties.property" + propertyId + ".owner", null);
        config.set("properties.property" + propertyId + ".world", firstPosition.getWorld().getName());
        config.set("properties.property" + propertyId + ".corner1.x", firstPosition.getX());
        config.set("properties.property" + propertyId + ".corner1.y", firstPosition.getY());
        config.set("properties.property" + propertyId + ".corner1.z", firstPosition.getZ());
        config.set("properties.property" + propertyId + ".corner1.chunk.x", firstPosition.getChunk().getX());
        config.set("properties.property" + propertyId + ".corner1.chunk.z", firstPosition.getChunk().getZ());
        config.set("properties.property" + propertyId + ".corner2.x", secondPosition.getX());
        config.set("properties.property" + propertyId + ".corner2.y", secondPosition.getY());
        config.set("properties.property" + propertyId + ".corner2.z", secondPosition.getZ());
        config.set("properties.property" + propertyId + ".corner2.chunk.x", secondPosition.getChunk().getX());
        config.set("properties.property" + propertyId + ".corner2.chunk.z", secondPosition.getChunk().getZ());

        updatePropertySize(getPropertySize() + 1);
        saveConfig();
    }

    private void updatePropertySize(int size) {
        config.set("propertySize", size);
        saveConfig();
    }

    private int getPropertySize() {
        return config.getInt("propertySize");
    }

    private void saveConfig() {
        try {
            config.save(propertyFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

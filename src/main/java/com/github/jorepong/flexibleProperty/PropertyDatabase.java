package com.github.jorepong.flexibleProperty;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PropertyDatabase {
    final private static String propertiesPath = "plugins/FlexibleProperty/properties";
    final private static Set<Property> properties = new HashSet<>();
    final private static Map<Chunk, Property> propertyChunks = new HashMap<>();

    public static void addProperty(Property property) {
        properties.add(property);
        property.getChunks().stream().forEach(chunk -> propertyChunks.put(chunk, property));
    }

    public static void loadProperties() {
        File folder = new File(propertiesPath);
        if(!folder.exists()) {
            folder.mkdirs();
        }

        File[] files = folder.listFiles();
        for(File file : files) {
            String id = file.getName().replace(".yml", "");
            Configuration propertyConfig = YamlConfiguration.loadConfiguration(file);
            World world = Bukkit.getWorld(propertyConfig.getString("world"));
            Location corner1 = new Location(world,
                    propertyConfig.getInt("corner1.x"),
                    propertyConfig.getInt("corner1.y"),
                    propertyConfig.getInt("corner1.z"));
            Location corner2 = new Location(world,
                    propertyConfig.getInt("corner2.x"),
                    propertyConfig.getInt("corner2.y"),
                    propertyConfig.getInt("corner2.z"));
            Property property = new Property(id, corner1, corner2);
            addProperty(property);
        }
    }

    public static void saveProperties() {
        properties.stream().forEach(property -> {
            try {
                File file = new File("plugins/FlexibleProperty/properties/" + property.getId() + ".yml");
                YamlConfiguration propertyConfig = YamlConfiguration.loadConfiguration(file);
                propertyConfig.set("owner", null);
                propertyConfig.set("world", property.getWorld().getName());
                propertyConfig.set("corner1.x", property.getCorner1().getX());
                propertyConfig.set("corner1.y", property.getCorner1().getY());
                propertyConfig.set("corner1.z", property.getCorner1().getZ());
                propertyConfig.set("corner2.x", property.getCorner2().getX());
                propertyConfig.set("corner2.y", property.getCorner2().getY());
                propertyConfig.set("corner2.z", property.getCorner2().getZ());
                propertyConfig.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

//    private void updatePropertySize(int size) {
//        config.set("propertySize", size);
//    }
//
//    private int getPropertySize() {
//        return config.getInt("propertySize");
//    }
}

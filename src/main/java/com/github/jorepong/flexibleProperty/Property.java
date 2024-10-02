package com.github.jorepong.flexibleProperty;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.util.*;

public class Property {
    private final UUID id;

    private final World world;
    private final Location corner1;
    private final Location corner2;
    private final List<Chunk> chunks = new ArrayList<>();

    public Property(Location corner1, Location corner2) {
        this.id = UUID.randomUUID();
        this.world = corner1.getWorld();
        this.corner1 = corner1;
        this.corner2 = corner2;

        parseChunks(corner1, corner2);
        PropertyDatabase.addProperty(this);
    }

    public Property(String id, Location corner1, Location corner2) {
        this.id = UUID.fromString(id);
        this.world = corner1.getWorld();
        this.corner1 = corner1;
        this.corner2 = corner2;

        parseChunks(corner1, corner2);
        PropertyDatabase.addProperty(this);
    }

    private void parseChunks(Location corner1, Location corner2) {
        for(int chunkX = corner1.getChunk().getX(); chunkX <= corner2.getChunk().getX(); chunkX++) {
            for(int chunkZ = corner1.getChunk().getZ(); chunkZ <= corner2.getChunk().getZ(); chunkZ++) {
                Bukkit.broadcastMessage("Chunk: " + chunkX + ", " + chunkZ);
                chunks.add(corner1.getWorld().getChunkAt(chunkX, chunkZ));
            }
        }
    }

    public List<Chunk> getChunks() {
        return new ArrayList<>(chunks);
    }

    public Location getCorner1() {
        return corner1;
    }

    public Location getCorner2() {
        return corner2;
    }

    public UUID getId() {
        return id;
    }

    public World getWorld() {
        return world;
    }
}

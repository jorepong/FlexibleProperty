package com.github.jorepong.flexibleProperty;

import com.github.jorepong.flexibleProperty.command.CommandManager;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlexibleProperty extends JavaPlugin {

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).verboseOutput(true)); // Load with verbose output

        new CommandAPICommand("ping")
                .executesPlayer((sender, args) -> {
                    sender.sendMessage("pong!");
                })
                .register();
    }

    @Override
    public void onEnable() {
        CommandAPI.onEnable();
        TestCommand.register();
        CommandManager.registerCommands();
        PropertyDatabase.loadProperties();
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
        PropertyDatabase.saveProperties();
    }
}

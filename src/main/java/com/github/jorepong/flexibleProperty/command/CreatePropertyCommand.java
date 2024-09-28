package com.github.jorepong.flexibleProperty.command;

import com.github.jorepong.flexibleProperty.PropertyDatabase;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CreatePropertyCommand {

    public void registerCommand() {
        new CommandAPICommand("fp")
                .withArguments(new StringArgument("arg1")
                        .replaceSuggestions(ArgumentSuggestions.strings("create")))
                .executesPlayer((player, args) -> {
                    createProperty(player);
                })
                .register();
    }

    private void createProperty(Player player) {
        Location location = player.getLocation();
        Location firstPosition = location.add(-4, 0, -4);
        Location secondPosition = location.add(5, 0, 5);

        PropertyDatabase propertyDatabase = new PropertyDatabase();
        propertyDatabase.saveProperty(firstPosition, secondPosition);

        player.sendMessage("Property created!");
    }

}

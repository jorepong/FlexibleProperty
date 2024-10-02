package com.github.jorepong.flexibleProperty.command;

import com.github.jorepong.flexibleProperty.Property;
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

        Property property = new Property(firstPosition, secondPosition);
        PropertyDatabase.addProperty(property);

        player.sendMessage("Property created!");
    }

}

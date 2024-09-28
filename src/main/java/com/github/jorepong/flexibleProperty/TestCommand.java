package com.github.jorepong.flexibleProperty;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.*;
import org.bukkit.entity.Player;

public class TestCommand {

    public static void register() {
        new CommandAPICommand("test")
                .withArguments(new PlayerArgument("target"))
                .executesPlayer((player, args) -> {
                    Player target = (Player) args.get("target");
                    target.sendMessage("HI!");
//                    player.sendMessage();
                })
                .register();


        new CommandAPICommand("mycommand")
                .withArguments(new StringArgument("world").replaceSuggestions(ArgumentSuggestions.strings(
                        "northland", "eastland", "southland", "westland"
                )))
                .executesPlayer((player, args) -> {
                    player.sendMessage("test");
                })
                .register();
    }

}

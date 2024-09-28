package com.github.jorepong.flexibleProperty.command;

public class CommandManager {

    public static void registerCommands() {
        new CreatePropertyCommand().registerCommand();
    }
}

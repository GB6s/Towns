package com.gb6.towns.commands;

import com.gb6.towns.managers.CommandManager;

public class TownCommandManager extends CommandManager {
    public TownCommandManager() {
        super("town");
        this.registerCommands(
                new CreateCommand(),
                new DescCommand(),
                new HelpCommand(),
                new RenameCommand(),
                new InviteCommand(),
                new JoinCommand(),
                new BankCommand()
        );
    }
}

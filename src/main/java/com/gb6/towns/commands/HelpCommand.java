package com.gb6.towns.commands;

import com.gb6.towns.managers.Command;
import org.bukkit.command.CommandSender;

import static com.gb6.towns.enums.Perm.NONE;
import static com.gb6.towns.enums.Requirement.BOTH;

public class HelpCommand extends Command {

    HelpCommand() {
        super("help", NONE, BOTH, 0);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }

}

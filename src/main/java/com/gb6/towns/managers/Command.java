package com.gb6.towns.managers;

import com.gb6.towns.enums.Perm;
import com.gb6.towns.enums.Requirement;
import com.gb6.towns.objects.Message;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static com.gb6.towns.enums.Requirement.*;

public abstract class Command {
    @Getter private String name;
    private int requiredArgs;
    private Perm permission;
    private Requirement requirement;

    public Command(String name, Perm perm, Requirement requirement, int requiredArgs) {
        this.name = name;
        this.permission = perm;
        this.requirement = requirement;
        this.requiredArgs = requiredArgs;
    }

    void run(CommandSender sender, String[] args) {
        Boolean isPlayer = sender instanceof Player;
        Boolean isConsole = sender instanceof ConsoleCommandSender;

        if (!isConsole && requirement == CONSOLE) {
            new Message("console-only").send(sender);
            return;
        }

        if (!isPlayer && requirement == PLAYER) {
            new Message("player-only").send(sender);
            return;
        }

        if (!sender.isOp() && permission.has(sender)) {
            new Message("no-permission").send(sender);
            return;
        }

        if (requiredArgs == -1 && args.length == 0) {
            new Message("invalid-arguments").send(sender);
            return;
        }

        if (requiredArgs != -1 && args.length < requiredArgs) {
            new Message("invalid-arguments").send(sender);
            return;
        }

        execute(sender, args);
    }

    protected abstract void execute(CommandSender sender, String[] args);

}

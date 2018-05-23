package com.gb6.towns.commands;

import com.gb6.towns.managers.Command;
import com.gb6.towns.objects.Resident;
import com.gb6.towns.utils.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.gb6.towns.enums.Perm.INVITE;
import static com.gb6.towns.enums.Requirement.PLAYER;
import static com.gb6.towns.utils.Constants.RESIDENT_MAP;

public class InviteCommand extends Command {
    InviteCommand() {
        super("invite", INVITE, PLAYER, 1);
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            Lang.INVALID_PLAYER.format("player", args[0]).send(player);
            return;
        }

        Resident resident = RESIDENT_MAP.get(player.getUniqueId());

        if (!resident.getTown().inviteResident(target)) {
            Lang.INVITE_ALREADY_SENT.format("player", target.getName()).send(player);
            return;
        }

        Lang.INVITE_TARGET.format("name", resident.getTown().getName()).send(target);
        Lang.INVITE_SENDER.format("name", target.getName()).send(player);
    }
}

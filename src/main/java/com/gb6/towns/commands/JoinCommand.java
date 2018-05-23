package com.gb6.towns.commands;

import com.gb6.towns.enums.Perm;
import com.gb6.towns.enums.Requirement;
import com.gb6.towns.managers.Command;
import com.gb6.towns.objects.Town;
import com.gb6.towns.utils.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

import static com.gb6.towns.utils.Constants.TOWN_MAP;

public class JoinCommand extends Command {
    JoinCommand() {
        super("join", Perm.NONE, Requirement.PLAYER, 1);
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Town town = TOWN_MAP.get(args[0]);
        Player player = (Player) sender;

        if (town == null) {
            Lang.INVALID_TOWN.format("town", args[0]).send(sender);
            return;
        }

        if (!town.getInvited().contains(player.getUniqueId())) {
            Lang.NO_INVITE.format("town", town.getName()).send(player);
            return;
        }

        Lang.TOWN_NEW_RESIDENT.format("name", player.getName()).send(town.getResidents().stream().map(Bukkit::getPlayer).collect(Collectors.toList()));

        town.getInvited().remove(player.getUniqueId());
        town.getResidents().add(player.getUniqueId());

        Lang.SUCCESS_JOINED.format("town", town.getName()).send(player);
    }
}

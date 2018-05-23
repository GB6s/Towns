package com.gb6.towns.commands;

import com.gb6.towns.enums.Perm;
import com.gb6.towns.enums.Requirement;
import com.gb6.towns.managers.Command;
import com.gb6.towns.objects.Message;
import com.gb6.towns.objects.Town;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            new Message("invalid-town").format("town", args[0]).send(sender);
            return;
        }

        if (!town.getInvited().contains(player.getUniqueId())) {
            new Message("no-invite").format("town", town.getName()).send(player);
            return;
        }

        new Message("new-resident").format("name", player.getName()).send(town.getResidents());

        town.getInvited().remove(player.getUniqueId());
        town.getResidents().add(player.getUniqueId());

        new Message("successfully-joined").format("town", town.getName()).send(player);
    }
}

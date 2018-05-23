package com.gb6.towns.commands;

import com.gb6.towns.managers.Command;
import com.gb6.towns.objects.Resident;
import com.gb6.towns.utils.Lang;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.gb6.towns.enums.Perm.RENAME;
import static com.gb6.towns.enums.Requirement.PLAYER;
import static com.gb6.towns.utils.Constants.RESIDENT_MAP;

public class RenameCommand extends Command {

    RenameCommand() {
        super("rename", RENAME, PLAYER, 1);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Resident resident = RESIDENT_MAP.get(player.getUniqueId());

        if (resident.getTown() == null) {
            Lang.NO_TOWN.send(sender);
            return;
        }

        resident.getTown().setName(args[0]);
        Lang.SUCCESS_NAME_SET.format("name", args[0]).send(player);
    }
}

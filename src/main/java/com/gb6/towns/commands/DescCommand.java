package com.gb6.towns.commands;

import com.gb6.towns.managers.Command;
import com.gb6.towns.objects.Resident;
import com.gb6.towns.utils.Lang;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.gb6.towns.enums.Perm.*;
import static com.gb6.towns.enums.Requirement.PLAYER;
import static com.gb6.towns.utils.Constants.*;

public class DescCommand extends Command {

    public DescCommand() {
        super("desc", NONE, PLAYER, -1);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            Lang.ERROR_UNKNOWN_COMMAND.send(sender);
            return;
        }

        Player player = (Player) sender;

        Resident resident = RESIDENT_MAP.get(player.getUniqueId());

        if (TOWN_MAP.get(resident.getTownUuid()) == null || !resident.hasPerm(DESC)) {
            Lang.NO_PERMISSION.send(sender);
            return;
        }

        TOWN_MAP.get(resident.getTownUuid()).setDescription(String.join(" ", args));
        Lang.SUCCESS_DESCRIPTION_SET.send(sender);
    }
}

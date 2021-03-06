package com.gb6.towns.commands;

import com.gb6.towns.enums.Role;
import com.gb6.towns.managers.Command;
import com.gb6.towns.objects.Resident;
import com.gb6.towns.objects.Town;
import com.gb6.towns.utils.Lang;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.gb6.towns.enums.Perm.CREATE;
import static com.gb6.towns.enums.Requirement.PLAYER;
import static com.gb6.towns.utils.Constants.*;

public class CreateCommand extends Command {
    CreateCommand() {
        super("create", CREATE, PLAYER, 1);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        String name = args[0];
        Resident resident = RESIDENT_MAP.get(player.getUniqueId());

        if (resident.getTown() != null) {
            Lang.ERROR_MUST_LEAVE_CURRENT_TOWN.send(sender);
            return;
        }

        if (TOWN_MAP.entrySet().stream().anyMatch(s -> s.getValue().getName().equalsIgnoreCase(name))) {
            Lang.ERROR_NAME_ALREADY_TAKEN.format("name", name).send(sender);
            return;
        }

        Town town = new Town(name);

        resident.setTown(name);
        resident.setRole(Role.MAYOR);
        town.getResidents().add(player.getUniqueId());

        TOWN_MAP.put(name, town);
        Lang.SUCCESS_TOWN_CREATED.format("name", name).send(sender);
    }
}

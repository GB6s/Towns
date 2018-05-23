package com.gb6.towns.commands;

import com.gb6.towns.enums.Requirement;
import com.gb6.towns.managers.Command;
import com.gb6.towns.objects.Resident;
import com.gb6.towns.utils.Lang;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.gb6.towns.enums.Perm.*;
import static com.gb6.towns.utils.Constants.RESIDENT_MAP;

public class BankCommand extends Command {
    BankCommand() {
        super("bank", NONE, Requirement.BOTH, 2);
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if(!StringUtils.isNumeric(args[1])) {
            Lang.ERROR_UNKNOWN_COMMAND.send(sender);
            return;
        }

        Resident resident = RESIDENT_MAP.get(((Player) sender).getUniqueId());
        double alter = Integer.parseInt(args[1]);

        switch (args[0].toLowerCase()) {
            case "deposit":
                if (!sender.isOp() && DEPOSIT.has(sender)) {
                    Lang.NO_PERMISSION.send(sender);
                    return;
                }

                //TODO: implement checks and eco

                resident.getTown().mutateBank(alter);
                Lang.BANK_DEPOSIT.format("amount", "" + Math.abs(alter)).send(sender);
                break;
            case "withdraw":
                if (!sender.isOp() && WITHDRAW.has(sender)) {
                    Lang.NO_PERMISSION.send(sender);
                    return;
                }

                //TODO: implement checks and eco

                resident.getTown().mutateBank(alter);
                Lang.BANK_WITHDRAW.format("amount", "" + Math.abs(alter)).send(sender);
                break;
            default:
                Lang.ERROR_UNKNOWN_COMMAND.send(sender);
        }
    }
}

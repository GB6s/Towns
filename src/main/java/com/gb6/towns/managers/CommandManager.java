package com.gb6.towns.managers;

import com.gb6.towns.Towns;
import com.gb6.towns.utils.Lang;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager implements CommandExecutor {

    private List<Command> commandList = new ArrayList<>();

    public CommandManager(String baseCommand) {
        Towns.getInstance().getCommand(baseCommand).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        for (Command cmd : commandList) {
            List<String> output = new ArrayList<>(Arrays.asList(args));

            if (output.size() > 0) {
                output.remove(0);
            }

            if (args.length == 0) {
                //TODO: add help command here
                //cmd.fire(sender, output);
                return true;
            }

            if (!args[0].equalsIgnoreCase(cmd.getName())) {
                continue;
            }

            cmd.run(sender, output.toArray(new String[output.size()]));
            return true;
        }
        Lang.ERROR_UNKNOWN_COMMAND.send(sender);
        return true;
    }

    protected void registerCommands(Command... command) {
        commandList.addAll(Arrays.asList(command));
    }

/*    public String generateHelpMessage() {
        StringBuilder b = new StringBuilder();
        b.append("&7============== &3" + command.getName() + " &7==============\n");
        b.append("&8<> = Required argument    [] = Optional argument\n");
        for (Map.Entry<String, Command> cmd : commands.entrySet()) {
            String cmdString = cmd.getValue().usage() + " &8-&7 " + cmd.getValue().description();
            b.append(cmdString + "\n");
        }
        return Prison.color(b.toString());
    }

*/

}

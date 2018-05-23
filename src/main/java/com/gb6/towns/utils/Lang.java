package com.gb6.towns.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;

public enum Lang {
    PREFIX("&bTowny &7>> "),

    ONLY_CONSOLE("&7This command can only be used by console"),
    ONLY_PLAYER("&7This command can only be used by players"),

    NO_PERMISSION("&7You do have permission to use this command"),
    NO_TOWN("&7You must be in a town to use this command"),
    NO_INVITE("&7You have not been invited to {town}"),

    INVALID_PLAYER("&cInvalid player: {player}"),
    INVALID_TOWN("&cInvalid town: {town}"),

    ERROR_UNKNOWN_COMMAND("7Unknown arguments, type /town help"),
    ERROR_NAME_ALREADY_TAKEN("&7Sorry but this tag is already taken: &f{name}"),
    ERROR_MUST_LEAVE_CURRENT_TOWN("&7Please leave your current town before using this command"),

    INVITE_ALREADY_SENT("&7Someone has already invited {player} to your town!"),
    INVITE_TARGET("&7You have been invited to &f{name} type &8/town join {name}"),
    INVITE_SENDER("&7You have sent an invite to {player}"),

    SUCCESS_JOINED("&7You have successfully joined: {town}"),
    SUCCESS_DESCRIPTION_SET("&7Description set."),
    SUCCESS_NAME_SET("&7Towns name has been set to: {name}"),
    SUCCESS_TOWN_CREATED("&7Successfully created town: &f{name}"),

    BANK_WITHDRAW("&7You have withdrawn {amount}"),
    BANK_DEPOSIT("&7You have deposited {amount}"),

    TOWN_NEW_RESIDENT("&7A new resident has joined: {name}!");


    @Getter private final String node;
    @Getter @Setter private String value;
    private static FileConfiguration LANG;

    Lang(final String value) {
        this.node = toString().toLowerCase().replaceFirst("_", ".").replace("_", "-");
        this.value = value;
    }

    public static void setFile(final FileConfiguration config) {
        LANG = config;
    }

    public Lang format(String replace, String replaceWith) {
        value = value.replace("{" + replace + "}", replaceWith);
        return this;
    }

    public void send(CommandSender... sender) {
        Arrays.stream(sender).forEach(s -> s.sendMessage(ChatColor.translateAlternateColorCodes('&', PREFIX.getValue()) + " " + ChatColor.translateAlternateColorCodes('&', value)));
    }

    public void send(List<CommandSender> senderList) {
        senderList.forEach(this::send);
    }

}

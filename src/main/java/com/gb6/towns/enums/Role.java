package com.gb6.towns.enums;

import lombok.Getter;

import java.util.List;

import static com.gb6.towns.enums.Perm.*;
import static java.util.Arrays.asList;

public enum Role {

    MAYOR("Mayor", -1, ALL),
    ASS_MAYOR("Assistant Mayor", -1, INVITE, LIST, INFO, PLOT, PLAYER, SET_ROLE, KICK, SET_SPAWN, EXPAND, PLOT_TYPE, PLOT_OWNER, PLOT_EVICT, DESC),
    OFFICER("Officer", -1, INVITE, LIST, INFO, PLOT, PLAYER, SET_ROLE, KICK, SETPLOT, DESC),
    TRUSTED("Trusted", 7, INVITE, LIST, INFO, PLOT, PLAYER),
    CITIZEN("Citizen", 5, LIST, INFO, PLOT, PLAYER),
    GUEST("Guest", 1, LIST, INFO, PLOT, PLAYER);

    @Getter private String prefix;
    @Getter private int plot_count;
    @Getter private List<Perm> permissions;

    Role(String prefix, int plot_count, Perm... perms) {
        this.plot_count = plot_count;
        this.prefix = prefix;
        this.permissions = asList(perms);
    }

    public boolean has(Perm perm) {
        return perm.equals(NONE) || permissions.contains(ALL) || permissions.contains(perm);
    }
}

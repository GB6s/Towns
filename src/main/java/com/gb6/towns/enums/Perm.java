package com.gb6.towns.enums;

import com.gb6.towns.objects.Resident;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.gb6.towns.utils.Constants.RESIDENT_MAP;

public enum Perm {
    ALL,
    NONE,
    INVITE,
    LIST,
    INFO,
    PLOT,
    PLAYER,
    SET_ROLE,
    KICK,
    SET_SPAWN,
    EXPAND,
    PLOT_TYPE,
    PLOT_OWNER,
    PLOT_EVICT,
    SETPLOT,
    DESC,
    RENAME,
    CREATE,
    WITHDRAW,
    DEPOSIT;

    @Getter private String permissionId;

    Perm() {
        permissionId = createPermissionId(this);
    }

    public boolean has(CommandSender player) {
        if (player instanceof Player) {
            Resident resident = RESIDENT_MAP.get(((Player) player).getUniqueId());
            return resident.getRole().getPermissions().contains(ALL) || resident.getRole().getPermissions().contains(this);
        }
        return equals(ALL) || player.hasPermission(permissionId);
    }

    public String createPermissionId(Perm perm) {
        return "town" + "." + perm.name().toLowerCase().replace('_', '.');
    }
}

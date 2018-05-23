package com.gb6.towns.objects;

import com.gb6.towns.enums.Perm;
import com.gb6.towns.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import static com.gb6.towns.utils.Constants.TOWN_MAP;

public class Resident {

    @Getter @Setter private Role role;
    @Getter @Setter private int plots_purchased;
    @Getter @Setter private String townUuid;

    public Resident() {
    }

    public boolean hasPerm(Perm perm) {
        return role.has(perm);
    }

    public Town getTown() {
        return TOWN_MAP.get(townUuid);
    }

    public void setTown(String uuid) {
        this.townUuid = uuid;
    }
}

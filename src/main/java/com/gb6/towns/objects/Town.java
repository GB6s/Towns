package com.gb6.towns.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Town {
    @Getter @Setter private String name;
    @Getter @Setter private String description;
    @Getter private List<UUID> residents = new ArrayList<>();
    @Getter private List<UUID> invited = new ArrayList<>();
    @Getter @Setter private double bank;

    public Town(String name) {
        this.name = name;
    }

    public boolean inviteResident(Player target) {
        if (invited.contains(target.getUniqueId())) {
            return false;
        }
        invited.add(target.getUniqueId());
        return true;
    }

    public void mutateBank(double amount) {
        bank += amount;
    }
}

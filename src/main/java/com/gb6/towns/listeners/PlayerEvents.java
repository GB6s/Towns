package com.gb6.towns.listeners;

import com.gb6.towns.objects.Resident;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.gb6.towns.engine.Events.listen;
import static com.gb6.towns.utils.Constants.RESIDENT_MAP;
import static org.bukkit.event.EventPriority.MONITOR;

public class PlayerEvents {
    public PlayerEvents() {
        listen(PlayerJoinEvent.class, MONITOR, (event) -> RESIDENT_MAP.putIfAbsent(event.getPlayer().getUniqueId(), new Resident()));
    }
}

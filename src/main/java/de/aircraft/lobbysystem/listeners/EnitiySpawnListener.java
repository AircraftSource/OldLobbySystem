package de.aircraft.lobbysystem.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EnitiySpawnListener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        if(e.getEntityType().equals(EntityType.PLAYER)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }
}

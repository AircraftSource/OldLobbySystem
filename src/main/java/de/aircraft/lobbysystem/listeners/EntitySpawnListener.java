package de.aircraft.lobbysystem.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if(e.getEntityType().equals(EntityType.PLAYER)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }
}

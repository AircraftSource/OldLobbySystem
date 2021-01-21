package de.aircraft.lobbysystem.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener {

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e) {
    if(e.getEntityType().equals(EntityType.PLAYER)) {
        e.setCancelled(false);
    } else {
        e.setCancelled(true);
    }
   }
}

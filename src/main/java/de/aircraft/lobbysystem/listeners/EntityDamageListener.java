package de.aircraft.lobbysystem.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if(e.getEntityType().equals(EntityType.PLAYER) || e.getEntityType().equals(EntityType.VILLAGER)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }
}

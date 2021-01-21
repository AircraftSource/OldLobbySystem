package de.aircraft.lobbysystem.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelListener implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}

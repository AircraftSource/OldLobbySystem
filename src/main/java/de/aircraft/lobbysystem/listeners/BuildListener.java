package de.aircraft.lobbysystem.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class BuildListener implements Listener {

    @EventHandler
    public void onBlock(BlockBreakEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onBlock(BlockPlaceEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void interact(InventoryClickEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void drop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onBlock(PlayerPickupItemEvent e) {
        e.setCancelled(true);
    }
}

package de.aircraft.lobbysystem.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerItemHeldListener implements Listener {

    @EventHandler
    public void onItemHeldListener(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        p.playSound(p.getLocation(), Sound.CLICK, 1, 3);
    }
}

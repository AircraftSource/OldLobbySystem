package de.aircraft.lobbysystem.listeners;

import de.aircraft.lobbysystem.LobbySystem;
import de.aircraft.lobbysystem.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onmove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getLocation().getBlockY() <= 5) {
            p.playSound(p.getLocation(), Sound.FIREWORK_BLAST, 1, 3);
            p.setVelocity(new Vector(0,5,0));
            Bukkit.getScheduler().runTaskLater(LobbySystem.instance, () -> {
                LocationManager.getLocation(p, "spawn");
            },20*2);
        }

    }
}

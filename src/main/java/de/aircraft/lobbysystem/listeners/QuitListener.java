package de.aircraft.lobbysystem.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void noQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }
}

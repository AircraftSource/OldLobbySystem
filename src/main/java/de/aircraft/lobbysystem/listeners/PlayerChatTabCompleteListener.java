package de.aircraft.lobbysystem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

public class PlayerChatTabCompleteListener implements Listener {

    @EventHandler
    public void onChatTabComplete(PlayerChatTabCompleteEvent e) {
        Player p = e.getPlayer();
        if(!p.hasPermission("aircraft.lobby.chattabcomplete")) {
            e.getTabCompletions().clear();
        }
    }
}

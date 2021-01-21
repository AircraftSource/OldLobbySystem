package de.aircraft.lobbysystem.listeners;

import de.aircraft.lobbysystem.utils.ScoreboardM;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(p.getUniqueId());

        String msg = e.getMessage();

        assert permissionPlayer != null;
        if(permissionPlayer.hasPermissionGroup("Inhaber")) {
            e.setFormat("§4I §8● §7 " + p.getName() + " §8» §7" + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        } else if(permissionPlayer.hasPermissionGroup("Admin")) {
            e.setFormat("§cA §8● §7 " + p.getName() + " §8» §7" +ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        }  else if(permissionPlayer.hasPermissionGroup("Dev")) {
            e.setFormat("§3D §8● §7 " + p.getName() + " §8» §7" + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        }  else if(permissionPlayer.hasPermissionGroup("Mod")) {
            e.setFormat("§cM §8● §7 " + p.getName() + " §8» §7" +ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        }  else if(permissionPlayer.hasPermissionGroup("Sup")) {
            e.setFormat("§bS §8● §7 " + p.getName() + " §8» §7" + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        }  else if(permissionPlayer.hasPermissionGroup("SrDev")){
            e.setFormat("§3SrD §8● §7 " + p.getName() + " §8» §7" + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        } else if(permissionPlayer.hasPermissionGroup("SrMod")){
            e.setFormat("§cSrM §8● §7 " + p.getName() + " §8» §7" + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        } else if(permissionPlayer.hasPermissionGroup("SrSup")){
            e.setFormat("§bSrS §8● §7 " + p.getName() + " §8» §7" + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        } else if(permissionPlayer.hasPermissionGroup("Dino")){
            e.setFormat("§bD §8● §7 " + p.getName() + " §8» §7" + msg);
        } else if(permissionPlayer.hasPermissionGroup("Premium")){
                e.setFormat("§6P §8● §7 " + p.getName() + " §8» §7" + msg);
        } else if(permissionPlayer.hasPermissionGroup("Builder")){
            e.setFormat("§2B §8● §7 " + p.getName() + " §8» §7" + msg);
        } else if(permissionPlayer.hasPermissionGroup("SrBuilder")){
            e.setFormat("§2SrB §8● §7 " + p.getName() + " §8» §7" + msg);
        }
        if(permissionPlayer.getPermissionGroupInfoList().size() == 1) {
            e.setFormat("§9S §8● §9 " + p.getName() + " §8» §7" + msg);
        }


    }
}

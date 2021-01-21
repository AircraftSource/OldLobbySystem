package de.aircraft.lobbysystem.listeners;

import de.aircraft.lobbysystem.LobbySystem;
import de.aircraft.lobbysystem.mysql.switcher.SQLSwitcher;
import de.aircraft.lobbysystem.utils.*;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.getWorld().setDifficulty(Difficulty.PEACEFUL);
        LocationManager.getLocation(p,"spawn");
        JoinBoots.onJoin(p);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbySystem.instance, ActionBar::sendUhrzeit,0,20);
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        final String message = year.format(new Date());
        p.setLevel(Integer.parseInt(message));
        SQLSwitcher.createPlayer(p.getUniqueId().toString());
        e.setJoinMessage(null);
        p.setGameMode(GameMode.ADVENTURE);
        p.setFoodLevel(20);
        p.setExp(1);
        p.setMaxHealth(6);
        setSchedularjoin(p);
        p.getInventory().clear();
        InventoryM.onInventory(p);
        ScoreboardM.setScoreboard(p);
        ScoreboardM.setTab(p);
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(p.getUniqueId());
        BukkitTask task = Bukkit.getScheduler().runTaskTimerAsynchronously(LobbySystem.instance, () -> {
            ScoreboardM.updateall(p);
            Bukkit.getOnlinePlayers().forEach(all -> ScoreboardM.updateTab(all));
        },0,20);

        if(p == null) {
            task.cancel();
        }
    }
    private static void setSchedularjoin(Player p) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(LobbySystem.instance , () -> {
            p.getWorld().setFullTime(1000);
        }, 0 , 20*60*5);
        Bukkit.getScheduler().runTaskLaterAsynchronously(LobbySystem.instance , () -> {
            p.getWorld().getLivingEntities().clear();
        }, 10*2);
    }
}

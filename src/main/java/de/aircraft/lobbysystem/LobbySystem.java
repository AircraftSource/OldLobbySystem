package de.aircraft.lobbysystem;

import de.aircraft.lobbysystem.cmds.SetupCMD;
import de.aircraft.lobbysystem.listeners.*;
import de.aircraft.lobbysystem.mysql.coins.CoinMySQL;
import de.aircraft.lobbysystem.mysql.onlinetime.OnlineMySQL;
import de.aircraft.lobbysystem.mysql.switcher.SwitcherMySQL;
import de.aircraft.lobbysystem.utils.Navigator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbySystem extends JavaPlugin {


    public static LobbySystem instance;
    public SwitcherMySQL swmysql;
    public OnlineMySQL onlineMySQL;
    public CoinMySQL coinMySQL;
    public String pr = "§9§lLOBBY §8● §a";
    public String noperms = pr + "§cDieser Command exestiert nicht oder du hast keine Rechte§8.";

    @Override
    public void onEnable() {
         instance = this;
        Location loc = new Location(Bukkit.getWorld("world"),-45,61,-11,126,3);
        Entity villager = loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setCustomName("§aTägliche Belohnung");

        onregisterListener();
        onregisterCMD();
        swmysql = new SwitcherMySQL();
        onlineMySQL = new OnlineMySQL();
        coinMySQL = new CoinMySQL();
        swmysql.update("CREATE TABLE IF NOT EXISTS switcher(UUID varchar(64), STATE int);");
    }

    @Override
    public void onDisable() {
        swmysql.close();
        onlineMySQL.close();
        coinMySQL.close();
    }

    private void onregisterCMD() {
        getCommand("setup").setExecutor(new SetupCMD());
    }
    private void onregisterListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new QuitListener(), this);
        pm.registerEvents(new WeatherChangeListener(), this);
        pm.registerEvents(new EntityDamageListener(), this);
        pm.registerEvents(new EntitySpawnListener(), this);
        pm.registerEvents(new PlayerChatListener(), this);
        pm.registerEvents(new FoodLevelListener(), this);
        pm.registerEvents(new CreatureSpawnListener(), this);
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new EntitySpawnListener(), this);
        pm.registerEvents(new PlayerChatTabCompleteListener(), this);
        pm.registerEvents(new IventoryClickListener(), this);
        pm.registerEvents(new PlayerCommandPreprocessListener(), this);
        pm.registerEvents(new PlayerItemHeldListener(), this);
        pm.registerEvents(new PlayerMoveListener(), this);
        pm.registerEvents(new BuildListener(), this);
        pm.registerEvents(new Navigator(), this);
    }
}

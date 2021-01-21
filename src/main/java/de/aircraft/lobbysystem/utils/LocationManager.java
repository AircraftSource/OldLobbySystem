package de.aircraft.lobbysystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LocationManager {

    private static File file = new File("plugins//AirCraftLobbySystem/locations.yml");
    private static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void saveCfg() {
        try{
            cfg.save(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLocation(String name, Location loc) {
        cfg.set(name+".world", loc.getWorld().getName());
        cfg.set(name+".x", loc.getX());
        cfg.set(name+".y", loc.getY());
        cfg.set(name+".z", loc.getZ());
        cfg.set(name+".yaw", loc.getYaw());
        cfg.set(name+".pitch", loc.getPitch());
        saveCfg();
        if(!file.exists()) {
            file.mkdir();
        }
    }
    public static void getLocation(Player p, String name) {
        World world = Bukkit.getWorld(cfg.getString(name+".world"));
        double x = cfg.getDouble(name+".x");
        double y = cfg.getDouble(name+".y");
        double z = cfg.getDouble(name+".z");
        float yaw = (float) cfg.getDouble(name+".yaw");
        float pitch = (float) cfg.getDouble(name+".pitch");
        Location loc = new Location(world,x,y,z,yaw,pitch);
        p.teleport(loc);
    }
}

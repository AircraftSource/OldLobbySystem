package de.aircraft.lobbysystem.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionBar {

    private static void sendActionbar(final Player player, final String message) {
        final IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer
                .a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}");
        final PacketPlayOutChat packet = new PacketPlayOutChat(iChatBaseComponent, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void sendUhrzeit() {
        SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
        final String message = "§7§lAkutuelle Zeit §8• §a"+ date.format(new Date());
        Bukkit.getOnlinePlayers().forEach(current -> sendActionbar(current, message));
    }
}

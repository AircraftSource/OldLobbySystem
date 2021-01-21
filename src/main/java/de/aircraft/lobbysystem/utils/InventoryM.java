package de.aircraft.lobbysystem.utils;

import de.aircraft.lobbysystem.LobbySystem;
import de.aircraft.lobbysystem.mysql.switcher.SQLSwitcher;
import de.aircraft.lobbysystem.utils.heads.Skull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryM {
    public static Inventory inv;
    public static void onInventory(Player p) {
        ItemStack itemStack = new ItemStack(Material.FLOWER_POT_ITEM);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(LobbySystem.instance.pr + "§e§lTELEPORTER");
        itemStack.setItemMeta(itemMeta);
        p.getInventory().setItem(5, itemStack);

        ItemStack itemStack1 = new ItemStack(Material.MINECART);
        ItemMeta itemMeta1 = itemStack1.getItemMeta();
        itemMeta1.setDisplayName(LobbySystem.instance.pr + "§e§lLOBBY");
        itemStack1.setItemMeta(itemMeta1);
        p.getInventory().setItem(7, itemStack1);
        onHider(p);
        ItemStack itemStack2 = Skull.getCustomSkull("http://textures.minecraft.net/texture/f37cae5c51eb1558ea828f58e0dff8e6b7b0b1a183d737eecf714661761");
        ItemMeta itemMeta2 = itemStack2.getItemMeta();
        itemMeta2.setDisplayName(LobbySystem.instance.pr + "§e§lSHOP");
        itemStack2.setItemMeta(itemMeta2);
        p.getInventory().setItem(1, itemStack2);
    }
    private static void onHider(Player p) {
        if(SQLSwitcher.getState(p.getUniqueId().toString())== 1) {
            for(Player all : Bukkit.getOnlinePlayers()) {
                if(all.hasPermission("aircraft.lobby.team")) {
                    p.showPlayer(all);
                } else {
                    p.hidePlayer(all);
                }
            }
            p.getInventory().setItem(3, new ItemBuilder(Material.REDSTONE).setDisplayName(LobbySystem.instance.pr + "§5TEAM").build());

        } else  if(SQLSwitcher.getState(p.getUniqueId().toString())== 2) {
            for(Player all : Bukkit.getOnlinePlayers()) {
                p.hidePlayer(all);
            }
            p.getInventory().setItem(3, new ItemBuilder(Material.CLAY_BALL).setDisplayName(LobbySystem.instance.pr + "§7UNSICHTBAR").build());

        } else  if(SQLSwitcher.getState(p.getUniqueId().toString())== 3) {
            for(Player all : Bukkit.getOnlinePlayers()) {
                p.showPlayer(all);
            }
            p.getInventory().setItem(3, new ItemBuilder(Material.GLOWSTONE_DUST).setDisplayName(LobbySystem.instance.pr + "§aSICHTBAR").build());
        }
    }


}

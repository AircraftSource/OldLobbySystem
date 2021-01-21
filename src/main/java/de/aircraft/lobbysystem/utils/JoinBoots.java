package de.aircraft.lobbysystem.utils;


import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class JoinBoots {

    public static void onJoin(Player player) {
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());
        ItemStack stack = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) stack.getItemMeta();

        if(permissionPlayer.hasPermissionGroup("Inhaber")) {
            meta.setColor(Color.fromRGB(255, 0, 0));
        } else if(permissionPlayer.hasPermissionGroup("Admin")) {
            meta.setColor(Color.fromRGB(255, 0, 0));
        }  else if(permissionPlayer.hasPermissionGroup("Dev")) {
            meta.setColor(Color.fromRGB(82, 98, 156));
        }  else if(permissionPlayer.hasPermissionGroup("Mod")) {
            meta.setColor(Color.fromRGB(191, 75, 75));
        }  else if(permissionPlayer.hasPermissionGroup("Sup")) {
            meta.setColor(Color.fromRGB(0, 247, 255));
        }  else if(permissionPlayer.hasPermissionGroup("SrDev")){
            meta.setColor(Color.fromRGB(82, 98, 156));
        } else if(permissionPlayer.hasPermissionGroup("SrMod")){
            meta.setColor(Color.fromRGB(191, 75, 75));
        } else if(permissionPlayer.hasPermissionGroup("SrSup")){
            meta.setColor(Color.fromRGB(0, 247, 255));
        } else if(permissionPlayer.hasPermissionGroup("Dino")){
            meta.setColor(Color.fromRGB(0, 247, 255));
        } else if(permissionPlayer.hasPermissionGroup("Premium")){
            meta.setColor(Color.fromRGB(255, 170, 0));
        }
        if(permissionPlayer.getPermissionGroupInfoList().size() == 1) {
            meta.setColor(Color.fromRGB(0, 38, 255));
        }
        stack.setItemMeta(meta);
        player.getEquipment().setBoots(stack);
    }
}

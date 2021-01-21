package de.aircraft.lobbysystem.listeners;

import de.aircraft.lobbysystem.LobbySystem;
import de.aircraft.lobbysystem.mysql.coins.SQLCoins;
import de.aircraft.lobbysystem.utils.ItemBuilder;
import de.aircraft.lobbysystem.utils.JoinBoots;
import de.aircraft.lobbysystem.utils.heads.Skull;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.api.service.ICloudService;
import eu.thesimplecloud.api.service.ServiceState;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import eu.thesimplecloud.module.permission.player.PlayerPermissionGroupInfo;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class IventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClicklobby(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ICloudPlayer player = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(p.getName());
        ICloudService lobby1 = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-1");
        ICloudService lobby2 = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-2");
        ICloudService lobby3 = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-3");
        ICloudService lobby4 = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-4");
        ICloudService lobby5 = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-5");
        if(e.getInventory().getTitle().equals(LobbySystem.instance.pr + "§e§lLOBBY")) {
            if(e.getClick() == ClickType.RIGHT || e.getClick() == ClickType.LEFT) {
                if(e.getCurrentItem().getType().equals(Material.NETHER_STAR)) {
                    p.sendMessage(LobbySystem.instance.pr  + "§cDu bist schon auf dieser Lobby§8!");
                    p.closeInventory();
                } else  if(e.getCurrentItem().getType().equals(Material.DEAD_BUSH)) {
                    p.sendMessage(LobbySystem.instance.pr  + "§cDiese Lobby ist offline§8!");
                    p.closeInventory();
                } else  if(e.getCurrentItem().getType().equals(Material.CLAY_BALL)) {
                    if (lobby1.getState().equals(ServiceState.VISIBLE) || lobby2.getState().equals(ServiceState.VISIBLE) || lobby3.getState().equals(ServiceState.VISIBLE) || lobby4.getState().equals(ServiceState.VISIBLE) || lobby5.getState().equals(ServiceState.VISIBLE)) {
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLobby-1")) {
                            p.closeInventory();
                            assert player != null;
                            assert lobby1 != null;
                            player.connect(lobby1);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLobby-2")) {
                            p.closeInventory();
                            assert player != null;
                            assert lobby2 != null;
                            player.connect(lobby2);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLobby-3")) {
                            p.closeInventory();
                            assert player != null;
                            assert lobby3 != null;
                            player.connect(lobby3);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLobby-4")) {
                            p.closeInventory();
                            assert player != null;
                            assert lobby4 != null;
                            player.connect(lobby4);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLobby-5")) {
                            p.closeInventory();
                            assert player != null;
                            assert lobby5 != null;
                            player.connect(lobby5);
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onInvenotrydefaultClickShop(InventoryClickEvent e) {
        Inventory invprem = Bukkit.createInventory(null, 9, LobbySystem.instance.pr + "§eKaufbestätigung §6P");
        Inventory invdino = Bukkit.createInventory(null, 9, LobbySystem.instance.pr + "§eKaufbestätigung §bD");
        ItemStack itemstackyes = new ItemStack(Skull.getCustomSkull("http://textures.minecraft.net/texture/4312ca4632def5ffaf2eb0d9d7cc7b55a50c4e3920d90372aab140781f5dfbc4"));
        ItemStack itemstackno = new ItemStack(Skull.getCustomSkull("http://textures.minecraft.net/texture/beb588b21a6f98ad1ff4e085c552dcb050efc9cab427f46048f18fc803475f7"));
        ItemMeta itemmetayes = itemstackyes.getItemMeta();
        ItemMeta itemmetano = itemstackno.getItemMeta();
        itemmetayes.setDisplayName("§aAkzeptieren");
        itemmetano.setDisplayName("§cAblehnen");
        itemstackyes.setItemMeta(itemmetayes);
        itemstackno.setItemMeta(itemmetano);
        invprem.setItem(3, itemstackyes);
        invprem.setItem(5, itemstackno);
        invdino.setItem(3, itemstackyes);
        invdino.setItem(5, itemstackno);
        Player p = (Player) e.getWhoClicked();
        Integer coinsfalseprem = 15000 - SQLCoins.getCoins(p.getUniqueId().toString());
        Integer coinsfalsedino = 30000 - SQLCoins.getCoins(p.getUniqueId().toString());
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(p.getUniqueId());
        assert permissionPlayer != null;
        if (e.getInventory().getTitle().equals(LobbySystem.instance.pr + "§e§lSHOP")) {
            if (e.getClick().equals(ClickType.LEFT) || e.getClick().equals(ClickType.RIGHT)) {
                if (!permissionPlayer.hasPermissionGroup("Builder") || permissionPlayer.hasPermissionGroup("SrBuilder") || permissionPlayer.hasPermissionGroup("Inhaber") || permissionPlayer.hasPermissionGroup("Admin") || permissionPlayer.hasPermissionGroup("SrDev") || permissionPlayer.hasPermissionGroup("Dev") || permissionPlayer.hasPermissionGroup("SrMod") || permissionPlayer.hasPermissionGroup("Mod") || permissionPlayer.hasPermissionGroup("SrSup") || permissionPlayer.hasPermissionGroup("Sup")) {
                    if (e.getCurrentItem().getType().equals(Material.GOLD_INGOT)) {
                        if(permissionPlayer.hasPermissionGroup("Dino")|| permissionPlayer.hasPermissionGroup("Premium")) {
                            p.sendMessage(LobbySystem.instance.pr + "§cDu hast entweder Premium oder ein höheren Rang§8!");
                        } else {
                            if(SQLCoins.getCoins(p.getUniqueId().toString()) >= 15000) {
                                p.openInventory(invprem);
                            } else {
                                p.closeInventory();
                                p.sendMessage(LobbySystem.instance.pr + "§cDir fehlen §8(§7" + coinsfalseprem +"§8) §eCoins§8!");
                            }
                        }
                    } else if (e.getCurrentItem().getType().equals(Material.EMERALD)) {
                        if(permissionPlayer.hasPermissionGroup("Dino")) {
                            p.sendMessage(LobbySystem.instance.pr + "§cDu hast entweder Premium oder ein höheren Rang§8!");
                        } else {
                            if(SQLCoins.getCoins(p.getUniqueId().toString()) >= 30000) {
                                p.openInventory(invdino);
                            } else {
                                p.closeInventory();
                                p.sendMessage(LobbySystem.instance.pr + "§cDir fehlen §8(§7" + coinsfalsedino +"§8) §eCoins§8!");
                            }
                        }
                    } else {
                        e.setCancelled(true);
                    }
                } else {
                    p.closeInventory();
                    p.sendMessage(LobbySystem.instance.pr + "§cDu hast entweder Premium oder ein höheren Rang§8!");
                }
            }
        } else  if (e.getInventory().getTitle().equals(LobbySystem.instance.pr + "§eKaufbestätigung §6P")) {
            if (e.getClick().equals(ClickType.LEFT) || e.getClick().equals(ClickType.RIGHT)) {
                if (!permissionPlayer.hasPermissionGroup("Builder") || permissionPlayer.hasPermissionGroup("SrBuilder") || permissionPlayer.hasPermissionGroup("Inhaber") || permissionPlayer.hasPermissionGroup("Admin") || permissionPlayer.hasPermissionGroup("SrDev") || permissionPlayer.hasPermissionGroup("Dev") || permissionPlayer.hasPermissionGroup("SrMod") || permissionPlayer.hasPermissionGroup("Mod") || permissionPlayer.hasPermissionGroup("SrSup") || permissionPlayer.hasPermissionGroup("Sup")) {
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAblehnen")) {
                        p.closeInventory();
                        p.sendMessage(LobbySystem.instance.pr + "§cDie Kaufaktion wurde abgebrochen§8.");
                    } else  if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAkzeptieren")) {
                        p.closeInventory();
                        SQLCoins.removeCoins(p.getUniqueId().toString(), 15000);
                        p.sendMessage(LobbySystem.instance.pr + "§7Dir wurde der §6Premium §7Rang hinzugefügt§8.");
                        long timeout = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(30);
                        permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Premium", timeout));
                        permissionPlayer.update();
                        JoinBoots.onJoin(p);
                    }
                }else {
                    p.sendMessage(LobbySystem.instance.pr + "§cDu hast entweder Premium oder ein höheren Rang§8!");
                }
            }
        } else  if (e.getInventory().getTitle().equals(LobbySystem.instance.pr + "§eKaufbestätigung §bD")) {
            if (e.getClick().equals(ClickType.LEFT) || e.getClick().equals(ClickType.RIGHT)) {
                if (!permissionPlayer.hasPermissionGroup("Builder") || permissionPlayer.hasPermissionGroup("SrBuilder") || permissionPlayer.hasPermissionGroup("Inhaber") || permissionPlayer.hasPermissionGroup("Admin") || permissionPlayer.hasPermissionGroup("SrDev") || permissionPlayer.hasPermissionGroup("Dev") || permissionPlayer.hasPermissionGroup("SrMod") || permissionPlayer.hasPermissionGroup("Mod") || permissionPlayer.hasPermissionGroup("SrSup") || permissionPlayer.hasPermissionGroup("Sup")) {
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAblehnen")) {
                        p.closeInventory();
                        p.sendMessage(LobbySystem.instance.pr + "§cDie Kaufaktion wurde abgebrochen§8.");
                    } else  if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAkzeptieren")) {
                        p.closeInventory();
                        SQLCoins.removeCoins(p.getUniqueId().toString(), 30000);
                        p.sendMessage(LobbySystem.instance.pr + "§7Dir wurde der §bDino §7Rang hinzugefügt§8.");
                        long timeout = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(30);
                        permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Dino", timeout));
                        permissionPlayer.update();
                        JoinBoots.onJoin(p);
                    }
                }else {
                    p.sendMessage(LobbySystem.instance.pr + "§cDu hast entweder Premium oder ein höheren Rang§8!");
                }
            }
        }
    }
}

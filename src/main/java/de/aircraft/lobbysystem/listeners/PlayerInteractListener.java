package de.aircraft.lobbysystem.listeners;

import de.aircraft.lobbysystem.LobbySystem;
import de.aircraft.lobbysystem.mysql.coins.SQLCoins;
import de.aircraft.lobbysystem.mysql.switcher.SQLSwitcher;
import de.aircraft.lobbysystem.utils.ItemBuilder;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.api.service.ICloudService;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerInteractListener implements Listener {
    private static ArrayList<Player> switcher = new ArrayList<>();
    Integer waitngtime = 20*2;
    Integer effecttime = 20;
    @EventHandler
    public void onInteractSwitcher(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)||e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if(e.getMaterial().equals(Material.GLOWSTONE_DUST)) {
                if(switcher.contains(p)) {
                    p.sendMessage(LobbySystem.instance.pr + "§cWarte einen Moment§8.");
                } else {
                    p.playSound(p.getLocation(), Sound.CLICK , 1 ,2);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, effecttime, 255));
                    SQLSwitcher.setState(p.getUniqueId().toString(), 1);
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        if(all.hasPermission("aircraft.lobby.team")) {
                            p.showPlayer(all);
                        } else {
                            p.hidePlayer(all);
                        }
                    }
                    p.getInventory().setItem(3, new ItemBuilder(Material.REDSTONE).setDisplayName(LobbySystem.instance.pr + "§5TEAM").build());
                    switcher.add(p);
                    Bukkit.getScheduler().runTaskLaterAsynchronously(LobbySystem.instance , () -> {
                        switcher.remove(p);
                    },waitngtime);
                }
            } else if(e.getMaterial().equals(Material.REDSTONE)) {
                if(switcher.contains(p)) {
                    p.sendMessage(LobbySystem.instance.pr + "§cWarte einen Moment§8.");
                } else {
                    p.playSound(p.getLocation(), Sound.CLICK , 1 ,2);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, effecttime, 255));
                    SQLSwitcher.setState(p.getUniqueId().toString(), 2);
                    for(Player all : Bukkit.getOnlinePlayers()) {
                            p.hidePlayer(all);
                    }
                    p.getInventory().setItem(3, new ItemBuilder(Material.CLAY_BALL).setDisplayName(LobbySystem.instance.pr + "§7UNSICHTBAR").build());
                    switcher.add(p);
                    Bukkit.getScheduler().runTaskLaterAsynchronously(LobbySystem.instance , () -> {
                        switcher.remove(p);
                    },waitngtime);
                }
            }else if(e.getMaterial().equals(Material.CLAY_BALL)) {
                if(switcher.contains(p)) {
                    p.sendMessage(LobbySystem.instance.pr + "§cWarte einen Moment§8.");
                } else {
                    p.playSound(p.getLocation(), Sound.CLICK , 1 ,2);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, effecttime, 255));
                    SQLSwitcher.setState(p.getUniqueId().toString(),3);
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        p.showPlayer(all);
                    }
                    p.getInventory().setItem(3, new ItemBuilder(Material.GLOWSTONE_DUST).setDisplayName(LobbySystem.instance.pr + "§aSICHTBAR").build());
                    switcher.add(p);
                    Bukkit.getScheduler().runTaskLaterAsynchronously(LobbySystem.instance , () -> {
                        switcher.remove(p);
                    },waitngtime);
                }
            }
        } else {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInteractLobby(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR)|| e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ICloudPlayer player = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(p.getName());
            ICloudService lobby1 = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-1");
            ICloudService lobby2 = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-2");
            ICloudService lobby3 = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-3");
            ICloudService lobby4 = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-4");
            ICloudService lobby5 = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("Lobby-5");
            if(e.getMaterial().equals(Material.MINECART)) {
                Inventory inv = Bukkit.createInventory(null, 9*3, LobbySystem.instance.pr + "§e§lLOBBY");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1,3);
                setGlas(inv, (short) 15, 0);
                setGlas(inv, (short) 7, 1);
                setGlas(inv, (short) 7, 2);
                setGlas(inv, (short) 15, 3);
                setGlas(inv, (short) 15, 4);
                setGlas(inv, (short) 15, 5);
                setGlas(inv, (short) 7, 6);
                setGlas(inv, (short) 7, 7);
                setGlas(inv, (short) 15, 8);
                setGlas(inv, (short) 7, 9);
                setGlas(inv, (short) 15, 10);
                setGlas(inv, (short) 15, 16);
                setGlas(inv, (short) 7, 17);
                setGlas(inv, (short) 15, 18);
                setGlas(inv, (short) 7, 19);
                setGlas(inv, (short) 7, 20);
                setGlas(inv, (short) 15, 21);
                setGlas(inv, (short) 15, 22);
                setGlas(inv, (short) 15, 23);
                setGlas(inv, (short) 7, 24);
                setGlas(inv, (short) 7, 25);
                setGlas(inv, (short) 15, 26);
                setLobbyItem(player, inv, lobby1,"§aLobby-1","§cLobby-1",11);
                setLobbyItem( player, inv, lobby2,"§aLobby-2", "§cLobby-2" , 12);
                setLobbyItem( player, inv, lobby3,"§aLobby-3", "§cLobby-3" , 13);
                setLobbyItem( player, inv, lobby4,"§aLobby-4", "§cLobby-4" , 14);
                setLobbyItem( player, inv, lobby5,"§aLobby-5", "§cLobby-5" , 15);
                assert player != null;
                if(player.getConnectedServerName().equals("Lobby-1")) {
                    assert lobby1 != null;
                    inv.setItem(11, new ItemBuilder(Material.NETHER_STAR).setDisplayName("§aLobby-1").setAmount(lobby1.getOnlineCount()).build());
                }
                if(player.getConnectedServerName().equals("Lobby-2")) {
                    assert lobby2 != null;
                    inv.setItem(12, new ItemBuilder(Material.NETHER_STAR).setDisplayName("§aLobby-2").setAmount(lobby2.getOnlineCount()).build());
                }
                if(player.getConnectedServerName().equals("Lobby-3")) {
                    assert lobby3 != null;
                    inv.setItem(13, new ItemBuilder(Material.NETHER_STAR).setDisplayName("§aLobby-3").setAmount(lobby3.getOnlineCount()).build());
                }
                if(player.getConnectedServerName().equals("Lobby-4")) {
                    assert lobby4 != null;
                    inv.setItem(14, new ItemBuilder(Material.NETHER_STAR).setDisplayName("§aLobby-4").setAmount(lobby4.getOnlineCount()).build());
                }
                if(player.getConnectedServerName().equals("Lobby-5")) {
                    assert lobby5 != null;
                    inv.setItem(15, new ItemBuilder(Material.NETHER_STAR).setDisplayName("§aLobby-5").setAmount(lobby5.getOnlineCount()).build());
                }
                p.openInventory(inv);
            }
        } else {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInteractdefaultShop(PlayerInteractEvent e) {
        Player p =e.getPlayer();
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(p.getUniqueId());
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR)|| e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(e.getItem().getType().equals(null)) {
             e.setCancelled(true);
            } else if(e.getItem().getItemMeta().getDisplayName().equals(LobbySystem.instance.pr + "§e§lSHOP")){
                if (permissionPlayer.hasPermissionGroup("Builder") || permissionPlayer.hasPermissionGroup("SrBuilder") || permissionPlayer.hasPermissionGroup("Inhaber") || permissionPlayer.hasPermissionGroup("Admin") || permissionPlayer.hasPermissionGroup("SrDev") || permissionPlayer.hasPermissionGroup("Dev") || permissionPlayer.hasPermissionGroup("SrMod") || permissionPlayer.hasPermissionGroup("Mod") || permissionPlayer.hasPermissionGroup("SrSup") || permissionPlayer.hasPermissionGroup("Sup")) {
                    p.sendMessage(LobbySystem.instance.pr +"§cWartungen als Teammitglied§8!");
                } else {
                    p.playSound(p.getLocation(), Sound.LEVEL_UP , 1, 3);
                    Inventory inv = Bukkit.createInventory(null, 3*9, LobbySystem.instance.pr + "§e§lSHOP");

                    ItemStack playerhead = new ItemStack(Material.SKULL_ITEM,1,(short)3);
                    SkullMeta skullmeta = (SkullMeta) playerhead.getItemMeta();
                    skullmeta.setOwner(p.getName());
                    skullmeta.setDisplayName("§7Deine Coins §7● §e" + SQLCoins.getCoins(p.getUniqueId().toString()));
                    playerhead.setItemMeta(skullmeta);

                    setGlas(inv, (short) 15, 0);
                    setGlas(inv, (short) 7, 1);
                    setGlas(inv, (short) 7, 2);
                    setGlas(inv, (short) 15, 3);
                    inv.setItem(4, playerhead);
                    setGlas(inv, (short) 15, 5);
                    setGlas(inv, (short) 7, 6);
                    setGlas(inv, (short) 7, 7);
                    setGlas(inv, (short) 15, 8);
                    setGlas(inv, (short) 7, 9);
                    setGlas(inv, (short) 15, 10);
                    inv.setItem(12,new ItemBuilder(Material.GOLD_INGOT).setDisplayName("§6Premium §7(§e15000§7)").setLore("§7Kaufe dir 1 Monat §6Premium").build());
                    inv.setItem(14,new ItemBuilder(Material.EMERALD).setDisplayName("§bDino §7(§e30000§7)").setLore("§7Kaufe dir 1 Monat §bDino").build());
                    setGlas(inv, (short) 15, 16);
                    setGlas(inv, (short) 7, 17);
                    setGlas(inv, (short) 15, 18);
                    setGlas(inv, (short) 7, 19);
                    setGlas(inv, (short) 7, 20);
                    setGlas(inv, (short) 15, 21);
                    setGlas(inv, (short) 15, 22);
                    setGlas(inv, (short) 15, 23);
                    setGlas(inv, (short) 7, 24);
                    setGlas(inv, (short) 7, 25);
                    setGlas(inv, (short) 15, 26);
                    p.openInventory(inv);
                }
            } else {
                e.setCancelled(true);
            }
                }

    }
    @EventHandler
    public void onInteracttdaily(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        Inventory dailyinv = Bukkit.createInventory(null, 9*3,LobbySystem.instance.pr + "§aTägliche Belöhnung");
        if(e.getRightClicked().getType().equals(EntityType.VILLAGER)) {
            p.openInventory(dailyinv);
        }
    }


    private static void setGlas(Inventory inv, short i, Integer platz) {
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, i);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("");
        itemStack.setItemMeta(itemMeta);
        inv.setItem(platz, itemStack);
    }
    private static void setLobbyItem(ICloudPlayer player ,Inventory inv ,ICloudService iCloudService, String serviceonline,String serviceoffline, Integer platz) {
        if(iCloudService == null) {
            inv.setItem(platz, new ItemBuilder(Material.DEAD_BUSH).setDisplayName(serviceoffline).build());
        } else {
            inv.setItem(platz, new ItemBuilder(Material.CLAY_BALL).setDisplayName(serviceonline).setAmount(iCloudService.getOnlineCount()).build());
        }

    }
}

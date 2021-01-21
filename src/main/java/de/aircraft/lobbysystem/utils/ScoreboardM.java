package de.aircraft.lobbysystem.utils;

import de.aircraft.lobbysystem.LobbySystem;
import de.aircraft.lobbysystem.mysql.coins.SQLCoins;
import de.aircraft.lobbysystem.mysql.onlinetime.SQLOnlineTime;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardM {

    public static void setScoreboard(Player p) {
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(p.getUniqueId());
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = sb.registerNewObjective("aaa","bbb");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§9§lAIR§f§lCRAFT §8● §7Lobby");
        Team coins = sb.registerNewTeam("coins");
        Team onlinetime = sb.registerNewTeam("onlinetime");
        Team playerteam = sb.registerNewTeam("playerteam");

        obj.getScore("").setScore(12);
        obj.getScore("§8 ▌ §7Rank").setScore(11);
        obj.getScore("§f").setScore(10);
        obj.getScore(" ").setScore(9);
        obj.getScore("§8 ▌ §7Coins").setScore(8);
        obj.getScore("§c") .setScore(7);
        obj.getScore("  ").setScore(6);
        obj.getScore("§8 ▌ §7Spielzeit").setScore(5);
        obj.getScore("§4").setScore(4);
        obj.getScore("    ").setScore(3);
        obj.getScore("§8 ▌ §7TeamSpeak").setScore(2);
        obj.getScore("§8× §cWartungen").setScore(1);
        obj.getScore("     ").setScore(0);


        playerteam.addEntry("§f");
        coins.addEntry("§c");
        onlinetime.addEntry("§4");
        coins.setPrefix("§8× §e" + SQLCoins.getCoins(p.getUniqueId().toString()));
        onlinetime.setPrefix("§8× §e"+SQLOnlineTime.getHour(p.getUniqueId().toString()) + " §7H");
        if(permissionPlayer.hasPermissionGroup("Inhaber")) {
            playerteam.setPrefix("§8× §4Inhaber");
        } else if(permissionPlayer.hasPermissionGroup("Admin")) {
            playerteam.setPrefix("§8× §4Admin");
        }  else if(permissionPlayer.hasPermissionGroup("Dev")) {
            playerteam.setPrefix("§8× §3Dev");
        }  else if(permissionPlayer.hasPermissionGroup("Mod")) {
            playerteam.setPrefix("§8× §cMod");
        }  else if(permissionPlayer.hasPermissionGroup("Sup")) {
            playerteam.setPrefix("§8× §bSup");
        }  else if(permissionPlayer.hasPermissionGroup("SrDev")){
            playerteam.setPrefix("§8× §3SrDev");
        } else if(permissionPlayer.hasPermissionGroup("SrMod")){
            playerteam.setPrefix("§8× §cSrMod");
        } else if(permissionPlayer.hasPermissionGroup("SrSup")){
            playerteam.setPrefix("§8× §bSrSup");
        } else if(permissionPlayer.hasPermissionGroup("Dino")){
            playerteam.setPrefix("§8× §bDino");
        } else if(permissionPlayer.hasPermissionGroup("Premium")){
            playerteam.setPrefix("§8× §6Premium");
        } else if(permissionPlayer.hasPermissionGroup("SrBuilder")){
            playerteam.setPrefix("§8× §2SrBuilder");
        } else if(permissionPlayer.hasPermissionGroup("Builder")){
            playerteam.setPrefix("§8× §2Builder");
        }
        if(permissionPlayer.getPermissionGroupInfoList().size() == 1) {
            playerteam.setPrefix("§8» §9Spieler");
        }
        p.setScoreboard(sb);
    }
    public static void updateall(Player p) {
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(p.getUniqueId());
        Scoreboard sb = p.getScoreboard();
        Team coins = sb.getTeam("coins");
        Team onlinetime = sb.getTeam("onlinetime");
        Team playerteam = sb.getTeam("playerteam");
        if(coins != null || playerteam != null) {
            coins.setPrefix("§8× §e" + SQLCoins.getCoins(p.getUniqueId().toString()));
            onlinetime.setPrefix("§8× §e" + SQLOnlineTime.getHour(p.getUniqueId().toString()) + " §7H");
        }
        if(playerteam != null) {
            if (permissionPlayer.hasPermissionGroup("Inhaber")) {
                playerteam.setPrefix("§8× §4Inhaber");
            } else if (permissionPlayer.hasPermissionGroup("Admin")) {
                playerteam.setPrefix("§8× §cAdmin");
            } else if (permissionPlayer.hasPermissionGroup("Dev")) {
                playerteam.setPrefix("§8× §3Dev");
            } else if (permissionPlayer.hasPermissionGroup("Mod")) {
                playerteam.setPrefix("§8× §cMod");
            } else if (permissionPlayer.hasPermissionGroup("Sup")) {
                playerteam.setPrefix("§8× §bSup");
            } else if (permissionPlayer.hasPermissionGroup("SrDev")) {
                playerteam.setPrefix("§8× §3SrDev");
            } else if (permissionPlayer.hasPermissionGroup("SrMod")) {
                playerteam.setPrefix("§8× §cSrMod");
            } else if (permissionPlayer.hasPermissionGroup("SrSup")) {
                playerteam.setPrefix("§8× §bSrSup");
            } else if (permissionPlayer.hasPermissionGroup("Dino")) {
                playerteam.setPrefix("§8× §bDino");
            } else if (permissionPlayer.hasPermissionGroup("Premium")) {
                playerteam.setPrefix("§8× §6Premium");
            } else if(permissionPlayer.hasPermissionGroup("SrBuilder")){
                playerteam.setPrefix("§8× §2SrBuilder");
            } else if(permissionPlayer.hasPermissionGroup("Builder")){
                playerteam.setPrefix("§8× §2Builder");
            }
            if (permissionPlayer.getPermissionGroupInfoList().size() == 1) {
                playerteam.setPrefix("§8» §9Spieler");
            }
        }
    }
    public static void setTab(Player p) {
        Scoreboard sb = p.getScoreboard();
        Team Inhaber = sb.registerNewTeam("000Inhaber");
        Team Admin = sb.registerNewTeam("001Admin");
        Team SrDev = sb.registerNewTeam("002SrDev");
        Team Dev = sb.registerNewTeam("003Dev");
        Team SrMod = sb.registerNewTeam("004SrMod");
        Team Mod = sb.registerNewTeam("005Mod");
        Team SrBuilder = sb.registerNewTeam("006SrBuilder");
        Team Builder = sb.registerNewTeam("007Builder");
        Team SrSup = sb.registerNewTeam("008SrSup");
        Team Sup = sb.registerNewTeam("009Sup");
        Team Dino = sb.registerNewTeam("010Dino");
        Team Premium = sb.registerNewTeam("011Premium");
        Team spieler = sb.registerNewTeam("012spieler");
        Inhaber.setPrefix("§4I §8• §7");
        Admin.setPrefix("§cA §8• §7");
        SrDev.setPrefix("§3SrD §8• §7");
        Dev.setPrefix("§3D §8• §7");
        SrMod.setPrefix("§cSrM §8• §7");
        Mod.setPrefix("§cM §8• §7");
        SrSup.setPrefix("§bSrS §8• §7");
        Sup.setPrefix("§BS §8• §7");
        Dino.setPrefix("§bD §8• §7");
        Premium.setPrefix("§6P §8• §7");
        spieler.setPrefix("§9S §8• §7");
        Builder.setPrefix("§2B §8• §7");
        SrBuilder.setPrefix("§2SrB §8• §7");
        Bukkit.getOnlinePlayers().forEach(player -> {
            IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());
            if(permissionPlayer.hasPermissionGroup("Inhaber")) {
                Inhaber.addEntry(player.getName());
            } else if(permissionPlayer.hasPermissionGroup("Admin")) {
                Admin.addEntry(player.getName());
            }  else if(permissionPlayer.hasPermissionGroup("Dev")) {
                Dev.addEntry(player.getName());
            }  else if(permissionPlayer.hasPermissionGroup("Mod")) {
                Mod.addEntry(player.getName());
            }  else if(permissionPlayer.hasPermissionGroup("Sup")) {
                Sup.addEntry(player.getName());
            }  else if(permissionPlayer.hasPermissionGroup("SrDev")){
                SrDev.addEntry(player.getName());
            } else if(permissionPlayer.hasPermissionGroup("SrMod")){
                SrMod.addEntry(player.getName());
            } else if(permissionPlayer.hasPermissionGroup("SrSup")){
                SrSup.addEntry(player.getName());
            } else if(permissionPlayer.hasPermissionGroup("Dino")){
                Dino.addEntry(player.getName());
            } else if(permissionPlayer.hasPermissionGroup("Premium")) {
                Premium.addEntry(player.getName());
            }  else if(permissionPlayer.hasPermissionGroup("SrBuilder")) {
                SrBuilder.addEntry(player.getName());
            }  else if(permissionPlayer.hasPermissionGroup("Builder")) {
                Builder.addEntry(player.getName());
            }
            if(permissionPlayer.getPermissionGroupInfoList().size() == 1) {
                spieler.addEntry(player.getName());
            }
        });

    }
    public static void updateTab(Player p) {
        Scoreboard sb = p.getScoreboard();
        Team Inhaber = sb.getTeam("000Inhaber");
        Team Admin = sb.getTeam("001Admin");
        Team SrDev = sb.getTeam("002SrDev");
        Team Dev = sb.getTeam("003Dev");
        Team SrMod = sb.getTeam("004SrMod");
        Team Mod = sb.getTeam("005Mod");
        Team SrBuilder = sb.getTeam("006SrBuilder");
        Team Builder = sb.getTeam("007Builder");
        Team SrSup = sb.getTeam("008SrSup");
        Team Sup = sb.getTeam("009Sup");
        Team Dino = sb.getTeam("010Dino");
        Team Premium = sb.getTeam("011Premium");
        Team spieler = sb.getTeam("012spieler");
        if(SrBuilder == null|| Builder == null ||Inhaber == null ||Admin == null ||SrDev == null ||Dev == null ||SrMod == null ||Mod == null ||SrSup == null ||Sup == null ||Dino == null ||Premium == null ||spieler == null) {
            setTab(p);
            return;
        }
        Bukkit.getOnlinePlayers().forEach(player -> {
            IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());
            if(permissionPlayer.hasPermissionGroup("Inhaber")) {
                Inhaber.addEntry(player.getName());
            } else if(permissionPlayer.hasPermissionGroup("Admin")) {
                Admin.addEntry(player.getName());
            }  else if(permissionPlayer.hasPermissionGroup("Dev")) {
                Dev.addEntry(player.getName());
            }  else if(permissionPlayer.hasPermissionGroup("Mod")) {
                Mod.addEntry(player.getName());
            }  else if(permissionPlayer.hasPermissionGroup("Sup")) {
                Sup.addEntry(player.getName());
            }  else if(permissionPlayer.hasPermissionGroup("SrDev")){
                SrDev.addEntry(player.getName());
            } else if(permissionPlayer.hasPermissionGroup("SrMod")){
                SrMod.addEntry(player.getName());
            } else if(permissionPlayer.hasPermissionGroup("SrSup")){
                SrSup.addEntry(player.getName());
            } else if(permissionPlayer.hasPermissionGroup("Dino")){
                Dino.addEntry(player.getName());
            } else if(permissionPlayer.hasPermissionGroup("Premium")) {
                Premium.addEntry(player.getName());
            }  else if(permissionPlayer.hasPermissionGroup("SrBuilder")) {
                SrBuilder.addEntry(player.getName());
            }  else if(permissionPlayer.hasPermissionGroup("Builder")) {
                Builder.addEntry(player.getName());
            }
            if(permissionPlayer.getPermissionGroupInfoList().size() == 1) {
                spieler.addEntry(player.getName());
            }
        });

    }
}


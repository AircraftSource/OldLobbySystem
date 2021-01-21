package de.aircraft.lobbysystem.cmds;

import de.aircraft.lobbysystem.LobbySystem;
import de.aircraft.lobbysystem.utils.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(!(s instanceof Player)) {
            s.sendMessage(LobbySystem.instance.pr + "§cDieser Kommand darf nur ein §7Spieler §cbenutzen§8.");
        } else {
            Player p = (Player) s;
            if(p.hasPermission("aircraft.lobby.setup")) {
                if(args.length == 0) {
                    p.sendMessage(LobbySystem.instance.pr + "§7/setup §8<§7setspawn§8>");
                    p.sendMessage(LobbySystem.instance.pr + "§7/setup §8<§7setloc§8> §8<§7Zahl§8>");
                } else if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("setspawn")) {
                        LocationManager.setLocation("spawn",p.getLocation());
                        p.sendMessage(LobbySystem.instance.pr + "§aDer Spawn wurde gesetzt§8.");
                    } else {
                        p.sendMessage(LobbySystem.instance.pr + "§7/setup §8<§7setspawn§8>");
                        p.sendMessage(LobbySystem.instance.pr + "§7/setup §8<§7setloc§8> §8<§7Zahl§8>");
                    }
                } else if(args.length == 2) {
                    if (args[0].equalsIgnoreCase("setloc")) {
                        try {
                            LocationManager.setLocation(args[1],p.getLocation());
                            p.sendMessage(LobbySystem.instance.pr + "§aDer Loc §7" + args[1] + "§a wurde gesetzt§8.");
                        }catch (NumberFormatException e) {
                            p.sendMessage(LobbySystem.instance.pr +"§cBitte gebe eine Zahl ein§8.");
                        }
                    }
                }
            } else {
                p.sendMessage(LobbySystem.instance.noperms);
            }
        }
        return false;
    }
}

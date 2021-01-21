package de.aircraft.lobbysystem.mysql.coins;

import de.aircraft.lobbysystem.LobbySystem;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLCoins {

    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = LobbySystem.instance.coinMySQL.query("SELECT * FROM coins WHERE UUID= '" + uuid + "'");
            if (rs.next())
                return (rs.getString("UUID") != null);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void createPlayer(String uuid) {
        if (!playerExists(uuid))
            LobbySystem.instance.coinMySQL.update("INSERT INTO coins(UUID, COINS) VALUES ('" + uuid + "', '0');");
    }

    public static Integer getCoins(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = LobbySystem.instance.coinMySQL.query("SELECT * FROM coins WHERE UUID= '" + uuid + "'");
                if (!rs.next() || Integer.valueOf(rs.getInt("COINS")).intValue() == 0);
                i = Integer.valueOf(rs.getInt("COINS"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getCoins(uuid);
        }
        return i;
    }

    public static void setCoins(String uuid, Integer coins) {
        if (playerExists(uuid)) {
            LobbySystem.instance.coinMySQL.update("UPDATE coins SET COINS= '" + coins + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setCoins(uuid, coins);
        }
    }

    public static void addCoins(String uuid, Integer coins) {
        if (playerExists(uuid)) {
            setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() + coins.intValue()));
        } else {
            createPlayer(uuid);
            addCoins(uuid, coins);
        }
    }

    public static void removeCoins(String uuid, Integer coins) {
        if (playerExists(uuid)) {
            setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() - coins.intValue()));
        } else {
            createPlayer(uuid);
            removeCoins(uuid, coins);
        }
    }
    public static void clearCoins(String uuid) {
        if (playerExists(uuid)) {
            setCoins(uuid,0);
        } else {
            createPlayer(uuid);
             clearCoins(uuid);
        }
    }
}

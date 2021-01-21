package de.aircraft.lobbysystem.mysql.onlinetime;

import de.aircraft.lobbysystem.LobbySystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLOnlineTime {
    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = LobbySystem.instance.onlineMySQL.query("SELECT * FROM onlinetime WHERE UUID= '" + uuid + "'");
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
            LobbySystem.instance.onlineMySQL.update("INSERT INTO onlinetime(UUID , HOUR, MIN , SEC) VALUES ('" + uuid + "', '0','0','0');");
    }
    public static Integer getHour(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = LobbySystem.instance.onlineMySQL.query("SELECT * FROM onlinetime WHERE UUID= '" + uuid + "'");
                if (!rs.next() || Integer.valueOf(rs.getInt("HOUR")).intValue() == 0);
                i = Integer.valueOf(rs.getInt("HOUR"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getHour(uuid);
        }
        return i;
    }
    public static Integer getMin(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = LobbySystem.instance.onlineMySQL.query("SELECT * FROM onlinetime WHERE UUID= '" + uuid + "'");
                if (!rs.next() || Integer.valueOf(rs.getInt("MIN")).intValue() == 0);
                i = Integer.valueOf(rs.getInt("MIN"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getMin(uuid);
        }
        return i;
    }
    public static Integer getSec(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = LobbySystem.instance.onlineMySQL.query("SELECT * FROM onlinetime WHERE UUID= '" + uuid + "'");
                if (!rs.next() || Integer.valueOf(rs.getInt("SEC")).intValue() == 0);
                i = Integer.valueOf(rs.getInt("SEC"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getSec(uuid);
        }
        return i;
    }
    public static void setHour(String uuid, Integer hour) {
        if (playerExists(uuid)) {
            LobbySystem.instance.onlineMySQL.update("UPDATE onlinetime SET HOUR= '" + hour + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setHour(uuid, hour);
        }
    }
    public static void setMin(String uuid, Integer min) {
        if (playerExists(uuid)) {
            LobbySystem.instance.onlineMySQL.update("UPDATE onlinetime SET MIN= '" + min + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setMin(uuid, min);
        }
    }
    public static void setSEC(String uuid, Integer sec) {
        if (playerExists(uuid)) {
            LobbySystem.instance.onlineMySQL.update("UPDATE onlinetime SET SEC= '" + sec + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setSEC(uuid, sec);
        }
    }
    public static void addHour(String uuid, Integer coins) {
        if (playerExists(uuid)) {
            setHour(uuid, Integer.valueOf(getHour(uuid).intValue() + coins.intValue()));
        } else {
            createPlayer(uuid);
            addHour(uuid, coins);
        }
    }
    public static void addMin(String uuid, Integer coins) {
        if (playerExists(uuid)) {
            setMin(uuid, Integer.valueOf(getMin(uuid).intValue() + coins.intValue()));
        } else {
            createPlayer(uuid);
            addMin(uuid, coins);
        }
    }
    public static void addSec(String uuid, Integer coins) {
        if (playerExists(uuid)) {
            setSEC(uuid, Integer.valueOf(getSec(uuid).intValue() + coins.intValue()));
        } else {
            createPlayer(uuid);
            addSec(uuid, coins);
        }
    }
}

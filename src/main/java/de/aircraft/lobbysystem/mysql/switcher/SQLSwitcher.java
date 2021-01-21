package de.aircraft.lobbysystem.mysql.switcher;

import de.aircraft.lobbysystem.LobbySystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLSwitcher {

    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = LobbySystem.instance.swmysql.query("SELECT * FROM switcher WHERE UUID= '" + uuid + "'");
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
            LobbySystem.instance.swmysql.update("INSERT INTO switcher(UUID, STATE) VALUES ('" + uuid + "', '3');");
    }

    public static Integer getState(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = LobbySystem.instance.swmysql.query("SELECT * FROM switcher WHERE UUID= '" + uuid + "'");
                if (!rs.next() || Integer.valueOf(rs.getInt("STATE")).intValue() == 0);
                i = Integer.valueOf(rs.getInt("STATE"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getState(uuid);
        }
        return i;
    }

    public static void setState(String uuid, Integer state) {
        if (playerExists(uuid)) {
            LobbySystem.instance.swmysql.update("UPDATE switcher SET STATE= '" + state + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setState(uuid, state);
        }
    }
}

package de.lcraft.api.plugin.modules.java.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLServerHelper {

    private MySQLServer server;

    public MySQLServerHelper(MySQLServer server) {
        this.server = server;
    }

    public PreparedStatement makeStatement(String sql) throws SQLException {
        return server.getConnection().prepareStatement(sql);
    }

    public MySQLServer getServer() {
        return server;
    }

}

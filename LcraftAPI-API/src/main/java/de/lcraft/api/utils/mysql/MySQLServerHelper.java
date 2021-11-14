package de.lcraft.api.utils.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLServerHelper {

    private MySQLServer server;

    public MySQLServerHelper(MySQLServer server) {
        this.server = server;
    }

    public PreparedStatement makeStatement(String sql) throws SQLException {
        return server.getConnection().prepareStatement(sql);
    }
    public boolean executeUpdate(String query) {
        boolean flag = false;
        try (Statement statement = getServer().getConnection().createStatement()) {
            if (statement != null) {
                statement.executeUpdate(query);
                flag = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    public MySQLServer getServer() {
        return server;
    }

}

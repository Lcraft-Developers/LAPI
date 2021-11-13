package de.lcraft.api.plugin.modules.java.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLServer {

    private String ip,
                   port,
                   username,
                   password;
    private Connection connection;
    private boolean isConnected = false;
    private MySQLServerHelper helper;

    public MySQLServer(String ip, String port) {
        this.ip = ip;
        this.port = port;
        helper = new MySQLServerHelper(this);
    }

    public void connectToUser(String username, String password) throws SQLException {
        this.username = username;
        this.password = password;

        connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/mydb", this.username, this.password);

        isConnected = true;
    }

    public void reconnect() throws SQLException {
        if(this.username != null && this.password != null && ip != null && port != null && !isConnected) {
            connectToUser(this.username, this.password);
        }
    }

    public void stopConnection() throws SQLException {
        if(connection != null && isConnected) {
            connection.close();

            isConnected = false;
            connection = null;
        }
    }

    public Connection getConnection() {
        return connection;
    }
    public String getIp() {
        return ip;
    }
    public String getPort() {
        return port;
    }
    public String getUsername() {
        return username;
    }
    public String getIpWithPort() {
        return ip + ":" + port;
    }
    public MySQLServerHelper getHelper() {
        return helper;
    }

}

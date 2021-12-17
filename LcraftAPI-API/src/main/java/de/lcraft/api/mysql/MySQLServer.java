package de.lcraft.api.mysql;

import java.sql.*;

public class MySQLServer {

    private String ip,
                   port,
                   username,
                   password;
    private Connection connection;
    private boolean isConnected = false;

    public MySQLServer(String ip, String port) {
        this.ip = ip;
        this.port = port;
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

    public PreparedStatement makeStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }
    public boolean executeUpdate(String query) {
        boolean flag = false;
        try (Statement statement = getConnection().createStatement()) {
            if (statement != null) {
                statement.executeUpdate(query);
                flag = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
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

}

package de.lcraft.api.mysql;

import java.sql.*;
import java.util.HashMap;

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

    public void createTable(String name, HashMap<String, String> values) {
        String query = "create table " + name + "(";
        int i = 0;
        for(String key : values.keySet()) {
            i++;
            String value = values.get(key);
            if(values.size() > i) {
                query = query + key + "   " + value + ", ";
            } else {
                query = query + key + "   " + value + ");";
            }
        }
        executeUpdate(query);
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

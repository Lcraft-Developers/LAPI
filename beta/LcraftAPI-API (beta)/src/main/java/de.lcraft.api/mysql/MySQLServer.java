package de.lcraft.api.mysql;

import java.sql.*;
import java.util.HashMap;
import java.util.Objects;

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

    public void connectToUser(String username, String password) {
        this.username = username;
        this.password = password;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/mydb", this.username, this.password);
            isConnected = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void reconnect() {
        if(Objects.nonNull(this.username) && Objects.nonNull(this.password) && Objects.nonNull(ip) && Objects.nonNull(port) && !isConnected) {
            connectToUser(this.username, this.password);
        }
    }
    public void stopConnection() {
        if(Objects.nonNull(connection) && isConnected) {
            try {
                connection.close();

                isConnected = false;
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public PreparedStatement makeStatement(String sql) {
        try {
            return getConnection().prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public boolean executeUpdate(String query) {
        boolean flag = false;
        try (Statement statement = getConnection().createStatement()) {
            if (Objects.nonNull(statement)) {
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
            continue;
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

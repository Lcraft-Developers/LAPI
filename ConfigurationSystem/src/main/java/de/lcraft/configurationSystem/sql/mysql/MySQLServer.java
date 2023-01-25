package de.lcraft.configurationSystem.sql.mysql;

import de.lcraft.configurationSystem.sql.api.*;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLServer implements SQLServer {

    private Connection connection;
    private MysqlDataSource mysqlDataSource;
    private String host;
    private int port;
    private String user;
    private String password;

    public MySQLServer(String host, int port, String user, String password) {
        init(host, port, user, password);
    }
    public MySQLServer(int port, String user, String password) {
        init(null, port, user, password);
    }
    public MySQLServer(String host, String user, String password) {
        init(host, null, user, password);
    }
    public MySQLServer(String user, String password) {
        init(null, null, user, password);
    }
    private void init(String host, Integer port, String userName, String password) {
        if(host != null) setHost(host);
        else setHost("localhost");

        if(port != null) setPort(port);
        else setPort(3306);

        if(userName != null) setUser(userName);
        else setUser("");

        if(password != null) setPassword(password);
        else setPassword("");

        initDataSource();
    }
    private void initDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        mysqlDataSource.setServerName(getHost());
        mysqlDataSource.setPort(getPort());
        mysqlDataSource.setUser(getUser());
        mysqlDataSource.setPassword(getPassword());

        setMysqlDataSource(mysqlDataSource);
    }

    @Override
    public void connect() {
        if(!isConnected()) {
            try {
                setConnection(getMysqlDataSource().getConnection());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            endConnection();
            connect();
        }
    }
    @Override
    public void endConnection() {
        if(isConnected()) {
            try {
                getConnection().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void executeCommand(SQLCommand cmd) {
        execute(cmd.createSQL(this));
    }
    @Override
    public void execute(String[] sqlArray) {
        for(String sql : sqlArray) {
            execute(sql);
        }
    }
    @Override
    public void execute(String sql) {
        if(isConnected()) {
            try {
                Statement statement = getConnection().createStatement();
                statement.execute(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }



    @Override
    public boolean isConnected() {
        try {
            return getConnection() != null && !getConnection().isClosed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public MysqlDataSource getMysqlDataSource() {
        return mysqlDataSource;
    }
    private void setMysqlDataSource(MysqlDataSource mysqlDataSource) {
        this.mysqlDataSource = mysqlDataSource;
    }
    public String getHost() {
        return host;
    }
    private void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    private void setPort(int port) {
        this.port = port;
    }
    public String getUser() {
        return user;
    }
    private void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    private void setPassword(String password) {
        this.password = password;
    }

}

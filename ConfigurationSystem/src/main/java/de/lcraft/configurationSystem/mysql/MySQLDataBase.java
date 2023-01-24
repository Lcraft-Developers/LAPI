package de.lcraft.configurationSystem.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.*;

public class MySQLDataBase {

    private MySQLServer mySQLServer;
    private String databaseName;
    private Connection connection;

    public MySQLDataBase(MySQLServer mySQLServer, String databaseName) {
        setMySQLServer(mySQLServer);
        setDatabaseName(databaseName);
    }
    public void reBuildConnection() {
        if(isConnected()) quitConnection();
        MysqlDataSource mysqlDataSource = getMySQLServer().getServerConnection();
        mysqlDataSource.setDatabaseName(getDatabaseName());

        try {
            setConnection(mysqlDataSource.getConnection());
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }
    public void quitConnection() {
        try {if(isConnected()) getConnection().isClosed();} catch (SQLException exception) {exception.printStackTrace();}
        setConnection(null);
    }
    public boolean isConnected() {
        if(getConnection() == null) return false;
        try {if(getConnection().isClosed()) return false;} catch (SQLException exception) {exception.printStackTrace();}
        return true;
    }

    public void executeCommand(Command cmd) {
        try {
            execute(cmd.createSQL(this));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void execute(String[] sqlArray) throws SQLException {
        if(isConnected()) {
            for(String sql : sqlArray) {
                execute(sql);
            }
        } else {
            throw new SQLException("No connection to the MySQL Database: " + System.lineSeparator() + this);
        }
    }
    public void execute(String sql) throws SQLException {
        if(isConnected()) {
            Statement statement = getConnection().createStatement();
            statement.execute(sql);
        } else {
            throw new SQLException("No connection to the MySQL Database: " + System.lineSeparator() + this);
        }
    }

    private void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    private void setMySQLServer(MySQLServer mySQLServer) {
        this.mySQLServer = mySQLServer;
    }
    private void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getDatabaseName() {
        return databaseName;
    }
    public MySQLServer getMySQLServer() {
        return mySQLServer;
    }
    public Connection getConnection() {
        return connection;
    }
    @Override
    public String toString() {
        return "MySQLDataBase{" +
                "mySQLServer=" + mySQLServer +
                ", databaseName='" + databaseName + '\'' +
                '}';
    }

}

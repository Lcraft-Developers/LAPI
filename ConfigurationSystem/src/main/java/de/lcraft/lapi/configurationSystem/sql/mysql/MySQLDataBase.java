package de.lcraft.lapi.configurationSystem.sql.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;
import de.lcraft.lapi.configurationSystem.api.sql.SQLCommand;
import de.lcraft.lapi.configurationSystem.api.sql.SQLDataBase;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDataBase implements SQLDataBase {

    private String dataBaseName;
    private Connection connection;
    private MysqlDataSource mysqlDataSource;

    public MySQLDataBase(String dataBaseName, MysqlDataSource mysqlDataSource) {
        init(dataBaseName, mysqlDataSource);
    }
    private void init(String dataBaseName, MysqlDataSource mysqlDataSource) {
        if(dataBaseName != null) setDataBaseName(dataBaseName);
        else setDataBaseName("");

        if(mysqlDataSource != null) {
            mysqlDataSource.setDatabaseName(getDataBaseName());
            setMysqlDataSource(mysqlDataSource);
        }
        else setMysqlDataSource(new MysqlDataSource());
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

    public String getDataBaseName() {
        return dataBaseName;
    }
    private void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
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

}

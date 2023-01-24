package de.lcraft.configurationSystem.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.ArrayList;

public class MySQLServer {

    private String host;
    private String user;
    private String password;
    private int port;
    private ArrayList<MySQLDataBase> mySQLDataBases;

    public MySQLServer(String host, int port, String user, String password) {
        setHost(host);
        setPort(port);
        setUser(user);
        setPassword(password);
        setMySQLDataBases(new ArrayList<>());
    }
    public MySQLServer(int port, String user, String password) {
        this("localhost", port, user, password);
    }
    public MySQLServer(String host, String user, String password) {
        this(host, 3306, user, password);
    }
    public MySQLServer(String user, String password) {
        this("localhost", 3306, user, password);
    }

    public MySQLDataBase connectToDatabase(String dataBaseName) {
        return new MySQLDataBase(this ,dataBaseName);
    }

    private void setHost(String host) {
        this.host = host;
    }
    private void setPort(int port) {
        this.port = port;
    }
    private void setUser(String user) {
        this.user = user;
    }
    private void setPassword(String password) {
        this.password = password;
    }
    private void setMySQLDataBases(ArrayList<MySQLDataBase> mySQLDataBases) {
        this.mySQLDataBases = mySQLDataBases;
    }

    public MysqlDataSource getServerConnection() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName(getHost());
        mysqlDataSource.setPort(getPort());
        mysqlDataSource.setUser(getUser());
        mysqlDataSource.setPassword(getPassword());

        return mysqlDataSource;
    }
    public String getHost() {
        return host;
    }
    public int getPort() {
        return port;
    }
    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }
    public ArrayList<MySQLDataBase> getMySQLDataBases() {
        return mySQLDataBases;
    }
    @Override
    public String toString() {
        return "MySQLServer{" +
                "host='" + host + '\'' +
                ", user='" + user + '\'' +
                ", port=" + port +
                '}';
    }

    public static String replaceEnd(String str, String replace, String replaced) {
        int startIndex = str.indexOf(replace);
        int lastIndex = startIndex + replace.length();

        StringBuilder stringBuilder = new StringBuilder(str);
        if(startIndex > -1) stringBuilder.replace(startIndex, lastIndex, replaced);
        return stringBuilder.toString();
    }
}

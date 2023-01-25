package de.lcraft.configurationSystem.sql.api;

public interface SQLDataBase extends SQLCommandSender {

    void connect();
    void endConnection();
    boolean isConnected();

}

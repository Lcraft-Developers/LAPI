package de.lcraft.api.configurationSystem.api.sql;

public interface SQLDataBase extends SQLCommandSender {

    void connect();
    void endConnection();
    boolean isConnected();

}

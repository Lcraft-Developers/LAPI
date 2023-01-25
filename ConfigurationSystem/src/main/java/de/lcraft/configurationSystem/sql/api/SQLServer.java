package de.lcraft.configurationSystem.sql.api;

public interface SQLServer extends SQLCommandSender {

    void connect();
    void endConnection();
    boolean isConnected();

}

package de.lcraft.api.configurationSystem.api.sql;

public interface SQLServer extends SQLCommandSender {

    void connect();
    void endConnection();
    boolean isConnected();

}

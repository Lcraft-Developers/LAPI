package de.lcraft.lapi.configurationSystem.api.sql;

public interface SQLCommandSender {

    void executeCommand(SQLCommand cmd);
    void execute(String[] sqlArray);
    void execute(String sql);

}

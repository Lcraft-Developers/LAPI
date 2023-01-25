package de.lcraft.configurationSystem.sql.api;

public interface SQLCommandSender {

    void executeCommand(SQLCommand cmd);
    void execute(String[] sqlArray);
    void execute(String sql);

}

package de.lcraft.lapi.configurationSystem.api.sql;

public interface SQLCommand {

    String[] createSQL(SQLCommandSender SQLCommandSender);
    String[] createSQL(SQLServer SQLServer);
    String[] createSQL(SQLDataBase SQLDataBase);

}

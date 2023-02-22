package de.lcraft.api.configurationSystem.api.sql;

public interface SQLCommand {

    String[] createSQL(SQLCommandSender SQLCommandSender);
    String[] createSQL(SQLServer SQLServer);
    String[] createSQL(SQLDataBase SQLDataBase);

}

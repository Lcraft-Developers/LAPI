package de.lcraft.configurationSystem.sql.api;

public interface SQLCommand {

    String[] createSQL(SQLCommandSender SQLCommandSender);
    String[] createSQL(SQLServer SQLServer);
    String[] createSQL(SQLDataBase SQLDataBase);

}

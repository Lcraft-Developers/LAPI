package de.lcraft.configurationSystem.mysql;

public interface Command {

    String[] createSQL(MySQLDataBase mySQLDataBase);

}

package de.lcraft.configurationSystem.mysql.commands;

import de.lcraft.configurationSystem.mysql.Command;
import de.lcraft.configurationSystem.mysql.MySQLDataBase;

public class deleteTable implements Command {

    private String tableName;

    public deleteTable(String tableName) {
        setTableName(tableName);
    }
    @Override
    public String[] createSQL(MySQLDataBase mySQLDataBase) {
        return new String[]{"DROP TABLE " + getTableName()};
    }

    public String getTableName() {
        return tableName;
    }
    private void setTableName(String name) {
        this.tableName = name;
    }

}

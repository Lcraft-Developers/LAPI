package de.lcraft.configurationSystem.sql.commands;

import de.lcraft.configurationSystem.sql.api.SQLCommand;
import de.lcraft.configurationSystem.sql.api.SQLCommandSender;
import de.lcraft.configurationSystem.sql.api.SQLDataBase;
import de.lcraft.configurationSystem.sql.api.SQLServer;

public class deleteTable implements SQLCommand {

    private String tableName;

    public deleteTable(String tableName) {
        init(tableName);
    }
    private void init(String tableName) {
        if(tableName != null) setTableName(tableName);
        else setTableName("");
    }

    @Override
    public String[] createSQL(SQLCommandSender SQLCommandSender) {
        return new String[0];
    }
    @Override
    public String[] createSQL(SQLServer SQLServer) {
        return new String[0];
    }
    @Override
    public String[] createSQL(SQLDataBase SQLDataBase) {
        return new String[]{"DROP TABLE " + getTableName()};
    }

    public String getTableName() {
        return tableName;
    }
    private void setTableName(String tableName) {
        this.tableName = tableName;
    }

}

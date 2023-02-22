package de.lcraft.api.configurationSystem.sql.commands;

import de.lcraft.api.configurationSystem.api.sql.SQLCommand;
import de.lcraft.api.configurationSystem.api.sql.SQLCommandSender;
import de.lcraft.api.configurationSystem.api.sql.SQLServer;
import de.lcraft.api.configurationSystem.api.sql.SQLDataBase;

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

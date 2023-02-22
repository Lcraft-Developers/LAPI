package de.lcraft.api.configurationSystem.sql.commands;

import de.lcraft.api.configurationSystem.api.sql.SQLCommand;
import de.lcraft.api.configurationSystem.api.sql.SQLCommandSender;
import de.lcraft.api.configurationSystem.api.sql.SQLServer;
import de.lcraft.api.javaUtils.StringUtils;
import de.lcraft.api.configurationSystem.api.sql.SQLDataBase;

import java.util.ArrayList;
import java.util.List;

public class createTable implements SQLCommand {

    private String tableName;
    private List<String[]> data;

    public createTable(String tableName, List<String[]> data) {
        init(tableName, data);
    }
    public createTable(String tableName) {
        init(tableName, null);
    }
    private void init(String tableName, List<String[]> data) {
        if(tableName != null) setTableName(tableName);
        else setTableName("");

        if(data != null) setData(data);
        else setData(new ArrayList<>());
    }

    public void addColumn(String id, String type) {
        setColumn(data.size(), id, type);
    }
    public void setColumn(int index, String id, String type) {
        getData().set(index, new String[]{id, type});
    }
    public void removeColumn(int index) {
        getData().remove(index);
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
        String sql = "CREATE TABLE " + getTableName() + " (";

        for(String[] c : getData()) {
            String id = c[0];
            String type = c[1];

            sql = sql + id + " " + type + ",";
        }

        return new String[]{StringUtils.replaceLast(sql, ",", "") + ")"};
    }

    public String getTableName() {
        return tableName;
    }
    private void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public List<String[]> getData() {
        return data;
    }
    private void setData(List<String[]> data) {
        this.data = data;
    }

}

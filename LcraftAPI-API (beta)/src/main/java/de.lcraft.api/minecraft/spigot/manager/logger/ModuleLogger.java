package de.lcraft.api.minecraft.spigot.manager.logger;

import org.bukkit.Bukkit;

public class ModuleLogger {

    private String moduleName;

    public ModuleLogger(String moduleName) {
        this.moduleName = moduleName;
    }
    public void send(ModuleLoggerType type, String msg) {
        if(type == ModuleLoggerType.ERROR) {
            Bukkit.getConsoleSender().sendMessage("ERROR >> " + msg);
        } else if(type == ModuleLoggerType.WARNING) {
            Bukkit.getConsoleSender().sendMessage("WARNING >> " + msg);
        } else if(type == ModuleLoggerType.INFO) {
            Bukkit.getConsoleSender().sendMessage("INFO >> " + msg);
        } else if(type == ModuleLoggerType.NOTHING) {
            Bukkit.getConsoleSender().sendMessage(msg);
        }
    }
    public void sendModule(ModuleLoggerType type, String msg) {
        if(type == ModuleLoggerType.ERROR) {
            Bukkit.getConsoleSender().sendMessage("[" + moduleName + "] " + "ERROR >> " + msg);
        } else if(type == ModuleLoggerType.WARNING) {
            Bukkit.getConsoleSender().sendMessage("[" + moduleName + "] " + "WARNING >> " + msg);
        } else if(type == ModuleLoggerType.INFO) {
            Bukkit.getConsoleSender().sendMessage("[" + moduleName + "] " + "INFO >> " + msg);
        } else if(type == ModuleLoggerType.NOTHING) {
            Bukkit.getConsoleSender().sendMessage("[" + moduleName + "] " + msg);
        }
    }

}

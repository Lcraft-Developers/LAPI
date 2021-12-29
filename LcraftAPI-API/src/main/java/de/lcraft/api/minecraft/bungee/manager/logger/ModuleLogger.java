package de.lcraft.api.minecraft.bungee.manager.logger;

import net.md_5.bungee.api.ProxyServer;

public class ModuleLogger {

    private String moduleName;

    public ModuleLogger(String moduleName) {
        this.moduleName = moduleName;
    }
    public void send(ModuleLoggerType type, String msg) {
        if(type == ModuleLoggerType.ERROR) {
            ProxyServer.getInstance().getConsole().sendMessage("ERROR >> " + msg);
        } else if(type == ModuleLoggerType.WARNING) {
            ProxyServer.getInstance().getConsole().sendMessage("WARNING >> " + msg);
        } else if(type == ModuleLoggerType.INFO) {
            ProxyServer.getInstance().getConsole().sendMessage("INFO >> " + msg);
        } else if(type == ModuleLoggerType.NOTHING) {
            ProxyServer.getInstance().getConsole().sendMessage(msg);
        }
    }
    public void sendModule(ModuleLoggerType type, String msg) {
        if(type == ModuleLoggerType.ERROR) {
            ProxyServer.getInstance().getConsole().sendMessage("[" + moduleName + "] " + "ERROR >> " + msg);
        } else if(type == ModuleLoggerType.WARNING) {
            ProxyServer.getInstance().getConsole().sendMessage("[" + moduleName + "] " + "WARNING >> " + msg);
        } else if(type == ModuleLoggerType.INFO) {
            ProxyServer.getInstance().getConsole().sendMessage("[" + moduleName + "] " + "INFO >> " + msg);
        } else if(type == ModuleLoggerType.NOTHING) {
            ProxyServer.getInstance().getConsole().sendMessage("[" + moduleName + "] " + msg);
        }
    }

}

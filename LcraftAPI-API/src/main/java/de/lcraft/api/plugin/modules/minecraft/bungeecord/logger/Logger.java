package de.lcraft.api.plugin.modules.minecraft.bungeecord.logger;

import net.md_5.bungee.api.ProxyServer;

public class Logger {

    private String moduleName = null;
    
    public Logger() {}
    public Logger(String moduleName) {
        this.moduleName = moduleName;
    }

    public void send(LoggerType type, String msg) {
        if(type == LoggerType.ERROR) {
            ProxyServer.getInstance().getConsole().sendMessage("ERROR >> " + msg);
        } else if(type == LoggerType.WARNING) {
            ProxyServer.getInstance().getConsole().sendMessage("WARNING >> " + msg);
        } else if(type == LoggerType.INFO) {
            ProxyServer.getInstance().getConsole().sendMessage("INFO >> " + msg);
        } else if(type == LoggerType.NOTHING) {
            ProxyServer.getInstance().getConsole().sendMessage(msg);
        }
    }

    public void sendModule(LoggerType type, String msg) {
        if(type == LoggerType.ERROR) {
            ProxyServer.getInstance().getConsole().sendMessage("[" + moduleName + "] " + "ERROR >> " + msg);
        } else if(type == LoggerType.WARNING) {
            ProxyServer.getInstance().getConsole().sendMessage("[" + moduleName + "] " + "WARNING >> " + msg);
        } else if(type == LoggerType.INFO) {
            ProxyServer.getInstance().getConsole().sendMessage("[" + moduleName + "] " + "INFO >> " + msg);
        } else if(type == LoggerType.NOTHING) {
            ProxyServer.getInstance().getConsole().sendMessage("[" + moduleName + "] " + msg);
        }
    }

}

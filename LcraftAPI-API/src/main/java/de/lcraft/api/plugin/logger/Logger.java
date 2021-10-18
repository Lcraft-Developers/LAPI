package de.lcraft.api.plugin.logger;

import org.bukkit.Bukkit;

public class Logger {

    private String moduleName = null;

    public Logger() {}
    public Logger(String moduleName) {this.moduleName = moduleName;}

    public void send(LoggerType type, LoggerPlace place, String msg) {
        //if(moduleName != null)  sendModule(type, place, msg);
        if(type == LoggerType.ERROR) {
            if(place == LoggerPlace.ALL) {
                Bukkit.broadcastMessage("ERROR >> " + msg);
            } else if(place == LoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("ERROR >> " + msg);
            }
        } else if(type == LoggerType.WARNING) {
            if(place == LoggerPlace.ALL) {
                Bukkit.broadcastMessage("WARNING >> " + msg);
            } else if(place == LoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("WARNING >> " + msg);
            }
        } else if(type == LoggerType.INFO) {
            if(place == LoggerPlace.ALL) {
                Bukkit.broadcastMessage("INFO >> " + msg);
            } else if(place == LoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("INFO >> " + msg);
            }
        } else if(type == LoggerType.NOTHING) {
            if(place == LoggerPlace.ALL) {
                Bukkit.broadcastMessage(msg);
            } else if(place == LoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage(msg);
            }
        }
    }

    public void sendModule(LoggerType type, LoggerPlace place, String msg) {
        if(type == LoggerType.ERROR) {
            if(place == LoggerPlace.ALL) {
                Bukkit.broadcastMessage("[" + moduleName + "] " + "ERROR >> " + msg);
            } else if(place == LoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("[" + moduleName + "] " + "ERROR >> " + msg);
            }
        } else if(type == LoggerType.WARNING) {
            if(place == LoggerPlace.ALL) {
                Bukkit.broadcastMessage("[" + moduleName + "] " + "WARNING >> " + msg);
            } else if(place == LoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("[" + moduleName + "] " + "WARNING >> " + msg);
            }
        } else if(type == LoggerType.INFO) {
            if(place == LoggerPlace.ALL) {
                Bukkit.broadcastMessage("[" + moduleName + "] " + "INFO >> " + msg);
            } else if(place == LoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("[" + moduleName + "] " + "INFO >> " + msg);
                System.out.println(msg);
            }
        } else if(type == LoggerType.NOTHING) {
            if(place == LoggerPlace.ALL) {
                Bukkit.broadcastMessage("[" + moduleName + "] " + msg);
            } else if(place == LoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("[" + moduleName + "] " + msg);
            }
        }
    }

}

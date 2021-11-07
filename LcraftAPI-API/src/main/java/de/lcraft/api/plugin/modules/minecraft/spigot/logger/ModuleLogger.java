package de.lcraft.api.plugin.modules.minecraft.spigot.logger;

import org.bukkit.Bukkit;

public class ModuleLogger {

    private String moduleName = null;

    public ModuleLogger() {}
    public ModuleLogger(String moduleName) {this.moduleName = moduleName;}

    public void send(ModuleLoggerType type, ModuleLoggerPlace place, String msg) {
        //if(moduleName != null)  sendModule(type, place, msg);
        if(type == ModuleLoggerType.ERROR) {
            if(place == ModuleLoggerPlace.ALL) {
                Bukkit.broadcastMessage("ERROR >> " + msg);
            } else if(place == ModuleLoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("ERROR >> " + msg);
            }
        } else if(type == ModuleLoggerType.WARNING) {
            if(place == ModuleLoggerPlace.ALL) {
                Bukkit.broadcastMessage("WARNING >> " + msg);
            } else if(place == ModuleLoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("WARNING >> " + msg);
            }
        } else if(type == ModuleLoggerType.INFO) {
            if(place == ModuleLoggerPlace.ALL) {
                Bukkit.broadcastMessage("INFO >> " + msg);
            } else if(place == ModuleLoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("INFO >> " + msg);
            }
        } else if(type == ModuleLoggerType.NOTHING) {
            if(place == ModuleLoggerPlace.ALL) {
                Bukkit.broadcastMessage(msg);
            } else if(place == ModuleLoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage(msg);
            }
        }
    }

    public void sendModule(ModuleLoggerType type, ModuleLoggerPlace place, String msg) {
        if(type == ModuleLoggerType.ERROR) {
            if(place == ModuleLoggerPlace.ALL) {
                Bukkit.broadcastMessage("[" + moduleName + "] " + "ERROR >> " + msg);
            } else if(place == ModuleLoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("[" + moduleName + "] " + "ERROR >> " + msg);
            }
        } else if(type == ModuleLoggerType.WARNING) {
            if(place == ModuleLoggerPlace.ALL) {
                Bukkit.broadcastMessage("[" + moduleName + "] " + "WARNING >> " + msg);
            } else if(place == ModuleLoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("[" + moduleName + "] " + "WARNING >> " + msg);
            }
        } else if(type == ModuleLoggerType.INFO) {
            if(place == ModuleLoggerPlace.ALL) {
                Bukkit.broadcastMessage("[" + moduleName + "] " + "INFO >> " + msg);
            } else if(place == ModuleLoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("[" + moduleName + "] " + "INFO >> " + msg);
                System.out.println(msg);
            }
        } else if(type == ModuleLoggerType.NOTHING) {
            if(place == ModuleLoggerPlace.ALL) {
                Bukkit.broadcastMessage("[" + moduleName + "] " + msg);
            } else if(place == ModuleLoggerPlace.SERVER) {
                Bukkit.getConsoleSender().sendMessage("[" + moduleName + "] " + msg);
            }
        }
    }

}

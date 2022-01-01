package de.lcraft.api.minecraft.spigot.manager.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;

public class ListenerManager {

    private ArrayList<Listener> flushListeners,
                                registeredListeners;
    private JavaPlugin plugin;

    public ListenerManager(JavaPlugin plugin) {
        this.plugin = plugin;
        flushListeners = new ArrayList<>();
        registeredListeners = new ArrayList<>();
    }

    public void addListener(Listener listener) {
        flushListeners.add(listener);
    }
    private void registerListener(Listener listener) {
        flushListeners.remove(listener);
        Bukkit.getPluginManager().registerEvents(listener, plugin);
        registeredListeners.add(listener);
    }
    public void removeListener(Listener listener) {
        if(listener != null) {
            if(registeredListeners.contains(listener)) {
                registeredListeners.remove(listener);
            } else if(flushListeners.contains(listener)) {
                flushListeners.remove(listener);
            }
        }
    }
    public ArrayList<Listener> flushRegistrationAllListeners() {
        if(!flushListeners.isEmpty()) {
            for(Listener l : flushListeners) {
                if(l != null) {
                    registerListener(l);
                }
            }
        }
        return flushListeners;
    }

    public ArrayList<Listener> getRegisteredListeners() {
        return registeredListeners;
    }
    public ArrayList<Listener> getAddedListeners() {
        return flushListeners;
    }
    public JavaPlugin getPlugin() {
        return plugin;
    }

}

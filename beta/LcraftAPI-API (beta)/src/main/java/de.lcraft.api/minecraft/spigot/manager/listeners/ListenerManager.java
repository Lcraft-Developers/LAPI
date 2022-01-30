package de.lcraft.api.minecraft.spigot.manager.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.Objects;

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
    public void registerListener(Listener listener) {
        flushListeners.remove(listener);
        Bukkit.getPluginManager().registerEvents(listener, plugin);
        registeredListeners.add(listener);
    }
    public void removeListener(Listener listener) {
        if(Objects.nonNull(listener)) {
            if(registeredListeners.contains(listener)) {
                registeredListeners.remove(listener);
            } else if(flushListeners.contains(listener)) {
                flushListeners.remove(listener);
            }
        }
    }
    public ArrayList<Listener> flushRegistrationAllListeners() {
        if(!flushListeners.isEmpty()) {
            for(Listener listener : flushListeners) {
                if(Objects.nonNull(listener)) {
                    registerListener(listener);
                }
                continue;
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

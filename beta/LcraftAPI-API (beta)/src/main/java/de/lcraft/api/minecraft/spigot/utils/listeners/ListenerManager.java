package de.lcraft.api.minecraft.spigot.utils.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class ListenerManager {

    private final ArrayList<Listener> registeredListeners;
    private final JavaPlugin plugin;

    public ListenerManager(JavaPlugin plugin) {
        this.plugin = plugin;
        registeredListeners = new ArrayList<>();
    }
    public final void registerListener(Listener listener) {
        if(!isRegistered(listener)) {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
            registeredListeners.add(listener);
        }
    }

    public final void unregisterListener(Listener listener) {
        if(isRegistered(listener)) {
            HandlerList.unregisterAll(listener);
            registeredListeners.remove(listener);
        }
    }

    public boolean isRegistered(Listener listener) {
        return registeredListeners.contains(listener);
    }
    public final ArrayList<Listener> getRegisteredListeners() {
        return registeredListeners;
    }
    public final JavaPlugin getPlugin() {
        return plugin;
    }

}

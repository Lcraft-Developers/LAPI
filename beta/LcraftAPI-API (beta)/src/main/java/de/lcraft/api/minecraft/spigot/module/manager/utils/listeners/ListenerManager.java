package de.lcraft.api.minecraft.spigot.module.manager.utils.listeners;

import org.bukkit.Bukkit;
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
        Bukkit.getPluginManager().registerEvents(listener, plugin);
        registeredListeners.add(listener);
    }

    public final ArrayList<Listener> getRegisteredListeners() {
        return registeredListeners;
    }
    public final JavaPlugin getPlugin() {
        return plugin;
    }

}

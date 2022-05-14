package de.lcraft.api.minecraft.spigot.utils.listeners;

import de.lcraft.api.minecraft.spigot.utils.listeners.event.bukkit.LcraftEventTransformer;
import de.lcraft.api.minecraft.spigot.utils.listeners.event.lcraft.LcraftEventManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.firebirdsql.event.EventManager;

import java.util.ArrayList;

public class ListenerManager {

    private final ArrayList<Listener> registeredListeners;
    private final JavaPlugin plugin;
    private final LcraftEventManager eventManager;
    private final LcraftEventTransformer eventTransformer;

    public ListenerManager(JavaPlugin plugin) {
        this.plugin = plugin;
        registeredListeners = new ArrayList<>();
        eventManager = new LcraftEventManager();
        eventTransformer = new LcraftEventTransformer();
        Bukkit.getPluginManager().registerEvents(getEventTransformer(),getPlugin());
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
    public LcraftEventManager getEventManager() {
        return eventManager;
    }
    public LcraftEventTransformer getEventTransformer() {
        return eventTransformer;
    }

}

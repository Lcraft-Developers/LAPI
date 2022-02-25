package de.lcraft.api.minecraft.spigot.module.manager.utils;

import de.lcraft.api.minecraft.spigot.module.manager.Module;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;

public class ListenerManager {

    private ArrayList<Listener> allListeners;
    private JavaPlugin plugin;

    public ListenerManager(JavaPlugin plugin) {
        this.plugin = plugin;
        allListeners = new ArrayList<>();
    }

    public void addListener(Listener listener) {
        allListeners.add(listener);
    }
    public void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, plugin);
        addListener(listener);
    }
    public ArrayList<Listener> registerAllListeners() {
        for(Listener l : allListeners) {
            Bukkit.getPluginManager().registerEvents(l, plugin);
        }
        return allListeners;
    }

    public ArrayList<Listener> getAllListeners() {
        return allListeners;
    }
    public JavaPlugin getPlugin() {
        return plugin;
    }

    public static class ModuleListenerManager extends ListenerManager {

        private Module module;

        public ModuleListenerManager(Module module) {
            super(module.getPlugin());
            this.module = module;
        }
        public Module getModule() {
            return module;
        }

    }

}

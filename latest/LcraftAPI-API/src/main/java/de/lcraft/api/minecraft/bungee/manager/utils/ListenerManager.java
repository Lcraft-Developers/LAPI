package de.lcraft.api.minecraft.bungee.manager.utils;

import de.lcraft.api.minecraft.bungee.manager.Module;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import java.util.ArrayList;

public class ListenerManager {

    private ArrayList<Listener> allListeners;
    private Plugin plugin;

    public ListenerManager(Plugin plugin) {
        this.plugin = plugin;
        allListeners = new ArrayList<>();
    }

    public void addListener(Listener listener) {
        allListeners.add(listener);
    }
    public void registerListener(Listener listener) {
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, listener);
        addListener(listener);
    }
    public ArrayList<Listener> registerAllListeners() {
        for(Listener listener : allListeners) {
            ProxyServer.getInstance().getPluginManager().registerListener(plugin, listener);
        }
        return allListeners;
    }

    public ArrayList<Listener> getAllListeners() {
        return allListeners;
    }
    public Plugin getPlugin() {
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

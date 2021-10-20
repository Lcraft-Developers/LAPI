package de.lcraft.api.plugin.modules.minecraft.spigot.listeners;

import de.lcraft.api.plugin.modules.minecraft.spigot.Module;
import org.bukkit.Bukkit;
import java.util.ArrayList;

public class ListenerManager {

    private ArrayList<Listener> allListeners;
    private Module m;

    public ListenerManager(Module m) {
        this.m = m;
    }

    public void addListener(Listener listener) {
        allListeners.add(listener);
    }

    public void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, m.getPlugin());
        addListener(listener);
    }

    public ArrayList<Listener> registerAllListeners() {
        for(Listener l : allListeners) {
            Bukkit.getPluginManager().registerEvents(l, m.getPlugin());
        }
        return allListeners;
    }

}

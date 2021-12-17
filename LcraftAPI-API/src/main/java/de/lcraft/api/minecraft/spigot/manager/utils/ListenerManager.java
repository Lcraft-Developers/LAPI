package de.lcraft.api.minecraft.spigot.manager.utils;

import de.lcraft.api.minecraft.spigot.manager.Module;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import java.util.ArrayList;

public class ListenerManager {

    private ArrayList<Listener> allListeners;
    private Module m;

    public ListenerManager(Module m) {
        this.m = m;
        allListeners = new ArrayList<>();
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

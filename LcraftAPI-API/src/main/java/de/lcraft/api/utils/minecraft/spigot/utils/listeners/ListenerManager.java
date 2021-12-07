package de.lcraft.api.utils.minecraft.spigot.utils.listeners;

import de.lcraft.api.utils.minecraft.spigot.module.Module;
import org.bukkit.Bukkit;
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

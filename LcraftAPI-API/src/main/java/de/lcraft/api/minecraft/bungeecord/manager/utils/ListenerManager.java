package de.lcraft.api.minecraft.bungeecord.manager.utils;

import de.lcraft.api.minecraft.bungeecord.manager.Module;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
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
        ProxyServer.getInstance().getPluginManager().registerListener(m.getPlugin(), listener);
        addListener(listener);
    }

    public ArrayList<Listener> registerAllListeners() {
        for(Listener l : allListeners) {
            ProxyServer.getInstance().getPluginManager().registerListener(m.getPlugin(), l);
        }
        return allListeners;
    }

}

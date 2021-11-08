package de.lcraft.api.plugin.modules.minecraft.bungeecord.module.listeners;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.manager.Module;
import net.md_5.bungee.api.ProxyServer;
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

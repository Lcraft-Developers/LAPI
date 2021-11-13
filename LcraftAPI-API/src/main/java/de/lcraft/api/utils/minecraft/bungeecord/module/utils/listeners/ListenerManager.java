package de.lcraft.api.utils.minecraft.bungeecord.module.utils.listeners;

import de.lcraft.api.utils.minecraft.bungeecord.module.Module;
import net.md_5.bungee.api.ProxyServer;
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

package de.lcraft.api.plugin.modules.listeners;

import de.lcraft.api.plugin.APIPluginMain;
import org.bukkit.Bukkit;

public class Listener implements org.bukkit.event.Listener {

    public Listener() {
        Bukkit.getPluginManager().registerEvents(this, APIPluginMain.getApiPluginMain());
    }

}

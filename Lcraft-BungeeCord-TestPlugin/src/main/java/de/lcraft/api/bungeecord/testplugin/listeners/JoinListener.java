package de.lcraft.api.bungeecord.testplugin.listeners;

import de.lcraft.api.utils.minecraft.bungeecord.module.utils.listeners.Listener;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.event.EventHandler;

public class JoinListener extends Listener {

    @EventHandler
    public void onQuit(PostLoginEvent e) {

    }

}

package de.lcraft.test.bungeecord.listeners;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PostLoginEvent e) {
		ProxyServer.getInstance().getConsole().sendMessage("Hi");
	}

}

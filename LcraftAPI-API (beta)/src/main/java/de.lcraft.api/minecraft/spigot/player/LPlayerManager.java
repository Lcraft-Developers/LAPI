package de.lcraft.api.minecraft.spigot.player;

import de.lcraft.api.minecraft.spigot.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.manager.configs.Config;
import de.lcraft.api.minecraft.spigot.manager.util.LanguagesManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class LPlayerManager implements Listener {

	private ArrayList<LPlayer> players;
	private Config userConfig;
	private ListenerManager listenerManager;
	private LanguagesManager languagesManager;

	public LPlayerManager(Config userConfig, ListenerManager listenerManager, LanguagesManager languagesManager) {
		this.userConfig = userConfig;
		this.listenerManager = listenerManager;
		this.languagesManager = languagesManager;
		players = new ArrayList<>();

		getListenerManager().registerListener(this);
	}

	public void reloadPlayers() throws IOException {
		if(userConfig.getSection("user") != null) {
			for(String c : userConfig.getSection("user").getKeys(false)) {
				UUID uuid = UUID.fromString(c);
				LPlayer p = new LPlayer(this, uuid, userConfig, getListenerManager(), getLanguagesManager());
				players.add(p);
			}
		}
	}
	public void savePlayers() throws IOException {
		if(!players.isEmpty()) {
			for(LPlayer c : players) {
				c.setToConfig();
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) throws IOException {
		if(getLPlayerByUUID(e.getPlayer().getUniqueId()) == null) {
			LPlayer p = new LPlayer(this, e.getPlayer().getUniqueId(), getUserConfig(), getListenerManager(), getLanguagesManager());
			players.add(p);
		}
	}

	public LPlayer getLPlayerByPlayer(Player p) {
		return getLPlayerByUUID(p.getUniqueId());
	}
	public LPlayer getLPlayerByRealName(String realName) {
		for(LPlayer c : players) {
			if(c.getRealName().equalsIgnoreCase(realName)) {
				return c;
			}
		}
		return null;
	}
	public LPlayer getLPlayerByUUID(UUID uuid) {
		for(LPlayer c : players) {
			if(c.getUUID().equals(uuid)) {
				return c;
			}
		}
		return null;
	}
	public boolean existsPlayer(UUID uuid) {
		if(getLPlayerByUUID(uuid) != null) return true;
		return false;
	}

	public LanguagesManager getLanguagesManager() {
		return languagesManager;
	}
	public ListenerManager getListenerManager() {
		return listenerManager;
	}
	public Config getUserConfig() {
		return userConfig;
	}
	public ArrayList<LPlayer> getPlayers() {
		return players;
	}
	public void setListenerManager(ListenerManager listenerManager) {
		this.listenerManager = listenerManager;
	}
	public void setLanguagesManager(LanguagesManager languagesManager) {
		this.languagesManager = languagesManager;
	}
	public void setUserConfig(Config userConfig) {
		this.userConfig = userConfig;
	}

}

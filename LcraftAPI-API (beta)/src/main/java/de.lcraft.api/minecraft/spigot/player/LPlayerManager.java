package de.lcraft.api.minecraft.spigot.player;

import de.lcraft.api.minecraft.spigot.manager.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.manager.configs.Config;
import de.lcraft.api.minecraft.spigot.manager.util.LanguagesManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
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

		getListenerManager().addListener(this);
		getListenerManager().flushRegistrationAllListeners();
	}

	public void reloadPlayers() {
		if(Objects.nonNull(userConfig.getSection("user"))) {
			for(String c : userConfig.getSection("user").getKeys(false)) {
				UUID uuid = UUID.fromString(c);
				LPlayer p = new LPlayer(this, uuid, userConfig, getListenerManager(), getLanguagesManager());
				players.add(p);
			}
		}
	}
	public void savePlayers() {
		if(!players.isEmpty()) {
			for(LPlayer c : players) {
				c.setToConfig();
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		if(getLPlayerByUUID(e.getPlayer().getUniqueId()) == null) {
			LPlayer p = new LPlayer(this, e.getPlayer().getUniqueId(), getUserConfig(), getListenerManager(), getLanguagesManager());
			players.add(p);
		}
	}

	public LPlayer getLPlayerByPlayer(Player player) {
		return getLPlayerByUUID(player.getUniqueId());
	}
	public LPlayer getLPlayerByRealName(String realName) {
		for(LPlayer c : players) {
			if(c.getRealName().equalsIgnoreCase(realName)) {
				return c;
			}
			continue;
		}
		return null;
	}
	public LPlayer getLPlayerByUUID(UUID uuid) {
		for(LPlayer c : players) {
			if(c.getUUID().equals(uuid)) {
				return c;
			}
			continue;
		}
		return null;
	}
	public boolean existsLPlayer(UUID uuid) {
		if(Objects.nonNull(getLPlayerByUUID(uuid))) return true;
		return false;
	}
	public ArrayList<LPlayer> getAllLPlayers() {
		return players;
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

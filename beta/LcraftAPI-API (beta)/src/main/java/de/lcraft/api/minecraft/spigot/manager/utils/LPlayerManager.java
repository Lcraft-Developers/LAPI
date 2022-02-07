package de.lcraft.api.minecraft.spigot.manager.utils;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.manager.configs.BukkitConfig;
import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.entities.LPlayer;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class LPlayerManager implements Listener {

	@Getter
	private ArrayList<LPlayer> players;
	@Getter (AccessLevel.PRIVATE)
	private BukkitConfig userConfig;
	@Getter
	private ListenerManager listenerManager;
	@Getter
	private LanguagesManager languagesManager;
	@Getter
	private SpigotClass spigotPlugin;

	public LPlayerManager(SpigotClass spigotPlugin, BukkitConfig userConfig, ListenerManager listenerManager, LanguagesManager languagesManager) {
		this.userConfig = userConfig;
		this.listenerManager = listenerManager;
		this.languagesManager = languagesManager;
		players = new ArrayList<>();
		this.spigotPlugin = spigotPlugin;

		getListenerManager().addListener(this);
		getListenerManager().flushRegistrationAllListeners();
	}

	public void reloadPlayers() {
		if(Objects.nonNull(userConfig.getSection("user"))) {
			for(String c : userConfig.getSection("user").getKeys(false)) {
				UUID uuid = UUID.fromString(c);
				LPlayer p = new LPlayer(getSpigotPlugin(), this, uuid, userConfig, getListenerManager(), getLanguagesManager());
				players.add(p);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		if(getLPlayerByUUID(e.getPlayer().getUniqueId()) == null) {
			LPlayer p = new LPlayer(getSpigotPlugin(), this, e.getPlayer().getUniqueId(), getUserConfig(), getListenerManager(), getLanguagesManager());
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
}

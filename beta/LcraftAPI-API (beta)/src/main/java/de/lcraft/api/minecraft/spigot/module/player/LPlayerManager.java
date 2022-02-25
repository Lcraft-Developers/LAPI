package de.lcraft.api.minecraft.spigot.module.player;

import de.lcraft.api.java_utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.module.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.module.manager.configs.BukkitConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class LPlayerManager implements Listener {

	private ArrayList<LPlayer> players;
	private BukkitConfig userConfig;
	private ListenerManager listenerManager;
	private LanguagesManager languagesManager;
	private SpigotClass spigotPlugin;

	public LPlayerManager(SpigotClass spigotPlugin, BukkitConfig userConfig, JavaPlugin plugin, LanguagesManager languagesManager) {
		this.userConfig = userConfig;
		this.listenerManager = new ListenerManager(plugin);
		this.languagesManager = languagesManager;
		players = new ArrayList<>();
		this.spigotPlugin = spigotPlugin;

		getListenerManager().registerListener(this);
	}

	public final void reloadPlayers() {
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
		if(Objects.isNull(getLPlayerByUUID(e.getPlayer().getUniqueId()))) {
			LPlayer p = new LPlayer(getSpigotPlugin(), this, e.getPlayer().getUniqueId(), getUserConfig(), getListenerManager(), getLanguagesManager());
			players.add(p);
			p.onJoin(e);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent e) {
		if(Objects.nonNull(getLPlayerByUUID(e.getPlayer().getUniqueId()))) {
			LPlayer p = getLPlayerByUUID(e.getPlayer().getUniqueId());
			p.onQuit(e);
			players.remove(p);
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
	public BukkitConfig getUserConfig() {
		return userConfig;
	}
	public void setListenerManager(ListenerManager listenerManager) {
		this.listenerManager = listenerManager;
	}
	public void setLanguagesManager(LanguagesManager languagesManager) {
		this.languagesManager = languagesManager;
	}
	public void setUserConfig(BukkitConfig userConfig) {
		this.userConfig = userConfig;
	}
	public SpigotClass getSpigotPlugin() {
		return spigotPlugin;
	}

}

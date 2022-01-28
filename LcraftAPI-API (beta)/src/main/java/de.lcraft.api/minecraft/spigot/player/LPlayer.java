package de.lcraft.api.minecraft.spigot.player;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.manager.configs.Config;
import de.lcraft.api.minecraft.spigot.manager.util.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.listeners.ListenerManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class LPlayer implements Listener {

	private UUID uuid;
	private String nickName;
	private String realName;

	private ListenerManager listenerManager;
	private LanguagesManager languagesManager;
	private LanguagesManager.Language lang;
	private SpigotClass plugin;
	private Config userCFG;
	private LPlayerManager lPlayerManager;

	public LPlayer(LPlayerManager manager, UUID uuid, Config userCFG, ListenerManager listenerManager, LanguagesManager languagesManager) {
		this.uuid = uuid;
		this.plugin = plugin;
		this.userCFG = userCFG;
		this.listenerManager = listenerManager;
		this.listenerManager.addListener(this);
		this.listenerManager.flushRegistrationAllListeners();
		this.languagesManager = languagesManager;
		this.lPlayerManager = manager;

		onEveryTick();
	}
	public void onEveryTick() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				reloadFromConfig();

				if(isOnline()) {
					getPlayer().setCustomName(nickName);
					getPlayer().setDisplayName(nickName);
				}
				if(isOnline()) {
					if(isVanished()) {
						for(Player c : Bukkit.getOnlinePlayers()) {
							c.hidePlayer(plugin, getPlayer());
							continue;
						}
					} else {
						for(Player c : Bukkit.getOnlinePlayers()) {
							c.showPlayer(plugin, getPlayer());
							continue;
						}
					}
				}
			}

		}, 2l, 2l);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onSendMessage(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		onSendChatMessage(e.getMessage());
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		for(LPlayer c : getlPlayerManager().getAllLPlayers()) {
			c.getPlayer().sendMessage(c.getLang().getJoinMessage());
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		for(LPlayer c : getlPlayerManager().getAllLPlayers()) {
			c.getPlayer().sendMessage(c.getLang().getQuitMessage());
		}
	}

	public void onSendChatMessage(String msg) {
		sendDefaultChatMessage(msg);
	}
	public String onGetChatMessage(LPlayer from, String msg) {
		return getDefaultChatMessage(from, msg);
	}
	public void sendDefaultChatMessage(String msg) {
		for(LPlayer c : getlPlayerManager().getAllLPlayers()) {
			if(c.isOnline()) {
				c.getPlayer().sendMessage(getDefaultChatMessage(this, msg));
			}
		}
	}
	public String getDefaultChatMessage(LPlayer from, String msg) {
		return from.getNickName() + ">>" + msg;
	}

	public UUID getUUID() {
		return uuid;
	}
	public boolean isOnline() {
		if(Objects.nonNull(getPlayer())) {
			return true;
		} else {
			return false;
		}
	}
	public Player getPlayer() {
		if(Bukkit.getOnlinePlayers().contains(uuid)) {
			return Bukkit.getPlayer(uuid);
		} else {
			return null;
		}
	}
	public OfflinePlayer getOfflinePlayer() {
		if(Objects.nonNull(Bukkit.getOfflinePlayer(uuid))) {
			return Bukkit.getOfflinePlayer(uuid);
		} else {
			return null;
		}
	}
	public LPlayerManager getlPlayerManager() {
		return lPlayerManager;
	}

	public String getRealName() {
		return realName;
	}
	public String setNickName(String nickName) {
		set("user." + getUUID().toString() + ".nickname", nickName, true);
		return getNickName();
	}
	public String getNickName() {
		if(isOnline()) {
			return getString("user." + getUUID().toString() + ".nickname", getPlayer().getName(), true);
		} else {
			return getString("user." + getUUID().toString() + ".nickname", getOfflinePlayer().getName(), true);
		}
	}
	public boolean setVanished(boolean vanished) {
		set("user." + getUUID().toString() + "vanished", vanished, true);
		return isVanished();
	}
	public boolean isVanished() {
		return Boolean.valueOf(getString("user." + uuid.toString() + "vanished", false, true));
	}
	public LanguagesManager.Language getLang() {
		return lang;
	}
	public ListenerManager getListenerManager() {
		return listenerManager;
	}
	public LanguagesManager getLanguagesManager() {
		return languagesManager;
	}

	public boolean hasPermission(String permission) {
		if(isOnline()) {
			return getPlayer().hasPermission(permission);
		}
		return false;
	}
	public void setToConfig() {
		set("user." + uuid.toString() + ".uuid", uuid, false);
		set("user." + uuid.toString() + ".name", realName, false);
		set("user." + uuid.toString() + ".nickname", nickName, true);
		set("user." + uuid.toString() + ".vanished", vanished, true);
		getLanguagesManager().setIDLanguage(getLanguagesManager().getIDFromUUID(uuid), lang);
	}
	public void reloadFromPlayerData() {
		if(Objects.nonNull(uuid)) {
			if(isOnline()) {
				realName = getPlayer().getName();
				nickName = getPlayer().getDisplayName();
			} else {
				realName = getOfflinePlayer().getName();
				nickName = getOfflinePlayer().getName();
			}
		}
		vanished = false;
		lang = getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(uuid));
	}
	public void reloadFromConfig() {
		if(isInEntry(uuid)) {
			uuid = UUID.fromString(userCFG.getString("user." + uuid.toString() + ".uuid"));
			realName = userCFG.getString("user." + uuid.toString() + ".name");
			nickName = userCFG.getString("user." + uuid.toString() + ".nickname");
			vanished = Boolean.valueOf(userCFG.getString("user." + uuid.toString() + ".vanished"));
			lang = getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(uuid));
		} else {
			reloadFromPlayerData();
			setToConfig();
		}
	}
	public boolean isInEntry(UUID uuid) {
		if(userCFG.exists("user." + uuid.toString() + ".uuid")) {
			return true;
		} else {
			return false;
		}
	}

	public Object set(String root, Object def, boolean isChangeable) {
		userCFG.set(root, def);
		userCFG.set(root + ".changeable", isChangeable);
		return def;
	}
	public Object get(String root, Object def, boolean isChangeable) {
		if(userCFG.exists(root)) {
			return userCFG.get(root);
		} else {
			return set(root, def, isChangeable);
		}
	}
	public String getString(String root, Object def, boolean isChangeable) {
		return String.valueOf(get(root, def, isChangeable));
	}

}

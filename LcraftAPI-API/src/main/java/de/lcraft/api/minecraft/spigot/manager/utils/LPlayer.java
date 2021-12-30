package de.lcraft.api.minecraft.spigot.manager.utils;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;
import java.util.UUID;

public class LPlayer implements Listener {

	private UUID uuid;
	private String nickName;
	private String realName;
	private boolean vanished;
	private ListenerManager listenerManager;
	private LanguagesManager languagesManager;
	private LanguagesManager.Language lang;
	private JavaPlugin plugin;
	private Config userCFG;

	public LPlayer(UUID uuid, Config userCFG, ListenerManager listenerManager, LanguagesManager languagesManager, JavaPlugin plugin) throws IOException {
		this.uuid = uuid;
		this.plugin = plugin;
		this.userCFG = userCFG;
		this.listenerManager = listenerManager;
		this.listenerManager.registerListener(this);
		this.languagesManager = languagesManager;

		onEveryTick();
	}
	public void onEveryTick() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				try {
					reloadFromConfig();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if(isOnline()) {
					getPlayer().setCustomName(nickName);
					getPlayer().setDisplayName(nickName);
				}
				if(isOnline()) {
					if(isVanished()) {
						for(Player c : Bukkit.getOnlinePlayers()) {
							c.hidePlayer(plugin, getPlayer());
						}
					} else {
						for(Player c : Bukkit.getOnlinePlayers()) {
							c.showPlayer(plugin, getPlayer());
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
		for(LPlayer c : SpigotClass.getAPIPluginMain().getPlayers()) {
			c.getPlayer().sendMessage(c.getLang().getJoinMessage());
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		for(LPlayer c : SpigotClass.getAPIPluginMain().getPlayers()) {
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
		for(LPlayer c : SpigotClass.getAPIPluginMain().getPlayers()) {
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
		if(getPlayer() != null) {
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
		if(Bukkit.getOfflinePlayer(uuid) != null) {
			return Bukkit.getOfflinePlayer(uuid);
		} else {
			return null;
		}
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
	public void setToConfig() throws IOException {
		set("user." + uuid.toString() + ".uuid", uuid, false);
		set("user." + uuid.toString() + ".name", realName, false);
		set("user." + uuid.toString() + ".nickname", nickName, true);
		set("user." + uuid.toString() + ".vanished", vanished, true);
		getLanguagesManager().setIDLanguage(getLanguagesManager().getIDFromUUID(uuid), lang);
	}
	public void reloadFromPlayerData() throws IOException {
		if(uuid != null) {
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
	public void reloadFromConfig() throws IOException {
		if(isInEntry(uuid)) {
			uuid = UUID.fromString(userCFG.cfg().getString("user." + uuid.toString() + ".uuid"));
			realName = userCFG.cfg().getString("user." + uuid.toString() + ".name");
			nickName = userCFG.cfg().getString("user." + uuid.toString() + ".nickname");
			vanished = Boolean.valueOf(userCFG.cfg().getString("user." + uuid.toString() + ".vanished"));
			lang = getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(uuid));
		} else {
			reloadFromPlayerData();
			setToConfig();
		}
	}
	public boolean isInEntry(UUID uuid) {
		if(userCFG.cfg().contains("user." + uuid.toString() + ".uuid")) {
			return true;
		} else {
			return false;
		}
	}

	public Object set(String root, Object def, boolean isChangeable) {
		userCFG.cfg().set(root, def);
		userCFG.cfg().set(root + ".changeable", isChangeable);
		userCFG.save();

		return def;
	}
	public Object get(String root, Object def, boolean isChangeable) {
		if(userCFG.cfg().contains(root)) {
			return userCFG.cfg().get(root);
		} else {
			return set(root, def, isChangeable);
		}
	}
	public String getString(String root, Object def, boolean isChangeable) {
		return String.valueOf(get(root, def, isChangeable));
	}

}

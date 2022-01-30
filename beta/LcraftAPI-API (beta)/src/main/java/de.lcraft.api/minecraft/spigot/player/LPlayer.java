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
	/*private String nickName;
	private String realName;
	private List<UUID> hiddenPlayers;
	private LanguagesManager.Language lang;*/

	private ListenerManager listenerManager;
	private LanguagesManager languagesManager;
	private SpigotClass plugin;
	private Config userCFG;
	private LPlayerManager lPlayerManager;

	public LPlayer(SpigotClass spigotPlugin, LPlayerManager manager, UUID uuid, Config userCFG, ListenerManager listenerManager, LanguagesManager languagesManager) {
		this.uuid = uuid;
		this.plugin = spigotPlugin;
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
				// Set NickName
				if(isOnline()) {
					getPlayer().setCustomName(getNickName());
					getPlayer().setDisplayName(getNickName());
				}
				// Make Players unseen
				if(isOnline()) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(Objects.nonNull(getVanishedUUID()) && !getVanishedUUID().isEmpty()) {
							if(getVanishedUUID().contains(p.getUniqueId().toString())) {
								getPlayer().hidePlayer(getPlugin(), p);
							} else {
								getPlayer().showPlayer(getPlugin(), p);
							}
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
		for(Player c : Bukkit.getOnlinePlayers()) {
			e.getPlayer().hidePlayer(getPlugin(), c);
		}
		for(LPlayer c : getLPlayerManager().getAllLPlayers()) {
			c.getPlayer().sendMessage(c.getLanguage().getJoinMessage());
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		for(LPlayer c : getLPlayerManager().getAllLPlayers()) {
			c.getPlayer().sendMessage(c.getLanguage().getQuitMessage());
		}
	}

	public void onSendChatMessage(String msg) {
		sendDefaultChatMessage(msg);
	}
	public String onGetChatMessage(LPlayer from, String msg) {
		return getDefaultChatMessage(from, msg);
	}
	public final void sendDefaultChatMessage(String msg) {
		for(LPlayer c : getLPlayerManager().getAllLPlayers()) {
			if(c.isOnline()) {
				c.getPlayer().sendMessage(getDefaultChatMessage(this, msg));
			}
		}
	}
	public final String getDefaultChatMessage(LPlayer from, String msg) {
		return from.getNickName() + ">>" + msg;
	}

	public final UUID getUUID() {
		return uuid;
	}
	public final boolean isOnline() {
		if(Objects.nonNull(getPlayer())) {
			return true;
		} else {
			return false;
		}
	}
	public final Player getPlayer() {
		if(Bukkit.getOnlinePlayers().contains(uuid)) {
			return Bukkit.getPlayer(uuid);
		} else {
			return null;
		}
	}
	public final OfflinePlayer getOfflinePlayer() {
		if(Objects.nonNull(Bukkit.getOfflinePlayer(uuid))) {
			return Bukkit.getOfflinePlayer(uuid);
		} else {
			return null;
		}
	}
	public final LPlayerManager getLPlayerManager() {
		return lPlayerManager;
	}

	public final boolean hasPermission(String permission) {
		if(isOnline()) {
			return getPlayer().hasPermission(permission);
		}
		return false;
	}
	public final boolean isInEntry(UUID uuid) {
		if(userCFG.exists("user." + uuid.toString() + ".uuid")) {
			return true;
		} else {
			return false;
		}
	}

	public final String setNickName(String nickName) {
		set("user." + getUUID().toString() + ".nickname", nickName, true);
		return getNickName();
	}
	public final LanguagesManager.Language setLanguage(LanguagesManager.Language language) {
		getLanguagesManager().setIDLanguage(getLanguagesManager().getIDFromUUID(getUUID()), language);
		return getLanguage();
	}

	public final void vanishFromPlayer(ArrayList<LPlayer> viewers, boolean visible) {
		for(LPlayer c : viewers) {
			ArrayList<String> vanished = c.getVanishedUUID();
			if(visible) {
				vanished.remove(getUUID().toString());
			} else {
				vanished.add(getUUID().toString());
			}
			c.getUserCFG().saveStringArrayList("user." + getUUID().toString() + ".vanished", vanished);
		}
	}
	public final void vanishFromAllPlayers(boolean visible) {
		vanishFromPlayer(getLPlayerManager().getAllLPlayers(), visible);
	}

	public final String getRealName() {
		if(isOnline()) {
			return getPlayer().getName();
		} else {
			return getOfflinePlayer().getName();
		}
	}
	public final String getNickName() {
		if(isOnline()) {
			return getString("user." + getUUID().toString() + ".nickname", getPlayer().getName(), true);
		} else {
			return getString("user." + getUUID().toString() + ".nickname", getOfflinePlayer().getName(), true);
		}
	}
	public final LanguagesManager.Language getLanguage() {
		return getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(getUUID()));
	}
	public final ArrayList<String> getVanishedUUID() {
		return getUserCFG().getStringArrayList("user." + getUUID().toString() + ".vanished");
	}

	public final ListenerManager getListenerManager() {
		return listenerManager;
	}
	public final LanguagesManager getLanguagesManager() {
		return languagesManager;
	}
	public final SpigotClass getPlugin() {
		return plugin;
	}
	public final Config getUserCFG() {
		return userCFG;
	}

	public final Object set(String root, Object def, boolean isChangeable) {
		userCFG.set(root, def);
		userCFG.set(root + ".changeable", isChangeable);
		return def;
	}
	public final Object get(String root, Object def, boolean isChangeable) {
		if(userCFG.exists(root)) {
			return userCFG.get(root);
		} else {
			return set(root, def, isChangeable);
		}
	}
	public final String getString(String root, Object def, boolean isChangeable) {
		return String.valueOf(get(root, def, isChangeable));
	}

}

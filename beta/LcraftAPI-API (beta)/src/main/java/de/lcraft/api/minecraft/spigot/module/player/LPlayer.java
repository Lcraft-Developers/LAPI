package de.lcraft.api.minecraft.spigot.module.player;

import de.lcraft.api.java_utils.configuration.Config;
import de.lcraft.api.java_utils.configuration.value.ConfigValue;
import de.lcraft.api.java_utils.language.Language;
import de.lcraft.api.java_utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.module.manager.logger.Logger;
import de.lcraft.api.minecraft.spigot.module.manager.logger.ModuleLogger;
import de.lcraft.api.minecraft.spigot.module.manager.logger.ModuleLoggerType;
import de.lcraft.api.minecraft.spigot.module.manager.utils.listeners.ListenerManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
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
	private Logger logger;

	public LPlayer(SpigotClass spigotPlugin, LPlayerManager manager, UUID uuid, Config userCFG, ListenerManager listenerManager, LanguagesManager languagesManager) {
		this.uuid = uuid;
		this.plugin = spigotPlugin;
		this.userCFG = userCFG;
		this.languagesManager = languagesManager;
		this.lPlayerManager = manager;
		this.listenerManager = listenerManager;
		this.listenerManager.registerListener(this);
		this.logger = new ModuleLogger("Lcraft Player Handler");

		getNickName(); getRealName(); getVanishedUUID();
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

	/*@EventHandler(priority = EventPriority.HIGHEST)
	public void onSendMessage(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		onSendChatMessage(e.getMessage());
	}*/
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		getLogger().send(ModuleLoggerType.INFO, e.getPlayer().getName() + " connected to the game with UUID (" + e.getPlayer().getUniqueId().toString() + ")");
		for(Player c : Bukkit.getOnlinePlayers()) {
			e.getPlayer().hidePlayer(getPlugin(), c);
		}
		for(LPlayer c : getLPlayerManager().getAllLPlayers()) {
			for(String text : getJoinMessage(c.getLanguage(), new String[]{"No Join message was set!"})) {
				if(Objects.nonNull(c) && c.isOnline()) {
					c.getPlayer().sendMessage(text);
				}
			}
		}
		String all = "";
		for(String text : getJoinMessage(getLanguagesManager().getMainLanguage(), new String[]{"No Join message was set!"})) {
			all = all + System.lineSeparator() + text;
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		getLogger().send(ModuleLoggerType.INFO, e.getPlayer().getName() + " left the game with UUID (" + e.getPlayer().getUniqueId().toString() + ")");
		for(LPlayer c : getLPlayerManager().getAllLPlayers()) {
			for(String text : getQuitMessage(c.getLanguage(), new String[]{"No Quit message was set!"})) {
				if(Objects.nonNull(c) && c.isOnline()) {
					c.getPlayer().sendMessage(text);
				}
			}
		}
	}

	public String[] getJoinMessage(Language language, String[] defaultMessage) {
		return language.getMessage("join.message", defaultMessage);
	}
	public String[] getQuitMessage(Language language, String[] defaultMessage) {
		return language.getMessage("quit.message", defaultMessage);
	}

	/*public void onSendChatMessage(String msg) {
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
	}*/

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
		if(Objects.nonNull(Bukkit.getPlayer(getUUID()))) {
			return Bukkit.getPlayer(getUUID());
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
	public final Language setLanguage(Language language) {
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
			int i = 0;
			for(String array : vanished) {
				c.getUserCFG().set("user." + getUUID().toString() + ".vanished." + i, array);
				i++;
			}
			c.getUserCFG().save();
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
		if(getUserCFG().exists("user." + getUUID().toString() + ".nickname")) {
			return getUserCFG().getString("user." + getUUID().toString() + ".nickname");
		} else {
			if(isOnline()) {
				getUserCFG().set("user." + getUUID().toString() + ".nickname", getPlayer().getName());
			} else {
				getUserCFG().set("user." + getUUID().toString() + ".nickname", getOfflinePlayer().getName());
			}
		}
		getUserCFG().save();
		return getNickName();
	}
	public final Language getLanguage() {
		return getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(getUUID()));
	}
	public final ArrayList<String> getVanishedUUID() {
		ArrayList<String> array = new ArrayList<>();
		if(getUserCFG().existsSection("user." + getUUID().toString() + ".vanished")) {
			if(Objects.nonNull(getUserCFG().getSection("user." + getUUID().toString() + ".vanished").getAllKeysWithoutValue())) {
				if(!getUserCFG().getSection("user." + getUUID().toString() + ".vanished").getAllKeysWithoutValue().isEmpty()) {
					for(ConfigValue v : getUserCFG().getSection("user." + getUUID().toString() + ".vanished").getAllKeysWithoutValue()) {
						array.add(v.getSavedValue().toString());
					}
				}
			}
		}
		return array;
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
	public Config getUserCFG() {
		return userCFG;
	}
	public Logger getLogger() {
		return logger;
	}

	public final Object set(String root, Object def, boolean isChangeable) {
		userCFG.set(root, def);
		userCFG.set(root + ".changeable", isChangeable);
		return def;
	}

}

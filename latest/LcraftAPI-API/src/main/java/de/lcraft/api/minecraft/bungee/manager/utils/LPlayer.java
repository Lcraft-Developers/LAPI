package de.lcraft.api.minecraft.bungee.manager.utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LPlayer implements Listener {

	private UUID uuid;
	private String nickName;
	private String realName;
	private ListenerManager listenerManager;
	private LanguagesManager languagesManager;
	private LanguagesManager.Language lang;
	private Plugin plugin;
	private Config userCFG;

	public LPlayer(UUID uuid, Config userCFG, LanguagesManager languagesManager, ListenerManager listenerManager, Plugin plugin) throws IOException {
		this.uuid = uuid;
		this.plugin = plugin;
		this.userCFG = userCFG;
		this.languagesManager = languagesManager;
		this.listenerManager = listenerManager;
		listenerManager.registerListener(this);

		onEveryTick();
	}
	public void onEveryTick() {
		ProxyServer.getInstance().getScheduler().schedule(plugin, new Runnable() {

			@Override
			public void run() {
				try {
					reloadFromConfig();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if(isOnline()) {
					getPlayer().setDisplayName(nickName);
				}
			}

		}, 1000 / 10, TimeUnit.MILLISECONDS);
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
	public ProxiedPlayer getPlayer() {
		if(ProxyServer.getInstance().getPlayer(uuid) != null) {
			return ProxyServer.getInstance().getPlayer(uuid);
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
		}
		return "";
	}
	public LanguagesManager.Language getLang() {
		return lang;
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
		getLanguagesManager().setIDLanguage(getLanguagesManager().getIDFromUUID(uuid), lang);
	}
	public void reloadFromPlayerData() throws IOException {
		if(uuid != null) {
			if(isOnline()) {
				realName = getPlayer().getName();
				nickName = getPlayer().getDisplayName();
			}
		}
		lang = getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(uuid));
	}
	public void reloadFromConfig() throws IOException {
		if(isInEntry(uuid)) {
			uuid = UUID.fromString(userCFG.cfg().getString("user." + uuid.toString() + ".uuid"));
			realName = userCFG.cfg().getString("user." + uuid.toString() + ".name");
			nickName = userCFG.cfg().getString("user." + uuid.toString() + ".nickname");
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

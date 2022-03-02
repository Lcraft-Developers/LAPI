package de.lcraft.api.minecraft.spigot.module.manager.utils.language;

import de.lcraft.api.java_utils.configuration.Config;
import de.lcraft.api.minecraft.spigot.module.manager.Module;
import de.lcraft.api.minecraft.spigot.module.manager.configs.ModuleConfig;

import java.util.Objects;

public class StandardMessages {

	private String PREFIX = "§7[§6LcraftAPI§7] §8>> §r",
	               ON_START = "§aThe Plugin has been §6successfully §astarted.",
	               ON_STOP = "§aThe Plugin has been §6successfully §astopped.",
	               NO_PERMISSIONS = "§cYou do not have Permissions for that!",
	               NO_PLAYER = "§cYou have to be a player to do that!",
	               NO_PLAYER_EXISTS = "§cThis Player has never existed!",
	               NO_PLAYER_FOUND = "§cThis Player is not online!",
	               NO_NUMBER = "§cYou have to type a number!",
	               HELP_MESSAGE = "§cYou have to use %helpmessage%!";
	private Config config;

	public StandardMessages(Config config) {
		this.config = config;
	}
	public StandardMessages(Module module) {
		this(new ModuleConfig(module, "standardMessages.yml"));
	}

	public void load() {
		load(null);
	}
	public void load(String PREFIX) {
		if(Objects.nonNull(PREFIX)) {
			setPREFIX(PREFIX);
		} else {
			setPREFIX(getConfig().getStringDefault("MESSAGE.PREFIX", getPREFIX()));
		}
		setON_START(getConfig().getStringDefault("MESSAGE.ON_START", getON_START()));
		setON_STOP(getConfig().getStringDefault("MESSAGE.ON_STOP", getON_STOP()));
		setNO_PERMISSIONS(getConfig().getStringDefault("MESSAGE.NO_PERMISSIONS", getNO_PERMISSIONS()));
		setNO_PLAYER(getConfig().getStringDefault("MESSAGE.NO_PLAYER", getNO_PLAYER()));
		setNO_PLAYER_EXISTS(getConfig().getStringDefault("MESSAGE.NO_PLAYER_EXISTS", getNO_PLAYER_EXISTS()));
		setNO_PLAYER_FOUND(getConfig().getStringDefault("MESSAGE.NO_PLAYER_FOUND", getNO_PLAYER_FOUND()));
		setNO_NUMBER(getConfig().getStringDefault("MESSAGE.NO_NUMBER", getNO_NUMBER()));
		setHELP_MESSAGE(getConfig().getStringDefault("MESSAGE.HELP_MESSAGE", getHELP_MESSAGE()));
	}

	public final String getPREFIX() {
		return PREFIX;
	}
	public final String getNO_NUMBER() {
		return NO_NUMBER;
	}
	public final Config getConfig() {
		return config;
	}
	public final String getNO_PERMISSIONS() {
		return NO_PERMISSIONS;
	}
	public final String getNO_PLAYER_EXISTS() {
		return NO_PLAYER_EXISTS;
	}
	public final String getON_START() {
		return ON_START;
	}
	public final String getON_STOP() {
		return ON_STOP;
	}
	public final String getNO_PLAYER_FOUND() {
		return NO_PLAYER_FOUND;
	}
	public String getHELP_MESSAGE() {
		return HELP_MESSAGE;
	}
	public String getNO_PLAYER() {
		return NO_PLAYER;
	}

	private final void setConfig(Config config) {
		this.config = config;
	}
	private final void setPREFIX(String PREFIX) {
		this.PREFIX = PREFIX;
	}
	private final void setON_STOP(String ON_STOP) {
		this.ON_STOP = ON_STOP;
	}
	private final void setON_START(String ON_START) {
		this.ON_START = ON_START;
	}
	private final void setNO_PLAYER_FOUND(String NO_PLAYER_FOUND) {
		this.NO_PLAYER_FOUND = NO_PLAYER_FOUND;
	}
	private final void setNO_PLAYER_EXISTS(String NO_PLAYER_EXISTS) {
		this.NO_PLAYER_EXISTS = NO_PLAYER_EXISTS;
	}
	private final void setNO_PERMISSIONS(String NO_PERMISSIONS) {
		this.NO_PERMISSIONS = NO_PERMISSIONS;
	}
	private final void setNO_NUMBER(String NO_NUMBER) {
		this.NO_NUMBER = NO_NUMBER;
	}
	public void setHELP_MESSAGE(String HELP_MESSAGE) {
		this.HELP_MESSAGE = HELP_MESSAGE;
	}
	public void setNO_PLAYER(String NO_PLAYER) {
		this.NO_PLAYER = NO_PLAYER;
	}

}

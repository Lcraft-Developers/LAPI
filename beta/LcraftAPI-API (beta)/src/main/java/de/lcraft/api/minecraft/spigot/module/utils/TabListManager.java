package de.lcraft.api.minecraft.spigot.module.utils;

import de.lcraft.api.minecraft.spigot.module.manager.utils.permissions.PermsManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Objects;

public class TabListManager {

	private ArrayList<String> header,
	                          footer;
	private Scoreboard scoreBoard;
	private TeamManager teamManager;
	private PermsManager permsManager;

	public TabListManager(Scoreboard scoreboard, PermsManager permsManager) {
		header = new ArrayList<>();
		footer = new ArrayList<>();
		this.scoreBoard = scoreboard;
		this.teamManager = new TeamManager(getScoreBoard());
		this.permsManager = permsManager;
	}
	public TabListManager(PermsManager permsManager) {
		this(new ScoreBoardManager().build(), permsManager);
	}

	public final void setToHeader(int index, String text) {
		getHeader().set(index, text);
	}
	public final String getFromHeader(int index) {
		return getHeader().get(index);
	}
	public final boolean existsIndexInHeader(int index) {
		return getFromHeader(index) != null;
	}
	public final boolean existsValueInHeader(String text) {
		return getHeader().contains(text);
	}
	public final ArrayList<String> getHeader() {
		return header;
	}

	public final void setToFooter(int index, String text) {
		getFooter().set(index, text);
	}
	public final String getFromFooter(int index) {
		return getFooter().get(index);
	}
	public final boolean existsIndexInFooter(int index) {
		return getFromFooter(index) != null;
	}
	public final boolean existsValueInFooter(String text) {
		return getFooter().contains(text);
	}
	public final ArrayList<String> getFooter() {
		return footer;
	}

	public Scoreboard getScoreBoard() {
		return scoreBoard;
	}
	public PermsManager getPermsManager() {
		return permsManager;
	}
	public TeamManager getTeamManager() {
		return teamManager;
	}

}

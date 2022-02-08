package de.lcraft.api.minecraft.spigot.utils;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import java.util.Objects;

public class TeamManager {

	private Scoreboard scoreBoard;

	public TeamManager(Scoreboard scoreboard) {
		this.scoreBoard = scoreboard;
	}
	public TeamManager() {
		this(new ScoreBoard().build());
	}

	public final Team createTeamWhenDoNotExists(String name) {
		if(!existsTeam(name)) {
			return getScoreBoard().registerNewTeam(name);
		} else {
			return getTeam(name);
		}
	}
	public final boolean existsTeam(String name) {
		if(Objects.nonNull(getTeam(name))) {
			return true;
		}
		return false;
	}
	public final Team getTeam(String name) {
		return getScoreBoard().getTeam(name);
	}
	public Scoreboard getScoreBoard() {
		return scoreBoard;
	}

}

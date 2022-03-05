package de.lcraft.api.minecraft.spigot.module.utils.manager;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class TeamManager {

	private Scoreboard scoreBoard;

	public TeamManager(Scoreboard scoreboard) {
		this.scoreBoard = scoreboard;
	}
	public TeamManager() {
		this(new ScoreBoardManager().build());
	}

	/*public void registerLuckPermTeamsAndRegisterPlayer(PermsManager permsManager, TeamManager teamManager) {
		RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (Objects.nonNull(provider) && permsManager.isLuckPermsEnabled()) {
			LuckPerms api = provider.getProvider();
			for(Group group : api.getGroupManager().getLoadedGroups()) {
				String name = group.getName();
				String displayName = group.getDisplayName();
				String prefix = group.getDisplayName();
				int weight = group.getWeight().getAsInt();

				Team t = teamManager.createTeamWhenDoNotExists(weight + name);
				t.setDisplayName(displayName);
				t.setPrefix(prefix);

				for(User u : api.getUserManager().getLoadedUsers()) {
					if(u.getPrimaryGroup().equals(name)) {
						t.addPlayer(Bukkit.getOfflinePlayer(u.getUniqueId()));
					}
				}
			}
		}
	}*/
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

package de.lcraft.api.minecraft.spigot.module.utils.manager;

import de.lcraft.api.minecraft.spigot.module.player.LPlayerManager;
import de.lcraft.api.minecraft.spigot.module.player.LPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;
import java.util.HashMap;
import java.util.Objects;

public class ScoreBoardManager {

	private HashMap<Integer, Object> allLines;
	private String displayName;
	private DisplaySlot displaySlot;
	private RenderType renderType;

	public ScoreBoardManager(String displayName) {
		allLines = new HashMap<>();
		setDisplayName(displayName);
	}
	public ScoreBoardManager() {
		allLines = new HashMap<>();
		setDisplayName("");
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public void setDisplaySlot(DisplaySlot displaySlot) {
		this.displaySlot = displaySlot;
	}
	public void setRenderType(RenderType renderType) {
		this.renderType = renderType;
	}

	public String getDisplayName() {
		return displayName;
	}
	public DisplaySlot getDisplaySlot() {
		return displaySlot;
	}
	public RenderType getRenderType() {
		return renderType;
	}

	public int add(Object obj) {
		int nextFreeSpace = getNextFreeSpace();
		set(nextFreeSpace, obj);
		return nextFreeSpace;
	}
	public int getNextFreeSpace() {
		int last = 0;
		for(int current : allLines.keySet()) {
			if(last <= current) {
				last = current;
				last++;
			}
		}
		return last;
	}
	public void set(Integer score, Object obj) {
		if(allLines.containsKey(score)) allLines.remove(score);
		allLines.put(score, obj);
	}
	public void remove(Integer score) {
		allLines.remove(score);
	}

	public Scoreboard build() {
		Scoreboard c = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = c.registerNewObjective("A", "B", "C");

		objective.setDisplayName(getDisplayName());
		objective.setDisplaySlot(getDisplaySlot());
		objective.setRenderType(getRenderType());

		for(Integer i : allLines.keySet()) {
			objective.getScore(allLines.get(i).toString()).setScore(i);
		}
		return c;
	}
	public Scoreboard setToPlayer(Player p) {
		Scoreboard scoreboard = build();
		p.setScoreboard(scoreboard);
		return scoreboard;
	}
	public Scoreboard setToAllPlayers(LPlayerManager lPlayerManager) {
		Scoreboard scoreboard = build();
		for(LPlayer player : lPlayerManager.getAllLPlayers()) {
			if(Objects.nonNull(player) && player.isOnline()) {
				if(Objects.nonNull(player.getPlayer())) {
					setToPlayer(player.getPlayer());
				}
			}
		}
		return scoreboard;
	}

}

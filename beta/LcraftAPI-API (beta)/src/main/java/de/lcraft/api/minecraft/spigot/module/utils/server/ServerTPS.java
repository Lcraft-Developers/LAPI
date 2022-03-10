package de.lcraft.api.minecraft.spigot.module.utils.server;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerTPS implements Runnable {

	private int TICK_COUNT = 0;
	private final long[] TICKS = new long[600];
	private final long LAST_TICK = 0L;

	public ServerTPS(JavaPlugin plugin) {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, 100L, 1L);
	}
	public final void run() {
		TICKS[(TICK_COUNT % TICKS.length)] = System.currentTimeMillis();
		TICK_COUNT += 1;
	}

	public final double getTPS() {
		return getTPS(100);
	}
	public final double getTPS(int ticks) {
		if (TICK_COUNT < ticks) {
			return 20.0D;
		}
		int target = (TICK_COUNT - 1 - ticks) % TICKS.length;
		long elapsed = System.currentTimeMillis() - TICKS[target];

		return ticks / (elapsed / 1000.0D);
	}
	public final long getElapsed(int tickID) {
		long time = TICKS[(tickID % TICKS.length)];
		return System.currentTimeMillis() - time;
	}

}

package de.lcraft.api.minecraft.spigot.utils.server;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerTPS {

    private static Lag lagometer;

    public ServerTPS(JavaPlugin plugin) {
        lagometer = new Lag();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, lagometer, 100L, 1L);
    }
    public class Lag implements Runnable {
        public static int TICK_COUNT= 0;
        public static long[] TICKS= new long[600];
        public static long LAST_TICK = 0L;

        public static double getTPS()
        {
            return getTPS(100);
        }

        public static double getTPS(int ticks)
        {
            if (TICK_COUNT< ticks) {
                return 20.0D;
            }
            int target = (TICK_COUNT- 1 - ticks) % TICKS.length;
            long elapsed = System.currentTimeMillis() - TICKS[target];

            return ticks / (elapsed / 1000.0D);
        }

        public static long getElapsed(int tickID)
        {
            if (TICK_COUNT- tickID >= TICKS.length)
            {
            }

            long time = TICKS[(tickID % TICKS.length)];
            return System.currentTimeMillis() - time;
        }

        public void run()
        {
            TICKS[(TICK_COUNT% TICKS.length)] = System.currentTimeMillis();

            TICK_COUNT+= 1;
        }
    }
    public static Lag getLagOmeter() {
        return lagometer;
    }

}

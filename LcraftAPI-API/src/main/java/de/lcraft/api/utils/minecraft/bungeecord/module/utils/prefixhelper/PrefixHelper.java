package de.lcraft.api.utils.minecraft.bungeecord.module.utils.prefixhelper;

import de.lcraft.api.utils.minecraft.bungeecord.module.utils.configs.ModuleConfig;

public class PrefixHelper {

    public static String PREFIX = "§7[§6Module§7] §r",
                         START_PREFIX = " §7>> §r",
                         NO_PERMISSIONS = "§cYou do not have permissions for that!",
                         NO_PLAYER = "§cYou must be a player to use that!",
                         NO_COMMAND_FOUND = "§cThat command do not exists!",
                         NO_NUMBER = "§cThats not a normal number!",
                         ON_LOAD = PREFIX + "§aThe Plugin is loaded.",
                         ON_START = PREFIX + "§aThe Plugin is started.",
                         ON_STOP = PREFIX + "§aThe Plugin is stopped.",
                         NO_PLAYER_FOUND = "§cThis Player doesn't exists!",
                         WORLD_NOT_FOUND = "§cThis World doesn't exists!";

    public ModuleConfig startPlugin(ModuleConfig mainCFG) {
        try {
            mainCFG = new ModuleConfig("Lcraft Prefix Helper", "config.yml");
            PREFIX = (String) ModuleConfig.getOption(mainCFG, "cb.PREFIX", PREFIX);
            START_PREFIX = (String) ModuleConfig.getOption(mainCFG, "cb.START_PREFIX", START_PREFIX);
            NO_PERMISSIONS = (String) ModuleConfig.getOption(mainCFG, "cb.NO_PERMISSIONS", NO_PERMISSIONS);
            NO_PLAYER = (String) ModuleConfig.getOption(mainCFG, "cb.NO_PLAYER", NO_PLAYER);
            NO_COMMAND_FOUND = (String) ModuleConfig.getOption(mainCFG, "cb.NO_COMMAND_FOUND", NO_COMMAND_FOUND);
            NO_NUMBER = (String) ModuleConfig.getOption(mainCFG, "cb.NO_NUMBER", NO_NUMBER);
            NO_PLAYER_FOUND = (String) ModuleConfig.getOption(mainCFG, "cb.NO_PLAYER_FOUND", NO_PLAYER_FOUND);
            WORLD_NOT_FOUND = (String) ModuleConfig.getOption(mainCFG, "cb.WORLD_NOT_FOUND", WORLD_NOT_FOUND);

            ON_LOAD = (String) ModuleConfig.getOption(mainCFG, "cb.ON_LOAD", ON_LOAD);
            ON_START = (String) ModuleConfig.getOption(mainCFG, "cb.ON_START", ON_START);
            ON_STOP = (String) ModuleConfig.getOption(mainCFG, "cb.ON_STOP", ON_STOP);
            return mainCFG;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

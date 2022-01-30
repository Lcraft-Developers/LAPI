package de.lcraft.apis.languages.system.bungeecord;

import de.lcraft.api.plugin.utils.minecraft.bungeecord.module.configs.ModuleConfig;

public class StartHelper {

    private Module module;
    public static String PREFIX = "§7[§6CityBuild§7] §r",
                         START_PREFIX = " §7>> §r",
                         NO_PERMISSIONS = "§cYou do not have permissions for that!",
                         NO_PLAYER = "§cYou must be a player to use that!",
                         NO_COMMAND_FOUND = "§cThat command do not exists!",
                         NO_NUMBER = "§cThats not a normal number!",
                         NO_PLAYER_FOUND = "§cThis Player doesn't exists!",
                         WORLD_NOT_FOUND = "§cThis World doesn't exists!";

    public ModuleConfig startPlugin(Module m, ModuleConfig mainCFG) {
        try {
            this.module = m;
            PREFIX = (String) ModuleConfig.getOption(mainCFG, "module.PREFIX", PREFIX);
            START_PREFIX = (String) ModuleConfig.getOption(mainCFG, "module.START_PREFIX", START_PREFIX);
            NO_PERMISSIONS = (String) ModuleConfig.getOption(mainCFG, "module.NO_PERMISSIONS", NO_PERMISSIONS);
            NO_PLAYER = (String) ModuleConfig.getOption(mainCFG, "module.NO_PLAYER", NO_PLAYER);
            NO_COMMAND_FOUND = (String) ModuleConfig.getOption(mainCFG, "module.NO_COMMAND_FOUND", NO_COMMAND_FOUND);
            NO_NUMBER = (String) ModuleConfig.getOption(mainCFG, "module.NO_NUMBER", NO_NUMBER);
            NO_PLAYER_FOUND = (String) ModuleConfig.getOption(mainCFG, "module.NO_PLAYER_FOUND", NO_PLAYER_FOUND);
            WORLD_NOT_FOUND = (String) ModuleConfig.getOption(mainCFG, "module.WORLD_NOT_FOUND", WORLD_NOT_FOUND);

            return mainCFG;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

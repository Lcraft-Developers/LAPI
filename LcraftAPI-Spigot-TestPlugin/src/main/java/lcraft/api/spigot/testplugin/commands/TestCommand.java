package lcraft.api.spigot.testplugin.commands;

import de.lcraft.api.plugin.modules.minecraft.spigot.Module;
import de.lcraft.api.plugin.modules.minecraft.spigot.commands.ModuleCommand;
import de.lcraft.api.plugin.modules.minecraft.spigot.logger.LoggerPlace;
import de.lcraft.api.plugin.modules.minecraft.spigot.logger.LoggerType;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class TestCommand extends ModuleCommand {

    public TestCommand(Module m) {
        super("test-spigot", "Its tests something", "", m);
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        getLogger().send(LoggerType.NOTHING, LoggerPlace.SERVER, "Testing...");
        return false;
    }

}

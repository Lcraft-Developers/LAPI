package lcraft.api.spigot.testplugin.commands;

import de.lcraft.api.plugin.modules.minecraft.spigot.Module;
import de.lcraft.api.plugin.modules.minecraft.spigot.logger.LoggerPlace;
import de.lcraft.api.plugin.modules.minecraft.spigot.logger.LoggerType;
import de.lcraft.apis.permissions.utils.spigot.ModuleCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class TestCommand extends ModuleCommand {

    public TestCommand(Module m) {
        super("test-spigot", "Its tests something", "", m);
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        getLogger().send(LoggerType.NOTHING, LoggerPlace.SERVER, "Testing...");
        return false;
    }

    @Override
    public ArrayList<String> allLanguages(ArrayList<String> beforeAllLanguages) {
        beforeAllLanguages.add("test-spigot-lang");
        return beforeAllLanguages;
    }

    @Override
    public ArrayList<String> allPermissions(ArrayList<String> beforeAllPermissions) {
        beforeAllPermissions.add("test-spigot-perm");
        return beforeAllPermissions;
    }

}

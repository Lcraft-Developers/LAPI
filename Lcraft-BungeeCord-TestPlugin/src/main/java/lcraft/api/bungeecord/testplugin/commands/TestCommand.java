package lcraft.api.bungeecord.testplugin.commands;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.manager.logger.ModuleLoggerType;
import de.lcraft.apis.permissions.utils.bungeecord.ModuleCommand;
import net.md_5.bungee.api.CommandSender;

import java.util.ArrayList;

public class TestCommand extends ModuleCommand {

    public TestCommand(Module m) {
        super("test-bungee", m);
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        getLogger().send(ModuleLoggerType.NOTHING, "Testing...");
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


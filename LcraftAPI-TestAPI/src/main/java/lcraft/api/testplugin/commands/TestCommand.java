package lcraft.api.testplugin.commands;

import de.lcraft.api.plugin.modules.Module;
import de.lcraft.api.plugin.modules.commands.ModuleCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class TestCommand extends ModuleCommand {

    public TestCommand(Module m) {
        super("test", "Its tests something", "", m);
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        Bukkit.broadcastMessage("Testing...");
        return false;
    }

}

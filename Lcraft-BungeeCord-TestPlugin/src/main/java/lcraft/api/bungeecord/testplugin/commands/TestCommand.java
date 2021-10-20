package lcraft.api.bungeecord.testplugin.commands;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.Module;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.commands.ModuleCommand;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.logger.LoggerType;
import net.md_5.bungee.api.CommandSender;

public class TestCommand extends ModuleCommand {

    public TestCommand(Module m) {
        super("test-bungee", m);
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        getLogger().send(LoggerType.NOTHING, "Testing...");
        return false;
    }

}

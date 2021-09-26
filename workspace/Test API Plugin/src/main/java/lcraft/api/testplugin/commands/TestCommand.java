package lcraft.api.testplugin.commands;

import de.lcraft.api.plugin.modules.Module;
import de.lcraft.api.plugin.modules.ModuleCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends ModuleCommand {

    public TestCommand(Module m) {
        super(m);
    }

    @Override
    public void execute(CommandSender commandSender, Command command, String s, String[] strings) {
        this.getM();
    }

    @Override
    public void executeOnlyPlayer(Player player, Command command, String s, String[] strings) {

    }

    @Override
    public void executeNotPlayer(CommandSender commandSender, Command command, String s, String[] strings) {

    }

}

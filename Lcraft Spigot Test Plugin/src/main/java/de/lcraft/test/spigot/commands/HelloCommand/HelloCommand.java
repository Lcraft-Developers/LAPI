package de.lcraft.test.spigot.commands.HelloCommand;

import de.lcraft.api.minecraft.spigot.manager.Module;
import de.lcraft.api.minecraft.spigot.manager.command.ModuleCommand;
import de.lcraft.api.minecraft.spigot.manager.utils.LPlayer;
import de.lcraft.test.spigot.commands.HelloCommand.subcommands.HelloTestCommand;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.util.ArrayList;

public class HelloCommand extends ModuleCommand {

	public HelloCommand(Module m) throws IOException {
		super("hello", "Hello", m, false);
		addSubCommand(new HelloTestCommand(m));
	}

	@Override
	public boolean onCommandExecute(CommandSender s, String[] args) {
		s.sendMessage("Hello");
		return super.onCommandExecute(s, args);
	}

	@Override
	public boolean onLPlayerCommand(LPlayer p, String[] args) {
		de.lcraft.test.spigot.LPlayer c = (de.lcraft.test.spigot.LPlayer) p;
		if(p.isOnline()) {
			p.getPlayer().sendMessage("Test Player");
			if(p.isVanished()) {
				p.getPlayer().sendMessage("Test Player Not Visible");
			} else {
				p.getPlayer().sendMessage("Test Player Visible");
			}
		}
		return super.onLPlayerCommand(p, args);
	}

	@Override
	public ArrayList<String> getAllPermissions(ArrayList<String> allPermissions) {
		return allPermissions;
	}

	@Override
	public ArrayList<String> getAllTranslations(ArrayList<String> allTranslations) {
		return allTranslations;
	}

}

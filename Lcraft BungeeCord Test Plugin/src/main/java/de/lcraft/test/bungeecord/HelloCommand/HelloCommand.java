package de.lcraft.test.bungeecord.HelloCommand;

import de.lcraft.api.minecraft.bungee.manager.Module;
import de.lcraft.api.minecraft.bungee.manager.command.ModuleCommand;
import de.lcraft.api.minecraft.bungee.manager.utils.LPlayer;
import de.lcraft.test.bungeecord.HelloCommand.subcommands.HelloTestCommand;
import net.md_5.bungee.api.CommandSender;
import java.io.IOException;
import java.util.ArrayList;

public class HelloCommand extends ModuleCommand {

	public HelloCommand(Module m) throws IOException {
		super("hello", "Hello", m, false);
		addSubCommand(new HelloTestCommand(m));
	}

	@Override
	public void onCommandExecute(CommandSender s, String[] args) {
		s.sendMessage("Hello");
	}

	@Override
	public void onLPlayerCommand(LPlayer p, String[] args) {
		LPlayer c = (LPlayer) p;
		if(p.isOnline()) {
			p.getPlayer().sendMessage("Test Player");
		}
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

package de.lcraft.test.bungeecord.HelloCommand.subcommands;

import de.lcraft.api.minecraft.bungee.manager.utils.LPlayer;
import de.lcraft.api.minecraft.bungee.manager.Module;
import de.lcraft.api.minecraft.bungee.manager.command.SubModuleCommand;
import net.md_5.bungee.api.CommandSender;
import java.io.IOException;
import java.util.ArrayList;

public class HelloTestCommand extends SubModuleCommand {

	public HelloTestCommand(Module m) throws IOException {
		super("test", "test", m, true);
	}

	@Override
	public void onCommandExecute(CommandSender s, String[] args) {
		s.sendMessage("Hello Test");
	}

	@Override
	public void onLPlayerCommand(de.lcraft.api.minecraft.bungee.manager.utils.LPlayer p, String[] args) {
		de.lcraft.api.minecraft.bungee.manager.utils.LPlayer c = (LPlayer) p;
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

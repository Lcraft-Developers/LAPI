package de.lcraft.test.spigot.commands.HelloCommand;

import de.lcraft.api.minecraft.spigot.manager.Module;
import de.lcraft.api.minecraft.spigot.manager.command.ModuleCommand;
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
	public ArrayList<String> getAllPermissions(ArrayList<String> allPermissions) {
		return allPermissions;
	}

	@Override
	public ArrayList<String> getAllTranslations(ArrayList<String> allTranslations) {
		return allTranslations;
	}

}

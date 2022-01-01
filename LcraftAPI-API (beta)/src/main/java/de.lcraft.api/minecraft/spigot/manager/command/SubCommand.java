package de.lcraft.api.minecraft.spigot.manager.command;

import de.lcraft.api.minecraft.spigot.manager.util.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.util.PermsManager;
import de.lcraft.api.minecraft.spigot.player.LPlayerManager;
import java.io.IOException;

public abstract class SubCommand extends Command {

	public SubCommand(String subCommand, String desc, PermsManager permsManager, LPlayerManager lPlayerManager, LanguagesManager languagesManager, boolean splitting) throws IOException {
		super(subCommand, desc, permsManager, lPlayerManager, languagesManager, splitting);
	}

}

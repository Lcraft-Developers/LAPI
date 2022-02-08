package de.lcraft.api.minecraft.spigot.utils.command;

import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.manager.utils.LPlayerManager;

public abstract class SubCommand extends Command {

	public SubCommand(String subLabel, String desc, PermsManager permsManager, LPlayerManager lPlayerManager, LanguagesManager languagesManager, boolean splitting, ListenerManager listenerManager) {
		super(subLabel, desc, permsManager, lPlayerManager, languagesManager, splitting, listenerManager);
	}

}

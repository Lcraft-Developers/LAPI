package de.lcraft.api.minecraft.spigot.module.manager.command;

import de.lcraft.api.minecraft.spigot.module.manager.utils.language.StandardMessages;
import de.lcraft.api.minecraft.spigot.module.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.module.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.module.player.LPlayerManager;

public abstract class SubCommand extends Command {

	public SubCommand(StandardMessages standardMessages, String subLabel, String desc, PermsManager permsManager, LPlayerManager lPlayerManager, LanguagesManager languagesManager, boolean splitting, ListenerManager listenerManager) {
		super(standardMessages, subLabel, desc, permsManager, lPlayerManager, languagesManager, splitting, listenerManager);
	}

}

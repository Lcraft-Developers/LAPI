package de.lcraft.test.bungeecord;

import de.lcraft.api.minecraft.bungee.manager.utils.Config;
import de.lcraft.api.minecraft.bungee.manager.utils.LanguagesManager;
import de.lcraft.api.minecraft.bungee.manager.utils.ListenerManager;
import net.md_5.bungee.api.plugin.Plugin;
import java.io.IOException;
import java.util.UUID;

public class LPlayer extends de.lcraft.api.minecraft.bungee.manager.utils.LPlayer {

	public LPlayer(UUID uuid, Config userCFG, ListenerManager listenerManager, LanguagesManager languagesManager, Plugin plugin) throws IOException {
		super(uuid, userCFG, languagesManager, listenerManager, plugin);
	}

}

package de.lcraft.test.spigot;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.manager.utils.Config;
import de.lcraft.api.minecraft.spigot.manager.utils.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.UUID;

public class LPlayer extends de.lcraft.api.minecraft.spigot.manager.utils.LPlayer {

	public LPlayer(UUID uuid, Config userCFG, ListenerManager listenerManager, LanguagesManager languagesManager, JavaPlugin plugin) throws IOException {
		super(uuid, userCFG, listenerManager, languagesManager, plugin);
	}

	@Override
	public void onSendChatMessage(String msg) {
		for(de.lcraft.api.minecraft.spigot.manager.utils.LPlayer c : SpigotClass.getAPIPluginMain().getPlayers()) {
			if(c.isOnline()) {
				c.getPlayer().sendMessage(getDefaultChatMessage(this, msg));
			}
		}
	}

	@Override
	public String onGetChatMessage(de.lcraft.api.minecraft.spigot.manager.utils.LPlayer from, String msg) {
		return from.getNickName() + " | " + msg;
	}

}

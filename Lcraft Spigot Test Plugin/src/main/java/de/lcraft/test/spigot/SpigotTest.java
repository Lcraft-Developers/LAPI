package de.lcraft.test.spigot;

import de.lcraft.api.minecraft.spigot.manager.Module;
import de.lcraft.test.spigot.commands.HelloCommand.HelloCommand;
import de.lcraft.test.spigot.listeners.JoinListener;
import java.io.IOException;

public class SpigotTest extends Module {

	@Override
	public void onEnable() throws IOException {
		getModuleCommandManager().addCommand(new HelloCommand(this), true);

		getListenerManager().addListener(new JoinListener());
	}

	@Override
	public void onDisable() {

	}

}

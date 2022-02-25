package de.lcraft.test.spigot;

import de.lcraft.api.minecraft.spigot.module.manager.Module;
import de.lcraft.test.spigot.commands.HelloCommand.HelloCommand;
import de.lcraft.test.spigot.listeners.HandelListener;
import java.io.IOException;

public class SpigotTest extends Module {

	@Override
	public void onEnable() throws IOException {
		getModuleCommandManager().addCommand(new HelloCommand(this), true);

		getListenerManager().addListener(new HandelListener());
	}

	@Override
	public void onDisable() {

	}

}

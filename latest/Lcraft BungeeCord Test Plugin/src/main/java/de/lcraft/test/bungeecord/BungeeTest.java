package de.lcraft.test.bungeecord;

import de.lcraft.api.minecraft.bungee.manager.Module;
import de.lcraft.test.bungeecord.HelloCommand.HelloCommand;
import de.lcraft.test.bungeecord.listeners.HandelListener;
import java.io.IOException;

public class BungeeTest extends Module {

	@Override
	public void onEnable() throws IOException {
		getModuleCommandManager().addCommand(new HelloCommand(this), true);

		getListenerManager().addListener(new HandelListener());
	}

	@Override
	public void onDisable() {

	}

}

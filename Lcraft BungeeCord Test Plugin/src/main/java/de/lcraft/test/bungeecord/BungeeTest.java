package de.lcraft.test.bungeecord;

import de.lcraft.api.minecraft.bungeecord.manager.Module;
import de.lcraft.test.bungeecord.commands.HelloCommand.HelloCommand;
import de.lcraft.test.bungeecord.listeners.JoinListener;
import java.io.IOException;

public class BungeeTest extends Module {

	@Override
	public void onEnable() throws IOException {
		getModuleCommandManager().addCommand(new HelloCommand(this), true);

		getListenerManager().addListener(new JoinListener());
	}

	@Override
	public void onDisable() {

	}

}

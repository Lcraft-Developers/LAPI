package de.lcraft.test.spigot;

import de.lcraft.api.minecraft.spigot.module.manager.Module;
import de.lcraft.api.minecraft.spigot.module.manager.configs.ModuleConfig;
import de.lcraft.test.spigot.commands.tps.TPSCommand;
import de.lcraft.test.spigot.listeners.JoinListener;

public class SpigotTest extends Module {

	private ModuleConfig moduleConfig;

	@Override
	public void onEnable() {
		moduleConfig = new ModuleConfig(this,"config.yml");

		getListenerManager().registerListener(new JoinListener());

		getModuleCommandManager().addCommand(new TPSCommand(this),true);
	}

	@Override
	public void onDisable() {

	}

}

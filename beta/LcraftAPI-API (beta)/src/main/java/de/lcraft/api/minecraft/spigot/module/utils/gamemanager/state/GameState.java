package de.lcraft.api.minecraft.spigot.module.utils.gamemanager.state;

import de.lcraft.api.java_utils.language.LanguageContainer;
import de.lcraft.api.minecraft.spigot.module.manager.utils.permissions.PermissionContainer;

public abstract class GameState implements LanguageContainer, PermissionContainer {

	private GameStateTypes type;

	public GameState(GameStateTypes type) {
		this.type = type;
	}

	public abstract void start();
	public abstract void stop();

	public GameStateTypes getType() {
		return type;
	}

}

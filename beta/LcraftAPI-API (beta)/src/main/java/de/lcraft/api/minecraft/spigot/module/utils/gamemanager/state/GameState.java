package de.lcraft.api.minecraft.spigot.module.utils.gamemanager.state;

import de.lcraft.api.java_utils.language.LanguageContainer;
import de.lcraft.api.minecraft.spigot.module.manager.utils.permissions.PermissionContainer;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GameState gameState = (GameState) o;
		return type == gameState.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type);
	}

}

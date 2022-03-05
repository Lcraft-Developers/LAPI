package de.lcraft.api.minecraft.spigot.module.utils.gamemanager.state;

import de.lcraft.api.java_utils.language.LanguageContainer;
import de.lcraft.api.minecraft.spigot.module.manager.utils.permissions.PermissionContainer;

import java.util.ArrayList;
import java.util.Objects;

public abstract class GameState extends LanguageContainer {

	private GameStateTypes type;
	private PermissionContainer permissionContainer;

	public GameState(GameStateTypes type) {
		this.type = type;
		this.permissionContainer = new PermissionContainer() {
			@Override
			protected ArrayList<String> allUsedPermissions() {
				return allUsedPerms(new ArrayList<>());
			}
		};
	}

	public abstract void start();
	public abstract void stop();
	public abstract ArrayList<String> allUsedPerms(ArrayList<String> translation);

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

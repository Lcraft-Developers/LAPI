package de.lcraft.api.minecraft.spigot.module.utils.gamemanager;

import de.lcraft.api.java_utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.module.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.module.utils.gamemanager.state.GameState;
import java.util.ArrayList;
import java.util.Objects;

public class GameStateManager {

	private GameState currentState;
	private LanguagesManager languagesManager;
	private PermsManager permsManager;

	public GameStateManager(PermsManager permsManager, LanguagesManager languagesManager) {
		this.languagesManager = languagesManager;
		this.permsManager = permsManager;
	}

	public void startGameState(GameState nextGameState) {
		stopCurrentGameState();
		setCurrentState(nextGameState);
		getCurrentState().start();

		logCurrentGameState();
	}
	public void stopCurrentGameState() {
		if(Objects.nonNull(getCurrentState())) {
			getCurrentState().stop();
		}
		setCurrentState(null);
	}

	public void logGameState(GameState gameState) {
		if(Objects.nonNull(gameState)) {
			ArrayList<String> allPermissions = gameState.allUsedPermissions();
			ArrayList<String> allTranslations = gameState.allUsedTranslatedText();
			for(String c : allTranslations) {
				getLanguagesManager().logText(c);
			}
			for(String c : allPermissions) {
				getPermsManager().logPermission(c);
			}
		}
	}
	public void logCurrentGameState() {
		logGameState(getCurrentState());
	}

	private void setCurrentState(GameState currentState) {
		this.currentState = currentState;
	}
	public GameState getCurrentState() {
		return currentState;
	}
	public LanguagesManager getLanguagesManager() {
		return languagesManager;
	}
	public PermsManager getPermsManager() {
		return permsManager;
	}

}

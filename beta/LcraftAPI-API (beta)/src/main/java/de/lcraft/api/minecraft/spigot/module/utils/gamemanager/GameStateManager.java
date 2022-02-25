package de.lcraft.api.minecraft.spigot.module.utils.gamemanager;

import de.lcraft.api.minecraft.spigot.module.utils.gamemanager.state.GameState;

import java.util.ArrayList;
import java.util.Objects;

public class GameStateManager {

	private GameState currentState;

	public void startGameState(GameState nextGameState) {
		stopCurrentGameState();
		setCurrentState(nextGameState);
		getCurrentState().start();
	}
	public void logGameState(GameState gameState) {
		if(Objects.nonNull(gameState)) {
			ArrayList<String> allPermissions = gameState.allUsedPermissions();
			ArrayList<String> allTranslations = gameState.allUsedTranslatedText();


		}
	}

	public void logCurrentGameState() {
		logGameState(getCurrentState());
	}
	public void stopCurrentGameState() {
		if(Objects.nonNull(getCurrentState())) {
			getCurrentState().stop();
		}
		setCurrentState(null);
	}

	private void setCurrentState(GameState currentState) {
		this.currentState = currentState;
	}
	public GameState getCurrentState() {
		return currentState;
	}

}

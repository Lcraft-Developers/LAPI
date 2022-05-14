package de.lcraft.api.java_utils.new_configuration.shardsystem;

import java.util.ArrayList;

public class ActionManager extends Thread {

	public static ArrayList<Action> actions;

	public ActionManager() {
		actions = new ArrayList<>();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(10);

			if(actions.isEmpty()) {
				Thread.sleep(40);
			} else {
				for(Action action : getActions()) {
					action.getMethod().accept(null);
				}
			}

			run();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public ArrayList<Action> getActions() {
		return actions;
	}

}

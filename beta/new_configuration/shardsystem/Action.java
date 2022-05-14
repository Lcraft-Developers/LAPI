package de.lcraft.api.java_utils.new_configuration.shardsystem;

import java.util.function.Consumer;

public abstract class Action {

	private Consumer<?> method;

	public Action(Consumer<?> method) {
		this.method = method;
	}

	public void queue() {
		ActionManager.actions.add(this);
	}

	public Consumer<?> getMethod() {
		return method;
	}

}

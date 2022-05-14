package de.lcraft.api.minecraft.spigot.utils.listeners.event;

public class EventException extends Exception{

	private final Throwable cause;

	public EventException(Throwable cause, String message) {
		super(message);
		this.cause = cause;
	}
	public EventException(String message) {
		super(message);
		cause = null;
	}
	public EventException(Throwable throwable) {
		cause = throwable;
	}
	public EventException() {
		cause = null;
	}

	@Override
	public Throwable getCause() {
		return cause;
	}

}
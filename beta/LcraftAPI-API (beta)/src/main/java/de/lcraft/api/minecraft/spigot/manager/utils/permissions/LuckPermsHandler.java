package de.lcraft.api.minecraft.spigot.manager.utils.permissions;

import de.lcraft.api.java_utils.CodeHelper;
import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.ContextConsumer;
import net.luckperms.api.context.ContextSet;
import net.luckperms.api.context.ImmutableContextSet;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class LuckPermsHandler implements ContextCalculator<Player> {

	private String permission;
	private ArrayList<String> next;

	public LuckPermsHandler(String perms, String... next) {
		this.permission = perms;
		this.next = new CodeHelper().makeStringArrayToArrayList(next);
	}
	@Override
	public final void calculate(Player player, ContextConsumer contextConsumer) {
		for(String c : getNext()) {
			contextConsumer.accept(getPermission(), c);
		}
	}
	@Override
	public final ContextSet estimatePotentialContexts() {
		ImmutableContextSet.Builder builder = ImmutableContextSet.builder();
		for(String c : getNext()) {
			builder.add(getPermission(), c);
		}
		return builder.build();
	}
	public String getPermission() {
		return permission;
	}
	public ArrayList<String> getNext() {
		return next;
	}

}

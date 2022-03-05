package de.lcraft.api.minecraft.spigot.module.manager.utils.permissions;

import java.util.ArrayList;

public abstract class PermissionContainer {

	public static final ArrayList<PermissionContainer> allPermissions = new ArrayList<>();

	public PermissionContainer() {
		allPermissions.add(this);
	}

	protected abstract ArrayList<String> allUsedPermissions();
	public ArrayList<String> getAllPermissions() {
		return allUsedPermissions();
	}

}

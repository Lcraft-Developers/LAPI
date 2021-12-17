package de.lcraft.api.minecraft.bungeecord.manager.utils.permissions;

import de.lcraft.api.minecraft.bungeecord.manager.ModuleConfig;

public class Permission {

	private String name;
	private boolean isEnabled;

	public Permission load(String perm, ModuleConfig allPermissionsCfg) {
		String root = "permissions." + perm;
		if(allPermissionsCfg.cfg().contains(root)) {
			name = perm;
			isEnabled = allPermissionsCfg.cfg().getBoolean(root + ".enabled");
		} else {
			set(perm, true, allPermissionsCfg);
		}

		return this;
	}
	public void set(String perm, boolean isEnabled, ModuleConfig allPermissionsCfg) {
		String root = "permissions." + perm;
		allPermissionsCfg.cfg().set(root + ".name", perm);
		allPermissionsCfg.cfg().set(root + ".enabled", isEnabled);
		this.name = perm;
		this.isEnabled = isEnabled;
		allPermissionsCfg.save();
	}

	public String getName() {
		return name;
	}
	public boolean isEnabled() {
		return isEnabled;
	}

}
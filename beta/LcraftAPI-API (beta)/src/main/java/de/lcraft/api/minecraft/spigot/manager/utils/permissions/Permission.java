package de.lcraft.api.minecraft.spigot.manager.utils.permissions;

import de.lcraft.api.minecraft.spigot.manager.configs.ModuleBukkitConfig;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import java.util.Objects;

public class Permission {

	private String permission;
	private ModuleBukkitConfig allPermissionsCfg;

	public Permission(String permission, ModuleBukkitConfig allPermissionsCfg) {
		this.permission = permission;
		this.allPermissionsCfg = allPermissionsCfg;
	}
	public final Permission register() {
		if(!isExiting()) {
			set(true);
		}
		return this;
	}
	public final void registerToLuckPerms(boolean isLuckPermsEnabled) {
		RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (Objects.nonNull(provider) && isLuckPermsEnabled) {
			LuckPerms api = provider.getProvider();
			api.getContextManager().registerCalculator(new LuckPermsHandler(getPermission(), "*", "admin"));
		}
	}
	public final void set(boolean isEnabled) {
		String root = getRoot();
		getAllPermissionsCfg().set(root + ".name", getPermission());
		getAllPermissionsCfg().set(root + ".enabled", isEnabled);
	}
	public final boolean isEnabled() {
		String root = getRoot();
		if(isExiting()) {
			return getAllPermissionsCfg().getBoolean(root + ".enabled");
		} else {
			set(true);
		}
		return isEnabled();
	}
	public final boolean isExiting() {
		String root = getRoot();
		if(getAllPermissionsCfg().exists(root)) {
			return true;
		}
		return false;
	}

	public final String getPermission() {
		return permission;
	}
	public final ModuleBukkitConfig getAllPermissionsCfg() {
		return allPermissionsCfg;
	}
	private final String getRoot() {
		return "permissions." + getPermission().replace(".", "_");
	}

}

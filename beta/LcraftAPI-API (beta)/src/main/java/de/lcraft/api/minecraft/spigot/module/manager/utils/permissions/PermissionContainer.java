package de.lcraft.api.minecraft.spigot.module.manager.utils.permissions;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public interface PermissionContainer {
	@Nonnull
	ArrayList<String> allUsedPermissions();
}

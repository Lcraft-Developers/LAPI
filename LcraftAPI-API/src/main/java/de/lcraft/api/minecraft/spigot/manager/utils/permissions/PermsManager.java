package de.lcraft.api.minecraft.spigot.manager.utils.permissions;

import de.lcraft.api.minecraft.spigot.manager.ModuleConfig;
import org.bukkit.entity.Player;
import java.io.IOException;

public class PermsManager {

    private ModuleConfig allPermissionsCfg,
                         adminsCfg,
                         cfg;

    public PermsManager() throws IOException {
        this.allPermissionsCfg = new ModuleConfig("Lcraft Permissions", "allPermissions.yml");
        this.adminsCfg = new ModuleConfig("Lcraft Permissions", "admins.yml");
        this.cfg = new ModuleConfig("Lcraft Permissions", "config.yml");
    }
    
    public boolean hasPermissions(Player player, String permission) {
        String ID = player.getUniqueId().toString();

        String root = "users." + ID + ".";
        adminsCfg.cfg().set(root + "name", ID);
        adminsCfg.cfg().set(root + "uuid", ID);
        adminsCfg.save();
        if(!adminsCfg.cfg().contains(root + "admin")) {
            adminsCfg.cfg().set(root + "admin", false);
        } else {
            if(adminsCfg.cfg().getBoolean(root + "admin")) {
                return true;
            }
        }
        adminsCfg.save();

        if(!logPermission(permission)) {
            return true;
        }
        if(player.hasPermission(permission)) {
            return true;
        }

        // Look for admin/* permissions
        root = "";
        for(String c : permission.split(".")) {
            root = root + c + ".";
            if(player.hasPermission(root + "*")) {
                return true;
            }
            if(player.hasPermission(root + "admin")) {
                return true;
            }
        }
        return false;
    }
    public boolean logPermission(String permission) {
        return new Permission().load(permission, allPermissionsCfg).isEnabled();
    }
    public boolean isLuckPermsEnabled() {
        if(cfg.cfg().contains("systems.luckperms.enabled") && cfg.cfg().get("systems.luckperms.enabled") instanceof Boolean
                && cfg.cfg().getBoolean("systems.luckperms.enabled")) {
            return true;
        } else {
            cfg.cfg().set("systems.luckperms.enabled", false);
            cfg.save();
            return isLuckPermsEnabled();
        }
    }

    public ModuleConfig getAllPermissionsCfg() {
        return allPermissionsCfg;
    }

}

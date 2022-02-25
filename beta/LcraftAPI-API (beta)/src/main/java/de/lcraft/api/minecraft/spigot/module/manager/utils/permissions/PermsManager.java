package de.lcraft.api.minecraft.spigot.module.manager.utils.permissions;

import de.lcraft.api.java_utils.CodeHelper;
import de.lcraft.api.minecraft.spigot.module.manager.configs.ModuleBukkitConfig;
import de.lcraft.api.minecraft.spigot.module.player.LPlayer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class PermsManager {

    private ModuleBukkitConfig allPermissionsCfg,
                         adminsCfg,
                         cfg;
    private ArrayList<String> extraPermissionEnding;

    public PermsManager(String... extraPermissionEnding) {
        this.allPermissionsCfg = new ModuleBukkitConfig("Lcraft Permissions", "allPermissions.yml");
        this.adminsCfg = new ModuleBukkitConfig("Lcraft Permissions", "admins.yml");
        this.cfg = new ModuleBukkitConfig("Lcraft Permissions", "config.yml");
        this.extraPermissionEnding = new ArrayList<>();

        for(String c : extraPermissionEnding) {
            this.extraPermissionEnding.add(c);
        }
    }
    public PermsManager() {
        this.allPermissionsCfg = new ModuleBukkitConfig("Lcraft Permissions", "allPermissions.yml");
        this.adminsCfg = new ModuleBukkitConfig("Lcraft Permissions", "admins.yml");
        this.cfg = new ModuleBukkitConfig("Lcraft Permissions", "config.yml");
        this.extraPermissionEnding = new ArrayList<>();
        extraPermissionEnding.add("*");
        extraPermissionEnding.add("admin");
    }
    public PermsManager(ArrayList<String> extraPermissionEnding) {
        this.allPermissionsCfg = new ModuleBukkitConfig("Lcraft Permissions", "allPermissions.yml");
        this.adminsCfg = new ModuleBukkitConfig("Lcraft Permissions", "admins.yml");
        this.cfg = new ModuleBukkitConfig("Lcraft Permissions", "config.yml");
        this.extraPermissionEnding = extraPermissionEnding;
    }

    // Old ID Getter
    /*public final int getIDFromString(String normal) {
        int id = 0;
        int bid = 0;
        for(String c : normal.split("")) {
            bid++;
            id = id + (bid * 3);
            if(c.equals("a")) {
                bid = bid + 1;
                id = id + bid;
            } else if(c.equals("b")) {
                bid = bid + 2;
                id = id + bid;
            } else if(c.equals("c")) {
                bid = bid + 3;
                id = id + bid;
            } else if(c.equals("d")) {
                bid = bid + 4;
                id = id + bid;
            } else if(c.equals("e")) {
                bid = bid + 5;
                id = id + bid;
            } else if(c.equals("f")) {
                bid = bid + 6;
                id = id + bid;
            } else if(c.equals("g")) {
                bid = bid + 7;
                id = id + bid;
            } else if(c.equals("h")) {
                bid = bid + 8;
                id = id + bid;
            } else if(c.equals("i")) {
                bid = bid + 9;
                id = id + bid;
            } else if(c.equals("j")) {
                bid = bid + 10;
                id = id + bid;
            } else if(c.equals("k")) {
                bid = bid + 11;
                id = id + bid;
            } else if(c.equals("l")) {
                bid = bid + 12;
                id = id + bid;
            } else if(c.equals("m")) {
                bid = bid + 13;
                id = id + bid;
            } else if(c.equals("n")) {
                bid = bid + 14;
                id = id + bid;
            } else if(c.equals("o")) {
                bid = bid + 15;
                id = id + bid;
            } else if(c.equals("p")) {
                bid = bid + 16;
                id = id + bid;
            } else if(c.equals("q")) {
                bid = bid + 17;
                id = id + bid;
            } else if(c.equals("r")) {
                bid = bid + 18;
                id = id + bid;
            } else if(c.equals("s")) {
                bid = bid + 19;
                id = id + bid;
            } else if(c.equals("t")) {
                bid = bid + 20;
                id = id + bid;
            } else if(c.equals("u")) {
                bid = bid + 21;
                id = id + bid;
            } else if(c.equals("v")) {
                bid = bid + 22;
                id = id + bid;
            } else if(c.equals("w")) {
                bid = bid + 23;
                id = id + bid;
            } else if(c.equals("x")) {
                bid = bid + 24;
                id = id + bid;
            } else if(c.equals("y")) {
                bid = bid + 25;
                id = id + bid;
            } else if(c.equals("z")) {
                bid = bid + 26;
                id = id + bid;
            } else if(c.equals("A")) {
                bid = bid + 27;
                id = id + bid;
            } else if(c.equals("B")) {
                bid = bid + 28;
                id = id + bid;
            } else if(c.equals("C")) {
                bid = bid + 29;
                id = id + bid;
            } else if(c.equals("D")) {
                bid = bid + 30;
                id = id + bid;
            } else if(c.equals("E")) {
                bid = bid + 31;
                id = id + bid;
            } else if(c.equals("F")) {
                bid = bid + 32;
                id = id + bid;
            } else if(c.equals("G")) {
                bid = bid + 33;
                id = id + bid;
            } else if(c.equals("H")) {
                bid = bid + 34;
                id = id + bid;
            } else if(c.equals("I")) {
                bid = bid + 35;
                id = id + bid;
            } else if(c.equals("J")) {
                bid = bid + 36;
                id = id + bid;
            } else if(c.equals("K")) {
                bid = bid + 37;
                id = id + bid;
            } else if(c.equals("L")) {
                bid = bid + 38;
                id = id + bid;
            } else if(c.equals("M")) {
                bid = bid + 39;
                id = id + bid;
            } else if(c.equals("N")) {
                bid = bid + 40;
                id = id + bid;
            } else if(c.equals("O")) {
                bid = bid + 41;
                id = id + bid;
            } else if(c.equals("P")) {
                bid = bid + 42;
                id = id + bid;
            } else if(c.equals("Q")) {
                bid = bid + 43;
                id = id + bid;
            } else if(c.equals("R")) {
                bid = bid + 44;
                id = id + bid;
            } else if(c.equals("S")) {
                bid = bid + 45;
                id = id + bid;
            } else if(c.equals("T")) {
                bid = bid + 46;
                id = id + bid;
            } else if(c.equals("U")) {
                bid = bid + 47;
                id = id + bid;
            } else if(c.equals("V")) {
                bid = bid + 48;
                id = id + bid;
            } else if(c.equals("W")) {
                bid = bid + 49;
                id = id + bid;
            } else if(c.equals("X")) {
                bid = bid + 50;
                id = id + bid;
            } else if(c.equals("Y")) {
                bid = bid + 51;
                id = id + bid;
            } else if(c.equals("Z")) {
                bid = bid + 52;
                id = id + bid;
            } else if(c.equals("0")) {
                bid = bid + 53;
                id = id + bid;
            } else if(c.equals("1")) {
                bid = bid + 54;
                id = id + bid;
            } else if(c.equals("2")) {
                bid = bid + 55;
                id = id + bid;
            } else if(c.equals("3")) {
                bid = bid + 56;
                id = id + bid;
            } else if(c.equals("4")) {
                bid = bid + 57;
                id = id + bid;
            } else if(c.equals("5")) {
                bid = bid + 58;
                id = id + bid;
            } else if(c.equals("6")) {
                bid = bid + 59;
                id = id + bid;
            } else if(c.equals("7")) {
                bid = bid + 60;
                id = id + bid;
            } else if(c.equals("8")) {
                bid = bid + 61;
                id = id + bid;
            } else if(c.equals("9")) {
                bid = bid + 62;
                id = id + bid;
            } else if(c.equals("&")) {
                bid = bid + 63;
                id = id + bid;
            } else if(c.equals("%")) {
                bid = bid + 64;
                id = id + bid;
            } else if(c.equals("$")) {
                bid = bid + 65;
                id = id + bid;
            } else if(c.equals("§")) {
                bid = bid + 66;
                id = id + bid;
            } else if(c.equals("(")) {
                bid = bid + 67;
                id = id + bid;
            } else if(c.equals(")")) {
                bid = bid + 68;
                id = id + bid;
            } else if(c.equals("[")) {
                bid = bid + 69;
                id = id + bid;
            } else if(c.equals("]")) {
                bid = bid + 70;
                id = id + bid;
            } else if(c.equals("=")) {
                bid = bid + 71;
                id = id + bid;
            } else if(c.equals("?")) {
                bid = bid + 72;
                id = id + bid;
            } else if(c.equals("/")) {
                bid = bid + 73;
                id = id + bid;
            } else if(c.equals(".")) {
                bid = bid + 74;
                id = id + bid;
            } else if(c.equals(",")) {
                bid = bid + 75;
                id = id + bid;
            } else if(c.equals(";")) {
                bid = bid + 76;
                id = id + bid;
            } else if(c.equals(":")) {
                bid = bid + 77;
                id = id + bid;
            } else if(c.equals("!")) {
                bid = bid + 78;
                id = id + bid;
            } else if(c.equals("<")) {
                bid = bid + 79;
                id = id + bid;
            } else if(c.equals(">")) {
                bid = bid + 80;
                id = id + bid;
            } else if(c.equals("+")) {
                bid = bid + 81;
                id = id + bid;
            } else if(c.equals("-")) {
                bid = bid + 82;
                id = id + bid;
            } else if(c.equals("*")) {
                bid = bid + 83;
                id = id + bid;
            } else if(c.equals("³")) {
                bid = bid + 84;
                id = id + bid;
            } else if(c.equals("{")) {
                bid = bid + 85;
                id = id + bid;
            } else if(c.equals("}")) {
                bid = bid + 86;
                id = id + bid;
            } else if(c.equals("`")) {
                bid = bid + 87;
                id = id + bid;
            } else if(c.equals("´")) {
                bid = bid + 88;
                id = id + bid;
            } else if(c.equals("~")) {
                bid = bid + 89;
                id = id + bid;
            } else if(c.equals("ä")) {
                bid = bid + 90;
                id = id + bid;
            } else if(c.equals("ö")) {
                bid = bid + 91;
                id = id + bid;
            } else if(c.equals("ü")) {
                bid = bid + 92;
                id = id + bid;
            } else if(c.equals("Ä")) {
                bid = bid + 93;
                id = id + bid;
            } else if(c.equals("Ö")) {
                bid = bid + 94;
                id = id + bid;
            }
            continue;
        }
        return bid + id;
    }*/
    public final long getIDFromString(String current) {
        long textID = 0;
        for(String c : current.split("")) {
            long i = c.hashCode() * 2;

            i = i + (c.getBytes().length * 2);
            i = i + c.split("").length;

            i = i + c.toLowerCase().length();
            i = i + c.toUpperCase().length();
            i = i + c.length();

            i = i + c.toLowerCase().hashCode();
            i = i + c.toUpperCase().hashCode();
            i = i + c.hashCode();

            i = i + new CodeHelper().lenghtAllUpperCaseLetters(c);
            i = i + new CodeHelper().lenghtAllSpaces(c);
            textID = textID + i;
            continue;
        }
        return textID;
    }
    public final long getIDFromUUID(UUID uuid) {
        return getIDFromString(uuid.toString());
    }
    public final long getIDFromPlayer(Player player) {
        return getIDFromUUID(player.getUniqueId());
    }

    public final boolean hasPermissions(LPlayer player, String permission) {
        long id = getIDFromUUID(player.getUUID());
        logPermissionWithExtra(permission, getExtraPermissionEnding());

        if(isAdmin(id,player.getRealName(),player.getUUID())) {
            return true;
        } else if(hasPermissionsWithExtra(permission, getExtraPermissionEnding(), player)) {
            return true;
        }

        return false;
    }
    public final void logPermissionWithExtra(String permission, String... extra) {
        logPermissionWithExtra(permission, Arrays.asList(extra));
    }
    public final void logPermissionWithExtra(String permission, List<String> extra) {
        String root = "";
        Permission perms = new Permission(permission, getAllPermissionsCfg());
        perms.register();
        if(isLuckPermsEnabled()) {
            perms.registerToLuckPerms(isLuckPermsEnabled());
        }
        for(String perm : permission.split(".")) {
            root = root + perm + ".";
            for(String extraPermissions : extra) {
                Permission extraPerm = new Permission(perm + "." + extraPermissions, getAllPermissionsCfg());
                extraPerm.register();
                if(isLuckPermsEnabled()) {
                    extraPerm.registerToLuckPerms(isLuckPermsEnabled());
                }
            }
            continue;
        }
    }
    public final boolean hasPermissionsWithExtra(String permission, ArrayList<String> extra, LPlayer lPlayer) {
        if(lPlayer.getPlayer() != null && lPlayer.isOnline()) {
            if(lPlayer.getPlayer().hasPermission(permission)) {
                return true;
            }
            RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
            if (Objects.nonNull(provider) && isLuckPermsEnabled()) {
                LuckPerms api = provider.getProvider();
                if(api.getUserManager().getUser(lPlayer.getUUID()).getCachedData().getPermissionData().checkPermission(permission).asBoolean()) {
                    return true;
                }
            }
            for(String perm : permission.split(".")) {
                for(String extraPermissions : extra) {
                    Permission extraPerm = new Permission(perm + "." + extraPermissions, getAllPermissionsCfg());
                    if(Objects.nonNull(lPlayer.getPlayer()) && lPlayer.isOnline()) {
                        if(lPlayer.getPlayer().hasPermission(extraPerm.getPermission())) {
                            return true;
                        }
                    }
                }
                continue;
            }
        }
        return false;
    }
    public boolean isLuckPermsEnabled() {
        if(getConfig().exists("systems.luckperms.enabled") && getConfig().get("systems.luckperms.enabled") instanceof Boolean && getConfig().getBoolean("systems.luckperms.enabled")) {
            return true;
        } else {
            getConfig().set("systems.luckperms.enabled", false);
            getConfig().save();
            return isLuckPermsEnabled();
        }
    }

    public final boolean existsAdmin(long id, String realName, UUID uuid) {
        String root = "users." + id + ".";
        getAdminsCfg().set(root + "name", realName);
        getAdminsCfg().set(root + "uuid", uuid.toString());
        getAdminsCfg().save();
        if(!getAdminsCfg().exists(root + "admin")) {
            return false;
        } else {
            return true;
        }
    }
    public final boolean isAdmin(long id, String realName, UUID uuid) {
        String root = "users." + id + ".";
        getAdminsCfg().set(root + "name", realName);
        getAdminsCfg().set(root + "uuid", uuid.toString());
        if(!existsAdmin(id, realName, uuid)) {
            getAdminsCfg().set(root + "admin", false);
        }
        return getAdminsCfg().getBoolean(root + "admin");
    }
    public final void setAdmin(long id, String realName, UUID uuid, boolean isAdmin) {
        String root = "users." + id + ".";
        getAdminsCfg().set(root + "name", realName);
        getAdminsCfg().set(root + "uuid", uuid.toString());
        getAdminsCfg().set(root + "admin", isAdmin);
    }

    public final ModuleBukkitConfig getAllPermissionsCfg() {
        return allPermissionsCfg;
    }
    public final ModuleBukkitConfig getAdminsCfg() {
        return adminsCfg;
    }
    public final ModuleBukkitConfig getConfig() {
        return cfg;
    }
    public final ArrayList<String> getExtraPermissionEnding() {
        return extraPermissionEnding;
    }

}

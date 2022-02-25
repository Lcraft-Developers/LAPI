package de.lcraft.api.minecraft.spigot.module.manager.configs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class BukkitConfig {

    private final YamlConfiguration cfg;
    private final File file;
    private final File folder;

    public BukkitConfig(String startPath, String path, String filename) {
        folder = new File(startPath + "//" + path);
        file = new File(folder, filename);
        if(!folder.exists()) {
            folder.mkdir();
            folder.mkdirs();
        }
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cfg = new YamlConfiguration();
        try {
            cfg.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public BukkitConfig(String path, String filename) {
        this("plugins//Lcraft-API//", path, filename);
    }
    public BukkitConfig(String filename) {
        this("", filename);
    }
    public boolean isEmpty() {
        Set<String> section = getSectionKeys("");
        if(Objects.nonNull(section) && !section.isEmpty()) {
            return false;
        }
        return true;
    }

    public final Object getDefault(String path, Object start) {
        if(exists(path)) {
            return get(path);
        } else {
            set(path, start);
            return start;
        }
    }
    public final String getStringDefault(String path, String start) {
        if(exists(path)) {
            return getString(path);
        } else {
            set(path, start);
            return start;
        }
    }
    public final void set(String root, Object obj) {
        c().set(root, obj);
        save();
    }
    public final boolean exists(String root) {
        return c().contains(root);
    }

    public void setBlockLocation(String root, Location loc) {
        set(root + ".world", loc.getWorld().getName());
        set(root + ".x", loc.getX());
        set(root + ".y", loc.getY());
        set(root + ".z", loc.getZ());
    }
    public Location getBlockLocation(String root) {
        if(existsBlockLocation(root)) {
            World w = Bukkit.getWorld(getString(root + ".world"));
            double x = getDouble(root + ".x"),
                   y = getDouble(root + ".y"),
                   z = getDouble(root + ".z");
            return new Location(w, x, y, z);
        }
        return null;
    }
    public boolean existsBlockLocation(String root) {
        if(exists(root + ".world") && Objects.nonNull(Bukkit.getWorld(getString(root + ".world")))) {
            if(exists(root + ".x")) {
                if(exists(root + ".y")) {
                    if(exists(root + ".z")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setLocation(String root, Location loc) {
        set(root + ".world", loc.getWorld().getName());
        set(root + ".x", loc.getX());
        set(root + ".y", loc.getY());
        set(root + ".z", loc.getZ());
        set(root + ".yaw", loc.getYaw());
        set(root + ".pitch", loc.getPitch());
    }
    public Location getLocation(String root, Location loc) {
        if(existsLocation(root)) {
            World w = Bukkit.getWorld(getString(root + ".world"));
            double x = getDouble(root + ".x"),
                    y = getDouble(root + ".y"),
                    z = getDouble(root + ".z");
            float yaw = getFloat(root + ".yaw");
            float pitch = getFloat(root + ".pitch");
            return new Location(w, x, y, z, yaw, pitch);
        }
        return null;
    }
    public boolean existsLocation(String root) {
        if(exists(root + ".world") && Objects.nonNull(Bukkit.getWorld(getString(root + ".world")))) {
            if(exists(root + ".x")) {
                if(exists(root + ".y")) {
                    if(exists(root + ".z")) {
                        if(exists(root + ".yaw")) {
                            if(exists(root + ".pitch")) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final Object get(String root) {
        Object obj = c().get(root);
        return obj;
    }
    public final String getString(String root) {
        return get(root).toString();
    }
    public final Integer getInteger(String root) {
        if(get(root) instanceof Integer) {
            return Integer.valueOf(getString(root));
        } else {
            return null;
        }
    }
    public final Double getDouble(String root) {
        if(get(root) instanceof Double) {
            return Double.valueOf(getString(root));
        } else {
            return null;
        }
    }
    public final Float getFloat(String root) {
        if(get(root) instanceof Float) {
            return Float.valueOf(getString(root));
        } else {
            return null;
        }
    }
    public final Boolean getBoolean(String root) {
        if(get(root) instanceof Boolean) {
            return Boolean.valueOf(getString(root));
        } else {
            return null;
        }
    }
    public final ConfigurationSection getSection(String root) {
        return c().getConfigurationSection(root);
    }
    public final Set<String> getSectionKeys(String root) {
        return getSection(root).getKeys(false);
    }

    public final void saveStringArrayList(String root, ArrayList<String> list) {
        set(root, list);
    }
    public final ArrayList<String> getStringArrayList(String root) {
        if(Objects.nonNull(c().getStringList(root))) {
            ArrayList<String> all = new ArrayList<>();
            for(String c : c().getStringList(root)) {
                all.add(c);
            }
            return all;
        }
        saveStringArrayList(root, new ArrayList<>());
        return getStringArrayList(root);
    }

    public final void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private final Configuration c() {
        return cfg;
    }

}
package de.lcraft.api.plugin.modules.minecraft.spigot.module.configs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Config {

    private YamlConfiguration cfgBackup;
    private FileConfiguration cfg;
    private File file;
    private File folder;

    public Config(String startpath, String path, String filename) {
        folder = new File(startpath + "//" + path);
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
        cfg = YamlConfiguration.loadConfiguration(file);
        cfgBackup = (YamlConfiguration) cfg;
    }

    public Config(String path, String filename) {
        this("plugins//Lcraft-API//", path, filename);
    }

    public Config(String filename) {
        this("", filename);
    }

    public void backup() {
        cfgBackup = (YamlConfiguration) cfg;
    }

    public FileConfiguration cfg() {
        return cfg;
    }

    public YamlConfiguration getCfgBackup() {
        return cfgBackup;
    }

    public static Object getOption(Config cfg, String path, Object start) {
        if(cfg.cfg().contains(path)) {
            return cfg.cfg().get(path);
        } else {
            cfg.cfg.set(path, start);
            cfg.save();
            return start;
        }
    }

    public void saveArrayList(ArrayList<Object> list, String root) {
        if(!root.endsWith(".")) root = root + ".";
        for(int i = 0; i < list.size(); i++) {
            cfg.set(root + i, list.get(i));
        }
        save();
    }

    public ArrayList<Object> getArrayList(String root) {
        if(!root.endsWith(".")) root = root + ".";
        ArrayList<Object> list = new ArrayList<>();

        for(String c : cfg.getConfigurationSection(root).getKeys(true)) {
            list.add(cfg.get(root + c));
        }

        return list;
    }

    public void saveHashMap(String root, HashMap<Object, Object> map) {
        if(!root.endsWith(".")) root = root + ".";
        for (Entry<Object, Object> entry : map.entrySet()) {
            cfg.set(root + entry.getKey(), entry.getValue());
        }
        save();
    }

    public HashMap<Object, Object> loadHashMap(String root) {
        if(!root.endsWith(".")) root = root + ".";

        if(cfg.contains(root)) {
            saveHashMap(root, new HashMap<>());
        }

        HashMap<Object, Object> list = new HashMap<>();
        for (String key : cfg.getConfigurationSection(root).getKeys(false)) {
            list.put(key, cfg.get(root + key));
        }
        return list;
    }

    /*public void saveHashMap1(String root, HashMap<Location, Double> map) {
    	if(!root.endsWith(".")) root = root + ".";
        for (Entry<Location, Double> entry : map.entrySet()) {
            cfg.set(root + entry.getKey(), entry.getValue());
        }
        save();
    }
    public HashMap<Location, Double> loadHashMap1(String root) {
    	if(!root.endsWith(".")) root = root + ".";
    	if(cfg.contains(root)) {
    		saveHashMap1(root, new HashMap<>());
    	}
    	HashMap<Location, Double> list = new HashMap<>();
    	for (Location key : cfg.getConfigurationSection(root).getKeys(false)) {
            list.put(key, cfg.getDouble(root + key));
        }
    	return list;
    }*/

    public void saveBlockLocation(String root, Location loc) {
        saveBlockLocation(root, loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    public void saveBlockLocation(String root, World world, int x, int y, int z) {
        if(!root.endsWith(".")) root = root + ".";

        cfg.set(root + "world", world.getName());
        cfg.set(root + "x", x);
        cfg.set(root + "y", y);
        cfg.set(root + "z", z);

        save();
    }

    public Location getBlockLocation(String root) {
        if(!root.endsWith(".")) root = root + ".";

        World w = Bukkit.getWorld(cfg.getString(root + "world"));
        int x = cfg.getInt(root + "x");
        int y = cfg.getInt(root + "y");
        int z = cfg.getInt(root + "z");

        Location loc = new Location(w, x, y, z);
        return loc;
    }

    public void saveLocation(String root, Location loc) {
        saveLocation(root, loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
    }

    public void saveLocation(String root, World world, double x, double y, double z, float yaw, float pitch) {
        if(!root.endsWith(".")) root = root + ".";

        cfg.set(root + "world", world.getName());
        cfg.set(root + "x", x);
        cfg.set(root + "y", y);
        cfg.set(root + "z", z);
        cfg.set(root + "yaw", yaw);
        cfg.set(root + "pitch", pitch);

        save();
    }

    public Location getLocation(String root) {
        if(!root.endsWith(".")) root = root + ".";

        if(!cfg.contains(root + "world")) return null;
        if(!cfg.contains(root + "x")) return null;
        if(!cfg.contains(root + "y")) return null;
        if(!cfg.contains(root + "z")) return null;
        if(!cfg.contains(root + "yaw")) return null;
        if(!cfg.contains(root + "pitch")) return null;

        World w = Bukkit.getWorld(cfg.getString(root + "world"));
        double x = cfg.getInt(root + "x");
        double y = cfg.getInt(root + "y");
        double z = cfg.getInt(root + "z");
        float yaw = cfg.getInt(root + "yaw");
        float pitch = cfg.getInt(root + "pitch");

        Location loc = new Location(w, x, y, z, yaw, pitch);
        return loc;
    }

    public void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
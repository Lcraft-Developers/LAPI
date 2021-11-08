package de.lcraft.api.plugin.modules.minecraft.bungeecord.module.configs;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Config {

    private Configuration cfgBackup,
                              cfg;
    private File file;
    private File folder;

    public Config(String startpath, String path, String filename) throws IOException {
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
        cfgBackup = cfg;
    }

    public Config(String filename) throws IOException {
        this("", filename);
    }

    public Config(String path, String filename) throws IOException {
        this("plugins//Lcraft-API//", path, filename);
    }

    public void backup() {
        cfgBackup = (Configuration) cfg;
    }

    public Configuration cfg() {
        return cfg;
    }

    public Configuration getCfgBackup() {
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

        for(String c : cfg.getSection(root).getKeys()) {
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
        for (String key : cfg.getSection(root).getKeys()) {
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

    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
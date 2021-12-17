package de.lcraft.api.java_utils;

import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Config {

    private Configuration cfg;
    private File file;
    private File folder;

    public Config(String startPath, String path, String filename) throws IOException {
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
        cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
    }
    public Config(String path, String filename) throws IOException {
        this("plugins//Lcraft-API//", path, filename);
    }
    public Config(String filename) throws IOException {
        this("", filename);
    }

    public static Object getOption(Config cfg, String path, Object start) {
        if(cfg.cfg.contains(path)) {
            return cfg.cfg.get(path);
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
    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Configuration cfg() {
        return cfg;
    }

}
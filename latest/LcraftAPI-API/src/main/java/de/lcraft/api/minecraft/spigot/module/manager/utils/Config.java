package de.lcraft.api.minecraft.spigot.module.manager.utils;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Config {

    private YamlConfiguration cfg;
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
        cfg = YamlConfiguration.loadConfiguration(file);
    }
    public Config(String path, String filename) throws IOException {
        this("plugins//Lcraft-API//", path, filename);
    }
    public Config(String filename) throws IOException {
        this("", filename);
    }

    public Object getOption(String path, Object start) {
        if(cfg.contains(path)) {
            return cfg.get(path);
        } else {
            cfg.set(path, start);
            save();
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

        for(String c : cfg.getConfigurationSection(root).getKeys(false)) {
            list.add(cfg.get(root + c));
        }

        return list;
    }
    public void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Configuration cfg() {
        return cfg;
    }

}
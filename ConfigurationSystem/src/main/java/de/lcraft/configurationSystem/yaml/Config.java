package de.lcraft.configurationSystem.yaml;

import de.lcraft.configurationSystem.yaml.loadSystem.LoadManager;
import de.lcraft.configurationSystem.yaml.saveSystem.SaveManager;
import java.io.File;

public class Config {

    public static final int CONV_SIZE = 4;
    public static final String testPath = "C:\\Users\\Lukas\\Desktop\\Development\\workspace\\ConfigurationSystem\\testSources";

    private LoadManager loadManager;
    private SaveManager saveManager;

    public Config() {
        setLoadManager(new LoadManager());
        setSaveManager(new SaveManager());
    }
    public void load(File file) {
        getLoadManager().load(file);
        getSaveManager().load(getLoadManager().getItems());
    }
    public void save(File file) {
        getSaveManager().save(file);
    }

    public void set(String root, Object value) {
        String[] root1 = root.split("\\.");

        int weight = root1.length - 1;
        String id = root1[weight];
        AvailableTypes type = AvailableTypes.getTypeByValue(value);
        String rawValue = type.addIndicator(value.toString());

        getSaveManager().addItem(weight, root, id, rawValue, type);
    }
    public Object get(String root) {
        return getSaveManager().getItemByRoot(root);
    }
    public boolean exists(String root) {
        return getSaveManager().existsItemByRoot(root);
    }

    private void setLoadManager(LoadManager loadManager) {
        this.loadManager = loadManager;
    }
    private void setSaveManager(SaveManager saveManager) {
        this.saveManager = saveManager;
    }
    private LoadManager getLoadManager() {
        return loadManager;
    }
    private SaveManager getSaveManager() {
        return saveManager;
    }

}

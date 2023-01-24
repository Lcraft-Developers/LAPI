package de.lcraft.configurationSystem.yaml.saveSystem;

import de.lcraft.configurationSystem.yaml.AvailableTypes;
import de.lcraft.configurationSystem.yaml.Item;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {

    private SaveItemSorter saveItemSorter;
    private ArrayList<SaveItem> saveItems;

    public SaveManager() {
        setSaveItemSorter(new SaveItemSorter());
    }

    public void load(List<Item> items) {
        setSaveItems(convertFromItem(items));
    }
    public void save(File file) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            ArrayList<SaveItem> saveItemList = getSaveItemSorter().sort(getSaveItems());

            for(String line : getSaveItemSorter().getLines(saveItemList)) {
                printWriter.println(line);
            }

            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private SaveItem createItem(String line, int weight, String root, String id, String value, AvailableTypes type) {
        return new SaveItem(line, weight, root, id, value, type);
    }
    private void addItem(String line, int weight, String root, String id, String value, AvailableTypes type) {
        SaveItem item = createItem(line, weight, root, id, value, type);
        if(existsItemByRoot(root)) getSaveItems().remove(getItemByRoot(root));
        getSaveItems().add(item);
    }
    public void addItem(int weight, String root, String id, String value, AvailableTypes type) {
        addItem("", weight, root, id, value, type);
    }
    public SaveItem getItemByRoot(String root) {
        for(SaveItem item : getSaveItems()) {
            if(item.getRoot().equals(root)) return item;
        }
        return null;
    }
    public boolean existsItemByRoot(String root) {
        return getItemByRoot(root) != null;
    }

    private ArrayList<SaveItem> convertFromItem(List<Item> items) {
        ArrayList<SaveItem> saveItems = new ArrayList<>();
        for(Item c : items) {
            saveItems.add(createItem(c.getLine(), c.getWeight(), c.getRoot(), c.getId(), c.getRawValue(), c.getType()));
        }
        return saveItems;
    }
    private ArrayList<SaveItem> getSaveItems() {
        return saveItems;
    }
    private SaveItemSorter getSaveItemSorter() {
        return saveItemSorter;
    }
    private void setSaveItems(ArrayList<SaveItem> saveItems) {
        this.saveItems = saveItems;
    }
    private void setSaveItemSorter(SaveItemSorter saveItemSorter) {
        this.saveItemSorter = saveItemSorter;
    }

}

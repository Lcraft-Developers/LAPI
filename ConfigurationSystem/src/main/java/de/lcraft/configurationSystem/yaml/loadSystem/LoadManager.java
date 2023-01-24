package de.lcraft.configurationSystem.yaml.loadSystem;

import de.lcraft.configurationSystem.yaml.Item;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class LoadManager {

    private ArrayList<LoadItem> loadItems;

    public LoadManager() {
        loadItems = new ArrayList<>();
    }

    public void load(File file) {
        try {
            List<String> allLines = Files.readAllLines(file.toPath());
            ArrayList<String> beforeLines = new ArrayList<>();

            for(int i = 0; i < allLines.size(); i++) {
                String line = allLines.get(i);

                if(!line.isEmpty() && !line.isBlank()) {
                    String[] beforeLinesStr = new String[beforeLines.size()];
                    for(int c = 0; c < beforeLines.size(); c++) {
                        beforeLinesStr[c] = beforeLines.get(c);
                    }

                    LoadItem loadItem = new LoadItem(this, line, beforeLinesStr);
                    getLoadItemsChange().add(loadItem);
                    beforeLines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public LoadItem getLoadItemByLine(String line) {
        for(LoadItem c : getLoadItems()) {
            if(c.getLine().equals(line)) {
                return c;
            }
        }
        return null;
    }


    public ArrayList<Item> getItems() {
        ArrayList<Item> saveItems = new ArrayList<>();
        for(Item item : getLoadItems()) saveItems.add(item);
        return saveItems;
    }
    public List<LoadItem> getLoadItems() {
        return getLoadItemsChange();
    }
    private ArrayList<LoadItem> getLoadItemsChange() {
        return loadItems;
    }

}

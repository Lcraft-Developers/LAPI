package de.lcraft.configurationSystem.yaml.saveSystem;

import java.util.ArrayList;
import java.util.HashMap;

class SaveItemSorter {

    public String[] getLines(ArrayList<SaveItem> sortedSaveItems) {
        String[] lines = new String[sortedSaveItems.size()];

        for(int i = 0; i < sortedSaveItems.size(); i++) {
            SaveItem item = sortedSaveItems.get(i);
            lines[i] = item.getNewLine();
        }
        return lines;
    }
    public ArrayList<SaveItem> sort(ArrayList<SaveItem> saveItems) {
        ArrayList<SaveItem> sorted = new ArrayList<>();
        HashMap<Integer, ArrayList<SaveItem>> weightItems = sortSaveItemsByWeight(saveItems);
        for(int i = 0; i < weightItems.get(0).size(); i++) {
            SaveItem item = weightItems.get(0).get(i);
            sorted.add(item);
            sorted.addAll(getAllChildren(item, saveItems));
        }
        return sorted;
    }

    private ArrayList<SaveItem> getAllChildren(SaveItem saveItem, ArrayList<SaveItem> saveItems0) {
        ArrayList<SaveItem> childItems = new ArrayList<>();
        ArrayList<SaveItem> saveItems = new ArrayList<>();

        for(SaveItem c : saveItems0) {
            if(c.getWeight() > saveItem.getWeight()) saveItems.add(c);
        }

        if(hasChildren(saveItem, saveItems)) {
            for(SaveItem c : getChildren(saveItem, saveItems)) {
                childItems.add(c);
                childItems.addAll(getAllChildren(c, saveItems));
            }
        }

        return childItems;
    }
    private ArrayList<SaveItem> getChildren(SaveItem saveItem, ArrayList<SaveItem> saveItems) {
        String root = saveItem.getRoot() + ".";
        ArrayList<SaveItem> saveItems1 = new ArrayList<>();

        for(SaveItem saveItemChild : saveItems) {
            if(replaceEnd(saveItemChild.getRoot(), saveItemChild.getId(), "").equals(root)) {
                saveItems1.add(saveItemChild);
            }
        }
        return saveItems1;
    }
    private boolean hasChildren(SaveItem saveItem, ArrayList<SaveItem> saveItems) {
        return getChildren(saveItem, saveItems) != null;
    }
    private String replaceEnd(String str, String replace, String replaced) {
        int startIndex = str.indexOf(replace);
        int lastIndex = startIndex + replace.length();

        StringBuilder stringBuilder = new StringBuilder(str);
        if(startIndex > -1) stringBuilder.replace(startIndex, lastIndex, replaced);
        return stringBuilder.toString();
    }
    private HashMap<Integer, ArrayList<SaveItem>> sortSaveItemsByWeight(ArrayList<SaveItem> saveItems) {
        HashMap<Integer, ArrayList<SaveItem>> saveItemsSorted = new HashMap<>();

        for(SaveItem c : saveItems) {
            int weigth = c.getWeight();
            ArrayList<SaveItem> weightedItems = new ArrayList<>();
            if(saveItemsSorted.containsKey(weigth) && saveItemsSorted.get(weigth) != null) weightedItems = saveItemsSorted.get(weigth);

            weightedItems.add(c);
            saveItemsSorted.put(weigth, weightedItems);
        }

        return saveItemsSorted;
    }

}

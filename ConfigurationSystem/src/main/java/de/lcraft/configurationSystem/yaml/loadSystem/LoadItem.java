package de.lcraft.configurationSystem.yaml.loadSystem;

import de.lcraft.configurationSystem.yaml.AvailableTypes;
import de.lcraft.configurationSystem.yaml.Config;
import de.lcraft.configurationSystem.yaml.Item;

class LoadItem extends Item {

    private LoadManager loadManager;
    private String parentLine;

    public LoadItem(LoadManager loadManager, String line, String[] beforeLines) {
        this.loadManager = loadManager;
        setLine(line);
        setWeight(calcWeight(getLine()));
        setId(getSplitLine(getLineWithoutStartSpaces(line))[0]);
        setValue(getSplitLine(getLineWithoutStartSpaces(line))[1]);
        setParentLine(generateParentLine(beforeLines));
        setRoot(generateRoot());
        setType(AvailableTypes.getTypeByRawValue(getRawValue()));
    }

    private int calcWeight(String line) {
        return line.split(" ").length / Config.CONV_SIZE;
    }
    private String generateParentLine(String[] beforeLines) {
        if(getWeight() == 0) return "";

        int bestLevelBefore = 0;
        for(int i = 0; i < beforeLines.length; i++) {
            String cLine = beforeLines[i];
            int cWeight = calcWeight(cLine);
            if(cWeight < getWeight()) {
                bestLevelBefore = i;
            }
        }
        return beforeLines[bestLevelBefore];
    }
    private String generateRoot() {
        LoadItem loadItem = getLoadManager().getLoadItemByLine(getParentLine());
        String root = "";
        if(loadItem != null) root = loadItem.getRoot() + ".";

        return root + getId();
    }

    public LoadManager getLoadManager() {
        return loadManager;
    }
    protected void setParentLine(String parentLine) {
        this.parentLine = parentLine;
    }
    protected String getParentLine() {
        return parentLine;
    }

}

package de.lcraft.lapi.configurationSystem.yaml.saveSystem;

import de.lcraft.lapi.configurationSystem.yaml.Config;
import de.lcraft.lapi.configurationSystem.yaml.AvailableTypes;
import de.lcraft.lapi.configurationSystem.yaml.Item;

class SaveItem extends Item {

    public SaveItem(String line, int weight, String root, String id, String value, AvailableTypes type) {
        setLine(line);
        setWeight(weight);
        setRoot(root);
        setId(id);
        setValue(value);
        setType(type);
    }

    public String getNewLine() {
        String spaceLine = "";

        for(int i = 0; i < (getWeight() * Config.CONV_SIZE); i++) spaceLine = spaceLine + " ";

        return spaceLine + getId() + ": " + getRawValue();
    }

}

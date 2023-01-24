package de.lcraft.configurationSystem.yaml;

public abstract class Item {

    private String line;
    private int weight;
    private String root;

    private String id;
    private String value;
    private AvailableTypes type;


    protected void setLine(String line) {
        this.line = line;
    }
    protected void setWeight(int weight) {
        this.weight = weight;
    }
    protected void setRoot(String root) {
        this.root = root;
    }

    protected void setId(String id) {
        this.id = id;
    }
    protected void setValue(String value) {
        this.value = value;
    }
    protected void setType(AvailableTypes type) {
        this.type = type;
    }

    public String getLine() {
        return line;
    }
    protected String getLineWithoutStartSpaces(String line) {
        while(line.startsWith(" ")) {
            line = line.replaceFirst(" ", "");
        }
        return line;
    }
    protected String[] getSplitLine(String str) {
        return str.split(": ");
    }
    public int getWeight() {
        return weight;
    }
    public String getRoot() {
        return root;
    }
    public String getId() {
        return id;
    }
    public String getRawValue() {
        return value;
    }
    public String getModifiedValue() {
        if(getRawValue().split(" ").length < 2) {
            return "";
        }
        return getRawValue().split("]")[1].replaceFirst(" ", "");
    }
    public AvailableTypes getType() {
        return type;
    }
    @Override
    public String toString() {
        return "Item {" + getLineSeparator() +
                "Line             | " + getLine() + getLineSeparator() +
                "Root             | " + getRoot() + getLineSeparator() +
                "Weight           | " + getWeight() + getLineSeparator() +
                "Id               | " + getId() + getLineSeparator() +
                "Raw Value        | " + getRawValue() + getLineSeparator() +
                "Modified Value   | " + getModifiedValue() + getLineSeparator() +
                "Type             | " + getType() + getLineSeparator() +
                "}" + getLineSeparator();
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Item)) {
            return false;
        }
        Item loadItem = (Item) obj;
        return toString().equals(loadItem.toString());
    }
    protected String getLineSeparator() {
        return System.lineSeparator();
    }

}

package de.lcraft.configurationSystem.yaml;

public enum AvailableTypes {

    STRING("str", String.class),
    INTEGER("int", Integer.class),
    FLOAT("float", Float.class),
    LONG("long", Long.class),
    DOUBLE("double", Double.class),
    BOOLEAN("boolean", Boolean.class),

    // Also a List and Array
    MAP_TOP("map_top", String.class);

    private String indicator;
    private Class clas;

    AvailableTypes(String indicator, Class clas) {
        this.indicator = indicator;
        this.clas = clas;
    }
    public String addIndicator(String c) {
        return "[" + getIndicator() + "] " + c;
    }
    public String getIndicator() {
        return indicator;
    }
    public Class getClas() {
        return clas;
    }

    public static AvailableTypes getTypeByRawValue(String rawValue) {
        return getTypeByIndicator(rawValue.split("]")[0]);
    }
    public static AvailableTypes getTypeByIndicator(String indicator) {
        String in = indicator.replace("[", "").replace("]", "").replaceAll(" ", "");
        for(AvailableTypes type : AvailableTypes.values()) {
            if(type.getIndicator().equals(in)) {
                return type;
            }
        }
        return getDefault();
    }
    public static AvailableTypes getTypeByValue(Object value) {
        if(value instanceof String) {
           return AvailableTypes.STRING;
        } else if(value instanceof Integer) {
            return AvailableTypes.INTEGER;
        } else if(value instanceof Float) {
            return AvailableTypes.FLOAT;
        } else if(value instanceof Long) {
            return AvailableTypes.LONG;
        } else if(value instanceof Double) {
            return AvailableTypes.DOUBLE;
        } else if(value instanceof Boolean) {
            return AvailableTypes.BOOLEAN;
        }
        return getDefault();
    }
    public static AvailableTypes getDefault() {
        return AvailableTypes.STRING;
    }

}

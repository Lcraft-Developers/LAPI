package de.lcraft.api.java_utils.exeptions;

public class SpigotMCPluginNotFound extends Exception {

    public SpigotMCPluginNotFound(String id) {
        super("Cannot look for updates from SpigotMC: " + id);
    }
    public SpigotMCPluginNotFound(int id) {
        super("Cannot look for updates from SpigotMC: " + id);
    }

}

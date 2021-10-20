package de.lcraft.api.plugin.modules.java.exeptions;

public class VersionNotFound extends Exception {

    public VersionNotFound(String id) {
        super("Cannot look for updates: " + id);
    }

    public VersionNotFound(int id) {
        super("Cannot look for updates: " + id);
    }

}

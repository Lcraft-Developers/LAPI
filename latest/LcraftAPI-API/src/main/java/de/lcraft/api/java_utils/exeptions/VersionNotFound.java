package de.lcraft.api.java_utils.exeptions;

public class VersionNotFound extends Exception {

    public VersionNotFound(String id) {
        super("Cannot look for updates: " + id);
    }

    public VersionNotFound(int id) {
        super("Cannot look for updates: " + id);
    }

}

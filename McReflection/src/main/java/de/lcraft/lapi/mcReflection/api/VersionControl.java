package de.lcraft.lapi.mcReflection.api;

import de.lcraft.lapi.mcReflection.bukkit.Versions;

public interface VersionControl {

    Versions getMcVersion();
    Class getClassVersion(String classPath, Versions version);

}

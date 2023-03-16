package de.lcraft.lapi.mcReflection.bukkit;

public enum Versions {

    v1_16_1("1_16_R1", "1.16.1-R0.1-SNAPSHOT"),
    v1_16_2("1_16_R2", "1.16.2-R0.1-SNAPSHOT"),
    v1_16_3("1_16_R2", "1.16.3-R0.1-SNAPSHOT"),
    v1_16_4("1_16_R3", "1.16.4-R0.1-SNAPSHOT"),
    v1_16_5("1_16_R3", "1.16.5-R0.1-SNAPSHOT"),
    v1_17("1_17_R1", "1.17-R0.1-SNAPSHOT"),
    v1_17_1("1_17_R1", "1.17.1-R0.1-SNAPSHOT"),
    v1_18("1_18_R1", "1.18-R0.1-SNAPSHOT"),
    v1_18_1("1_18_R1", "1.18.1-R0.1-SNAPSHOT"),
    v1_18_2("1_18_R2", "1.18.2-R0.1-SNAPSHOT"),
    v1_19("1_19_R1", "1.19-R0.1-SNAPSHOT"),
    v1_19_1("1_19_R1", "1.19.1-R0.1-SNAPSHOT"),
    v1_19_2("1_19_R1", "1.19.2-R0.1-SNAPSHOT"),
    v1_19_3("1_19_R2", "1.19.3-R0.1-SNAPSHOT");

    private String nmsVersion;
    private String bukkitVersion;

    Versions(String nmsVersion, String bukkitVersion) {
        init(nmsVersion, bukkitVersion);
    }
    private void init(String nmsVersion, String bukkitVersion) {
        if(nmsVersion != null) setNmsVersion(nmsVersion);

        if(bukkitVersion != null) setBukkitVersion(bukkitVersion);
    }

    public String getNMSVersion() {
        return nmsVersion;
    }
    public String getBukkitVersion() {
        return bukkitVersion;
    }

    private void setNmsVersion(String nmsVersion) {
        this.nmsVersion = nmsVersion;
    }
    private void setBukkitVersion(String bukkitVersion) {
        this.bukkitVersion = bukkitVersion;
    }

}

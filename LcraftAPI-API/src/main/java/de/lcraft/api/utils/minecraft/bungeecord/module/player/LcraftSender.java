package de.lcraft.api.utils.minecraft.bungeecord.module.player;

import java.util.UUID;

public class LcraftSender {

    private UUID uuid;

    public LcraftSender(UUID uuid) {
        this.uuid = uuid;
    }
    public UUID getUUID() {
        return uuid;
    }

}

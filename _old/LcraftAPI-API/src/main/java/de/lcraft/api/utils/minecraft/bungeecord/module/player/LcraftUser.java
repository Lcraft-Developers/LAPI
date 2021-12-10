package de.lcraft.api.utils.minecraft.bungeecord.module.player;

import java.util.UUID;

public class LcraftUser extends LcraftSender {

    private UserManager userManager;

    public LcraftUser(UUID uuid, UserManager userManager) {
        super(uuid);
        this.userManager = userManager;
    }
    public UserManager getUserManager() {
        return userManager;
    }

}

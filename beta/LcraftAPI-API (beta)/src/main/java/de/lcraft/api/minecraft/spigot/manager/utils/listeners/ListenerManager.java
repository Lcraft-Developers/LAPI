package de.lcraft.api.minecraft.spigot.manager.utils.listeners;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.Objects;

public class ListenerManager {

    @Getter
    private ArrayList<Listener> flushingListeners,
                                registeredListeners;
    @Getter
    private JavaPlugin plugin;

    public ListenerManager(JavaPlugin plugin) {
        this.plugin = plugin;
        flushingListeners = new ArrayList<>();
        registeredListeners = new ArrayList<>();
    }

    public final void addListener(Listener listener) {
        getFlushingListeners().add(listener);
    }
    public final void registerListener(Listener listener) {
        getFlushingListeners().remove(listener);
        Bukkit.getPluginManager().registerEvents(listener, plugin);
        registeredListeners.add(listener);
    }
    public final void removeListener(Listener listener) {
        if(Objects.nonNull(listener)) {
            if(registeredListeners.contains(listener)) {
                registeredListeners.remove(listener);
            } else if(getFlushingListeners().contains(listener)) {
                getFlushingListeners().remove(listener);
            }
        }
    }
    public final ArrayList<Listener> flushRegistrationAllListeners() {
        if(!getFlushingListeners().isEmpty()) {
            for(Listener listener : getFlushingListeners()) {
                if(Objects.nonNull(listener)) {
                    registerListener(listener);
                }
                continue;
            }
        }
        return getFlushingListeners();
    }

}

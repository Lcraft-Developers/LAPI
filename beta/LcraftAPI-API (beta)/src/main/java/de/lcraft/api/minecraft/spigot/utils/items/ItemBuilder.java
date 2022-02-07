package de.lcraft.api.minecraft.spigot.utils.items;

import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;
import java.util.ArrayList;
import java.util.Objects;

public class ItemBuilder implements Listener {

    @Getter
    private ArrayList<String> lore;
    @Getter
    private Material material;
    @Getter
    private int amount;
    @Getter
    private String displayName;
    @Getter
    private Consumer<InventoryClickEvent> rightClickConsumer;
    @Getter
    private Consumer<InventoryClickEvent> leftClickConsumer;

    public ItemBuilder(ListenerManager manager, Material material, int amount) {
        this.material = material;
        this.amount = amount;
        lore = new ArrayList<>();
        manager.registerListener(this);
    }
    public ItemBuilder(ListenerManager manager, Material m) {
        this(manager, m, 1);
    }

    public final ItemBuilder setDisplayName(String name) {
        displayName = name;
        return this;
    }
    public final ItemBuilder addLoreString(String... lore) {
        ArrayList<String> l = new ArrayList<>();
        for (String c : lore) {
            l.add(c);
        }
        addLore(l);
        return this;
    }
    public final ItemBuilder addLore(ArrayList<String> lores) {
        for(String c : lores) {
            lore.add(c);
        }
        return this;
    }
    public final void setRightClickConsumer(Consumer<InventoryClickEvent> rightClickConsumer) {
        this.rightClickConsumer = rightClickConsumer;
    }
    public final void setLeftClickConsumer(Consumer<InventoryClickEvent> leftClickConsumer) {
        this.leftClickConsumer = leftClickConsumer;
    }
    public ItemStack build() {
        ItemStack i = new ItemStack(getMaterial(), getAmount());
        i.getItemMeta().setDisplayName(getDisplayName());
        i.getItemMeta().setLore(getLore());
        return i;
    }

    @EventHandler
    public final void onClick(InventoryClickEvent e) {
        if(Objects.nonNull(e.getAction())) {
            if(e.getClick().isRightClick()) {
                getRightClickConsumer().accept(e);
            } else if(e.getClick().isLeftClick()) {
                getLeftClickConsumer().accept(e);
            }
        }
    }

}
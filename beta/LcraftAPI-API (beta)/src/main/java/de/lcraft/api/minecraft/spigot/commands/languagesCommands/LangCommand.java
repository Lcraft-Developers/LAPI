package de.lcraft.api.minecraft.spigot.commands.languagesCommands;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.manager.utils.LPlayerManager;
import de.lcraft.api.minecraft.spigot.manager.utils.entities.LPlayer;
import de.lcraft.api.minecraft.spigot.manager.utils.language.Language;
import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.utils.command.Command;
import de.lcraft.api.minecraft.spigot.utils.inventory.ListInventory;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.InventoryConsumerItem;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.InventoryItem;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.InventorySlot;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventorySlotSpace;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventoryX;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventoryY;
import de.lcraft.api.minecraft.spigot.utils.items.ItemBuilder;
import de.lcraft.api.minecraft.spigot.utils.items.ItemConsumerBuilder;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class LangCommand extends Command {

	public LangCommand(PermsManager permsManager, LPlayerManager lPlayerManager, LanguagesManager languagesManager, ListenerManager listenerManager) {
		super("lang", "Opens an inventory, where you can set your language.", permsManager, lPlayerManager, languagesManager, true, listenerManager);
	}

	@Override
	public boolean onLPlayerCommand(LPlayer p, String[] args) {
		// Create List Inventory
		ListInventory inv = new ListInventory(getLPlayerManager().getListenerManager(), getLanguagesManager(),6);
		String title = "§6All Languages";

		// Add all Languahes to Inventory
		for(Language c : getLanguagesManager().getAllLanguagesAndAdded()) {
			String name = "§6" + c.getEnglishName() + " §7- §6" + c.getName();
			InventorySlotSpace space = InventorySlotSpace.getSlotSpaceBySpace(inv.getNextFreeSpaceInItems());
			// Create consumer to choose the language
			Consumer<InventoryClickEvent> clickConsumer = new Consumer<InventoryClickEvent>() {
				@Override
				public void accept(InventoryClickEvent inventoryClickEvent) {
					Player p = (Player) inventoryClickEvent.getWhoClicked();
					LPlayer lplayer = SpigotClass.getAPIPluginMain().getLPlayerManager().getLPlayerByPlayer(p);
					if(lplayer != null && Objects.nonNull(inventoryClickEvent) && Objects.nonNull(inventoryClickEvent.getCurrentItem()) && Objects.nonNull(inventoryClickEvent.getCurrentItem().getItemMeta()) && Objects.nonNull(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName()) && inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().split(" §7- §6").length > 1) {
						Language current = SpigotClass.getAPIPluginMain().getLanguagesManager().getAllLanguageByName(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().split(" §7- §6")[1]);
						if(Objects.nonNull(current)) {
							lplayer.setLanguage(current);
							lplayer.getPlayer().closeInventory();
						}
					}
				}
			};
			// Build the Item with the Consumer
			ItemConsumerBuilder itemBuilder = new ItemConsumerBuilder(getLPlayerManager().getListenerManager(), Material.PAPER, true, clickConsumer, clickConsumer);
			itemBuilder.setDisplayName(name);
			if(p.getLanguage().getName().equals(c.getName())) {
				itemBuilder.setMaterial(Material.GLOW_INK_SAC);
			}
			InventoryConsumerItem item = new InventoryConsumerItem(getListenerManager(), new InventorySlot(space.getX(), space.getY()), inv.getListTitle(title, p.getUUID(), 1, inv.getMaxListPages(5*9)), true, true, true, itemBuilder);
			inv.setItem(item);
		}

		// Make lastPage Item
		ItemConsumerBuilder lastPageItem = new ItemConsumerBuilder(getLPlayerManager().getListenerManager(), Material.REDSTONE_BLOCK, false, null, null);
		lastPageItem.setDisplayName(translate(p.getUUID(), "§6Back to last Page"));
		InventoryItem lastPage = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.ONE, InventoryY.SIX), title, true, true, true, lastPageItem);

		// Make nextPage Item
		ItemConsumerBuilder nextPageItem = new ItemConsumerBuilder(getLPlayerManager().getListenerManager(), Material.REDSTONE_BLOCK, false, null, null);
		nextPageItem.setDisplayName(translate(p.getUUID(), "§6Next Page"));
		InventoryItem nextPage = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.NINE, InventoryY.SIX), title, true, true, true, nextPageItem);

		// Add Placeholder
		inv.addPlaceHolders(new ItemBuilder(getListenerManager(), Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§7 "));

		// Open Inventory
		p.getPlayer().closeInventory();
		p.getPlayer().openInventory(inv.getListPageInventory(title, p.getUUID(),5*9,1, lastPage, nextPage));
		return super.onLPlayerCommand(p, args);
	}

	@Override
	public boolean onConsoleCommand(CommandSender s, String[] args) {
		s.sendMessage(getLanguagesManager().getMainLanguage().translate("§cYou have to be a player to do that!"));
		return super.onConsoleCommand(s, args);
	}

	@Override
	public ArrayList<String> getAllPermissions(ArrayList<String> allPermissions) {
		return allPermissions;
	}

	@Override
	public ArrayList<String> getAllTranslations(ArrayList<String> allTranslations) {
		return allTranslations;
	}

}

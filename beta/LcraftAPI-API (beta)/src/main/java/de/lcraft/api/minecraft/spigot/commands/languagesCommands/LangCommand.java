package de.lcraft.api.minecraft.spigot.commands.languagesCommands;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.manager.utils.LPlayerManager;
import de.lcraft.api.minecraft.spigot.manager.utils.entities.LPlayer;
import de.lcraft.api.minecraft.spigot.manager.utils.language.Language;
import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.language.StandardMessages;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.utils.command.Command;
import de.lcraft.api.minecraft.spigot.utils.inventory.InventoryHelper;
import de.lcraft.api.minecraft.spigot.utils.inventory.ListInventory;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.InventoryConsumerItem;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.InventoryItem;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventorySlot;
import de.lcraft.api.minecraft.spigot.utils.items.ItemConsumerBuilder;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class LangCommand extends Command {

	public LangCommand(StandardMessages standardMessages, PermsManager permsManager, LPlayerManager lPlayerManager, LanguagesManager languagesManager, ListenerManager listenerManager) {
		super(standardMessages, "lang", "Opens an inventory, where you can set your language.", permsManager, lPlayerManager, languagesManager, true, listenerManager);
	}

	@Override
	public boolean onLPlayerCommand(LPlayer p, String[] args) {
		if(args.length == 0) {
			// Open Inventory
			p.getPlayer().closeInventory();
			p.getPlayer().openInventory(makeInventory(p));
			p.getPlayer().sendMessage(getTranslatedPREFIX(p.getUUID()) + translate(p.getUUID(), "§aSuccessfully opened the Languages Inventory"));
		} else {
			p.getPlayer().sendMessage(getTranslatedPREFIX(p.getUUID()) + getHelpMessage("lang", p.getUUID(),"/",""));
		}
		return super.onLPlayerCommand(p, args);
	}

	@Override
	public boolean onConsoleCommand(CommandSender s, String[] args) {
		s.sendMessage(getStandardTranslatedPREFIX() + getStandardTranslatedNO_PLAYER());
		return super.onConsoleCommand(s, args);
	}

	public Inventory makeInventory(LPlayer p) {
		// Create List Inventory
		ListInventory inv = new ListInventory(getLPlayerManager().getListenerManager(), getLanguagesManager(),6);
		String title = "§6All Languages";

		// Make lastPage Item
		ItemConsumerBuilder lastPageItem = new ItemConsumerBuilder(getLPlayerManager().getListenerManager(), Material.REDSTONE_BLOCK, false, null, null);
		lastPageItem.setDisplayName(translate(p.getUUID(), "§6Back to last Page"));
		InventoryItem lastPage = new InventoryItem(getListenerManager(), title, true, true, true, lastPageItem);

		// Make nextPage Item
		ItemConsumerBuilder nextPageItem = new ItemConsumerBuilder(getLPlayerManager().getListenerManager(), Material.REDSTONE_BLOCK, false, null, null);
		nextPageItem.setDisplayName(translate(p.getUUID(), "§6Next Page"));
		InventoryItem nextPage = new InventoryItem(getListenerManager(), title, true, true, true, nextPageItem);

		// Add all Languages to Inventory
		for(Language c : getLanguagesManager().getAllLanguagesAndAdded()) {
			String name = "§6" + c.getEnglishName() + " §7- §6" + c.getName();
			InventoryHelper helper = new InventoryHelper();
			InventorySlot space = helper.getNextFreeSlot(inv);

			// Create consumer to choose the language
			Consumer<InventoryClickEvent> clickConsumer = new Consumer<InventoryClickEvent>() {
				@Override
				public void accept(InventoryClickEvent inventoryClickEvent) {
					Player p = (Player) inventoryClickEvent.getWhoClicked();
					LPlayer lplayer = SpigotClass.getAPIPluginMain().getLPlayerManager().getLPlayerByPlayer(p);
					if(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().split(" §7- §6").length > 1) {
						String name = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().split(" §7- §6")[1];
						Language clickedLanguage = SpigotClass.getAPIPluginMain().getLanguagesManager().getAllLanguageByName(name);
						if(Objects.nonNull(clickedLanguage)) {
							lplayer.setLanguage(clickedLanguage);
							lplayer.getPlayer().closeInventory();
						}
					}
				}
			};
			// Build the Item with the Consumer
			ItemConsumerBuilder itemBuilder = new ItemConsumerBuilder(getListenerManager(), Material.PAPER, true, clickConsumer, clickConsumer);
			itemBuilder.setDisplayName(name);
			if(p.getLanguage().getName().equals(c.getName())) {
				itemBuilder.setMaterial(Material.GLOWSTONE_DUST);
			}
			InventoryConsumerItem item = new InventoryConsumerItem(getListenerManager(), inv.getTitle(title, p.getUUID()), true, true, true, itemBuilder);
			inv.setItem(item, space);

			System.out.println(item);
		}

		// Add Placeholder
		InventoryHelper helper = new InventoryHelper();

		return helper.makePlaceholders(inv.getListPageInventory(title, p.getUUID(), 5*9, 1, lastPage, nextPage), getListenerManager(), title);
	}

	@Override
	public ArrayList<String> getAllPermissions(ArrayList<String> allPermissions) {
		return allPermissions;
	}

	@Override
	public ArrayList<String> getAllTranslations(ArrayList<String> allTranslations) {
		allTranslations.add(getPREFIX() + "§aSuccessfully opened the Languages Inventory");
		allTranslations.add(getStandardTranslatedNO_PLAYER());
		allTranslations.add(getHelpMessage("lang", "/", ""));
		return allTranslations;
	}

}
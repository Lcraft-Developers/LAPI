package de.lcraft.api.minecraft.spigot.plugin.commands.language;

import de.lcraft.api.java_utils.language.DefaultLanguages;
import de.lcraft.api.java_utils.language.Language;
import de.lcraft.api.java_utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.module.player.LPlayerManager;
import de.lcraft.api.minecraft.spigot.module.player.LPlayer;
import de.lcraft.api.minecraft.spigot.module.manager.utils.language.StandardMessages;
import de.lcraft.api.minecraft.spigot.module.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.utils.command.Command;
import de.lcraft.api.minecraft.spigot.module.utils.inventory.InventoryHelper;
import de.lcraft.api.minecraft.spigot.module.utils.inventory.ListInventory;
import de.lcraft.api.minecraft.spigot.module.utils.inventory.item.InventoryConsumerItem;
import de.lcraft.api.minecraft.spigot.module.utils.inventory.item.InventoryItem;
import de.lcraft.api.minecraft.spigot.module.utils.inventory.item.slot.InventorySlot;
import de.lcraft.api.minecraft.spigot.module.utils.items.consumer.ItemConsumerBuilder;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class LangCommand extends Command {

	public LangCommand(StandardMessages standardMessages, PermsManager permsManager, LPlayerManager lPlayerManager, LanguagesManager languagesManager) {
		super(standardMessages, null, "lang", "Opens an inventory, where you can set your language.", permsManager, languagesManager, lPlayerManager, true);
		for(Language language : languagesManager.getAllLanguagesAndAdded()) {
			Command langCommand = new Command(standardMessages, this, language.getShortLanguage() + "-" + language.getShortLanguageType(), "This is to choose the language " + language.getEnglishName(), permsManager, languagesManager, lPlayerManager, true) {
				@Override
				public boolean onLPlayerCommand(LPlayer p, String[] args) {
					p.getPlayer().sendMessage(getTranslatedPREFIX(p.getUUID()) + translate(p.getUUID(), "§aSucessfully chose the language §6%Name%").replace("%Name%", language.getEnglishName()));
					return super.onLPlayerCommand(p, args);
				}

				@Override
				public boolean onConsoleCommand(CommandSender s, String[] args) {
					s.sendMessage(getStandardTranslatedPREFIX() + getStandardTranslatedNO_PLAYER());
					return super.onConsoleCommand(s, args);
				}

				@Override
				public ArrayList<String> allUsedTranslations(ArrayList<String> translations) {
					translations.add("§aSuccessfully chose the language §6%Name%");
					return translations;
				}

				@Override
				protected ArrayList<String> allUsedPerms(ArrayList<String> perms) {
					return perms;
				}
			};
		}
	}

	@Override
	public boolean onLPlayerCommand(LPlayer p, String[] args) {
		if(args.length == 0) {
			// Open Inventory
			p.getPlayer().closeInventory();
			p.getPlayer().openInventory(makeInventory(p));
			p.getPlayer().sendMessage(getTranslatedPREFIX(p.getUUID()) + translate(p.getUUID(), "§aSuccessfully opened the Languages Inventory"));
		} else {
			String[] helpArgs = new String[getLanguagesManager().getAllLanguagesAndAdded().size() + 1];
			helpArgs[0] = "";
			for(int i = 1; i <= getLanguagesManager().getAllLanguagesAndAdded().size(); i++) {
				Language currentLang = getLanguagesManager().getAllLanguagesAndAdded().get(i - 1);
				helpArgs[i] = currentLang.getShortLanguage() + "-" + currentLang.getShortLanguageType();
			}
			p.getPlayer().sendMessage(getTranslatedPREFIX(p.getUUID()) + getHelpMessage("lang", p.getUUID(),"/",helpArgs));
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
		ItemConsumerBuilder lastPageItem = new ItemConsumerBuilder(Material.REDSTONE_BLOCK, getLPlayerManager().getListenerManager(), null, null);
		lastPageItem.setDisplayName(translate(p.getUUID(), "§6Back to last Page"));
		InventoryItem lastPage = new InventoryItem(getListenerManager(), title, true, true, true, lastPageItem);

		// Make nextPage Item
		ItemConsumerBuilder nextPageItem = new ItemConsumerBuilder(Material.REDSTONE_BLOCK, getLPlayerManager().getListenerManager(), null, null);
		nextPageItem.setDisplayName(translate(p.getUUID(), "§6Next Page"));
		InventoryItem nextPage = new InventoryItem(getListenerManager(), title, true, true, true, nextPageItem);

		// Add all Languages to Inventory
		for(Language c : getLanguagesManager().getAllLanguagesAndAdded()) {
			String name = "§6" + c.getEnglishName() + " §7- §6" + c.getName();
			InventoryHelper helper = new InventoryHelper();
			InventorySlot space = helper.getNextFreeSlot(inv);

			// Create consumer to choose the language
			Consumer<InventoryClickEvent> clickConsumer = inventoryClickEvent -> {
				Player p1 = (Player) inventoryClickEvent.getWhoClicked();
				LPlayer lplayer = SpigotClass.getAPIPluginMain().getLPlayerManager().getLPlayerByPlayer(p1);
				if (Objects.requireNonNull(Objects.requireNonNull(inventoryClickEvent.getCurrentItem()).getItemMeta()).getDisplayName().split(" §7- §6").length > 1) {
					String name1 = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().split(" §7- §6")[1];
					Language clickedLanguage = getLanguagesManager().getAllLanguageByName(name1);
					if (Objects.nonNull(clickedLanguage)) {
						lplayer.setLanguage(clickedLanguage);
						lplayer.getPlayer().sendMessage(getTranslatedPREFIX(p.getUUID()) + translate(p.getUUID(), "§aSuccessfully chose the language §6%Name%").replace("%Name%", clickedLanguage.getEnglishName()));
						lplayer.getPlayer().openInventory(makeInventory(lplayer));
					}
				}
			};
			// Build the Item with the Consumer
			ItemConsumerBuilder itemBuilder = new ItemConsumerBuilder(Material.PAPER, getListenerManager(), clickConsumer, clickConsumer);
			itemBuilder.setCancelEvent(true);
			itemBuilder.setDisplayName(name);
			if(p.getLanguage().getName().equals(c.getName())) {
				itemBuilder.setMaterial(Material.GLOWSTONE_DUST);
			}
			InventoryConsumerItem item = new InventoryConsumerItem(getListenerManager(), inv.getTitle(title, p.getUUID()), true, true, true, itemBuilder);
			inv.setItem(item, space);
		}

		// Add Placeholder
		InventoryHelper helper = new InventoryHelper();

		return helper.makePlaceholders(inv.getListPageInventory(title, p.getUUID(), 5*9, 1, lastPage, nextPage), getListenerManager(), title);
	}

	@Override
	public ArrayList<String> allUsedTranslations(ArrayList<String> translations) {
		translations.add("§aSuccessfully opened the Languages Inventory");
		translations.add(getStandardTranslatedNO_PLAYER());
		translations.add("§aSuccessfully chose the language §6%Name%");
		String[] helpArgs = new String[getLanguagesManager().getAllLanguagesAndAdded().size() + 1];
		helpArgs[0] = "";
		for(int i = 1; i <= getLanguagesManager().getAllLanguagesAndAdded().size(); i++) {
			Language currentLang = getLanguagesManager().getAllLanguagesAndAdded().get(i - 1);
			helpArgs[i] = currentLang.getShortLanguage() + "-" + currentLang.getShortLanguageType();
		}
		translations.add(getHelpMessage("lang", "/", helpArgs));
		return translations;
	}

	@Override
	protected ArrayList<String> allUsedPerms(ArrayList<String> perms) {
		return perms;
	}

}

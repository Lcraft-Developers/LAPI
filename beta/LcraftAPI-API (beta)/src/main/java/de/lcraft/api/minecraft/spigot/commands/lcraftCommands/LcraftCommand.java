package de.lcraft.api.minecraft.spigot.commands.lcraftCommands;

import de.lcraft.api.minecraft.spigot.manager.utils.LPlayerManager;
import de.lcraft.api.minecraft.spigot.manager.utils.entities.LPlayer;
import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.language.StandardMessages;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.utils.command.Command;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.InventoryItem;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.InventorySlot;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventoryX;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventoryY;
import de.lcraft.api.minecraft.spigot.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class LcraftCommand extends Command {

	public LcraftCommand(StandardMessages standardMessages, PermsManager permsManager, LPlayerManager lPlayerManager, LanguagesManager languagesManager, ListenerManager listenerManager) {
		super(standardMessages, "lcraft", "Opens you an inventory, wich shows information about Lcraft.", permsManager, lPlayerManager, languagesManager, true, listenerManager);
	}

	@Override
	public boolean onLPlayerCommand(LPlayer p, String[] args) {
		if(args.length == 0) {
			// Open Gui
			p.getPlayer().openInventory(makeInventory(p));
			p.getPlayer().sendMessage(getTranslatedPREFIX(p.getUUID()) + translate(p.getUUID(), "§aSuccessfully opened the Lcraft Inventory"));
		} else {
			p.getPlayer().sendMessage(getTranslatedPREFIX(p.getUUID()) + getHelpMessage("lcraft", p.getUUID(),"/",""));
		}
		return super.onLPlayerCommand(p, args);
	}

	@Override
	public boolean onConsoleCommand(CommandSender s, String[] args) {
		s.sendMessage(getStandardTranslatedPREFIX() + translateWithStandardLanguage("§aThis Plugin was made by §bLcraft Developers"));
		return super.onConsoleCommand(s, args);
	}

	public Inventory makeInventory(LPlayer p) {
		// Create inv
		de.lcraft.api.minecraft.spigot.utils.inventory.Inventory inv = new de.lcraft.api.minecraft.spigot.utils.inventory.Inventory(getListenerManager(), getLanguagesManager(), 6);
		String title = "§6Information";

		// Create normal Item
		ItemBuilder byItemBuilder = new ItemBuilder(getListenerManager(), Material.GOLD_BLOCK);
		byItemBuilder.setDisplayName(translate(p.getUUID(), "§bThis Plugin was made by Lcraft Developers"));
		InventoryItem byItem = new InventoryItem(getListenerManager(), title, true, true, true, byItemBuilder);
		inv.setItem(byItem, new InventorySlot(InventoryX.FIVE, InventoryY.THREE));

		// Create and Set all Hearth Items
		ItemBuilder OneShowItemBuilder = new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK);
		InventoryItem OneShowItem = new InventoryItem(getListenerManager(), title, true, true, true, OneShowItemBuilder);
		OneShowItem.getItem().setDisplayName(translate(p.getUUID(),"§bThanks"));
		inv.setItem(OneShowItem, new InventorySlot(InventoryX.SIX, InventoryY.THREE));

		ItemBuilder TwoShowItemBuilder = new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK);
		InventoryItem TwoShowItem = new InventoryItem(getListenerManager(), title, true, true, true, TwoShowItemBuilder);
		TwoShowItem.getItem().setDisplayName(translate(p.getUUID(),"§bThanks"));
		inv.setItem(TwoShowItem, new InventorySlot(InventoryX.SEVEN, InventoryY.THREE));

		ItemBuilder ThreeShowItemBuilder = new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK);
		ThreeShowItemBuilder.setDisplayName(translate(p.getUUID(),"§bThanks"));
		InventoryItem ThreeShowItem = new InventoryItem(getListenerManager(), title, true, true, true, ThreeShowItemBuilder);
		inv.setItem(ThreeShowItem, new InventorySlot(InventoryX.FOUR, InventoryY.THREE));

		ItemBuilder FourShowItemBuilder = new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK);
		InventoryItem FourShowItem = new InventoryItem(getListenerManager(), title, true, true, true, FourShowItemBuilder);
		FourShowItem.getItem().setDisplayName(translate(p.getUUID(),"§bThanks"));
		inv.setItem(FourShowItem, new InventorySlot(InventoryX.THREE, InventoryY.THREE));

		ItemBuilder FiveShowItemBuilder = new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK);
		InventoryItem FiveShowItem = new InventoryItem(getListenerManager(), title, true, true, true, FiveShowItemBuilder);
		FiveShowItem.getItem().setDisplayName(translate(p.getUUID(),"§bThanks"));
		inv.setItem(FiveShowItem, new InventorySlot(InventoryX.FOUR, InventoryY.TWO));

		ItemBuilder SixShowItemBuilder = new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK);
		InventoryItem SixShowItem = new InventoryItem(getListenerManager(), title, true, true, true, SixShowItemBuilder);
		SixShowItem.getItem().setDisplayName(translate(p.getUUID(),"§bThanks"));
		inv.setItem(SixShowItem, new InventorySlot(InventoryX.FIVE, InventoryY.TWO));

		ItemBuilder SevenShowItemBuilder = new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK);
		InventoryItem SevenShowItem = new InventoryItem(getListenerManager(), title, true, true, true, SevenShowItemBuilder);
		SevenShowItem.getItem().setDisplayName(translate(p.getUUID(),"§bThanks"));
		inv.setItem(SevenShowItem, new InventorySlot(InventoryX.SIX, InventoryY.TWO));

		ItemBuilder EightShowItemBuilder = new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK);
		InventoryItem EightShowItem = new InventoryItem(getListenerManager(), title, true, true, true, EightShowItemBuilder);
		EightShowItem.getItem().setDisplayName(translate(p.getUUID(),"§bThanks"));
		inv.setItem(EightShowItem, new InventorySlot(InventoryX.FIVE, InventoryY.FOUR));

		ItemBuilder NineShowItemBuilder = new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK);
		InventoryItem NineShowItem = new InventoryItem(getListenerManager(), title, true, true, true, NineShowItemBuilder);
		NineShowItem.getItem().setDisplayName(translate(p.getUUID(),"§bThanks"));
		inv.setItem(NineShowItem, new InventorySlot(InventoryX.SIX, InventoryY.FOUR));

		ItemBuilder TenShowItemBuilder = new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK);
		InventoryItem TenShowItem = new InventoryItem(getListenerManager(), title, true, true, true, TenShowItemBuilder);
		TenShowItem.getItem().setDisplayName(translate(p.getUUID(),"§bThanks"));
		inv.setItem(TenShowItem, new InventorySlot(InventoryX.FOUR, InventoryY.FOUR));

		ItemBuilder ElevenShowItemBuilder = new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK);
		InventoryItem ElevenShowItem = new InventoryItem(getListenerManager(), title, true, true, true, ElevenShowItemBuilder);
		ElevenShowItem.getItem().setDisplayName(translate(p.getUUID(),"§bThanks"));
		inv.setItem(ElevenShowItem, new InventorySlot(InventoryX.FIVE, InventoryY.FIVE));

		return inv.getInventory(title, p.getUUID());
	}

	@Override
	public ArrayList<String> getAllPermissions(ArrayList<String> allPermissions) {
		return allPermissions;
	}

	@Override
	public ArrayList<String> getAllTranslations(ArrayList<String> allTranslations) {
		allTranslations.add("§bThanks");
		allTranslations.add("§aThis Plugin was made by §bLcraft Developers");
		allTranslations.add("§aSuccessfully opened the Lcraft Inventory");
		allTranslations.add(getHelpMessage("lcraft", "/",""));
		return allTranslations;
	}

}

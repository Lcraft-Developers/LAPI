package de.lcraft.api.minecraft.spigot.commands.lcraftCommands;

import de.lcraft.api.minecraft.spigot.manager.utils.LPlayerManager;
import de.lcraft.api.minecraft.spigot.manager.utils.entities.LPlayer;
import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.utils.command.Command;
import de.lcraft.api.minecraft.spigot.utils.inventory.Inventory;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.InventoryItem;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.InventorySlot;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventoryX;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventoryY;
import de.lcraft.api.minecraft.spigot.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class LcraftCommand extends Command {

	public LcraftCommand(PermsManager permsManager, LPlayerManager lPlayerManager, LanguagesManager languagesManager, ListenerManager listenerManager) {
		super("lcraft", "Opens you an inventory, wich shows information about Lcraft.", permsManager, lPlayerManager, languagesManager, true, listenerManager);
	}

	@Override
	public boolean onLPlayerCommand(LPlayer p, String[] args) {
		// Create inv
		Inventory inv = new Inventory(getListenerManager(), getLanguagesManager(), 6);
		String title = "§6Information";

		// Create normal Item
		InventoryItem byItem = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.FIVE, InventoryY.THREE), title, true, true, true, new ItemBuilder(getListenerManager(), Material.GOLD_BLOCK));
		byItem.getItem().setDisplayName(translate(p.getUUID(), "§bThis Plugin was made by Lcraft Developers"));

		// Create Hearth Item
		InventoryItem OneShowItem = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.SIX, InventoryY.THREE), title, true, true, true, new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK));
		InventoryItem TwoShowItem = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.SEVEN, InventoryY.THREE), title, true, true, true, new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK));
		InventoryItem ThreeShowItem = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.FOUR, InventoryY.THREE), title, true, true, true, new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK));
		InventoryItem FourShowItem = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.THREE, InventoryY.THREE), title, true, true, true, new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK));
		InventoryItem FiveShowItem = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.FOUR, InventoryY.TWO), title, true, true, true, new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK));
		InventoryItem SixShowItem = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.FIVE, InventoryY.TWO), title, true, true, true, new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK));
		InventoryItem SeverShowItem = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.SIX, InventoryY.TWO), title, true, true, true, new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK));
		InventoryItem EightShowItem = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.FIVE, InventoryY.FOUR), title, true, true, true, new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK));
		InventoryItem NineShowItem = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.SIX, InventoryY.FOUR), title, true, true, true, new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK));
		InventoryItem TenShowItem = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.FOUR, InventoryY.FOUR), title, true, true, true, new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK));
		InventoryItem ElevenShowItem = new InventoryItem(getListenerManager(), new InventorySlot(InventoryX.FIVE, InventoryY.FIVE), title, true, true, true, new ItemBuilder(getListenerManager(), Material.DIAMOND_BLOCK));

		// Set all Items into the GUI
		inv.setItem(byItem);
		inv.setItem(OneShowItem);
		inv.setItem(TwoShowItem);
		inv.setItem(ThreeShowItem);
		inv.setItem(FourShowItem);
		inv.setItem(FiveShowItem);
		inv.setItem(SixShowItem);
		inv.setItem(SeverShowItem);
		inv.setItem(EightShowItem);
		inv.setItem(NineShowItem);
		inv.setItem(TenShowItem);
		inv.setItem(ElevenShowItem);

		// Open Gui
		inv.addPlaceHolders(new ItemBuilder(getListenerManager(), Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§7 "));
		p.getPlayer().openInventory(inv.getInventory(title, p.getUUID()));
		return super.onLPlayerCommand(p, args);
	}

	@Override
	public boolean onConsoleCommand(CommandSender s, String[] args) {
		s.sendMessage("§aThis Plugin was made by §bLcraft Developers");
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

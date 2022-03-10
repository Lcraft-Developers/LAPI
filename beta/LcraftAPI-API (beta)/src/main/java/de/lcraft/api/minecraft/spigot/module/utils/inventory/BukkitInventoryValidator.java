package de.lcraft.api.minecraft.spigot.module.utils.inventory;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.module.player.LPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class BukkitInventoryValidator {

	public boolean isPlayer(InventoryClickEvent e) {
		return e.getWhoClicked() instanceof Player;
	}
	public Player getPlayer(InventoryClickEvent e) {
		if(isPlayer(e)) {
			return (Player) e.getWhoClicked();
		}
		return null;
	}

	public boolean isLPlayer(InventoryClickEvent e) {
		if(isPlayer(e) && Objects.nonNull(getPlayer(e))) {
			return SpigotClass.getAPIPluginMain().getLPlayerManager().existsLPlayer(getPlayer(e).getUniqueId());
		}
		return false;
	}
	public LPlayer getLPlayer(InventoryClickEvent e) {
		if(isLPlayer(e)) {
			return SpigotClass.getAPIPluginMain().getLPlayerManager().getLPlayerByUUID(e.getWhoClicked().getUniqueId());
		}
		return null;
	}

	public boolean existsInventory(InventoryClickEvent e) {
		return Objects.nonNull(e.getClickedInventory()) && Objects.nonNull(e.getView());
	}
	public boolean existsInventoryAndHasInventoryTitle(InventoryClickEvent e) {
		return existsInventory(e) && Objects.nonNull(e.getView().getTitle());
	}
	public boolean isInventoryType(InventoryClickEvent e, InventoryType type) {
		if(existsInventory(e) && Objects.nonNull(e.getView().getType()) && Objects.nonNull(e.getClickedInventory().getType())) {
			if(e.getView().getType().equals(e.getClickedInventory().getType())) {
				return e.getView().getType().equals(type) && e.getClickedInventory().getType().equals(type);
			} else {
				return e.getView().getType().equals(type) || e.getClickedInventory().getType().equals(type);
			}
		}
		return false;
	}
	public String getInventoryTitle(InventoryClickEvent e) {
		if(existsInventoryAndHasInventoryTitle(e)) {
			return e.getView().getTitle();
		}
		return null;
	}
	public boolean isInventoryTitle(InventoryClickEvent e, String oldTitle, boolean startsWith, boolean equalsIgnoreCase) {
		if(existsInventoryAndHasInventoryTitle(e)) {
			String title = getInventoryTitle(e);
			if(equalsIgnoreCase) {
				if(title.equalsIgnoreCase(oldTitle) || oldTitle.equalsIgnoreCase(title)) {
					return true;
				}
			} else {
				if(title.equals(oldTitle) || oldTitle.equals(title)) {
					return true;
				}
			}

			if(startsWith) {
				return title.startsWith(oldTitle) || oldTitle.startsWith(title);
			}
		}
		return false;
	}

	public boolean existsItem(ItemStack itemStack) {
		return Objects.nonNull(itemStack);
	}
	public boolean existsItemWithItemMeta(ItemStack itemStack) {
		return existsItem(itemStack) && Objects.nonNull(itemStack.getItemMeta());
	}
	public boolean existsItemWithItemMetaAndDisplayName(ItemStack itemStack) {
		return existsItemWithItemMeta(itemStack) && Objects.nonNull(itemStack.getItemMeta().getDisplayName()) && !itemStack.getItemMeta().getDisplayName().isEmpty();
	}
	public String getItemName(ItemStack item) {
		if(existsItemWithItemMetaAndDisplayName(item)) {
			return item.getItemMeta().getDisplayName();
		}
		return null;
	}
	public boolean isItemName(ItemStack item, String oldItem, boolean startsWith, boolean equalsIgnoreCase) {
		if(existsItemWithItemMetaAndDisplayName(item)) {
			String newItem = getItemName(item);
			if(equalsIgnoreCase) {
				if(newItem.equalsIgnoreCase(oldItem) || oldItem.equalsIgnoreCase(newItem)) {
					return true;
				}
			} else {
				if(newItem.equals(oldItem) || oldItem.equals(newItem)) {
					return true;
				}
			}

			if(startsWith) {
				return newItem.startsWith(oldItem) || oldItem.startsWith(newItem);
			}
		}
		return false;
	}

	public boolean isLeftClick(InventoryClickEvent e) {
		return Objects.nonNull(e.getClick()) && e.getClick().isLeftClick();
	}
	public boolean isRightClick(InventoryClickEvent e) {
		return Objects.nonNull(e.getClick()) && e.getClick().isRightClick();
	}

}

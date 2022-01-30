package de.lcraft.api.utils.minecraft.spigot.module.utils.inventory;

import de.lcraft.api.utils.minecraft.spigot.SpigotClass;
import de.lcraft.api.utils.minecraft.spigot.languages.filesystem.LanguagesManager;
import de.lcraft.api.utils.minecraft.spigot.module.utils.configs.ModuleConfig;
import de.lcraft.api.utils.minecraft.spigot.module.utils.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public abstract class Inventory implements Listener {

	private SpigotClass plugin;
	private ItemBuilder BACK_TO_LAST_INV,
	                    BACK_TO_PAGE,
	                    NEXT_TO_PAGE;
	private Inventory backInv;
	private boolean canBack;
	private ModuleConfig invConfig;
	private String id;

	public Inventory(SpigotClass plugin, Inventory backInv, boolean canBack, String id) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
		this.backInv = backInv;
		this.canBack = canBack;
		invConfig = new ModuleConfig("Lcraft Inventorys//" + id, "config.yml");
		this.id = id;
	}

	public org.bukkit.inventory.Inventory getListPage(ArrayList<ItemStack> items, int page, int invSize, UUID player, String title, Material placeHolder) throws IOException {
		title = getListTitle(title, player, page, getListPages(items, invSize));
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, invSize, title);

		int pagethings = 0;
		int pages = 1;
		ArrayList<ItemStack> item = new ArrayList<>();
		for(int i = 0; i < items.size(); i++) {
			pagethings++;
			if(pagethings > (invSize - 9)) {
				pagethings = 0;
				pages = pages + 1;
			}
			if(pages == page) {
				item.add(items.get(i));
			}
		}

		for(ItemStack c : item) {
			inv.addItem(c);
		}
		inv = placeHolder(inv, new ItemBuilder(placeHolder).setDisplayName("§7").build());
		BACK_TO_LAST_INV = new ItemBuilder(Material.ORANGE_BANNER).setDisplayName(new LanguagesManager().getPlayer(player).translate("§aGo back to last Inv"));
		BACK_TO_PAGE = new ItemBuilder(Material.RED_BANNER).setDisplayName(new LanguagesManager().getPlayer(player).translate("§aGo back to last Page"));
		NEXT_TO_PAGE = new ItemBuilder(Material.LIME_BANNER).setDisplayName(new LanguagesManager().getPlayer(player).translate("§aGo next Page"));

		if(canBack) inv.setItem(invSize - 1, BACK_TO_LAST_INV.build());
		if(page > 1) inv.setItem(invSize - 2, BACK_TO_PAGE.build());
		if(getListPages(items, invSize) > page) inv.setItem(invSize - 8, NEXT_TO_PAGE.build());

		return inv;
	}
	public org.bukkit.inventory.Inventory placeHolder(org.bukkit.inventory.Inventory inv, ItemStack placeHolder) {
		for(int i = 0; i < inv.getSize(); i++) {
			if(inv.getItem(0) == null || (inv.getItem(0) != null && (inv.getItem(0).getType() == Material.AIR || inv.getItem(0) instanceof InventoryHolder))) {
				inv.setItem(i, placeHolder);
			}
		}
		return inv;
	}

	public int getListPages(ArrayList<ItemStack> items, int invSize) {
		int pagethings = 0;
		int pages = 1;
		ArrayList<ItemStack> item = new ArrayList<>();
		for(int i = 0; i < items.size(); i++) {
			pagethings++;
			if(pagethings > (invSize - 9)) {
				pagethings = 0;
				pages = pages + 1;
			}
		}
		return pages;
	}
	public String getListTitle(String title, UUID player, int page, int pages) throws IOException {
		title = new LanguagesManager().getPlayer(player).translate(title + " §6%cpage%§7/§6%maxpage%").replace("%cpage%", page + "").replace("%maxpage%", pages + "");
		return title;
	}


	public SpigotClass getPlugin() {
		return plugin;
	}
	public void setBACK_TO_LAST_INV(ItemBuilder BACK_TO_LAST_INV) {
		this.BACK_TO_LAST_INV = BACK_TO_LAST_INV;
	}
	public void setBACK_TO_PAGE(ItemBuilder BACK_TO_PAGE) {
		this.BACK_TO_PAGE = BACK_TO_PAGE;
	}
	public void setNEXT_TO_PAGE(ItemBuilder NEXT_TO_PAGE) {
		this.NEXT_TO_PAGE = NEXT_TO_PAGE;
	}
	public ModuleConfig getInvConfig() {
		return invConfig;
	}
	public Inventory getBackInv() {
		return backInv;
	}
	public abstract ArrayList<String> allPermissions(ArrayList<String> allPermissions);
	public abstract ArrayList<String> allLanguages(ArrayList<String> allLanguages);
	public String getId() {
		return id;
	}

}

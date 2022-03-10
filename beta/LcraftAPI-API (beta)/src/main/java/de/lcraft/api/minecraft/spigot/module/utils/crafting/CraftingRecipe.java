package de.lcraft.api.minecraft.spigot.module.utils.crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.HashMap;

public class CraftingRecipe {

    private final String name;

    private boolean activated;
    private final NamespacedKey namespacedKey;

    private final ItemStack result;
    private final HashMap<Character, Material> recipeItems;
    private final ArrayList<CraftingRecipe> craftingRecipes = new ArrayList<>();

    public CraftingRecipe(JavaPlugin plugin, String name, ItemStack result) {
        this.name = name;
        this.result = result;
        recipeItems = new HashMap<>();
        activated = false;
        namespacedKey = new NamespacedKey(plugin, name);
    }

    public final void setRecipeSlot(CraftingPlace slot, Material item) {
        if(hasSlotBeenSet(slot)) {
            removeRecipeSlot(slot);
        }

        recipeItems.put(slot.getKey(), item);
    }
    public final void removeRecipeSlot(CraftingPlace slot) {
        if(hasSlotBeenSet(slot)) {
            recipeItems.remove(slot.getKey());
        }
    }
    public final boolean hasSlotBeenSet(CraftingPlace slot) {
		return recipeItems.containsKey(slot.getKey());
	}

    public ShapedRecipe register() {
        if(!activated) {
            ShapedRecipe recipe = new ShapedRecipe(namespacedKey, result);
            recipe.shape("ABC", "DEF", "GHI");

            for(CraftingPlace places : CraftingPlace.values()) {
                recipe.setIngredient(places.getKey(), recipeItems.get(places.getKey()));
            }

            Bukkit.addRecipe(recipe);
            activated = true;
            craftingRecipes.add(this);
            return recipe;
        } else {
            unregister();
            return register();
        }
    }
    public void unregister() {
        if(activated) {
            activated = false;
            Bukkit.removeRecipe(namespacedKey);
            craftingRecipes.remove(this);
        }
    }
    public final void unregisterAll() {
        for(CraftingRecipe c : craftingRecipes) {
            c.unregister();
        }
    }

}

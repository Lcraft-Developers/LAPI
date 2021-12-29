package de.lcraft.api.minecraft.spigot.utils.crafting;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.HashMap;

public class CraftingRecipe {

    private String name;
    private ItemStack result;
    private HashMap<Character, Material> recipeItems;
    private boolean activated;
    private NamespacedKey namespacedKey;
    private static ArrayList<CraftingRecipe> craftingRecipes = new ArrayList<>();

     /**
     * The place in the crafting table
     * FIRST   | SECOND | THIRD
     * FOURTH  | FIFTH  | SIXTH | -->> RESULT
     * SEVENTH | EIGHT  | NINTH
     */

    public CraftingRecipe(String name, ItemStack result) {
        this.name = name;
        this.result = result;
        recipeItems = new HashMap<>();
        activated = false;
        namespacedKey = new NamespacedKey(SpigotClass.getAPIPluginMain(), name);
    }

    public void setRecipeSlot(CraftingPlace slot, Material item) {
        if(hasSlotBeenSet(slot)) {
            removeRecipeSlot(slot);
        }

        recipeItems.put(slot.getKey(), item);
    }
    public void removeRecipeSlot(CraftingPlace slot) {
        if(hasSlotBeenSet(slot)) {
            recipeItems.remove(slot.getKey());
        }
    }
    public boolean hasSlotBeenSet(CraftingPlace slot) {
        if(recipeItems.containsKey(slot.getKey())) {
            return true;
        }
        return false;
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
    public static void unregisterAll() {
        for(CraftingRecipe c : craftingRecipes) {
            c.unregister();
        }
    }

}

package de.lcraft.api.utils.minecraft.spigot.module.utils.crafting;

public enum CraftingPlace {

    FIRST('A'), SECOND('B'), THIRD('C'), FOURTH('D'), FIFTH('E'), SIXTH('F'), SEVENTH('G'), EIGHT('H'), NINTH('I');


    private final char key;
    CraftingPlace(char key) {
        this.key = key;
    }
    public char getKey() {
        return key;
    }

}

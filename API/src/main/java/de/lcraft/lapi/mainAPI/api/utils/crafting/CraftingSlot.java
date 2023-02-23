package de.lcraft.lapi.mainAPI.api.utils.crafting;

public enum CraftingSlot {

    TOP_LEFT('A'), TOP_MIDDLE('B'), TOP_RIGHT('C'),
    MIDDLE_LEFT('D'), MIDDLE_MIDDLE('E'), MIDDLE_RIGHT('F'),
    BOTTOM_LEFT('G'), BOTTOM_MIDDLE('H'), BOTTOM_RIGHT('I');

    private Character character;

    CraftingSlot(Character character) {
        init(character);
    }
    private void init(Character character) {
        if(character != null) setCharacter(character);
        else setCharacter('0');
    }

    public Character getCharacter() {
        return character;
    }
    private void setCharacter(Character character) {
        this.character = character;
    }

}

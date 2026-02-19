package com.narxoz.rpg.loot;

import java.util.ArrayList;
import java.util.List;

public class FireLootTable implements LootTable {

    private final List<String> items;
    private final int goldDrop;
    private final int experienceDrop;

    public FireLootTable() {
        this.items = new ArrayList<>();
        items.add("Fire Gem");
        items.add("Flame Rune");
        items.add("Dragon Scale");

        this.goldDrop = 150;
        this.experienceDrop = 300;
    }

    @Override
    public List<String> getItems() {
        // Returning a copy to preserve immutability
        return new ArrayList<>(items);
    }

    @Override
    public int getGoldDrop() {
        return goldDrop;
    }

    @Override
    public int getExperienceDrop() {
        return experienceDrop;
    }

    @Override
    public LootTable clone() {
        // Deep copy: new object with its own list
        FireLootTable copy = new FireLootTable();
        return copy;
    }
}
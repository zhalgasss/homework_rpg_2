package com.narxoz.rpg.loot;

import java.util.ArrayList;
import java.util.List;

public class ShadowLootTable implements LootTable {

    private final List<String> items;
    private final int goldDrop;
    private final int experienceDrop;

    public ShadowLootTable() {
        this.items = new ArrayList<>();
        items.add("Shadow Gem");
        items.add("Dark Essence");
        items.add("Shadow Rune");

        this.goldDrop = 180;
        this.experienceDrop = 350;
    }

    @Override
    public List<String> getItems() {
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
        return new ShadowLootTable();
    }
}
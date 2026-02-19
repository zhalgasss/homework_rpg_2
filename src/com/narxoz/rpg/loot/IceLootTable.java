package com.narxoz.rpg.loot;

import java.util.ArrayList;
import java.util.List;

public class IceLootTable implements LootTable {

    private final List<String> items;
    private final int goldDrop;
    private final int experienceDrop;

    public IceLootTable() {
        this.items = new ArrayList<>();
        items.add("Ice Gem");
        items.add("Frost Rune");
        items.add("Frozen Scale");

        this.goldDrop = 120;
        this.experienceDrop = 280;
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
        return new IceLootTable();
    }
}
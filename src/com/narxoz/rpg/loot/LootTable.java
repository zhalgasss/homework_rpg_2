package com.narxoz.rpg.loot;

import java.util.List;

public interface LootTable extends Cloneable {
    List<String> getItems();
    int getGoldDrop();
    int getExperienceDrop();
    LootTable clone();
}
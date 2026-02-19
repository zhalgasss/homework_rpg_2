package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.ShadowStrike;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.ShadowLootTable;

import java.util.ArrayList;
import java.util.List;

public class ShadowComponentFactory implements EnemyComponentFactory {

    @Override
    public List<Ability> createAbilities() {
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new ShadowStrike());
        return abilities;
    }

    @Override
    public LootTable createLootTable() {
        return new ShadowLootTable();
    }

    @Override
    public String createAIBehavior() {
        return "TACTICAL";
    }
}
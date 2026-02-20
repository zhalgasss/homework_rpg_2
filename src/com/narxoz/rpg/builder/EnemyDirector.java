package com.narxoz.rpg.builder;

import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.EnemyComponentFactory;

public class EnemyDirector {

    private final EnemyBuilder builder;

    public EnemyDirector(EnemyBuilder builder) {
        this.builder = builder;
    }

    // -------------------------
    // MINION
    // -------------------------
    public Enemy createMinion(String name, EnemyComponentFactory factory) {
        return builder
                .setName(name)
                .setHealth(100)
                .setDamage(10)
                .setDefense(5)
                .setSpeed(10)
                .setElement("NONE")
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setAIBehavior(factory.createAIBehavior())
                .build(); // FACTORY METHOD
    }

    // -------------------------
    // ELITE
    // -------------------------
    public Enemy createElite(String name, EnemyComponentFactory factory) {
        return builder
                .setName(name)
                .setHealth(300)
                .setDamage(25)
                .setDefense(15)
                .setSpeed(12)
                .setElement("ELITE")
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setAIBehavior(factory.createAIBehavior())
                .build(); // FACTORY METHOD
    }

    // -------------------------
    // MINI BOSS
    // -------------------------
    public Enemy createMiniBoss(String name, EnemyComponentFactory factory) {
        return builder
                .setName(name)
                .setHealth(800)
                .setDamage(50)
                .setDefense(30)
                .setSpeed(8)
                .setElement("BOSS")
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setAIBehavior(factory.createAIBehavior())
                .build(); // FACTORY METHOD
    }

    // -------------------------
    // RAID BOSS
    // -------------------------
    public Enemy createRaidBoss(String name, EnemyComponentFactory factory) {
        return builder
                .setName(name)
                .setHealth(3000)
                .setDamage(120)
                .setDefense(60)
                .setSpeed(5)
                .setElement("RAID")
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setAIBehavior(factory.createAIBehavior())
                .build(); // FACTORY METHOD
    }
}
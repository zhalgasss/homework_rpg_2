package com.narxoz.rpg;

import com.narxoz.rpg.enemy.BasicEnemyBuilder;
import com.narxoz.rpg.builder.EnemyBuilder;
import com.narxoz.rpg.builder.EnemyDirector;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.FireComponentFactory;
import com.narxoz.rpg.factory.IceComponentFactory;
import com.narxoz.rpg.factory.ShadowComponentFactory;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.prototype.EnemyRegistry;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== RPG Enemy System - Creational Patterns Capstone ===\n");

        // ============================================================
        // PART 1: ABSTRACT FACTORY PATTERN
        // ============================================================
        System.out.println("============================================");
        System.out.println("PART 1: ABSTRACT FACTORY - Themed Components");
        System.out.println("============================================\n");

        EnemyComponentFactory fireFactory = new FireComponentFactory();
        EnemyComponentFactory iceFactory = new IceComponentFactory();
        EnemyComponentFactory shadowFactory = new ShadowComponentFactory();

        System.out.println("Fire Factory creates:");
        System.out.println("Abilities: " + fireFactory.createAbilities());
        System.out.println("Loot: " + fireFactory.createLootTable().getItems());
        System.out.println("AI: " + fireFactory.createAIBehavior());

        System.out.println("\nIce Factory creates:");
        System.out.println("Abilities: " + iceFactory.createAbilities());
        System.out.println("Loot: " + iceFactory.createLootTable().getItems());
        System.out.println("AI: " + iceFactory.createAIBehavior());

        System.out.println("\nShadow Factory creates:");
        System.out.println("Abilities: " + shadowFactory.createAbilities());
        System.out.println("Loot: " + shadowFactory.createLootTable().getItems());
        System.out.println("AI: " + shadowFactory.createAIBehavior());

        // ============================================================
        // PART 2: BUILDER + FACTORY METHOD
        // ============================================================
        System.out.println("\n============================================");
        System.out.println("PART 2: BUILDER - Complex Enemy Construction");
        System.out.println("============================================\n");

        EnemyBuilder builder = new BasicEnemyBuilder();
        EnemyDirector director = new EnemyDirector(builder);

        Enemy fireGoblin = director.createMinion(
                "Fire Goblin",
                fireFactory
        );

        fireGoblin.display();

        // ============================================================
        // PART 3: PROTOTYPE PATTERN
        // ============================================================
        System.out.println("\n============================================");
        System.out.println("PART 3: PROTOTYPE - Enemy Cloning & Variants");
        System.out.println("============================================\n");

        EnemyRegistry registry = new EnemyRegistry();

        // Register base template
        registry.registerTemplate("goblin", fireGoblin);

        // Clone difficulty variants
        Enemy eliteGoblin = registry.createFromTemplate("goblin");
        eliteGoblin.multiplyStats(2.0);

        Enemy championGoblin = registry.createFromTemplate("goblin");
        championGoblin.multiplyStats(5.0);

        System.out.println("Elite Goblin:");
        eliteGoblin.display();

        System.out.println("\nChampion Goblin:");
        championGoblin.display();

        // ============================================================
        // PART 4: ALL PATTERNS WORKING TOGETHER
        // ============================================================
        System.out.println("\n============================================");
        System.out.println("PART 4: ALL PATTERNS WORKING TOGETHER");
        System.out.println("============================================\n");

        Enemy shadowGoblin = director.createMinion(
                "Shadow Goblin",
                shadowFactory
        );

        shadowGoblin.display();

        Enemy eliteShadowGoblin = shadowGoblin.clone();
        eliteShadowGoblin.multiplyStats(3.0);

        System.out.println("\nElite Shadow Goblin (Cloned):");
        eliteShadowGoblin.display();

    }
}
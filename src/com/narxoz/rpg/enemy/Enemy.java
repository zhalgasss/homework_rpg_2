package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy {

    protected String name;
    protected int health;
    protected int damage;
    protected int defense;
    protected int speed;
    protected String element;
    protected List<Ability> abilities;
    protected LootTable lootTable;
    protected String aiBehavior;

    protected Enemy() {
        this.abilities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public List<Ability> getAbilities() {
        return new ArrayList<>(abilities);
    }

    public LootTable getLootTable() {
        return lootTable;
    }

    public String getAiBehavior() {
        return aiBehavior;
    }

    public void display() {
        System.out.println("Enemy: " + name);
        System.out.println("Health: " + health);
        System.out.println("Damage: " + damage);
        System.out.println("Defense: " + defense);
        System.out.println("Speed: " + speed);
        System.out.println("Element: " + element);
        System.out.println("AI: " + aiBehavior);
        System.out.println("Abilities:");
        for (Ability a : abilities) {
            System.out.println(" - " + a.getName());
        }
        System.out.println("Loot: " + lootTable.getItems());
    }

    // PROTOTYPE
    public abstract Enemy clone();

    protected void addAbilityInternal(Ability ability) {
        this.abilities.add(ability);
    }

    protected void setLootTableInternal(LootTable lootTable) {
        this.lootTable = lootTable;
    }

    protected void setCoreStatsInternal(String name, int health, int damage,
                              int defense, int speed,
                              String element, String aiBehavior) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.element = element;
        this.aiBehavior = aiBehavior;
    }

    // ---------- Prototype helpers ----------

    public void multiplyStats(double multiplier) {
        this.health = (int) (this.health * multiplier);
        this.damage = (int) (this.damage * multiplier);
        this.defense = (int) (this.defense * multiplier);
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void addAbility(Ability ability) {
        this.abilities.add(ability);
    }
}
package com.narxoz.rpg.enemy;

import java.util.HashMap;
import java.util.Map;

public class DragonBoss extends Enemy {

    private final Map<Integer, Integer> phases = new HashMap<>();

    public void addPhase(int phase, int healthThreshold) {
        phases.put(phase, healthThreshold);
    }

    @Override
    public Enemy clone() {
        DragonBoss copy = new DragonBoss();

        copy.name = this.name;
        copy.health = this.health;
        copy.damage = this.damage;
        copy.defense = this.defense;
        copy.speed = this.speed;
        copy.element = this.element;
        copy.aiBehavior = this.aiBehavior;
        copy.lootTable = this.lootTable.clone();

        this.abilities.forEach(a -> copy.abilities.add(a.clone()));
        this.phases.forEach(copy.phases::put);

        return copy;
    }
}
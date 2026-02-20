package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;

import java.util.ArrayList;

public class BasicEnemy extends Enemy {

    public BasicEnemy() {
        this.abilities = new ArrayList<>();
    }

    @Override
    public Enemy clone() {
        BasicEnemy copy = new BasicEnemy();
        copy.name = this.name;
        copy.health = this.health;
        copy.damage = this.damage;
        copy.defense = this.defense;
        copy.speed = this.speed;
        copy.element = this.element;
        copy.aiBehavior = this.aiBehavior;
        copy.lootTable = this.lootTable.clone();

        for (Ability a : this.abilities) {
            copy.abilities.add(a.clone());
        }

        return copy;
    }
}
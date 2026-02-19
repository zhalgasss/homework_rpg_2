package com.narxoz.rpg.combat;

public interface Ability extends Cloneable {
    String getName();
    int getDamage();
    String getDescription();
    Ability clone();
}
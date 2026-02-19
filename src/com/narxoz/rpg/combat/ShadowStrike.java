package com.narxoz.rpg.combat;

public class ShadowStrike implements Ability {

    private final int damage;
    private final int blindDuration;

    public ShadowStrike() {
        this.damage = 55;
        this.blindDuration = 2; // in turns
    }

    @Override
    public String getName() {
        return "Shadow Strike";
    }

    @Override
    public int getDamage() {
        return damage;
    }

    public int getBlindDuration() {
        return blindDuration;
    }

    @Override
    public String getDescription() {
        return "A powerful strike from the shadows dealing heavy damage and "
                + "blinding the target for " + blindDuration + " turns.";
    }

    @Override
    public Ability clone() {
        return new ShadowStrike();
    }
}
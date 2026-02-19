package com.narxoz.rpg.combat;

public class FrostBreath implements Ability {

    private final int damage;
    private final int slowPercentage;

    public FrostBreath() {
        this.damage = 40;
        this.slowPercentage = 30;
    }

    @Override
    public String getName() {
        return "Frost Breath";
    }

    @Override
    public int getDamage() {
        return damage;
    }

    public int getSlowPercentage() {
        return slowPercentage;
    }

    @Override
    public String getDescription() {
        return "Breathes icy wind, dealing damage and slowing enemies by "
                + slowPercentage + "%.";
    }

    @Override
    public Ability clone() {
        // Deep copy not needed here because fields are primitives
        return new FrostBreath();
    }
}
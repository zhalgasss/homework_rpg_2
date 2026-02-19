package com.narxoz.rpg.combat;

public class FlameBreath implements Ability {

    @Override
    public String getName() {
        return "Flame Breath";
    }

    @Override
    public int getDamage() {
        return 50;
    }

    @Override
    public String getDescription() {
        return "Breathes fire dealing AoE damage.";
    }

    @Override
    public Ability clone() {
        return new FlameBreath();
    }
}
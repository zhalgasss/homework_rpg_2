# Homework 2: RPG Enemy System — Creational Patterns Capstone

## Overview

You are a game developer building the **enemy system** for an MMORPG. Your RPG already has heroes (from Homework 1). Now it needs enemies to fight!

The game director has a challenge: enemies in this game are **complex**. A Dragon Boss has health, damage, defense, speed, elemental attributes, multiple abilities, boss phases, loot tables, and AI behavior. Creating these enemies with plain constructors is a nightmare. And the game needs **hundreds of enemy variants** — Fire Dragons, Ice Dragons, Shadow Dragons, Elite Goblins, Champion Goblins, Goblin Kings...

Your mission: **Use design patterns to tame this complexity.**

---

## What You'll Build

An enemy creation system that uses **all four creational design patterns** working together:

### The Four Patterns

| Pattern | Role | What It Does |
|---------|------|-------------|
| **Builder** | Constructs complex enemies | Step-by-step assembly with fluent interface |
| **Prototype** | Creates enemy variants | Clone templates, modify for variants |
| **Abstract Factory** | Creates themed components | Matching abilities + loot + AI per theme |
| **Factory Method** | Produces enemy objects | Embedded in Builder.build() and Director |

### How They Work Together

```
Abstract Factory ──> Builder ──> Prototype
 (themed parts)    (assembly)    (cloning)
                       |
               Factory Method
              (build() creates enemy)
```

1. **Abstract Factory** creates themed components (Fire abilities + Fire loot + Aggressive AI)
2. **Builder** assembles a complex enemy from those components, step by step
3. **Factory Method** is the `build()` call that produces the final Enemy
4. **Prototype** clones the built enemy to create variants efficiently

---

## Why These Patterns?

### Without Builder:
```java
// 15 parameters — which is which?!
new DragonBoss("Fire Dragon", 50000, 500, 200, 50, "FIRE",
    abilities, 30000, 15000, 5000, loot, "AGGRESSIVE",
    true, true, 20);
```

### Without Prototype:
```java
// Need 100 goblin variants? Build each from scratch...
Enemy goblin1 = new GoblinBuilder().setName("Goblin").setHealth(100)...build();
Enemy goblin2 = new GoblinBuilder().setName("Goblin").setHealth(100)...build();
// Repeat 98 more times? That's insane!
```

### Without Abstract Factory:
```java
// Nothing prevents mixing themes!
Enemy dragon = new BossEnemyBuilder()
    .setAbilities(fireAbilities)
    .setLootTable(iceLoot)      // BUG: Fire dragon drops ice loot?!
    .build();
```

**Patterns solve real problems. You'll feel the pain, then appreciate the solution.**

---

## Connection to Homework 1

This project extends the RPG universe from Homework 1:
- **HW1**: You created heroes (Warrior, Mage, Archer) using Factory patterns
- **HW2**: You create enemies those heroes fight, using Builder & Prototype patterns
- **HW1 patterns return**: Abstract Factory and Factory Method appear again in a new context

By the end of HW2, you will understand **all four creational patterns** and how they complement each other.

---

## Getting Started

1. **Read** `ASSIGNMENT.md` for detailed requirements
2. **Read** `QUICKSTART.md` for setup instructions
3. **Study** the provided code:
   - `DragonBoss.java` — feel the telescoping constructor pain
   - `Goblin.java` — see a simple enemy structure
   - `EnemyComponentFactory.java` — Abstract Factory interface
4. **Read** hint files:
   - `builder/BUILDER_HINTS.md` — Builder & Prototype guidance
   - `factory/FACTORY_HINTS.md` — Abstract Factory & Factory Method guidance
5. **Track** progress with `STUDENT_CHECKLIST.md`
6. **Check** `FAQ.md` when stuck

---

## Requirements at a Glance

- **25-30 Java classes** (your implementation)
- **2 UML diagrams** (Builder+FM and Prototype+AF)
- **Working demo** in Main.java showing all 4 patterns
- **5-6 days** estimated time (24-32 hours)

See `ASSIGNMENT.md` for the complete grading rubric (100 points).

---

## The Big Picture

After completing both homework assignments, you will be able to answer:

> *"I need to create objects. Which creational pattern should I use?"*

| Situation | Pattern |
|-----------|---------|
| Different object types, same interface | **Factory Method** |
| Families of related objects that must match | **Abstract Factory** |
| Complex object with many optional parts | **Builder** |
| Need efficient copies of existing objects | **Prototype** |

**That understanding will serve you throughout your entire career.** Good luck!

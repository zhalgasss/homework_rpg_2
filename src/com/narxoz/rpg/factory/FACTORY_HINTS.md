# Abstract Factory & Factory Method Hints

This package is where your **Abstract Factory** (themed component factories)
and supporting code should live.

---

## Abstract Factory Pattern (Themed Enemy Components)

### The Problem

Enemies have themed components that MUST match:

| Theme   | Abilities                          | Loot                              | AI         |
|---------|------------------------------------|------------------------------------|------------|
| Fire    | Flame Breath, Fire Shield, Meteor  | Fire Gem, Dragon Scale, Flame Rune | AGGRESSIVE |
| Ice     | Frost Breath, Ice Shield, Blizzard | Ice Gem, Frost Scale, Ice Rune     | DEFENSIVE  |
| Shadow  | Shadow Strike, Vanish, Dark Nova   | Shadow Gem, Dark Essence, Shadow Rune | TACTICAL |

**Without Abstract Factory:**
```java
// DANGER: Nothing prevents this mismatch!
List<Ability> abilities = createFireAbilities();
LootTable loot = createIceLootTable();  // WRONG THEME!
String ai = "TACTICAL";                  // WRONG THEME!
// Fire abilities + Ice loot + Tactical AI = broken theme!
```

**With Abstract Factory:**
```java
// ONE factory guarantees ALL components match:
EnemyComponentFactory factory = new FireComponentFactory();
List<Ability> abilities = factory.createAbilities();  // Fire abilities
LootTable loot = factory.createLootTable();           // Fire loot
String ai = factory.createAIBehavior();               // Fire AI
// Guaranteed consistency!
```

### Connection to Homework 1

This is the **same pattern** you used (or should have used) in HW1:

| HW1                                | HW2                                    |
|-------------------------------------|----------------------------------------|
| `EquipmentFactory` (interface)      | `EnemyComponentFactory` (interface)    |
| `MedievalEquipmentFactory`          | `FireComponentFactory`                 |
| Creates: Weapon + Armor             | Creates: Abilities + Loot + AI         |
| Guarantees: themed equipment pair   | Guarantees: themed component family    |

**Same pattern, different domain. Practice makes permanent!**

### Your Tasks

1. **Study `EnemyComponentFactory.java`** — the interface is provided
2. **Implement concrete factories:**
   - `FireComponentFactory` — creates fire abilities, fire loot, aggressive AI
   - `IceComponentFactory` — creates ice abilities, ice loot, defensive AI
   - `ShadowComponentFactory` — creates shadow abilities, shadow loot, tactical AI
3. **For each factory, create the concrete products:**
   - 2-3 themed abilities (implement `Ability` interface)
   - 1 themed loot table (implement `LootTable` interface)
   - 1 AI behavior type
4. **Use the factory in your Builder:**
   ```java
   EnemyComponentFactory factory = new FireComponentFactory();
   Enemy dragon = new BossEnemyBuilder()
       .setAbilities(factory.createAbilities())
       .setLootTable(factory.createLootTable())
       .setAI(factory.createAIBehavior())
       .build();
   ```

### Questions
- What happens if you try to mix Fire abilities with Ice loot? How does Abstract Factory prevent this?
- How would you add a new theme (e.g., NatureComponentFactory)? How many classes would you need?
- Is this easier or harder than the if-else approach? Why?

---

## Factory Method Pattern (Embedded in Builder & Director)

### Where Is Factory Method?

You do NOT need to build a separate Factory Method structure.
It's already **embedded** in two places:

### Place 1: Builder's `build()` Method

```java
interface EnemyBuilder {
    // ... setter methods ...

    Enemy build();  // <-- THIS is the Factory Method!
}

class BasicEnemyBuilder implements EnemyBuilder {
    public Enemy build() {
        return new BasicEnemy(name, health, damage, ...);
        // ^ Concrete factory method — creates BasicEnemy
    }
}

class BossEnemyBuilder implements EnemyBuilder {
    public Enemy build() {
        return new BossEnemy(name, health, damage, phases, ...);
        // ^ Different concrete factory method — creates BossEnemy
    }
}
```

The `build()` method:
- Returns a `Product` (Enemy) — just like Factory Method
- Different builders override it to create different concrete enemies
- Client code calls `builder.build()` without knowing which type is created

**This IS Factory Method pattern!**

### Place 2: Director's Delegation

```java
class EnemyDirector {
    private EnemyBuilder builder;  // Which concrete builder? Director doesn't know!

    public Enemy createBoss(EnemyComponentFactory factory) {
        return builder                           // Delegates to builder
            .setName("Boss")
            .setHealth(50000)
            .setAbilities(factory.createAbilities())
            .build();                            // Factory Method called here!
    }
}
```

The Director:
- Works with `EnemyBuilder` interface (not concrete builders)
- Calls `build()` polymorphically
- Different builders produce different enemy types
- **This is textbook Factory Method delegation!**

### Connection to Homework 1

In HW1, Factory Method was used for Character creation:
- `CharacterFactory.createCharacter()` → returned Warrior, Mage, Archer

In HW2, Factory Method appears inside Builder:
- `EnemyBuilder.build()` → returns BasicEnemy, BossEnemy, EliteEnemy
- Same concept, different structure!

### Your Task

You don't need to create a separate Factory Method class hierarchy.
Instead, in your code comments or demo, **identify** where Factory Method
appears and explain WHY it's Factory Method:

```java
// In Main.java or in comments:
// Factory Method is here: builder.build()
// - Returns Enemy (Product interface)
// - Different builders return different concrete types
// - Client code doesn't know which concrete type is returned
// This is Factory Method embedded in Builder pattern!
```

---

## How Abstract Factory + Builder + Factory Method Work Together

```
                    Abstract Factory
                    (themed components)
                         |
            +------------+------------+
            |            |            |
    FireFactory    IceFactory    ShadowFactory
    (fire abilities,  (ice abilities,  (shadow abilities,
     fire loot,        ice loot,        shadow loot,
     aggressive AI)    defensive AI)    tactical AI)
            |
            v
         Builder
         (assembles enemy from components)
            |
            v
     build() ← Factory Method
         (creates final Enemy object)
            |
            v
         Enemy
```

**Each pattern solves a different problem:**
- **Abstract Factory**: "Which themed components should I use?"
- **Builder**: "How do I assemble this complex enemy step-by-step?"
- **Factory Method**: "Which concrete Enemy type should build() return?"

---

## Testing Your Design

Ask yourself:
1. Can my factories guarantee themed consistency? ✓
2. Does my Builder use factory components (not hardcoded abilities)? ✓
3. Can I identify Factory Method in `build()` and Director? ✓
4. Can I add a new theme by only creating new classes (not modifying old ones)? ✓
5. Does my code use interfaces, not concrete classes? ✓

---

## Resources

- Refactoring Guru: Abstract Factory
- Refactoring Guru: Factory Method
- "Head First Design Patterns" — Factory chapters
- Review your HW1 implementation for reference!

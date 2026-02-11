# Builder & Prototype Pattern Implementation Hints

This package is where your **Builder** implementations should live.
The `prototype/` package is where your **Prototype** (registry, cloning) logic goes.

---

## Builder Pattern (Enemy Construction)

### The Problem: Telescoping Constructor

Open `DragonBoss.java`. Count the constructor parameters. **Fifteen.**

```java
// Can you tell what each parameter means?
new DragonBoss("Fire Dragon", 50000, 500, 200, 50, "FIRE",
    abilities, 30000, 15000, 5000, loot, "AGGRESSIVE",
    true, true, 20);
// What does 'true, true, 20' mean? What's 30000 vs 15000?
```

Now imagine:
- Adding a 16th parameter (e.g., `summonMinions`)
- A new developer reading this code for the first time
- Accidentally swapping two `int` parameters — the compiler won't catch it!

**This is the Telescoping Constructor anti-pattern. Builder solves it.**

### The Solution: Builder Pattern

```java
// Clean, readable, self-documenting:
Enemy dragon = new BossEnemyBuilder()
    .setName("Fire Dragon")
    .setHealth(50000)
    .setDamage(500)
    .setDefense(200)
    .setSpeed(50)
    .setElement("FIRE")
    .addAbility(new FlameBreath())
    .addAbility(new FireShield())
    .addPhase(1, 50000)
    .addPhase(2, 30000)
    .setLootTable(fireLoot)
    .setAI("AGGRESSIVE")
    .build();
// Every parameter is labeled! Can't mix them up!
```

### Questions to Answer

1. **What fields are mandatory vs optional?**
   - Mandatory: name, health (every enemy needs these)
   - Optional: abilities, phases, loot, element (not all enemies have all of these)
   - How do you enforce mandatory fields?

2. **How does the fluent interface work?**
   ```java
   // Each setter returns 'this' for chaining:
   public EnemyBuilder setName(String name) {
       this.name = name;
       return this;  // <-- This enables chaining!
   }
   ```

3. **Should the built Enemy be immutable?**
   - YES! Once `build()` is called, the enemy should not change
   - No setters on the Enemy class (or at least, not public ones)
   - Builder accumulates fields, then creates the final immutable object

4. **What happens if required fields are missing?**
   ```java
   public Enemy build() {
       // Validate before creating!
       if (name == null || health <= 0) {
           throw new IllegalStateException("Enemy must have name and health!");
       }
       return new ConcreteEnemy(name, health, damage, ...);
   }
   ```

### Possible Builder Approaches

#### Approach 1: Separate Builders for Different Complexity
```
EnemyBuilder (interface)
├── BasicEnemyBuilder    — for simple enemies (Goblin, Skeleton)
├── BossEnemyBuilder     — for complex bosses (Dragon, Demon Lord)
└── EliteEnemyBuilder    — for enhanced variants
```

#### Approach 2: Single Generic Builder
```
EnemyBuilder
├── setName(), setHealth(), setDamage()     — basic methods
├── addAbility(), addPhase()                — advanced methods
├── setLootTable(), setAI()                 — component methods
└── build()                                  — creates the enemy
```

**Which approach is better? Research and decide!**

---

## Director Pattern (Preset Enemy Construction)

The Director uses a Builder to construct preset enemy configurations.

### Structure
```java
class EnemyDirector {
    private EnemyBuilder builder;

    public EnemyDirector(EnemyBuilder builder) {
        this.builder = builder;
    }

    // Preset constructions — these are like "recipes"
    public Enemy createMinion() {
        return builder
            .setName("Minion")
            .setHealth(50)
            .setDamage(10)
            .build();
    }

    public Enemy createRaidBoss(EnemyComponentFactory componentFactory) {
        return builder
            .setName("Raid Boss")
            .setHealth(100000)
            .setDamage(1000)
            .setAbilities(componentFactory.createAbilities())
            .setLootTable(componentFactory.createLootTable())
            .addPhase(1, 100000)
            .addPhase(2, 50000)
            .addPhase(3, 25000)
            .build();
    }
}
```

### Factory Method in Director
Notice: The Director delegates creation to the Builder.
The `build()` method IS the Factory Method — it creates the product.
Different builders can produce different Enemy subclasses!

This is **Factory Method embedded in Builder**. You don't need to build
a separate Factory Method structure — it's already there!

### Questions
- When would you use Director vs calling Builder directly?
- Can the Director work with any Builder implementation? (Hint: YES — polymorphism!)
- Where is the Factory Method in this structure?

---

## Prototype Pattern (Enemy Cloning & Variants)

### The Problem

You have a base Goblin. You need:
- 50 regular Goblins for the dungeon
- 10 Elite Goblins (2x stats)
- 5 Champion Goblins (5x stats + extra abilities)
- 1 Goblin King (10x stats + boss phases)

Creating each from scratch is wasteful. Clone and modify!

### Deep Copy vs Shallow Copy

**THIS IS THE MOST COMMON MISTAKE. Read carefully!**

```java
// SHALLOW COPY (BROKEN):
public Enemy clone() {
    Goblin copy = new Goblin(this.name);
    copy.abilities = this.abilities;  // WRONG! Same list reference!
    return copy;
}

// What happens:
Enemy original = new Goblin("Goblin");
original.addAbility(new Stab());

Enemy clone = original.clone();
clone.addAbility(new PoisonStab());

// Check original:
original.getAbilities();  // [Stab, PoisonStab]  ← BUG!
// Original changed because clone shares the same abilities list!
```

```java
// DEEP COPY (CORRECT):
public Enemy clone() {
    Goblin copy = new Goblin(this.name);
    copy.health = this.health;
    copy.damage = this.damage;

    // Deep copy the abilities list:
    copy.abilities = new ArrayList<>();
    for (Ability ability : this.abilities) {
        copy.abilities.add(ability.clone());  // Clone each ability too!
    }

    // Deep copy the loot table:
    if (this.lootTable != null) {
        copy.lootTable = this.lootTable.clone();
    }

    return copy;
}
```

### Template Registry

The Registry stores base enemy templates and returns **clones** (never originals!):

```java
class EnemyRegistry {
    private Map<String, Enemy> templates = new HashMap<>();

    public void registerTemplate(String key, Enemy prototype) {
        templates.put(key, prototype);
    }

    public Enemy createFromTemplate(String key) {
        Enemy template = templates.get(key);
        if (template == null) {
            throw new IllegalArgumentException("Unknown template: " + key);
        }
        return template.clone();  // Return CLONE, not original!
    }
}
```

### Questions
1. Why must `createFromTemplate()` return a clone and not the original?
2. What happens if `Ability.clone()` does shallow copy?
3. Should you use Java's `Cloneable` interface or write your own `clone()`?
   (Hint: Research this! Java's `Cloneable` has well-known issues.)

---

## How Builder + Prototype Work Together

**Builder creates the complex original. Prototype clones it efficiently.**

```java
// Step 1: Build a complex boss using Builder
Enemy fireDragon = new BossEnemyBuilder()
    .setName("Fire Dragon")
    .setHealth(50000)
    .setAbilities(fireFactory.createAbilities())
    .setLootTable(fireFactory.createLootTable())
    .addPhase(1, 50000)
    .addPhase(2, 30000)
    .build();

// Step 2: Register as template
registry.registerTemplate("fire-dragon", fireDragon);

// Step 3: Clone variants (much faster than building from scratch!)
Enemy eliteDragon = registry.createFromTemplate("fire-dragon");
eliteDragon.multiplyStats(2.0);  // 2x stronger

Enemy ancientDragon = registry.createFromTemplate("fire-dragon");
ancientDragon.multiplyStats(5.0);  // 5x stronger
ancientDragon.addAbility(new AncientRoar());  // Extra ability
```

**Why this is powerful:**
- Building a Dragon from scratch = 15+ Builder steps
- Cloning a Dragon template = 1 clone() call + modifications
- Need 10 dragon variants? Build once, clone 10 times!

---

## Testing Your Design

Ask yourself:
1. Can I build a complex boss without a telescoping constructor? ✓
2. Can I clone enemies with correct deep copy? ✓
3. Does modifying a clone leave the original unchanged? ✓
4. Does the registry return clones, not originals? ✓
5. Can I identify Factory Method in my Builder.build()? ✓
6. Do my component factories guarantee themed consistency? ✓

If you answered yes to all, you're on the right track!

---

## Need More Help?

Resources:
- Refactoring Guru: Builder Pattern
- Refactoring Guru: Prototype Pattern
- "Head First Design Patterns" — Builder chapter
- "Effective Java" by Joshua Bloch — Item 2 (Builder pattern)

Remember: **Understanding WHEN to use each pattern is more important than memorizing HOW.**
- Simple objects → Constructor is fine (Goblin)
- Complex objects → Use Builder (DragonBoss)
- Need variants → Use Prototype (cloning)
- Need themed families → Use Abstract Factory (components)

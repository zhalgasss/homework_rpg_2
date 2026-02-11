# Frequently Asked Questions (FAQ)

## General Questions

### Q: How much code do I need to write?
**A:** Approximately 25-30 Java classes. Focus on:
- 3+ enemy types with clone support
- 2+ builders (Basic + Boss) + Director
- 3+ themed component factories
- 6+ themed abilities, 3+ loot tables
- Enemy registry for Prototype
- Working demo in Main.java

Quality > Quantity. Clean pattern implementation beats lots of messy code.

### Q: How does this relate to Homework 1?
**A:** HW2 extends the RPG universe from HW1:
- **HW1**: You created heroes using Factory Method + Abstract Factory
- **HW2**: You create enemies using Builder + Prototype + (Abstract Factory + Factory Method)
- Same world, different challenges, complementary patterns
- HW2 is a standalone project (no code dependency on HW1)

### Q: Which pattern should I implement first?
**A:** Recommended order (bottom-up):
1. **Ability + LootTable interfaces** (foundation)
2. **Concrete abilities and loot tables** (products)
3. **Abstract Factory** (themed component creation)
4. **Builder** (enemy construction using factory components)
5. **Prototype** (cloning built enemies)
6. **Factory Method** (identify in Builder/Director, add comments)

---

## Builder Pattern Questions

### Q: What exactly is the Builder pattern?
**A:** Builder separates the construction of a complex object from its representation. Instead of a huge constructor with 15 parameters, you build step-by-step:

```java
// BEFORE (telescoping constructor):
new Dragon("Fire Dragon", 50000, 500, 200, 50, "FIRE", abilities,
    30000, 15000, 5000, loot, "AGGRESSIVE", true, true, 20);

// AFTER (Builder):
new BossEnemyBuilder()
    .setName("Fire Dragon")
    .setHealth(50000)
    .setDamage(500)
    .setElement("FIRE")
    .addAbility(new FlameBreath())
    .setLootTable(fireLoot)
    .build();
```

### Q: How does fluent interface work?
**A:** Each setter returns `this`, allowing method chaining:

```java
public EnemyBuilder setName(String name) {
    this.name = name;
    return this;  // Returns the builder itself!
}

// This enables:
builder.setName("Dragon").setHealth(50000).setDamage(500).build();
```

### Q: What's the difference between Builder and Factory?
**A:** Key distinction:

| Aspect | Factory Method | Builder |
|--------|---------------|---------|
| **Purpose** | Create different types | Construct complex objects |
| **Process** | One-step creation | Multi-step construction |
| **Parameters** | Few | Many (optional + mandatory) |
| **Example** | `createWarrior()` → Warrior | `.setName().setHealth().addAbility().build()` → Dragon |

Factory Method: "WHICH object to create?"
Builder: "HOW to construct a complex object step-by-step?"

### Q: What fields should be mandatory vs optional?
**A:** Design decision, but recommended:
- **Mandatory**: name, health (every enemy needs these)
- **Optional**: damage, defense, speed, element, abilities, phases, loot, AI

Validate mandatory fields in `build()`:
```java
public Enemy build() {
    if (name == null) throw new IllegalStateException("Name is required!");
    if (health <= 0) throw new IllegalStateException("Health must be positive!");
    return new ConcreteEnemy(...);
}
```

### Q: Should the built Enemy be immutable?
**A:** Yes! Once `build()` creates an Enemy:
- No public setters on the Enemy class
- All fields set through the Builder only
- This prevents accidental modifications after construction
- Exception: Prototype might need package-private setters for clone modifications

### Q: What's the Director pattern?
**A:** The Director defines preset construction sequences using a Builder:

```java
class EnemyDirector {
    private EnemyBuilder builder;

    public Enemy createRaidBoss(EnemyComponentFactory factory) {
        return builder
            .setName("Raid Boss")
            .setHealth(100000)
            .setAbilities(factory.createAbilities())
            .setLootTable(factory.createLootTable())
            .addPhase(1, 100000)
            .addPhase(2, 50000)
            .build();
    }
}

// Usage:
EnemyDirector director = new EnemyDirector(new BossEnemyBuilder());
Enemy fireBoss = director.createRaidBoss(new FireComponentFactory());
Enemy iceBoss = director.createRaidBoss(new IceComponentFactory());
```

The Director knows WHAT to build. The Builder knows HOW to build.

### Q: Do I need multiple Builder classes?
**A:** Recommended but not strictly required. Options:
- **Two builders** (recommended): `BasicEnemyBuilder` + `BossEnemyBuilder`
- **One generic builder**: Works but less type-safe
- **Three+ builders**: If you want finer granularity

---

## Prototype Pattern Questions

### Q: What exactly is the Prototype pattern?
**A:** Create new objects by cloning existing ones instead of building from scratch:

```java
// WITHOUT Prototype: Build each variant from scratch
Enemy goblin1 = new BasicEnemyBuilder().setName("Goblin").setHealth(100)...build();
Enemy goblin2 = new BasicEnemyBuilder().setName("Goblin").setHealth(100)...build();
// Repeat for every goblin?!

// WITH Prototype: Clone a template
Enemy template = new BasicEnemyBuilder().setName("Goblin").setHealth(100)...build();
Enemy goblin1 = template.clone();  // Fast!
Enemy goblin2 = template.clone();  // Fast!
```

### Q: What is deep copy vs shallow copy?
**A:** The most critical concept in Prototype pattern!

**Shallow copy** copies field values directly:
```java
copy.abilities = this.abilities;  // Both point to SAME list!
// Modifying clone's abilities modifies original too! BUG!
```

**Deep copy** creates new objects for reference types:
```java
copy.abilities = new ArrayList<>();
for (Ability a : this.abilities) {
    copy.abilities.add(a.clone());  // Each ability is cloned!
}
// Clone has its own independent list. Safe!
```

**Rule of thumb:**
- Primitive types (int, boolean, double) → shallow copy is fine
- Reference types (List, Map, custom objects) → MUST deep copy

### Q: Should I use Java's Cloneable interface?
**A:** Research this! Java's `Cloneable` has well-known issues:
- `Object.clone()` does shallow copy by default
- `Cloneable` is a "marker interface" (no methods!)
- Many Java experts recommend AGAINST it (see Effective Java)

**Recommended approach:** Define your own `clone()` method:
```java
public interface Enemy {
    Enemy clone();  // Your own clone contract
}
```

This gives you full control over deep copy behavior.

### Q: How does the Registry work?
**A:** The Registry stores base templates and returns clones:

```java
class EnemyRegistry {
    private Map<String, Enemy> templates = new HashMap<>();

    public void registerTemplate(String key, Enemy template) {
        templates.put(key, template);
    }

    public Enemy createFromTemplate(String key) {
        return templates.get(key).clone();  // Always return CLONE!
    }
}
```

**Critical:** `createFromTemplate()` returns a CLONE, never the original!
If it returned the original, modifying the "clone" would corrupt the template.

### Q: When should I use Prototype vs Builder?
**A:**
- **Builder**: When creating a NEW complex object from scratch
- **Prototype**: When creating a VARIANT of an existing object

```java
// First time: Use Builder
Enemy fireDragon = new BossEnemyBuilder()
    .setName("Fire Dragon").setHealth(50000)...build();

// Variants: Use Prototype (much faster!)
Enemy eliteDragon = fireDragon.clone();
eliteDragon.multiplyStats(2.0);
```

---

## Abstract Factory Questions

### Q: Why do I need Abstract Factory again?
**A:** To guarantee themed consistency. Without it:
```java
// BUG: Nothing prevents mixing fire abilities with ice loot!
builder.setAbilities(fireAbilities).setLootTable(iceLoot);
```

With Abstract Factory:
```java
// ONE factory creates ALL matching components:
EnemyComponentFactory factory = new FireComponentFactory();
builder.setAbilities(factory.createAbilities())     // fire abilities
       .setLootTable(factory.createLootTable())      // fire loot
       .setAI(factory.createAIBehavior());           // fire AI
// All from same factory = guaranteed match!
```

### Q: How is this different from HW1's Abstract Factory?
**A:** Same pattern, different products:
- **HW1**: Factory creates Weapon + Armor (equipment family)
- **HW2**: Factory creates Abilities + LootTable + AI (component family)

The structure is identical. Practice makes permanent!

### Q: How many products should each factory create?
**A:** At minimum: abilities list + loot table + AI behavior (3 products).
You can add more (e.g., special mechanics, spawn effects).

---

## Factory Method Questions

### Q: Do I need to build a separate Factory Method hierarchy?
**A:** No! Factory Method is **embedded** in your Builder and Director:
- `EnemyBuilder.build()` IS the factory method
- Different builders override it to create different enemy types
- Director delegates to builder polymorphically

Just identify it and add comments explaining where it appears.

### Q: Where exactly is Factory Method?
**A:**
```java
// HERE: build() is the factory method
interface EnemyBuilder {
    Enemy build();  // Factory Method — returns product
}

class BasicEnemyBuilder implements EnemyBuilder {
    public Enemy build() { return new BasicEnemy(...); }  // Concrete factory method
}

class BossEnemyBuilder implements EnemyBuilder {
    public Enemy build() { return new BossEnemy(...); }   // Different concrete product
}

// AND HERE: Director delegates to builder
class EnemyDirector {
    private EnemyBuilder builder;  // Doesn't know concrete type!
    public Enemy createBoss() {
        return builder.build();     // Polymorphic factory method call
    }
}
```

---

## Design Questions

### Q: Should Enemy be an interface or abstract class?
**A:** Consider:
- **Interface**: If implementations are very different
- **Abstract class**: If you want shared fields (name, health, abilities list)

**Recommendation:** Abstract class is often cleaner:
```java
public abstract class AbstractEnemy implements Enemy {
    protected String name;
    protected int health;
    protected int damage;
    protected List<Ability> abilities;
    // Shared implementation...

    public abstract Enemy clone();  // Each subclass handles its own cloning
}
```

### Q: How do I add a new theme (e.g., Nature)?
**A:** With proper Abstract Factory, you ONLY create new classes:
1. `NatureAbility1.java`, `NatureAbility2.java` (new abilities)
2. `NatureLootTable.java` (new loot)
3. `NatureComponentFactory.java` (new factory)

That's it! No existing code changes. This is the Open/Closed Principle in action.

### Q: Can the Prototype clone use the Builder to construct the copy?
**A:** Interesting idea! But typically:
- **Clone**: Creates copy of EXISTING object (preserves current state)
- **Builder**: Creates NEW object from scratch

Clone is faster because it copies existing state directly.
Builder is for initial construction; Prototype is for duplication.

---

## Common Mistakes

### Mistake 1: Not using fluent interface
```java
// BAD:
builder.setHealth(100);
builder.setDamage(50);
Enemy e = builder.build();

// GOOD:
Enemy e = builder.setHealth(100).setDamage(50).build();
```

### Mistake 2: Shallow copy in clone()
```java
// BAD: Shared ability list!
copy.abilities = this.abilities;

// GOOD: Independent copy!
copy.abilities = new ArrayList<>();
for (Ability a : this.abilities) {
    copy.abilities.add(a.clone());
}
```

### Mistake 3: Registry returns original
```java
// BAD: Returns the template itself!
return templates.get(key);

// GOOD: Returns a clone!
return templates.get(key).clone();
```

### Mistake 4: No validation in build()
```java
// BAD: Allows invalid enemies!
public Enemy build() { return new Enemy(...); }

// GOOD: Validates first!
public Enemy build() {
    if (name == null) throw new IllegalStateException("Name required!");
    return new Enemy(...);
}
```

### Mistake 5: Mixing themes without Abstract Factory
```java
// BAD: Manual component creation allows mixing
builder.addAbility(new FlameBreath()).setLootTable(new IceLootTable());

// GOOD: Factory guarantees consistency
EnemyComponentFactory factory = new FireComponentFactory();
builder.setAbilities(factory.createAbilities()).setLootTable(factory.createLootTable());
```

---

## Still Stuck?

1. **Read the hint files**: `BUILDER_HINTS.md` and `FACTORY_HINTS.md`
2. **Research patterns**: Refactoring Guru, Head First Design Patterns
3. **Draw it out**: Class hierarchy, pattern interactions, data flow
4. **Start smaller**: Get one pattern working, then add the next
5. **Test often**: Run Main.java after every change
6. **Ask for help**: Clarification on requirements, conceptual questions

**Remember: Struggling with design decisions IS the learning process!**
The patterns will "click" once you work through the problems they solve.

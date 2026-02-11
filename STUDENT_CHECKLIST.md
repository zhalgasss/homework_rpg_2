# Student Progress Checklist

Use this checklist to track your progress and ensure you've completed all requirements.

## Phase 1: Understanding (Day 1)
- [ ] Read `README.md` completely
- [ ] Read `ASSIGNMENT.md` completely
- [ ] Research Builder pattern
  - [ ] Understand the telescoping constructor problem
  - [ ] Understand fluent interface / method chaining
  - [ ] Find at least 2 example implementations online
- [ ] Research Prototype pattern
  - [ ] Understand deep copy vs shallow copy
  - [ ] Understand template/registry concept
  - [ ] Find at least 2 example implementations online
- [ ] Review Abstract Factory pattern (from HW1)
  - [ ] Understand product families concept
  - [ ] Understand how it applies to themed components
- [ ] Study the provided code:
  - [ ] `DragonBoss.java` — feel the constructor pain!
  - [ ] `Goblin.java` — simple enemy structure
  - [ ] `EnemyComponentFactory.java` — Abstract Factory interface
  - [ ] `BUILDER_HINTS.md` — Builder & Prototype guidance
  - [ ] `FACTORY_HINTS.md` — Abstract Factory & Factory Method guidance

## Phase 2: Planning (Day 1-2)
- [ ] Sketch full class hierarchy on paper
  - [ ] Enemy hierarchy
  - [ ] Builder hierarchy
  - [ ] Component Factory hierarchy
  - [ ] Prototype/Registry structure
- [ ] Map how the 4 patterns interact:
  - [ ] Abstract Factory → creates components
  - [ ] Builder → assembles enemies from components
  - [ ] Factory Method → embedded in build() and Director
  - [ ] Prototype → clones built enemies into variants
- [ ] List all classes you need to create
- [ ] Start rough UML diagrams

## Phase 3: Foundation — Interfaces & Abilities (Day 2)
- [ ] Complete `Ability` interface
  - [ ] Define getName(), getDamage(), getDescription()
  - [ ] Define clone() method
- [ ] Complete `LootTable` interface
  - [ ] Define getItems(), getGoldDrop(), getExperienceDrop()
  - [ ] Define clone() method
- [ ] Implement Fire abilities:
  - [ ] FlameBreath
  - [ ] FireShield
  - [ ] (Optional) MeteorStorm
- [ ] Implement Ice abilities:
  - [ ] FrostBreath
  - [ ] IceShield
  - [ ] (Optional) Blizzard
- [ ] Implement Shadow abilities:
  - [ ] ShadowStrike
  - [ ] Vanish
  - [ ] (Optional) DarkNova
- [ ] Implement Loot Tables:
  - [ ] FireLootTable
  - [ ] IceLootTable
  - [ ] ShadowLootTable

**Checkpoint:** Do all abilities implement clone()? Do all loot tables implement clone()?

## Phase 4: Abstract Factory — Themed Factories (Day 2-3)
- [ ] Implement `FireComponentFactory`
  - [ ] Creates fire abilities
  - [ ] Creates fire loot table
  - [ ] Returns aggressive AI behavior
- [ ] Implement `IceComponentFactory`
  - [ ] Creates ice abilities
  - [ ] Creates ice loot table
  - [ ] Returns defensive AI behavior
- [ ] Implement `ShadowComponentFactory`
  - [ ] Creates shadow abilities
  - [ ] Creates shadow loot table
  - [ ] Returns tactical AI behavior
- [ ] Test: Each factory produces matching themed components

**Checkpoint:** Can you guarantee Fire factory ONLY produces fire components?

## Phase 5: Builder — Enemy Construction (Day 3-4)
- [ ] Complete `Enemy` interface (or convert to abstract class)
  - [ ] Core stat methods
  - [ ] Ability management
  - [ ] Loot table access
  - [ ] Display method
  - [ ] Clone method signature
- [ ] Create `EnemyBuilder` interface
  - [ ] Fluent setter methods (return `this`)
  - [ ] `build()` method
- [ ] Implement `BasicEnemyBuilder`
  - [ ] Builds simple enemies (Goblin, Skeleton, Orc)
  - [ ] Validates mandatory fields
- [ ] Implement `BossEnemyBuilder`
  - [ ] Builds complex bosses (Dragon, Demon Lord)
  - [ ] Supports phases, special properties
  - [ ] Validates mandatory fields
- [ ] Implement `EnemyDirector`
  - [ ] createMinion() — simple enemy preset
  - [ ] createElite() — medium difficulty preset
  - [ ] createMiniBoss() — challenging preset
  - [ ] createRaidBoss() — ultimate preset
- [ ] Refactor `DragonBoss.java` — remove/simplify telescoping constructor
- [ ] Test: Build a complex boss using Builder with factory components

**Checkpoint:** Can you build a Dragon Boss using fluent Builder + FireComponentFactory?

## Phase 6: Prototype — Cloning & Variants (Day 4-5)
- [ ] Implement `clone()` in Goblin with DEEP COPY
  - [ ] Clone abilities list (new list, each ability cloned)
  - [ ] Clone loot table
  - [ ] Copy primitive stats
- [ ] Implement `clone()` in other enemy types
- [ ] Create `EnemyRegistry` class
  - [ ] registerTemplate(String key, Enemy prototype)
  - [ ] createFromTemplate(String key) — returns CLONE
  - [ ] listTemplates() — shows registered templates
- [ ] Add stat modification helpers:
  - [ ] multiplyStats(double multiplier) — for difficulty tiers
  - [ ] setElement(String element) — for elemental variants
  - [ ] addAbility(Ability ability) — for enhanced variants
- [ ] Create difficulty variants:
  - [ ] Elite (2x stats)
  - [ ] Champion (5x stats + extra abilities)
  - [ ] Boss variant (10x stats + phases)
- [ ] Create elemental variants:
  - [ ] Fire variant (fire abilities from FireComponentFactory)
  - [ ] Ice variant (ice abilities from IceComponentFactory)
  - [ ] Shadow variant (shadow abilities from ShadowComponentFactory)
- [ ] Test deep copy: modify clone, verify original unchanged

**Checkpoint:** Does modifying a clone leave the original template unchanged?

## Phase 7: Factory Method — Identify & Document (Day 4-5)
- [ ] Identify Factory Method in Builder.build()
  - [ ] Add comment explaining it's a factory method
  - [ ] Different builders return different concrete types
- [ ] Identify Factory Method in Director
  - [ ] Director delegates to builder polymorphically
  - [ ] Director doesn't know concrete builder type
- [ ] Add clear comments in Main.java identifying FM usage

**Checkpoint:** Can you explain WHERE Factory Method appears and WHY?

## Phase 8: Integration Demo (Day 5)
- [ ] Complete Main.java Part 1: Abstract Factory demo
- [ ] Complete Main.java Part 2: Builder demo
- [ ] Complete Main.java Part 3: Prototype demo
- [ ] Complete Main.java Part 4: Full pipeline demo
- [ ] Print pattern summary
- [ ] Test complete demo runs without errors
- [ ] Output is clear, informative, and professional

**Checkpoint:** Does your demo showcase all 4 patterns clearly?

## Phase 9: UML Diagrams (Day 5-6)
- [ ] Create Diagram 1: Builder + Factory Method
  - [ ] EnemyBuilder interface and concrete builders
  - [ ] Director class
  - [ ] Enemy hierarchy
  - [ ] Show build() as factory method
  - [ ] Proper UML notation
- [ ] Create Diagram 2: Prototype + Abstract Factory
  - [ ] Enemy with clone() method
  - [ ] EnemyRegistry
  - [ ] EnemyComponentFactory and concrete factories
  - [ ] Product families (abilities, loot)
  - [ ] Proper UML notation
- [ ] Export as PDF or image
- [ ] Verify diagrams match your actual implementation

## Phase 10: Code Quality & Final Testing (Day 6)
- [ ] Java naming conventions (PascalCase, camelCase, UPPER_SNAKE_CASE)
- [ ] Proper encapsulation (private fields, public methods)
- [ ] No code duplication
- [ ] All TODOs completed or removed
- [ ] Code compiles without errors
- [ ] Main.java demo runs successfully
- [ ] UML diagrams included in project folder
- [ ] Review grading rubric in ASSIGNMENT.md

---

## Self-Assessment Questions

Before submitting, answer honestly:

### Builder Pattern
1. Can you explain WHY Builder is better than telescoping constructors?
2. Does your Builder use fluent interface (method chaining)?
3. Does your `build()` validate mandatory fields?
4. Is the built Enemy immutable (no public setters)?

### Prototype Pattern
5. Does your `clone()` perform deep copy for ALL complex fields?
6. Does modifying a clone leave the original unchanged?
7. Does your registry return clones, never originals?
8. Can you create difficulty and elemental variants from templates?

### Abstract Factory
9. Do your component factories guarantee themed consistency?
10. Does your Builder USE the factory components (not hardcode)?
11. Can you add a new theme without modifying existing code?

### Factory Method
12. Can you point to WHERE Factory Method appears in your code?
13. Can you explain WHY `build()` is a factory method?

### Pattern Comparison
14. Can you explain when to use Builder vs Factory Method?
15. Can you explain when to use Prototype vs Builder?
16. Can you explain when to use Abstract Factory vs Factory Method?

If you can confidently answer all 16 questions, you're ready to submit!

---

## Time Management Tips

**Don't have 6 full days?** Prioritize:
1. **Minimum viable product** (Days 1-4):
   - 2 enemy types, 1 builder, basic clone, 2 factories
   - Simple demo showing each pattern
2. **Polish and diagrams** (Days 5-6):
   - More enemies, variants, enhanced demo
   - UML diagrams, code quality
   - Director and advanced features

**Stuck on something?**
- Read `BUILDER_HINTS.md` and `FACTORY_HINTS.md`
- Research the specific pattern online
- Draw it on paper
- Ask instructor for clarification

**Remember:** The goal is to UNDERSTAND the patterns, not just complete the assignment!

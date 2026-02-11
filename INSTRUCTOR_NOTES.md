# Instructor Notes - Homework 2

## Project Overview
This homework serves as a **creational patterns capstone**, teaching Builder and Prototype as primary patterns while reinforcing Factory Method and Abstract Factory from HW1. Students build an RPG enemy system with themed variants.

## What's Provided to Students

### Minimal Scaffolding
- **Interfaces**: `Enemy`, `Ability`, `LootTable`, `EnemyComponentFactory` (all with TODO comments)
- **Two examples**: `Goblin.java` (simple), `DragonBoss.java` (complex — shows Builder pain!)
- **Main.java skeleton**: Clear TODOs for all 4 pattern demonstrations
- **No pattern implementations**: Students design builders, clone logic, factories, registry themselves

### Documentation (Comprehensive)
- **README.md**: Overview and motivation
- **ASSIGNMENT.md**: Detailed requirements, grading rubric, examples
- **QUICKSTART.md**: IDE setup, running the project
- **STUDENT_CHECKLIST.md**: 10-phase day-by-day tracking
- **FAQ.md**: Common questions, pattern comparisons, mistakes
- **BUILDER_HINTS.md**: Builder + Director + Prototype guidance
- **FACTORY_HINTS.md**: Abstract Factory + Factory Method guidance

## Learning Objectives

### Builder Pattern (PRIMARY)
Students learn to:
- Eliminate telescoping constructors with fluent interfaces
- Separate construction logic from representation
- Validate before building (mandatory vs optional fields)
- Apply Director pattern for preset constructions
- Appreciate immutability of built objects

### Prototype Pattern (PRIMARY)
Students learn to:
- Implement deep copy for complex object graphs
- Distinguish deep vs shallow copy (critical!)
- Design template registries
- Create object variants efficiently via cloning
- Understand when cloning beats construction

### Abstract Factory (SUPPORTING — reinforces HW1)
Students learn to:
- Create themed component families (abilities + loot + AI)
- Guarantee consistency within a theme
- Integrate factories with Builder pattern
- Apply the same pattern in a new context

### Factory Method (SUPPORTING — reinforces HW1)
Students learn to:
- Recognize Factory Method embedded in Builder.build()
- Understand Director as Factory Method delegation
- Distinguish Factory Method from Builder pattern

### Cross-Pattern Understanding
- When to use each creational pattern (comparison table)
- How patterns complement each other in real systems
- Pattern selection based on problem characteristics

## Time Estimate: 5-6 Days

### Day 1: Understanding (3-5 hours)
- Read all documentation
- Research Builder and Prototype patterns
- Review Abstract Factory from HW1
- Study provided examples
- Plan class hierarchy

### Day 2: Foundation (4-6 hours)
- Complete Ability and LootTable interfaces
- Implement themed abilities (6+)
- Implement themed loot tables (3+)
- Start component factories

### Day 2-3: Abstract Factory (3-4 hours)
- Implement 3 themed component factories
- Test component consistency
- Verify no theme mixing possible

### Day 3-4: Builder (6-8 hours)
- Design EnemyBuilder interface
- Implement BasicEnemyBuilder and BossEnemyBuilder
- Implement EnemyDirector
- Refactor DragonBoss to use Builder
- Add validation

### Day 4-5: Prototype (5-7 hours)
- Implement clone() with deep copy in enemy classes
- Create EnemyRegistry
- Create difficulty variants (Elite, Champion, Boss)
- Create elemental variants (Fire, Ice, Shadow)
- Test deep copy correctness

### Day 5-6: Integration & Polish (4-6 hours)
- Complete Main.java demo (all 4 patterns)
- Identify Factory Method, add comments
- Create UML diagrams
- Code quality review
- Final testing

**Total: 24-32 hours** (appropriate for 5-6 days of focused work)

## Expected Student Work

### Classes (Minimum 25-30)

**Enemy Types (3+):**
- Refactored DragonBoss
- Enhanced Goblin (with clone)
- Skeleton, Orc, or other (student choice)

**Abilities (6+):**
- FlameBreath, FireShield (fire theme)
- FrostBreath, IceShield (ice theme)
- ShadowStrike, Vanish (shadow theme)

**Loot Tables (3+):**
- FireLootTable, IceLootTable, ShadowLootTable

**Builder Classes (4+):**
- EnemyBuilder (interface)
- BasicEnemyBuilder
- BossEnemyBuilder
- EnemyDirector

**Component Factories (4+):**
- EnemyComponentFactory (provided as interface)
- FireComponentFactory
- IceComponentFactory
- ShadowComponentFactory

**Prototype Classes (2+):**
- EnemyRegistry
- (Optional) StatModifier, VariantFactory

**UML Diagrams (2):**
- Builder + Factory Method
- Prototype + Abstract Factory

## Grading Rubric (100 points)

### Builder Pattern — PRIMARY (25 points)
- [ ] Builder interface with fluent methods (5 pts)
- [ ] 2+ concrete builders (BasicEnemy + Boss) (5 pts)
- [ ] Director with preset construction methods (5 pts)
- [ ] Mandatory/optional field handling + validation (5 pts)
- [ ] No telescoping constructors (5 pts)

### Prototype Pattern — PRIMARY (25 points)
- [ ] Clone method with correct deep copy (5 pts)
- [ ] Deep copy for abilities, loot, phases (8 pts)
- [ ] Template registry (7 pts)
- [ ] 3+ enemy variants from templates (5 pts)

### Abstract Factory — SUPPORTING (15 points)
- [ ] EnemyComponentFactory interface properly used (3 pts)
- [ ] 3+ themed concrete factories (6 pts)
- [ ] Guaranteed component consistency (3 pts)
- [ ] Builder uses factory components (3 pts)

### Factory Method — SUPPORTING (5 points)
- [ ] FM visible in Builder.build() / Director (3 pts)
- [ ] Student explains FM in comments (2 pts)

### Code Quality (10 points)
- [ ] Java naming conventions (2 pts)
- [ ] Proper encapsulation and immutability (3 pts)
- [ ] Code organization and readability (3 pts)
- [ ] No duplication (2 pts)

### UML Diagrams (15 points)
- [ ] Builder + Factory Method diagram (7 pts)
- [ ] Prototype + Abstract Factory diagram (8 pts)

### Demonstration (5 points)
- [ ] Main.java demonstrates all 4 patterns (3 pts)
- [ ] Clear, professional output (2 pts)

## Common Student Mistakes to Watch For

### Builder Pattern Mistakes

**Using telescoping constructor alongside Builder:**
```java
// Student adds Builder but keeps the old constructor in use
new DragonBoss("name", 50000, 500, 200, 50, ...);  // Still using it!
```
**Feedback:** "Your Builder is implemented, but you're still using the telescoping constructor. The whole point is to replace it. Use the Builder everywhere."

**Not validating in build():**
```java
public Enemy build() { return new Enemy(name, health, ...); }
// What if name is null? Health is 0?
```
**Feedback:** "Your build() doesn't validate required fields. What happens if someone calls `.build()` without setting a name?"

**No fluent interface:**
```java
builder.setName("Dragon");
builder.setHealth(50000);
Enemy e = builder.build();
```
**Feedback:** "Your builder works but isn't fluent. Return `this` from each setter for method chaining."

### Prototype Pattern Mistakes

**Shallow copy (THE most common mistake):**
```java
copy.abilities = this.abilities;  // Same reference!
```
**Feedback:** "Your clone shares the abilities list with the original. Add an ability to the clone and check the original — you'll see the bug!"

**Registry returns originals:**
```java
public Enemy getTemplate(String key) {
    return templates.get(key);  // Returns the actual template!
}
```
**Feedback:** "Your registry returns the original template. Any modifications will corrupt the template for future clones!"

**Not cloning nested objects:**
```java
// Clones the list but not the abilities inside it
copy.abilities = new ArrayList<>(this.abilities);
// Abilities are still shared!
```
**Feedback:** "You created a new list (good!) but the ability objects inside are still shared references. You need to clone each ability too."

### Abstract Factory Mistakes

**Not using factory for components:**
```java
// Student creates abilities manually instead of using factory
builder.addAbility(new FlameBreath());  // Where's the factory?
```
**Feedback:** "You're creating abilities directly. Use your ComponentFactory to create them — that's what guarantees themed consistency."

**Allowing theme mixing:**
```java
builder.setAbilities(fireFactory.createAbilities())
       .setLootTable(iceFactory.createLootTable());  // MISMATCH!
```
**Feedback:** "Your code allows mixing Fire abilities with Ice loot. Use a SINGLE factory for all components of one enemy."

### General Mistakes

**Not understanding when to use which pattern:**
**Feedback:** "Can you explain WHY you chose Builder here instead of Factory Method? What problem does Builder solve that Factory doesn't?"

**Over-engineering:**
Students add databases, GUIs, complex combat systems.
**Feedback:** "Your extra features are impressive, but the patterns are hard to identify. Focus on demonstrating the 4 creational patterns clearly."

## Discussion Points for Class

### After Submission
1. **Pattern Selection**: "Given a new scenario, which creational pattern would you choose? Why?"
2. **Pattern Comparison**: "What's the difference between Builder and Abstract Factory? When would you use each?"
3. **Deep Copy Challenge**: "Who got bitten by shallow copy? What happened?"
4. **Real-World Applications**: "Where have you seen Builder in Java standard library? (StringBuilder, Stream.Builder)"
5. **Capstone Reflection**: "Now that you know all 4 creational patterns, can you think of a system that uses all of them?"

### Common Student Realizations
- "Oh! Builder isn't just for constructors — it makes complex construction READABLE!"
- "Deep copy is WAY harder than I expected with nested objects"
- "Abstract Factory is the same pattern everywhere — I finally get it"
- "Factory Method is hiding inside other patterns — it's more fundamental than I thought"
- "All 4 patterns solve different aspects of the same problem: object creation"

## Quick Assessment Checklist

### 5-Minute Grading
1. **Does it compile?** (If not, significant deduction)
2. **Are all 4 patterns present?** (Look for Builder, clone(), factory, Director)
3. **Run Main.java** — does output show all patterns?
4. **Check clone()** — is it deep copy? (Look for `new ArrayList<>` + per-element clone)
5. **Check Builder** — is it fluent? (Look for `return this;`)
6. **Check component factories** — do they create matching themed components?
7. **Check Registry** — does it return clones?
8. **Review UML** — do diagrams match implementation?

### Red Flags
- Telescoping constructors still in use
- `copy.abilities = this.abilities;` (shallow copy!)
- Registry returns `templates.get(key)` without `.clone()`
- No component factories (abilities created directly)
- Builder without validation
- UML diagrams don't match code

### Green Flags
- Fluent Builder with validation
- Deep copy with per-element cloning
- Registry always returns clones
- Factory guarantees themed consistency
- Clear Factory Method identification in comments
- Clean demo output showing all patterns
- UML diagrams accurately represent implementation

## Customization Options

### Make It Easier
- Provide one complete Builder implementation as example
- Provide clone() for Goblin (students extend to other types)
- Reduce to 2 themes instead of 3
- Remove Director requirement
- Remove Factory Method identification requirement

### Make It Harder
- Require 5+ themes and 5+ enemy types
- Require thread-safe registry (ConcurrentHashMap)
- Require serializable enemies (save/load)
- Require combat simulation using the enemy system
- Require pattern comparison essay (500 words)
- Require Builder validation with custom exceptions

### Different Domain
Same pattern structure works for:
- **Document System**: Builder for documents, Prototype for templates
- **Vehicle Config**: Builder for cars, Prototype for model variants
- **Meal Ordering**: Builder for custom meals, Prototype for combo templates
- **UI Framework**: Builder for forms, Prototype for component presets

---

## Final Notes

This is a **capstone assignment** covering all 4 creational patterns. Students who complete this will have:
- **Deep understanding** of Builder and Prototype (new learning)
- **Reinforced mastery** of Abstract Factory and Factory Method (from HW1)
- **Comparative knowledge** — when to use each pattern
- **Integration skills** — how patterns complement each other

The enemy system domain makes this engaging:
- Building bosses is fun
- Cloning variants is satisfying
- Themed components are intuitive
- Game development motivates students

**Key success factor:** The DragonBoss.java telescoping constructor. Students who feel that pain will appreciate Builder deeply. Don't reduce it — the pain IS the lesson!

---

*Created for Narxoz University - Software Design Patterns Course*
*Patterns: Builder (PRIMARY), Prototype (PRIMARY), Abstract Factory (SUPPORTING), Factory Method (SUPPORTING)*
*Domain: RPG Enemy System*

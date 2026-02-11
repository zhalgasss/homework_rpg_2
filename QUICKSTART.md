# Quick Start Guide

## Prerequisites
- Java 17 or higher installed
- IDE of your choice (IntelliJ IDEA, Eclipse, VS Code)

**No Maven or Gradle needed** — this is a plain Java project.

## Setup

### 1. Verify Java Installation
```bash
java -version
# Should show Java 17 or higher

javac -version
# Should show Java 17 or higher
```

### 2. Open Project in IDE

#### IntelliJ IDEA (Recommended)
1. Open IntelliJ IDEA
2. File -> Open
3. Navigate to `homework-2` folder and select it
4. IntelliJ will detect the Java files automatically
5. **Mark `src` as Sources Root**: Right-click `src` folder -> Mark Directory as -> Sources Root

#### VS Code
1. Open VS Code
2. File -> Open Folder -> Select `homework-2`
3. Install "Extension Pack for Java" if not installed
4. VS Code should detect `src/` as the source folder

#### Eclipse
1. Open Eclipse
2. File -> New -> Java Project
3. Uncheck "Use default location", browse to `homework-2`
4. Set `src` as the source folder
5. Finish

### 3. Verify Project Structure

Your project should look like:
```
homework-2/
├── src/
│   └── com/
│       └── narxoz/
│           └── rpg/
│               ├── Main.java              # Start here!
│               ├── enemy/
│               │   ├── Enemy.java         # Interface to complete
│               │   ├── Goblin.java        # Example enemy
│               │   └── DragonBoss.java    # Complex boss example
│               ├── builder/
│               │   └── BUILDER_HINTS.md   # Read this!
│               ├── factory/
│               │   ├── EnemyComponentFactory.java  # Abstract Factory interface
│               │   └── FACTORY_HINTS.md   # Read this!
│               ├── combat/
│               │   └── Ability.java       # Ability interface
│               ├── loot/
│               │   └── LootTable.java     # Loot table interface
│               ├── prototype/             # Your registry goes here
│               └── game/                  # Optional game logic
├── README.md
├── ASSIGNMENT.md
├── QUICKSTART.md                          # This file!
├── STUDENT_CHECKLIST.md
├── FAQ.md
└── .gitignore
```

## Building & Running

### Using IDE (Recommended)
1. Open `Main.java`
2. Click the green **Run** button (or right-click -> Run 'Main.main()')
3. Output appears in the console

### Using Command Line
```bash
# Navigate to project
cd homework-2

# Compile all Java files
# On Windows:
javac -d out src/com/narxoz/rpg/Main.java src/com/narxoz/rpg/enemy/*.java src/com/narxoz/rpg/combat/*.java src/com/narxoz/rpg/loot/*.java src/com/narxoz/rpg/factory/*.java

# On Mac/Linux:
javac -d out $(find src -name "*.java")

# Run
java -cp out com.narxoz.rpg.Main
```

**Note:** As you add more Java files, include them in the `javac` command.
Using an IDE is much easier — it compiles everything automatically.

## Development Workflow

### Recommended Order
1. **Day 1**: Read all docs, research patterns, plan design
2. **Day 2**: Implement Ability and LootTable interfaces, create themed abilities
3. **Day 2-3**: Implement Abstract Factory (themed component factories)
4. **Day 3-4**: Implement Builder (EnemyBuilder, BossEnemyBuilder, Director)
5. **Day 4-5**: Implement Prototype (clone methods, EnemyRegistry)
6. **Day 5-6**: Integration demo, UML diagrams, code quality

### Testing Your Changes
After each change:
1. Make sure your code compiles (no red underlines in IDE)
2. Run `Main.java`
3. Check output is as expected

### Finding TODOs
Search for "TODO" in your IDE to find what needs to be completed:
- **IntelliJ**: View -> Tool Windows -> TODO
- **VS Code**: Search "TODO" across workspace (Ctrl+Shift+F)
- **Eclipse**: Search -> File Search -> "TODO"

## Tips for Success

### 1. Start Small
Don't implement everything at once:
- Get ONE ability working
- Get ONE component factory working
- Get ONE builder working
- Get ONE clone working
- THEN expand

### 2. Test Frequently
Run `Main.java` after every small change. Catch errors early.

### 3. Draw Before Coding
Sketch your class hierarchy on paper:
- Which classes implement which interfaces?
- Which patterns connect where?
- What's the data flow?

### 4. Study the Provided Code
- `Goblin.java` — simple enemy structure
- `DragonBoss.java` — **the pain point** (why Builder is needed)
- `EnemyComponentFactory.java` — Abstract Factory interface
- `BUILDER_HINTS.md` — pattern guidance
- `FACTORY_HINTS.md` — pattern guidance

### 5. Read the Hint Files!
`BUILDER_HINTS.md` and `FACTORY_HINTS.md` contain:
- Pattern explanations
- Code skeletons
- Common mistakes to avoid
- Design questions to guide you

## Common Issues

### "Cannot find symbol" errors
- You're using a class that doesn't exist yet
- Complete the interfaces first, then create implementations
- Check package declarations match directory structure

### "Package does not exist"
- Make sure `src` is marked as Sources Root in your IDE
- Check that your package declaration matches the folder path

### Import errors
- Your classes must be in the correct packages
- Use `import com.narxoz.rpg.combat.Ability;` etc.
- IDE can auto-import (Alt+Enter in IntelliJ)

### Main class not found
- Ensure `Main.java` has: `package com.narxoz.rpg;`
- Ensure `Main` class has: `public static void main(String[] args)`
- Run from within the IDE for easiest setup

## Need Help?

1. Read `BUILDER_HINTS.md` and `FACTORY_HINTS.md`
2. Read `FAQ.md`
3. Research on Refactoring Guru (website)
4. Draw your design on paper
5. Ask instructor for **clarification** (not solutions!)

---

Happy coding! Remember: **the patterns you learn here will be valuable throughout your career.**

# START HERE - Homework 2: Creational Patterns Capstone

## Project Ready to Use!

This is a complete homework assignment for teaching **Builder** and **Prototype** design patterns (primary), with **Abstract Factory** and **Factory Method** reinforced as supporting patterns. All four creational patterns work together in one unified RPG enemy system.

---

## Project Structure

```
homework-2/
│
├── 00-START-HERE.md           ← You are here!
├── PROJECT_SUMMARY.md          ← Quick overview (read this next!)
├── INSTRUCTOR_NOTES.md         ← Grading guide and tips
│
├── README.md                   ← Students read this first
├── ASSIGNMENT.md               ← Detailed requirements & rubric
├── QUICKSTART.md               ← Technical setup guide (IDE-focused)
├── STUDENT_CHECKLIST.md        ← Day-by-day progress tracker
├── FAQ.md                      ← Common questions answered
│
├── .gitignore                  ← Git ignore rules
│
└── src/com/narxoz/rpg/
    ├── Main.java                          # Demo skeleton (all 4 patterns)
    │
    ├── enemy/
    │   ├── Enemy.java                     # Interface with TODOs
    │   ├── Goblin.java                    # Simple enemy example
    │   └── DragonBoss.java               # Complex boss (telescoping constructor!)
    │
    ├── builder/
    │   └── BUILDER_HINTS.md               # Builder + Prototype guidance
    │
    ├── factory/
    │   ├── EnemyComponentFactory.java     # Abstract Factory interface
    │   └── FACTORY_HINTS.md               # Abstract Factory + Factory Method guidance
    │
    ├── combat/
    │   └── Ability.java                   # Ability interface with TODOs
    │
    ├── loot/
    │   └── LootTable.java                # Loot table interface with TODOs
    │
    ├── prototype/                         # (Empty - students create registry here)
    │
    └── game/                              # (Empty - optional game logic)
```

---

## Quick Start

### For Instructor
1. **Read**: `PROJECT_SUMMARY.md` (2 minutes)
2. **Review**: `INSTRUCTOR_NOTES.md` (10 minutes)
3. **Customize**: Grading rubric if needed
4. **Distribute**: Give students the entire `homework-2/` folder

### For Students
Direct them to:
1. `README.md` — Overview
2. `ASSIGNMENT.md` — Requirements
3. `QUICKSTART.md` — Setup instructions
4. `STUDENT_CHECKLIST.md` — Track progress

---

## At a Glance

| Aspect | Details |
|--------|---------|
| **Primary Patterns** | Builder + Prototype |
| **Supporting Patterns** | Abstract Factory + Factory Method |
| **Domain** | RPG Enemy System |
| **Time Required** | 5-6 days (24-32 hours) |
| **Difficulty** | High (minimal scaffolding, capstone level) |
| **Deliverables** | Code + 2 UML diagrams |
| **Points** | 100 total (70 patterns, 10 quality, 15 UML, 5 demo) |
| **Target Audience** | Students who completed HW1 (Factory patterns) |
| **Student Work** | ~25-30 Java classes + 2 UML diagrams |
| **Build Tool** | None (plain Java, IDE-friendly) |
| **Prerequisite** | Understanding of Factory Method & Abstract Factory from HW1 |

---

## Key Features

- **Creational Patterns Capstone** — All 4 creational patterns in one unified system
- **Minimal Scaffolding** — Only interfaces and 2 examples provided
- **Pain-Driven Learning** — DragonBoss's 15-parameter constructor teaches Builder motivation
- **Deep Copy Challenge** — Prototype pattern forces students to handle complex object graphs
- **HW1 Reinforcement** — Abstract Factory + Factory Method appear in new context
- **Comprehensive Documentation** — 8 files answering all common questions
- **Engaging Domain** — RPG enemy system (fun and creative)

---

## Learning Objectives

After completing this assignment, students will:
- Understand **why** Builder pattern exists (telescoping constructors)
- Handle **deep vs shallow copy** correctly (Prototype)
- Apply **Abstract Factory** in a new context (themed enemy components)
- Recognize **Factory Method** embedded in Builder/Director
- **Compare all 4 creational patterns** — know when to use each
- Design systems where **multiple patterns complement each other**

---

## What Makes This a Capstone

```
HW1: Factory Method + Abstract Factory
     (Learn: How to create different types of objects)
         |
         v
HW2: Builder + Prototype + Abstract Factory + Factory Method
     (Master: ALL creational patterns working together)
         |
         v
Result: Complete creational pattern mastery
```

Students who complete both assignments can confidently answer:
> "I need to create objects. Which pattern should I use?"

---

**Created for**: Narxoz University - Software Design Patterns Course
**Type**: Creational Patterns Capstone
**Domain**: RPG Enemy System
**Status**: Ready to use

# Princess Academy : Worlds Collide
## Project created by **Group Charmees**
![Boss Silhouette](https://github.com/LarzyArt/OOP_FinalProject_PrincessAcademy/blob/main/for_readme.png)

Princess Academy is a 2D story-driven RPG built using OOP principles for CSIT227 Final Project.

The game features a chapter-based narrative, visual dialoguem and turn-based battles with unique character skills and enemy encounters.

---

## Game Overview
Princess Academy is a narrative-focused RPG where the player follows a group of characters progressing through six chapters. Each chapter focuses on a specific character introducing their story involvement and character development. Each chapter introduces new environments, enemies, battles.

## Core Features

### **Story & Chapters**

- Six fully Scripted chapters
- Prologue and Epilogue Stories
- Visual style dialogue system
- Dynamic background switching per chapter
- Smooth narrative progression


### **Turn-Based Battle System**

- Follows Phased-Based/Group-Based Battle System
- Available actions: **Attack, Skill, Heal, Target Enemy**
- Skills and Status Effects
- Win/Loss condition based on HP


### **Character System**

- Each character has: **HP, MP, Class**
- Each character has unique set of skills

### **Enemy System**

- Enemy mobs are skill focused
- Each chapter has one miniboss and two mobs
- chapter 6 is a special case with only one boss

---

## User Interface

- Built with **Java Swing**
- Custom pixel-style font
- Buttons for skills and interaction
- Battle UI with enemy selection
- Animated Main Menu(GIF)
- Custom Backgrounds 

---

## Object-Oriented Structure
Key OOP principles implemented:

- **Encapsulation**: HP, MP, skills and behaviors stored inside classes
- **Inheritance**: Characters extends 'GameCharacter';
- **Polymorphism**: Skills behave differently per character
- **Abstraction**: GameCharacter and MobNPC classes hiding complex methods

## Exception Handling
The game uses try-catch blocks for:
- Image, font 
- Missing resources
- Ui and thread-related errors
- Ensures the game does not crash due to missing assets.

---

# Team Members
**Programmers:**
- Baraga, Laurence
- Daydayan, Kimberly
- Ecarma, Melody
- Pedrosa, Neilcen
- Rodrigo, Abigail


**Art:**
 - Larz (Main menu, splashscreen, dialogue)
 - Everyone (Battle Chapters)


 **Story & Writing:**
- Lynzy_002 (AKA: 2021-2022 Larz)

---

## License
This project is for academic and educational purposes.

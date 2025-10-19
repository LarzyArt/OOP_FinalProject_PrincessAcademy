import java.util.Scanner;

//Everyone contributed to making this file and methods
public class Game {
    public static void main(String[] args) {
        Scanner skillinput = new Scanner(System.in);
        Character[] characters = {
            new Character("Audry", "Assassin", "Melee", "Acidic Slime", 100, 25),
            new Character("Giantha", "Tank", "Melee", "World Tree Branch", 250, 20),
            new Character("Lazuli", "Healer", "Ranged", "Staff", 150, 30),
            new Character("Lynzi", "Dealer", "Melee", "Star Magic", 170, 30),
            new Character("Shiera", "Support", "Ranged", "Earth Magic", 120, 25)
        };
        MobNPC[] mobs = {
            //Boss
            new MobNPC("Twinkle", "Boss", "Melee", "Puppet", 500),
            //Minibosses
            new MobNPC("Kassundre", "Miniboss", "Ranged", "Dark Magic", 300),
            new MobNPC("Lava Beast", "Miniboss", "Melee", "Fire Magic", 350),
            new MobNPC("Siren Empress", "Miniboss", "Ranged", "Water Magic", 320),
            new MobNPC("Resonara", "Miniboss", "Ranged", "Sound Magic", 310),
            new MobNPC("Eclipse Core", "Miniboss", "Melee", "Astral Magic", 350),
            //Mobs
            new MobNPC("Student Puppet", "Minion", "Melee", "Wooden Sword", 100),
            new MobNPC("Corrupted Skeleton", "Minion", "Melee", "Bone Sword", 120),
            new MobNPC("Water Sprite", "Minion", "Ranged", "Water Magic", 130),
            new MobNPC("Echo Imp", "Minion", "Ranged", "Sound Magic", 130),
            new MobNPC("Astral Glob", "Minion", "Melee", "Astral Slime", 110),
            new MobNPC("Princess Puppet", "Minion", "Melee", "wand", 100,5),
            new MobNPC("Magma Skeleton", "Minion", "Melee", "Bone Sword", 120,2),
            new MobNPC("Water Blob", "Minion", "Ranged", "Water Magic", 130,1),
            new MobNPC("Resonance Goblin", "Minion", "Ranged", "Sound Magic", 130, 4),
            new MobNPC("Moon Sprite", "Minion", "Melee", "Astral magic", 110, 3),
        };
        Credits[] credits = {
            new Credits("Laurence Baraga", "Developer", "Student", "Pencil", 2, 1),
            new Credits("Kimberly Daydayan","Developer", "Student", "Crochet Hookja", 50, 50),
            new Credits("Neilcen Pedrosa", "Developer", "Student", "World Tree Branch", 250, 20),
            new Credits("Abigail Rodrigo", "Developer", "Student", "Staff", 150, 30),
            new Credits("Melody Ecarma", "Developer", "Student", "Star Magic", 170, 30),
        };

        GameSystem gameSystem = new GameSystem(characters, mobs, credits, skillinput);
        gameSystem.MainMenu();

    skillinput.close();
    }
}

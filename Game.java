import java.util.Scanner;

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
            new MobNPC("Twinkle", "Boss", "Melee", "Puppet", 500),
            new MobNPC("Kassundre", "Miniboss", "Ranged", "Dark Magic", 300),
            new MobNPC("Student Puppet", "Minion", "Melee", "Wooden Sword", 100)
        };

        BattleSystem battleSystem = new BattleSystem(characters, mobs, skillinput);
        battleSystem.MainMenu();
        
    skillinput.close();
    }
}

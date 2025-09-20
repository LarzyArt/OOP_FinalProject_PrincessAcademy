import java.util.Scanner;

public class BattleSystem {
    private Character[] characters;
    private Scanner skillInput;

    public BattleSystem(Character[] characters, Scanner skillInput) {
        this.characters = characters;
        this.skillInput = skillInput;
    }

    public void startBattle() {
        boolean continueBattle = true;

        while (continueBattle) {
            for (Character character : characters) {
                character.showStats();

                boolean validChoice = false;

                while (!validChoice) {
                    System.out.println("Choose a skill for " + character.name + ":");
                    switch (character.name.toLowerCase()) {
                        case "audry":
                            System.out.println("1. Slime Bounce");
                            System.out.println("2. Acid Shot");
                            System.out.println("3. Beyond the Abyss");
                            break;
                        case "giantha":
                            System.out.println("1. Giant Punch");
                            System.out.println("2. Giant's Roar");
                            System.out.println("3. Club Smash");
                            break;
                        case "lazuli":
                            System.out.println("1. Basic Heal");
                            System.out.println("2. Ocean's Blessing");
                            System.out.println("3. Harmonic Wave");
                            break;
                        case "lynzi":
                            System.out.println("1. Majestic Kick");
                            System.out.println("2. Galactic Fist");
                            System.out.println("3. Meteoric Smash");
                            break;
                        case "shiera":
                            System.out.println("1. Stone Spikes");
                            System.out.println("2. Earth Wall");
                            System.out.println("3. Iron Maiden");
                            break;
                    }

                    System.out.print("Enter skill number (1-3): ");
                    int skill = skillInput.nextInt();

                    if (skill >= 1 && skill <= 3) {
                        character.useSkill(skill);
                        validChoice = true;
                    } else {
                        System.out.println("Invalid choice! Try again.");
                    }
                }

                System.out.println();
            }

            System.out.println("All characters have used their skills this turn.");
            System.out.print("Continue to next turn? (yes/no): ");
            String nextTurn = skillInput.next();

            if (nextTurn.equalsIgnoreCase("no")) {
                continueBattle = false;
                System.out.println("Battle ended.");
            }
        }
    }
}

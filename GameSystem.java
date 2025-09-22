import java.util.Scanner;


public class GameSystem {
    private Character[] characters;
    private MobNPC[] mobs;
    private Scanner skillInput;


    public GameSystem(Character[] characters,MobNPC[] mobs, Scanner skillInput) {
        this.characters = characters;
        this.mobs = mobs;
        this.skillInput = skillInput;
    }
    public void MainMenu(){
        int choice = 0;
        //Basically menu when you start the game
        while(choice != 3){
            System.out.println("==== Main Menu ====\n");
            System.out.println("What do you want to do?");
            System.out.println("1. Start Game");
            System.out.println("2. Show Stats");
            System.out.println("3. Exit");
            System.out.println("====================\n");
            System.out.print("Enter next Action: ");

                choice = skillInput.nextInt();
                    if(choice == 1){ //Attacks
                        System.out.println("\nAttack function is non existent");
                        System.out.println("Returning to main menu...");
                        }else if(choice == 2){ //Shows Stats
                            System.out.println();
                            showStats();
                        }else if(choice == 3){ //CLoses Program
                            System.out.println("Exiting game...");
                        }else{
                            System.out.println("Invalid choice. Returning to main menu.");
                    }
                    System.out.println();
        }
    }
    public void showStats(){
        // Show stats for characters or mobs based on user input
        System.out.println("Do you want to see Character or Mob Stats? (c/m)");
        System.out.print("Enter choice: ");

            char choice = skillInput.next().toLowerCase().charAt(0);
            System.out.println();
            // c for characters
            if (choice == 'c') { 
                System.out.println("Choose a character to view stats:");
                System.out.println("====================");
                for (int i = 0; i < characters.length; i++) {
                System.out.println((i + 1) + ". " + characters[i].getName());
                }
                System.out.println("====================");
            // Get user input for character choice
            System.out.print("Enter Character (1 - 5): ");
            int charchoice = skillInput.nextInt();
            System.out.println();
            if (charchoice >= 1 && charchoice <= characters.length) {
                characters[charchoice - 1].showStats();
            } else {
                System.out.println("Invalid choice. \nChoose a valid character: (1 - 5)\n");
            }
            } // m for mobs
             else if (choice == 'm') {
                    System.out.println("Choose a mob to view stats:");
                    System.out.println("====================");
                    for (int i = 0; i < mobs.length; i++) {
                    System.out.println((i + 1) + ". " + mobs[i].getName());
                }
                System.out.println("====================");
                // Get user input for mob choice
                System.out.print("Enter Mob (1 - 5): ");
                int mobchoice = skillInput.nextInt();
                System.out.println();
                if (mobchoice >= 1 && mobchoice <= mobs.length) {
                    mobs[mobchoice - 1].showStats();
                } else {
                    System.out.println("Invalid choice. \nChoose a valid mob: (1 - 5)\n");
                }
            } else {
                System.out.println("Invalid choice.");
            } // Ask if they want to view another stats
            System.out.println("====================");
            System.out.println("Do you want to view another stats? (y/n)");
            System.out.println("====================");
            System.out.print("Enter choice: ");
            char again = skillInput.next().toLowerCase().charAt(0);
            if (again == 'y') {
                showStats();
            } else {
                System.out.println("Exiting stats view...");
            }
    }
}

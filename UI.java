import java.util.Scanner;

public class UI {
    protected Character[] characters;
    protected MobNPC[] mobs;
    protected Credits[] credits;
    protected Scanner skillInput;


    public UI(Character[] characters, MobNPC[] mobs,Credits[] credits, Scanner skillInput) {
        this.characters = characters;
        this.mobs = mobs;
        this.credits = credits;
        this.skillInput = skillInput;
    }

    public void displayMenu(){
        System.out.println("==== *Princess Academy: Worlds Collide* ====\n");
        System.out.println("What do you want to do?");
        System.out.println("1. Play Story Mode");
        System.out.println("2. Character/Mob Stats");
        System.out.println("3. Credits");
        System.out.println("4. Exit");
        System.out.println();
        System.out.println("====================");
        System.out.print("Enter next Action: ");
    }

    public void prompt(int choice){
        if(choice == 1){ // Starts Game
                StartGame();
                // Wait for the user to press Enter so the menu doesn't immediately reappear

                try {
                    skillInput.nextLine();
                } catch (Exception e) {

                }
            }else if(choice == 2){ // Shows Stats
                System.out.println();
                showStats();
            }else if(choice == 3){ // Shows Credits
                ShowCredits();

            }else if(choice == 4){ // CLoses Program
                System.out.println("Exiting game...");
            }else{
                System.out.println("Invalid choice. Returning to main menu.");
        }
        System.out.println();
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
                System.out.print("Enter Mob (1 - 11): ");
                int mobchoice = skillInput.nextInt();
                System.out.println();
                if (mobchoice >= 1 && mobchoice <= mobs.length) {
                    mobs[mobchoice - 1].showStats();
                } else {
                    System.out.println("Invalid choice. \nChoose a valid mob: (1 - 11)\n");
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
                System.out.println();
                showStats();
            } else {
                System.out.println("Exiting stats view...");
            }
    }
    public void ShowCredits(){
        System.out.println("\n---- Credits ----\n");
            for (Credits credit : credits) {
            credit.ShowCredits();
        }
        System.out.println("\n========================");
        System.out.print("Press x to exit: ");
        char opts = skillInput.next().charAt(0);
            if(opts == 'x'){
                return;
            }else {
                while(opts != 'x'){
                System.out.println("Invalid input...\n");
                System.out.println("========================");
                System.out.print("Press x to exit: ");
                opts = skillInput.next().charAt(0);
                System.out.println("Returning to main menu...\n");

            }
        }
    }

    public void StartGame(){
        Dialogue dialogue = new Dialogue();
        int chapter = -1;
        System.out.println("==== Story Mode ====\n");
        System.out.println("Chapter 1: Battle of the Sea");
        System.out.println("Chapter 2: The Survival");
        System.out.println("Chapter 3: Darkness and Void");
        System.out.println("Chapter 4: Heart of the Living Forest");
        System.out.println("Chapter 5: The Castle Memoir");
        System.out.println("Chapter 6: The Final Show");
        System.out.println("====================");
        System.out.println("Enter Chapter Number to play (1-6), or 0 to return to the main menu.");
        System.out.print("Enter Action: ");

        try {
            chapter = skillInput.nextInt();
        } catch (Exception e) {
            System.out.println();
            // invalid input, consume the token and return
            skillInput.next();
            System.out.println("Invalid input. Returning to main menu...");
            return;
        }

        if (chapter == 0) {
            System.out.println("Returning to main menu...");
            return; // return to menu
        }

        if (chapter >= 1 && chapter <= 6) {
            System.out.println("Starting Chapter " + chapter + "...");
        } else {
            System.out.println("Invalid chapter. Returning to main menu...");
        }
        switch(chapter){
            case 1:
                dialogue.sirenEmpressInteraction();
                dialogue.sirenEmpressDefeated();
                break;
            case 2:
                dialogue.lavaBeastInteraction();
                dialogue.lavaBeastDefeated();
                break;

            case 3:
                dialogue.eclipseCoreInteraction();
                dialogue.eclipseCoreDefeated(); 
                break;
            case 4:
                dialogue.resonaraInteraction();
                dialogue.resonaraDefeated();
                break;
            case 5:
                dialogue.kassundreInteraction();
                dialogue.kassundreDefeated();
                break;
            case 6:
                dialogue.twinkleInteraction();
                dialogue.twinkleDefeated();
                break;
        }

        try{
            System.out.println("Press Enter to return to main menu...");
            skillInput.nextLine();
        }
        catch(Exception e){
            System.out.println();
            System.out.println("Returning to main menu...");
        }
    }
}

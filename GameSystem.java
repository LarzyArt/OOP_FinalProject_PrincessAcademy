//Everyone contributed to making this file and methods
import java.util.Scanner;

public class GameSystem {
    protected Character[] characters;
    protected MobNPC[] mobs;
    protected Credits[] credits;
    protected Scanner skillInput;


    public GameSystem(Character[] characters, MobNPC[] mobs,Credits[] credits, Scanner skillInput) {
        this.characters = characters;
        this.mobs = mobs;
        this.credits = credits;
        this.skillInput = skillInput;
    }
    public void MainMenu(){
        int choice = 0;
        UI menu = new UI(characters, mobs,credits, skillInput);
        while(choice != 4){
                menu.displayMenu();
                choice = skillInput.nextInt();
                System.out.println();
                menu.prompt(choice);
        }
    }
}

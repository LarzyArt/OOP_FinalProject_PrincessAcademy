import java.util.Scanner;
//
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
    //
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

    //Baraga contribution
    

    //Daydayan contribution
    

    //Pedrosa contribution
    

    //Baraga contribution
    
}

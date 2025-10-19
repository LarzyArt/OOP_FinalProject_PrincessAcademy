import java.util.Scanner;
//Everyone contributed to making this file and methods
public class UI {
    protected Character[] characters;
    protected MobNPC[] mobs;
    protected Credits[] credits;
    protected Scanner skillInput;
    protected int currentChapter = -1;
    // flag set when player requests to abort the current battle and return to the menu
    protected boolean abortBattle = false;
    // flag set when the last battle was aborted so StartGame can skip post-battle dialogues
    protected boolean lastBattleAborted = false;


    public UI(Character[] characters, MobNPC[] mobs,Credits[] credits, Scanner skillInput) {
        this.characters = characters;
        this.mobs = mobs;
        this.credits = credits;
        this.skillInput = skillInput;
    }
    //Everyone contributed
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

    //Baraga contribution: prompt method to handle menu choices
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

    //Daydayan contribution
    

    //Pedrosa contribution
    

    //Baraga contribution: StartGame method to handle story mode (currently unfinished)
    public void StartGame(){
        Dialogue dialogue = new Dialogue();
        int chapter = -1;
        boolean tag2 = false;
        boolean tag3 = false;
        boolean tag4 = false;
        boolean tag5 = false;
        boolean tag6 = false;

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
            this.currentChapter = chapter;
        } else {
            System.out.println("Invalid chapter. Returning to main menu...");
        }
        switch(chapter){
            case 1:
                dialogue.sirenEmpressInteraction();
                BattleHUD();
                StartBattle(characters, mobs);// enemies take their turn and attack alive characters
                if (lastBattleAborted) { lastBattleAborted = false; break; }
                if (!isChapterEnemyAlive(chapter)) {
                    tag2 = true;
                    dialogue.sirenEmpressDefeated();
                } else dialogue.playerDefeated();
                break;
            case 2:
                if(tag2 == false){
                    System.out.println("\n====================");
                    System.out.println("[Error]: You have not finished chapter 1.");
                    System.out.println("Finish chapter 1 to unlock chapter 2.\n");
                }else{
                    dialogue.lavaBeastInteraction();
                    BattleHUD();
                    StartBattle(characters, mobs);// enemies take their turn and attack alive characters
                    if (lastBattleAborted) { lastBattleAborted = false; break; }
                    if (!isChapterEnemyAlive(chapter)) {
                        tag3 = true;
                        dialogue.lavaBeastDefeated();
                    } else dialogue.playerDefeated();
                }
                break;
            case 3:
                if(tag3 == false){
                    System.out.println("[Error]: You have not finished chapter 1.");
                    System.out.println("Finish chapter 1 to unlock chapter 2\n");
                }else{
                    dialogue.eclipseCoreInteraction();
                    BattleHUD();
                    StartBattle(characters, mobs);// enemies take their turn and attack alive characters
                    if (lastBattleAborted) { lastBattleAborted = false; break; }
                    if (!isChapterEnemyAlive(chapter)) {
                        tag4 = true;
                        dialogue.eclipseCoreDefeated(); 
                    } else dialogue.playerDefeated();
                }
                break;
            case 4:
                if(tag4 == false){
                    System.out.println("[Error]: You have not finished chapter 1.");
                    System.out.println("Finish chapter 1 to unlock chapter 2\n");
                }else{
                    dialogue.resonaraInteraction();
                    BattleHUD();
                    StartBattle(characters, mobs);// enemies take their turn and attack alive characters
                    if (lastBattleAborted) { lastBattleAborted = false; break; }
                    if (!isChapterEnemyAlive(chapter)) {
                        tag5 = true;
                        dialogue.resonaraDefeated();
                    } else dialogue.playerDefeated();
                }
                break;
            case 5:
                if(tag5 == false){
                    System.out.println("[Error]: You have not finished chapter 1.");
                    System.out.println("Finish chapter 1 to unlock chapter 2\n");
                }else{
                    dialogue.kassundreInteraction();
                    BattleHUD();
                    StartBattle(characters, mobs);// enemies take their turn and attack alive characters
                    if (lastBattleAborted) { lastBattleAborted = false; break; }
                    if (!isChapterEnemyAlive(chapter)) {
                        tag6 = true;
                        dialogue.kassundreDefeated();
                    } else dialogue.playerDefeated();
                }
                break;
            case 6:
                if(tag6 == false){
                    System.out.println("[Error]: You have not finished chapter 1.");
                    System.out.println("Finish chapter 1 to unlock chapter 2\n");
                }else{
                    dialogue.twinkleInteraction();
                    BattleHUD();
                    StartBattle(characters, mobs);// enemies take their turn and attack alive characters
                    if (lastBattleAborted) { lastBattleAborted = false; break; }
                    if (!isChapterEnemyAlive(chapter)) {
                        dialogue.twinkleDefeated();
                    } else dialogue.playerDefeated();
                }
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

    //returns true if any enemy for the given chapter is alive
    private boolean isChapterEnemyAlive(int chapter) {
        for (MobNPC enemy : mobs) {
            if (enemy.chapter != chapter) continue;
            if (enemy.isAlive()) return true;
        }
        return false;
    }
    //check if battle is ongoing
    private boolean isBattleOngoing(){
        boolean partyAlive = false, enemiesAlive = false;
        for (Character party : characters) if (party.isAlive()) partyAlive = true;
        // only count enemies that belong to the current chapter
        for (MobNPC enemies : mobs) if (enemies.chapter == this.currentChapter && enemies.isAlive()) enemiesAlive = true;
        return partyAlive && enemiesAlive;
    }

    //Screen for when battle starts
    public void BattleHUD(){
        System.out.println("\n===============================");
        System.out.println("         BATTLE STATUS");
        System.out.println("===============================");

        //party stats
        System.out.println("\nParty:");
        for (Character party : characters) {
            if (party.isAlive())
                System.out.println(party.name + " | HP: " + party.healthPoints + " | MP: " + party.manaPoints);
            else
                System.out.println(party.name + " | has been Defeated!!!");
        }

        //Everyone contributed via Discord VC
        //mob stats
        System.out.println("\nEnemies: ");
        for (MobNPC enemies : mobs) {
            // only show enemies for the active chapter
            if (enemies.chapter != this.currentChapter) continue;
            if (enemies.isAlive())
                System.out.println(enemies.name + " | HP: " + enemies.healthPoints);
            else
                System.out.println(enemies.name + " | has been Defeated!!!");
                
        }
        System.out.println("===============================");
    }
    
    //Everyone Contributed via Discord VC
    //turn based battle here
    public void StartBattle(Character[] characters, MobNPC[] mobs){
        int turnCount = 1;
        abortBattle = false;
        lastBattleAborted = false;
        while (isBattleOngoing()) {
            System.out.println("\n========== TURN " + turnCount + " ==========");
            // enemy phase: each alive enemy in the current chapter attacks a random alive character
            characterPhase();
            if (abortBattle) {
                System.out.println("Battle aborted. Returning to main menu...");
                lastBattleAborted = true;
                abortBattle = false;
                break;
            }
                // enemy phase: each alive enemy in the current chapter attacks a random alive character
                enemyPhase();
            // show status after the turn
            BattleHUD();
            // tick status durations for characters and mobs
            for (Character c : characters) c.tickStatus();
            for (MobNPC m : mobs) m.tickStatus();
            turnCount++;
            if (turnCount > 1000) break;
        }
    }

}

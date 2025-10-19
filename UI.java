import java.util.ArrayList;
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
    

    //Pedrosa contribution
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

    //Everyone Contributed via Discord and Messenger VC
    // enemies attack: each alive enemy in the active chapter uses a random skill on a random alive character
    private void enemyPhase() {
          ArrayList<Integer> aliveCharacterIndexes = new java.util.ArrayList<>();
        for (int i = 0; i < characters.length; i++) {
            if (characters[i].isAlive()) aliveCharacterIndexes.add(i);
        }
        if (aliveCharacterIndexes.isEmpty()) return; // no targets

        for (MobNPC enemy : mobs) {
            if (enemy.chapter != this.currentChapter) continue;
            if (!enemy.isAlive()) continue;

            // if monster is stunned, they skip their action
            if (enemy.isStunned()) {
                System.out.println(enemy.getName() + " is stunned and cannot act this turn.");
                continue;
            }

            // prefer taunted party members if any
            ArrayList<Integer> tauntedTargets = new ArrayList<>();
            for (int i = 0; i < characters.length; i++) {
                if (characters[i].isAlive() && characters[i].tauntTurns > 0) tauntedTargets.add(i);
            }

            int randIndex = -1;
            if (!tauntedTargets.isEmpty()) {
                int pick = (int) (Math.random() * tauntedTargets.size());
                randIndex = tauntedTargets.get(pick);
            } else {
                int randomListIndex = (int) (Math.random() * aliveCharacterIndexes.size());
                randIndex = aliveCharacterIndexes.get(randomListIndex);
            }
            Character target = characters[randIndex];

            // pick a random skill between 1 and 3 (most mobs define 1-3)
            int skill = (int) (Math.random() * 3) + 1;

            System.out.println();
            // handle lava beast AoE case explicitly
            if (enemy.getName().equalsIgnoreCase("lava beast") && skill == 3) {
                int damage = (int)(Math.random() * 31) + 20;
                System.out.println(enemy.getName() + " used Corrupted Eruption! Deals " + damage + " damage to all party members!");
                for (Character c : characters) {
                    if (c.isAlive()) c.takedamage(damage);
                }
            } else {
                enemy.useSkill(skill, target);
            }

            // remove target from list if they died
            if (!target.isAlive()) {
                // rebuild aliveCharacterIndexes to avoid duplicates and removed dead targets
                aliveCharacterIndexes.clear();
                for (int i = 0; i < characters.length; i++) {
                    if (characters[i].isAlive()) aliveCharacterIndexes.add(i);
                }
                if (aliveCharacterIndexes.isEmpty()) return; // all characters dead
            }
        }
    }

    //Everyone contributed via Discord and messenger VC
    //character attack: character attacks via user input and heals  via user input
    private void characterPhase() {
        ArrayList<Integer> aliveEnemyIndexes = new ArrayList<>();
        // collect indices of mobs that belong to the current chapter and are alive
        for (int i = 0; i < mobs.length; i++) {
            if (mobs[i].chapter == this.currentChapter && mobs[i].isAlive()) {
                aliveEnemyIndexes.add(i);
            }
        }
        if (aliveEnemyIndexes.isEmpty()) return; // no targets

        for (Character party : characters) {
            if (!party.isAlive()) continue;
            // if the character is stunned, they skip their action this turn
            if (party.isStunned()) {
                System.out.println(party.getName() + " is stunned and cannot act this turn.");
                continue;
            }
            // if the character is stunned, they skip their action this turn

            //displayss character info
            System.out.println("\n" + party.name + " | HP: " + party.healthPoints + " | MP: " + party.manaPoints);
            System.out.println("===============================");

            // choose skill first
            // show skill list for this character
            int skill = -1;
            String[] skills = party.getSkillList();
            if (skills.length == 0) {
                System.out.println("\n" + party.getName() + " has no usable skills. Skipping action for this character.");
                continue; // skip to next character
            }
            System.out.println("\n" + party.getName() + " Skill list:");
            for (String s : skills) System.out.println(s);
            System.out.println("===============================");
            while (true) {
                System.out.print("Choose skill for " + party.getName() + " (1-" + skills.length + "): ");
                String tok = skillInput.next();
                if (tok.equalsIgnoreCase("q")) { abortBattle = true; return; }
                try {
                    int pick = Integer.parseInt(tok);
                    if (pick >= 1 && pick <= skills.length) {
                        skill = pick;
                        break;
                    }
                } catch (NumberFormatException ex) {
                }
                System.out.println("Invalid skill. Try again.");
            }

            String targetType = party.getSkillTargetType(skill);
            MobNPC target = null;
            Character ally = null;

            if (targetType.equals("ENEMY")) {
                // display enemy targets and choose one
                System.out.println("\nChoose target for " + party.getName() + ":");
                System.out.println("===============================");
                for (int i = 0; i < aliveEnemyIndexes.size(); i++) {
                    MobNPC m = mobs[aliveEnemyIndexes.get(i)];
                    System.out.println((i + 1) + ". " + m.getName() + " | HP: " + m.healthPoints);
                }
                System.out.println("===============================");
                int chosenEnemyIndex = -1;
                while (true) {
                    System.out.print("Choose target (1-" + aliveEnemyIndexes.size() + "): ");
                    String tok = skillInput.next();
                    if (tok.equalsIgnoreCase("q")) { abortBattle = true; return; }
                    try {
                        int pick = Integer.parseInt(tok);
                        if (pick >= 1 && pick <= aliveEnemyIndexes.size()) {
                            chosenEnemyIndex = aliveEnemyIndexes.get(pick - 1);
                            break;
                        }
                    } catch (NumberFormatException ex) {
                    }
                    System.out.println("Invalid choice. Try again.");
                }
                target = mobs[chosenEnemyIndex];
                System.out.println("\n" + party.getName() + " uses skill " + skill + " on " + target.getName() + "...");
                party.useSkill(skill, target, party, characters);
            } else if (targetType.equals("ALLY")) {
                // choose an alive ally (including self)
                ArrayList<Integer> aliveAllyIndexes = new ArrayList<>();
                for (int i = 0; i < characters.length; i++) {
                    if (characters[i].isAlive()) aliveAllyIndexes.add(i);
                }
                if (aliveAllyIndexes.isEmpty()) {
                    System.out.println("No alive allies to target. Skill canceled.");
                } else {
                    System.out.println("\nChoose ally target for " + party.getName() + ":");
                    for (int i = 0; i < aliveAllyIndexes.size(); i++) {
                        Character c = characters[aliveAllyIndexes.get(i)];
                        System.out.println((i + 1) + ". " + c.getName() + " | HP: " + c.healthPoints + " | MP: " + c.manaPoints);
                    }
                    int chosenAllyIndex = -1;
                    while (true) {
                        System.out.print("Choose ally (1-" + aliveAllyIndexes.size() + "): ");
                        String tok = skillInput.next();
                        if (tok.equalsIgnoreCase("q")) { abortBattle = true; return; }
                        try {
                            int pick = Integer.parseInt(tok);
                            if (pick >= 1 && pick <= aliveAllyIndexes.size()) {
                                chosenAllyIndex = aliveAllyIndexes.get(pick - 1);
                                break;
                            }
                        } catch (NumberFormatException ex) {
                        }
                        System.out.println("Invalid choice. Try again.");
                    }
                    ally = characters[chosenAllyIndex];
                    System.out.println(party.getName() + " uses skill " + skill + " on ally " + ally.getName() + "...");
                    party.useSkill(skill, null, ally, characters);
                }
            } else if (targetType.equals("ALL") || targetType.equals("SELF")) {
                // 'ALL' affects all allies; 'SELF' affects self (pass party as ally)
                System.out.println(party.getName() + " uses skill " + skill + "...");
                party.useSkill(skill, null, party, characters);
            } else {
                // default fallback treat as enemy-target
                int chosenEnemyIndex = -1;
                while (true) {
                    System.out.print("Choose Enemy (1-" + aliveEnemyIndexes.size() + "): ");
                    String tok = skillInput.next();
                    if (tok.equalsIgnoreCase("q")) { abortBattle = true; return; }
                    try {
                        int pick = Integer.parseInt(tok);
                        if (pick >= 1 && pick <= aliveEnemyIndexes.size()) {
                            chosenEnemyIndex = aliveEnemyIndexes.get(pick - 1);
                            break;
                        }
                    } catch (NumberFormatException ex) {
                    }
                    System.out.println("Invalid choice. Try again.");
                }
                target = mobs[chosenEnemyIndex];
                System.out.println(party.getName() + " uses skill " + skill + " on " + target.getName() + "...");
                party.useSkill(skill, target, party, characters);
            }

            // if the target exists and died, rebuild the list of alive enemies for the chapter
            if (target != null && !target.isAlive()) {
                aliveEnemyIndexes.clear();
                for (int i = 0; i < mobs.length; i++) {
                    if (mobs[i].chapter == this.currentChapter && mobs[i].isAlive()) {
                        aliveEnemyIndexes.add(i);
                    }
                }
                if (aliveEnemyIndexes.isEmpty()) return; // all enemies dead
            }
        }
    }


}

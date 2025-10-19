//Everyone contributed to making this file and methods
public class MobNPC {
    String name;
    String charClass;
    String type;
    String weapon;
    int healthPoints;
    int chapter;
    //Status effects
    int stunnedTurns = 0;
    int tauntTurns = 0; // when >0, this mob will prefer targeting the taunting character (handled by UI)
    int damageReductionTurns = 0;
    double damageReductionPercent = 0.0;
    //Mobs don't use mana
    //int manaPoints;

    public MobNPC(String name, String charClass, String type, String weapon, int healthPoints, int chapter) {
        this.name = name;
        this.charClass = charClass;
        this.type = type;
        this.weapon = weapon;
        this.healthPoints = healthPoints;
        this.chapter = chapter;
    }

    public void takedamage(int damage){
        // apply damage reduction if active
        if (damageReductionTurns > 0 && damageReductionPercent > 0.0) {
            int reduced = (int) Math.round(damage * (1.0 - damageReductionPercent));
            damage = Math.max(0, reduced);
        }
        healthPoints -= damage;
        if (healthPoints <= 0) {
            healthPoints = 0;
            System.out.println(name + " took " + damage + " damage! Remaining HP: " + healthPoints);
            System.out.println(name + " HP has dropped to 0! It has been defeated!");
        } else {
            System.out.println(name + " took " + damage + " damage! Remaining HP: " + healthPoints);
        }
    }
    
    public boolean isAlive(){
        return healthPoints > 0;
    }

    public String getName() {
        return name;
    }

    // status helpers
    public boolean isStunned() { return stunnedTurns > 0; }
    public void applyStun(int turns) { stunnedTurns = Math.max(stunnedTurns, turns); System.out.println(name + " is stunned for " + stunnedTurns + " turn(s)."); }
    public void applyTaunt(int turns) { tauntTurns = Math.max(tauntTurns, turns); System.out.println(name + " is taunted for " + tauntTurns + " turn(s)."); }
    public void applyDamageReduction(double percent, int turns) { damageReductionPercent = percent; damageReductionTurns = Math.max(damageReductionTurns, turns); System.out.println(name + " has damage reduction for " + damageReductionTurns + " turn(s)."); }
    public void tickStatus() {
        if (stunnedTurns > 0) stunnedTurns--;
        if (tauntTurns > 0) tauntTurns--;
        if (damageReductionTurns > 0) {
            damageReductionTurns--;
            if (damageReductionTurns == 0) damageReductionPercent = 0.0;
        }
    }

    //=================== Skill System ==================
    //To use skills 
    // Ecarma contribution
    public void useSkill(int skill, Character target) {
        switch (name.toLowerCase()) {
            //Boss
            case "twinkle":
                twinkleSkills(skill, target);
                break;
            //MiniBosses
            case "kassundre":
                kassundreSkills(skill, target);
                break;
            case "lava beast":
                lavaBeastSkills(skill, target);
                break;
            case "siren empress":
                sirenEmpressSKills(skill, target);
                break;
            case "Resonara":
                resonaraSkills(skill, target);
                break;
            case "eclipse core":
                eclipseCoreSkills(skill, target);
                break;
            //Mobs
            case "student puppet":
                studentPuppetSkills(skill, target);
                break;
            case "corrupted skeleton":
                corruptedSkeletonSkills(skill, target);
                break;
            case "water sprite":
                waterSpriteSKills(skill, target);
                break;
            case "echo imp":
                echoImpSkills(skill, target);
                break;
            case "astral glob":
                astralGlobSkills(skill, target);
                break;
            case "Princess Puppet":
                princessPuppetSkills(skill, target);
                break;
            case "Magma Skeleton":
                magmaSkeletonSkills(skill, target);
                break;
            case "water blob":
                waterblobSKills(skill, target);
                break;
            case "Resonance Goblin":
                resonanceGoblinSkills(skill, target);
                break;
            case "Moon sprite":
                moonSpriteSkills(skill, target);
                break;    
            default:
                System.out.println(name + " has no skills defined!");
        }
    }

    //-------------------------------- Character Skills ------------------------------
    // -------------------- Final Boss --------------------
    //Baraga contribution: Twinkle's Skills
    // ----------------- Twinkle's Skills -----------------
    private void twinkleSkills(int skill, Character target) {
        //Boss fight skills
        int damage = 0;
        switch (skill) {
            case 1: // Puppet Slash
                    damage = (int)(Math.random() * 26) + 5;
                    target.takedamage(damage);
                    System.out.println(name + " used Puppet Slash! Deals " + damage + " damage.");
            case 2: // Lazer Devastation
                    int hits = (int)(Math.random() * 2) + 1;
                    damage = 0;
                    for (int i = 0; i < hits; i++) {
                        damage += 20;
                    }
                    target.takedamage(damage);
                    System.out.println(name + " used Lazer Devastation! Hits " + hits + " times for " + damage + " damage.");
                break;
            case 3: // Corruption
                    damage = 60;
                    target.takedamage(damage);
                    System.out.println(name + " used Corruption! Deals " + damage + " fixed damage.");
                break;
        }
    }

    // --------------------- Mini Boss ----------------------
    //Daydayan contribution: Siren Empress
    //-----------------------Siren Empress-------------------------
    private void sirenEmpressSKills(int skill, Character target) {
        int damage = 0;
        switch (skill) {
            case 1: // Water Vortex
                    damage = (int)(Math.random() * 26) + 10;
                    target.takedamage(damage);
                    System.out.println(name + " used Water Vortex! Deals " + damage + " damage.");
                break;
            case 2: // Tidal Wave
                    int hits = (int)(Math.random() * 3) + 1;
                    for (int i = 0; i < hits; i++){
                    damage += (int)(Math.random() * 6) + 5;
                    }
                    target.takedamage(damage);
                    System.out.println(name + " used Tidal Wave! Hits " + hits + " times for " + damage + " damage.");
                break;
            case 3: // Siren's Call
                    damage = (int)(Math.random() * 21) + 25;
                    target.takedamage(damage);
                    System.out.println(name + " used Siren's Call! Deals " + damage + " damage.");
                break;
        }
    }

    //Pedrosa contribution
    
    //Ecarma contribution
    //-----------------------Resonanara-------------------------
    private void resonaraSkills(int skill, Character target) {
        int damage = 0;
        int hits = 0;
        switch (skill) {
            case 1: // Dissonant Chords
                hits = (int)(Math.random() * 3) + 2; 
                damage = 0;
                for (int i = 0; i < hits; i++) {
                    damage += (int)(Math.random() * 6) + 6;
                }
                target.takedamage(damage);
                System.out.println(name + " used Dissonant Chords! Hits " + hits + " times for " + damage + " damage.");
                break;

            case 2: // Sonic Wave
                damage = 35;
                target.takedamage(damage);
                System.out.println(name + " used Sonic Wave! Deals " + damage + " fixed damage."); 
                break;

            case 3: // Crescendo
                hits = (int)(Math.random() * 4) + 2;
                for (int i = 0; i < hits; i++) {
                    damage += (int)(Math.random() * 11) + 10;
                }
                target.takedamage(damage);
                System.out.println(name + " used Crescendo! Hits " + hits + " times for " + damage + " damage.");
                break;
        }
    }

    //Rodrigo contribution
    //-----------------------Eclipse Core-------------------------
    private void eclipseCoreSkills(int skill, Character target) {
        int damage = 0;
        switch (skill) {
            case 1: // Dark Ooze
                damage = 30;
                target.takedamage(damage);
                System.out.println(name + " used Dark Ooze! Deals " + damage + " damage.");
                break;
            case 2: // Stellar Absorb
                damage = (int)(Math.random() * 10) + 15;
                target.takedamage(damage);
                System.out.println(name + " used Stellar Absorb! Deals " + damage + " damage.");
                break;
            case 3: // Eclipse
                damage = (int)(Math.random() * 11) + 30;
                target.takedamage(damage);
                System.out.println(name + " used Eclipse! Deals " + damage + " damage.");
                break;
        }
    }



    //Baraga contribution: Kassundre's Skills
    // ----------------- Kassundre's Skills -----------------
    private void kassundreSkills(int skill, Character target) {
        //Miniboss fight skills
        int damage = 0;
        switch (skill) {
            case 1: // Corrupted tears
                    int hits = (int)(Math.random() * 4) + 1;
                    damage = 0;
                    for (int i = 0; i < hits; i++) {
                        damage += (int)(Math.random() * 6) + 5;
                    }
                    target.takedamage(damage);
                    System.out.println(name + " used Corrupted Tears! Hits " + hits + " times for " + damage + " damage.");
                break;
            case 2: // Corrupted Hug
                    damage = (int)(Math.random() * 26) + 10;
                    target.takedamage(damage);
                    System.out.println(name + " used Corrupted Hug! Deals " + damage + " damage.");
                break;
            case 3: // Corrupted FLora
                    damage = (int)(Math.random() * 21) + 20;
                    target.takedamage(damage);
                    System.out.println(name + " used Corrupted Flora! Deals " + damage + " damage.");
                break;
        }
    }

    // ------------------------ Mobs -----------------------------
    
    //Daydayan contribution: Water Sprite Skills
    //----------------- Water Sprite Skills -----------------
private void waterSpriteSKills(int skill, Character target) {
        int damage = 0;
        switch (skill) {
            case 1: // Water Jet
                damage = (int)(Math.random() * 11) + 5;
                target.takedamage(damage);
                System.out.println(name + " used Water Jet! Deals " + damage + " damage.");
                break;

            case 2: // Bubble Bash
                damage = (int)(Math.random() * 16) + 10;
                target.takedamage(damage);
                System.out.println(name + " used Bubble Bash! Deals " + damage + " damage.");
                break;
        }
    }

    //Pedrosa contribution
    
    //Ecarma contribution
    //----------------- Echo Imp Skills -----------------
        private void echoImpSkills(int skill, Character target) {
            int damage = 0;
            switch (skill) {
            case 1: // Piercing Squeak
                damage = (int)(Math.random() * 6) + 10;
                target.takedamage(damage);
                System.out.println(name + " used Piercing Squeak! Deals " + damage + " damage.");
                break;

            case 2: // Loud Clap
                damage = (int)(Math.random() * 10) + 15;
                target.takedamage(damage);
                System.out.println(name + " used Loud Clap! Deals " + damage + " damage.");
                break;
        }
    }

    //Rodrigo contribution
    //----------------- Astral Glob Skills -----------------
    private void astralGlobSkills(int skill, Character target) {
        int damage = 0;
        switch (skill) {
            case 1: // Glob Smash
                damage = (int)(Math.random() * 8)+ 10;
                System.out.println(name + " used Glob Smash! Deals " + damage + " damage.");
                break;
            case 2: // Glowing Drip
                damage = (int)(Math.random() * 12) + 15;
                System.out.println(name + " used Glowing Drip! Deals " + damage + " magic damage.");
                break;
        }
    }

    //Baraga contribution: Student Puppet's Skills
    // ----------------- Student Puppet's Skills -----------------
    private void studentPuppetSkills(int skill, Character target) {
        //Minion fight skills
        int damage = 0;
        switch (skill) {
            case 1: // Puppet Punch
                damage = (int)(Math.random() * 6) + 5;
                target.takedamage(damage);
                System.out.println(name + " used Puppet Punch! Deals " + damage + " damage.");
                break;
            case 2: // Puppet Slash
                    damage = (int)(Math.random() * 11) + 10;
                    target.takedamage(damage);
                    System.out.println(name + " used Puppet Slash! Deals " + damage + " damage.");
                break;
        }
    }

    // ----------------- Princess Puppet's Skills -----------------
    private void princessPuppetSkills(int skill, Character target) {
        //Minion fight skills
        int damage = 0;
        switch (skill) {
            case 1: // Puppet Punch
                damage = (int)(Math.random() * 6) + 5;
                target.takedamage(damage);
                System.out.println(name + " used Puppet Punch! Deals " + damage + " damage.");
                break;
            case 2: // Puppet Slash
                    damage = (int)(Math.random() * 11) + 10;
                    target.takedamage(damage);
                    System.out.println(name + " used Puppet Slash! Deals " + damage + " damage.");
                break;
        }
    }

    //----------------- Magma Skeleton's Skills -----------------
    private void magmaSkeletonSkills(int skill, Character target) {
        int damage = 0;
        switch (skill) {
            case 1: // Bone Slash
                damage = (int)(Math.random() * 11) + 5;
                target.takedamage(damage);
                System.out.println(name + " used Bone Slash! Deals " + damage + " damage.");
                break;
            case 2: // Dark Stab
                damage = (int)(Math.random() * 6) + 10;
                target.takedamage(damage);
                System.out.println(name + " used Dark Stab! Deals " + damage + " damage.");
                break;
        }
    }

    //----------------- Water Blob's Skills -----------------
    private void waterblobSKills(int skill, Character target) {
        int damage = 0;
        switch (skill) {
            case 1: // Water Jet
                damage = (int)(Math.random() * 11) + 5;
                target.takedamage(damage);
                System.out.println(name + " used Water Jet! Deals " + damage + " damage.");
                break;

            case 2: // Bubble Bash
                damage = (int)(Math.random() * 16) + 10;
                target.takedamage(damage);
                System.out.println(name + " used Bubble Bash! Deals " + damage + " damage.");
                break;
        }
    }

        //----------------- Resonance Goblin's Skills -----------------
        private void resonanceGoblinSkills(int skill, Character target) {
            int damage = 0;
            switch (skill) {
            case 1: // Piercing Squeak
                damage = (int)(Math.random() * 6) + 10;
                target.takedamage(damage);
                System.out.println(name + " used Piercing Squeak! Deals " + damage + " damage.");
                break;

            case 2: // Loud Clap
                damage = (int)(Math.random() * 10) + 15;
                target.takedamage(damage);
                System.out.println(name + " used Loud Clap! Deals " + damage + " damage.");
                break;
        }
    }

    //----------------- Moon Sprite's Skills -----------------
    private void moonSpriteSkills(int skill, Character target) {
        int damage = 0;
        switch (skill) {
            case 1: // Glob Smash
                damage = (int)(Math.random() * 8)+ 10;
                System.out.println(name + " used Glob Smash! Deals " + damage + " damage.");
                break;
            case 2: // Glowing Drip
                damage = (int)(Math.random() * 12) + 15;
                System.out.println(name + " used Glowing Drip! Deals " + damage + " magic damage.");
                break;
        }
    }

    //----------------- Show Stats -----------------
    //Everyone contributed
    public void showStats() {
        System.out.println("---- " + name + " ----");
        System.out.println("Class: " + charClass);
        System.out.println("Type: " + type);
        System.out.println("Weapon: " + weapon);
        System.out.println("Health Points: " + healthPoints);
        System.out.println("------------------");
        System.out.println();
    }


}
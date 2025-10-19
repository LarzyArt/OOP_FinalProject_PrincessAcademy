//Everyone contributed to making this file and methods
public class Character {
    String name;
    String charClass;
    String type;
    String weapon;
    int healthPoints;
    int manaPoints;
     // status effect counters
    int tauntTurns = 0; // if >0, enemies should prefer/target this character
    int damageReductionTurns = 0; // number of turns left for damage reduction
    double damageReductionPercent = 0.0; // fraction to reduce incoming damage while active
    int stunnedTurns = 0; // if >0, this character can't act


    public Character(String name, String charClass, String type, String weapon, int healthPoints, int manaPoints) {
        this.name = name;
        this.charClass = charClass;
        this.type = type;
        this.weapon = weapon;
        this.healthPoints = healthPoints;
        this.manaPoints = manaPoints;
    }
    
    public String getName() {
        return name;
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
            System.out.println(name + " HP has dropped to 0! You have been defeated!");
        } else {
            System.out.println(name + " took " + damage + " damage! Remaining HP: " + healthPoints);
        }
    }
    public void healAlly(int heal){
        healthPoints += heal;
        System.out.println(name + " healed for " + heal + " HP. Current HP: " + healthPoints);
    }

    public void restoreMP(int mp){
        manaPoints += mp;
        System.out.println(name + " restored " + mp + " MP. Current MP: " + manaPoints);
    }

    public boolean isAlive(){
        return healthPoints > 0;
    }

    // status helper for characters
    public boolean isStunned() { return stunnedTurns > 0; }
    public void applyStun(int turns) { stunnedTurns = Math.max(stunnedTurns, turns); System.out.println(name + " is stunned for " + stunnedTurns + " turn(s).");
    }

    // ================== Skill System ==================
    //To use skills
    //Rodrigo contribution
    public void useSkill(int skill, MobNPC target, Character ally, Character[] party) {
        // if this character is stunned, they cannot use skills
        if (this.isStunned()) {
            System.out.println(this.getName() + " is stunned and cannot use skills.");
            return;
        }
        switch (name.toLowerCase()) {
            case "audry":
                audrySkills(skill, target);
                break;
            case "giantha":
                gianthaSkills(skill, target);
                break;
            case "lazuli":
                lazuliSkills(skill, ally, party);
                break;
            case "lynzi":
                lynziSkills(skill, target);
                break;
            case "shiera":
                shieraSkills(skill, target, ally);
                break;
            default:
                System.out.println(name + " has no skills defined!");
        }
    }

    //-------------------------------- Character Skills ------------------------------
    //Daydayan contribution
        // ----------------- Lynzi's Skills -----------------
    private void lynziSkills(int skill, MobNPC target) {
        int damage = 0;
        
        switch (skill) {
            case 1: // Majestic Kick
                damage = (int)(Math.random() * 21) + 20;
                System.out.println(name + " used Majestic Kick! Deals " + damage + " damage.");
                break;
            case 2: // Galactic Fist
                if (manaPoints >= 2) {
                    manaPoints -= 2;
                    damage = (int)(Math.random() * 36) + 20;
                    target.takedamage(damage);
                    System.out.println(name + " used Galactic Fist! Deals " + damage + " damage.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 3: // Meteoric Smash
                if (manaPoints >= 10) {
                    manaPoints -= 10;
                    damage = (int)(Math.random() * 151) + 100;
                    target.takedamage(damage);
                    // Lynzi becomes unable to act for 2 turns after using Meteoric Smash
                    this.stunnedTurns = Math.max(this.stunnedTurns, 2);
                    System.out.println(name + " used Ultimate, Meteoric Smash! Deals " + damage + " damage and is exhausted for 2 turns.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
        }
    }
    //Pedrosa contribution
    
    //Ecarma contribution
    // ----------------- Giantha's Skills -----------------
    private void gianthaSkills(int skill, MobNPC target) {
        int damage = 0;
        switch (skill) {
            case 1: // Giant Punch
                damage = (int)(Math.random() * 6) + 5;
                target.takedamage(damage);
                System.out.println(name + " used Giant Punch! Deals " + damage + " damage.");
                break;
            case 2: // Giant's Roar
                if (manaPoints >= 1) {
                    manaPoints -= 1;
                    // apply taunt to self so enemies prefer this character for 1 turn
                    this.tauntTurns = Math.max(this.tauntTurns, 1);
                    System.out.println(name + " used Giant's Roar! Enemies will target " + name + " for 1 turn.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 3: // Club Smash
                if (manaPoints >= 9) {
                    manaPoints -= 9;
                    damage = (int)(Math.random() * 21) + 20;
                    target.takedamage(damage);
                    // stun the targeted enemy for 1 turn (they can't act)
                    target.applyStun(1);
                    System.out.println(name + " used Ultimate, Club Smash! Deals " + damage + " damage and stuns the target for 1 turn.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
        }
    }

    
    //Rodrigo contribution
    // ----------------- Shiera's Skills -----------------
    private void shieraSkills(int skill, MobNPC target, Character ally) {
        int damage = 0;
        switch (skill) {
            case 1: // Stone Spikes
                if (manaPoints >= 4) {
                    manaPoints -= 4;
                    int hits = (int)(Math.random() * 4) + 1;
                    damage = 0;
                    for (int i = 0; i < hits; i++) {
                        damage += (int)(Math.random() * 10) + 1;
                    }
                    target.takedamage(damage);
                    System.out.println(name + " used Stone Spikes! Hits " + hits + " times for " + damage + " damage.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 2: // Earth Wall
                if (manaPoints >= 6) {
                    manaPoints -= 6;
                    // apply damage reduction to self
                    this.damageReductionPercent = 0.20;
                    this.damageReductionTurns = Math.max(this.damageReductionTurns, 4);
                    System.out.println(name + " used Earth Wall! Reduces incoming damage by 20% for 4 turns.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 3: // Iron Maiden
                if (manaPoints >= 10) {
                    manaPoints -= 10;
                    damage = 50;
                    target.takedamage(damage);
                    // stun the enemy for 1 turn
                    target.applyStun(1);
                    System.out.println(name + " used Ultimate, Iron Maiden! Deals " + damage + " fixed damage and stuns enemy for 1 turn.");
                    } else System.out.println(name + " doesn't have enough mana!");
                break;
        }
    }

    //Baraga contribution: Lazuli's Skills
    // ----------------- Lazuli's Skills -----------------
    private void lazuliSkills(int skill, Character ally, Character[] party) {
        int heal = 0;
        switch (skill) {
            case 1: // Basic Heal
                if (manaPoints >= 10) {
                    manaPoints -= 10;
                    // restore ~25% of current HP (best-effort since no max HP stored) and some MP
                    heal = Math.max(15, ally.healthPoints / 4);
                    ally.healAlly(heal);
                    ally.restoreMP(10);
                    System.out.println(name + " used Basic Heal! Restores " + heal + " HP and 10 MP to " + ally.getName() + ".");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 2: // Ocean's Blessing
                if (manaPoints >= 20) {
                    manaPoints -= 20;
                    // heal and restore MP to all allies
                    for (Character c : party) {
                        if (c != null && c.isAlive()) {
                            int h = Math.max(10, c.healthPoints / 7); // ~15% of current
                            c.healAlly(h);
                            c.restoreMP(10);
                        }
                    }
                    System.out.println(name + " used Ocean's Blessing! Heals allies and restores MP to all allies.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 3: // Harmonic Wave
                if (manaPoints == 0) {
                    for (Character c : party) {
                        if (c != null && c.isAlive()) {
                            int h = Math.max(20, c.healthPoints / 2);
                            c.healAlly(h);
                            c.restoreMP(100);
                        }
                    }
                    System.out.println(name + " used Ultimate, Harmonic Wave! Restores large MP and HP to all allies.");
                } else System.out.println(name + " must have 0 mana to use Harmonic Wave!");
                break;
            }
        }

    // ----------------- Show Stats -----------------
    //Everyone contributed
    public void showStats() {
        System.out.println("---- " + name + " ----");
        System.out.println("Class: " + charClass);
        System.out.println("Type: " + type);
        System.out.println("Weapon: " + weapon);
        System.out.println("HP: " + healthPoints + ", MP: " + manaPoints);
        System.out.println("------------------");
        System.out.println();
    }

    // Return the skill list for this character as an array of strings
    // Each entry should describe the skill name and any MP cost or requirement
    public String[] getSkillList() {
        switch (name.toLowerCase()) {
            case "audry":
                return new String[]{"1) Slime Bounce - Cost: 3 MP","2) Acid Shot - Cost: 7 MP","3) Beyond the Abyss - Cost: 15 MP"};
            case "giantha":
                return new String[]{"1) Giant Punch - Cost: 0 MP","2) Giant's Roar - Cost: 1 MP","3) Club Smash - Cost: 9 MP"};
            case "lazuli":
                return new String[]{"1) Basic Heal - Cost: 10 MP","2) Ocean's Blessing - Cost: 20 MP","3) Harmonic Wave - Requires 0 MP"};
            case "lynzi":
                return new String[]{"1) Majestic Kick - Cost: 0 MP","2) Galactic Fist - Cost: 2 MP","3) Meteoric Smash - Cost: 10 MP"};
            case "shiera":
                return new String[]{"1) Stone Spikes - Cost: 4 MP","2) Earth Wall - Cost: 6 MP","3) Iron Maiden - Cost: 10 MP"};
            default:
                return new String[0];
        }
    }

    //Everyone Contributed
    // Return a string indicating the target type for a given skill index.
    // Possible return values: "ENEMY", "ALLY", "ALL", "SELF".
    public String getSkillTargetType(int skill) {
        switch (name.toLowerCase()) {
            case "audry":
            case "giantha":
            case "lynzi":
                return "ENEMY";
            case "lazuli":
                switch (skill) {
                    case 1: return "ALLY"; // Basic Heal
                    case 2: return "ALL";  // Ocean's Blessing
                    case 3: return "ALL";  // Harmonic Wave
                    default: return "ALLY";
                }
            case "shiera":
                switch (skill) {
                    case 2: return "SELF"; // Earth Wall
                    default: return "ENEMY";
                }
            default:
                return "ENEMY";
        }
    }

    //Everyone Contributed
    // Decrement status durations each turn
    public void tickStatus() {
        if (tauntTurns > 0) tauntTurns--;
        if (stunnedTurns > 0) stunnedTurns--;
        if (damageReductionTurns > 0) {
            damageReductionTurns--;
            if (damageReductionTurns == 0) damageReductionPercent = 0.0;
        }
    }
}

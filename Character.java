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

    //Pedrosa contribution
    
    //Ecarma contribution
    
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
                    System.out.println(name + " used Earth Wall! Reduces damage by 20% for 4 turns.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 3: // Iron Maiden
                if (manaPoints >= 10) {
                    manaPoints -= 10;
                    damage = 50;
                    target.takedamage(damage);
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
}

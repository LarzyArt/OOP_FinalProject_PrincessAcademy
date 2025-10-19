//Everyone contributed to making this file and methods
public class Character {
    String name;
    String charClass;
    String type;
    String weapon;
    int healthPoints;
    int manaPoints;

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
    void takedamage(int damage){
        healthPoints -= damage;
        if(healthPoints < 0) healthPoints = 0;
        System.out.println(name + "took " + damage + "damage! Remaining HP: " + healthPoints);
    }

    // ================== Skill System ==================
    //To use skills
    //Rodrigo contribution
    public void useSkill(int skill, MobNPC target, Character ally) {
        switch (name.toLowerCase()) {
            case "audry":
                audrySkills(skill, target);
                break;
            case "giantha":
                gianthaSkills(skill, target);
                break;
            case "lazuli":
                lazuliSkills(skill, ally);
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
        private void lazuliSkills(int skill, Character ally) {
            switch (skill) {
                case 1: // Basic Heal
                    if (manaPoints >= 10) {
                        manaPoints -= 10;
                        System.out.println(name + " used Basic Heal! Restores 25% HP + MP to one ally.");
                    } else System.out.println(name + " doesn't have enough mana!");
                    break;
                case 2: // Ocean's Blessing
                    if (manaPoints >= 20) {
                        manaPoints -= 20;
                        System.out.println(name + " used Ocean's Blessing! Heals 15% HP and restores 10 MP to all allies.");
                    } else System.out.println(name + " doesn't have enough mana!");
                    break;
                case 3: // Harmonic Wave
                    if (manaPoints == 0) {
                        System.out.println(name + " used Ultimate, Harmonic Wave! Restores 100% MP and 50% HP to all allies.");
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

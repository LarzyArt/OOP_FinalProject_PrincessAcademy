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

    //-------------------------------- Character Skills ------------------------------
    //Daydayan contribution: Lynzi's Skills
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
    
    //Rodrigo contribution
    
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

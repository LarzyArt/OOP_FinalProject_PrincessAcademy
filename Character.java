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

    // ----------------- Audry's Skills -----------------
    private void audrySkills(int skill, MobNPC target) {
        int damage = 0;
        switch (skill) {
            case 1: // Slime Bounce
                if (manaPoints >= 3) {
                    manaPoints -= 3;
                    damage = (int)(Math.random() * 21) + 20;
                    target.takedamage(damage);
                    System.out.println(name + " used Slime Bounce! Deals " + damage + " damage.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 2: // Acid Shot
                if (manaPoints >= 7) {
                    manaPoints -= 7;
                    int hits = (int)(Math.random() * 3) + 1;
                    damage = 0;
                    for (int i = 0; i < hits; i++) {
                        damage += (int)(Math.random() * 11) + 5;
                    }
                    target.takedamage(damage);
                    System.out.println(name + " used Acid Shot! Hits " + hits + " times for " + damage + " damage.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 3: // Beyond the Abyss
                if (manaPoints >= 15) {
                    manaPoints -= 15;
                    damage = 150;
                    target.takedamage(damage);
                    System.out.println(name + " used Ultimate, Beyong the Abyss! Deals " + damage + " fixed damage.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
        }
    }

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
                    System.out.println(name + " used Giant's Roar! Enemies target only " + name + " for 1 turn.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 3: // Club Smash
                if (manaPoints >= 9) {
                    manaPoints -= 9;
                    damage = (int)(Math.random() * 21) + 20;
                    target.takedamage(damage);
                    System.out.println(name + " used Ultimate, Club Smash! Deals " + damage + " damage, enemies can't attack for 1 turn.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
        }
    }

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
                    System.out.println(name + " used Ultimate, Meteoric Smash! Deals " + damage + " damage but can't move for 2 turns.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
        }
    }

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

    // ----------------- Show Stats -----------------
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

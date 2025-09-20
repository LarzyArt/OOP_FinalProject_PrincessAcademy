public class MobNPC {
    String name;
    String charClass;
    String type;
    String weapon;
    int healthPoints;
    //Mobs don't use mana
    //int manaPoints;

    public MobNPC(String name, String charClass, String type, String weapon, int healthPoints) {
        this.name = name;
        this.charClass = charClass;
        this.type = type;
        this.weapon = weapon;
        this.healthPoints = healthPoints;
    }
    
    public String getName() {
        return name;
    }

    //=================== Skill System ==================
    public void useSkill(int skill) {
        switch (name.toLowerCase()) {
            case "twinkle":
                twinkleSkills(skill);
                break;
            case "kassundre":
                kassundre(skill);
                break;
            case "student puppet":
                studentPuppetSkills(skill);
                break;
            default:
                System.out.println(name + " has no skills defined!");
        }
    }

    // ----------------- Twinkle's Skills -----------------
    private void twinkleSkills(int skill) {
        //Boss fight skills
        switch (skill) {
            case 1: // Puppet Slash
                    int damage = (int)(Math.random() * 26) + 5;
                    System.out.println(name + " used Puppet Slash! Deals " + damage + " damage.");
            case 2: // Lazer Devastation
                    int hits = (int)(Math.random() * 2) + 1;
                    int totalDamage = 0;
                    for (int i = 0; i < hits; i++) {
                        totalDamage += 20;
                    }
                    System.out.println(name + " used Acid Shot! Hits " + hits + " times for " + totalDamage + " damage.");
                break;
            case 3: // Corruption
                    int dmg = 60;
                    System.out.println(name + " used Corruption! Deals " + dmg + " fixed damage.");
                break;
        }
    }
    // ----------------- Kassundre's Skills -----------------
    private void kassundre(int skill) {
        //Miniboss fight skills
        switch (skill) {
            case 1: // Corrupted tears
                    int hits = (int)(Math.random() * 4) + 1;
                    int totalDamage = 0;
                    for (int i = 0; i < hits; i++) {
                        totalDamage += (int)(Math.random() * 6) + 5;
                    }
                    System.out.println(name + " used Corrupted Tears! Hits " + hits + " times for " + totalDamage + " damage.");
                break;
            case 2: // Corrupted Hug
                    int damage = (int)(Math.random() * 26) + 10;
                    System.out.println(name + " used Corrupted Hug! Deals " + damage + " damage.");
                break;
            case 3: // Corrupted FLora
                    int dmg = (int)(Math.random() * 21) + 20;
                    System.out.println(name + " used Corrupted Flora! Deals " + dmg + " damage.");
                break;
        }
    }
    // ----------------- Student Puppet's Skills -----------------
    private void studentPuppetSkills(int skill) {
        //Minion fight skills
        switch (skill) {
            case 1: // Puppet Punch
                int damage = (int)(Math.random() * 6) + 5;
                System.out.println(name + " used Puppet Punch! Deals " + damage + " damage.");
                break;
            case 2: // Puppet Slash
                    int dmg = (int)(Math.random() * 11) + 10;
                    System.out.println(name + " used Puppet Slash! Deals " + dmg + " damage.");
                break;
        }
    }

    //----------------- Show Stats -----------------
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

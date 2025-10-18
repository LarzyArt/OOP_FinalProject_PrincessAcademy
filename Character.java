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
    //Daydayan contribution

    //Pedrosa contribution
    
    //Ecarma contribution
    
    //Rodrigo contribution
    
    //Baraga contribution

    // ----------------- Show Stats -----------------
    //
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

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

    void takedamage(int damage){
        healthPoints -= damage;
        if(healthPoints < 0) healthPoints = 0;
        System.out.println(name + "took " + damage + "damage! Remaining HP: " + healthPoints);
    }
    
    public String getName() {
        return name;
    }

    //=================== Skill System ==================
    //To use skills 
    // Ecarma contribution

    //-------------------------------- Character Skills ------------------------------
    //Daydayan contribution

    //Pedrosa contribution
    
    //Ecarma contribution
    
    //Rodrigo contribution
    
    //Baraga contribution

    // -------------------- Final Boss --------------------
    //Baraga contribution

    // --------------------- Mini Boss ----------------------
    //Daydayan contribution

    //Pedrosa contribution
    
    //Ecarma contribution
    
    //Rodrigo contribution
    
    //Baraga contribution
    // ------------------------ Mobs -----------------------------
    
    //Daydayan contribution

    //Pedrosa contribution
    
    //Ecarma contribution
    
    //Rodrigo contribution
    
    //Baraga contribution
    
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
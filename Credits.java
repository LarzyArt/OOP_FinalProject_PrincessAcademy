public class Credits {
    String name;
    String Position;
    String type;
    String weapon;
    int healthPoints;
    int manaPoints;

    public Credits(String name, String position, String type, String weapon, int healthPoints, int manaPoints) {
        this.name = name;
        this.Position = position;
        this.type = type;
        this.weapon = weapon;
        this.healthPoints = healthPoints;
        this.manaPoints = manaPoints;
    }

    public void ShowCredits() {
        System.out.println("---- " + name + " ----");
        System.out.println("Position: " + Position);
        System.out.println("Type: " + type);
        System.out.println("Weapon: " + weapon);
        System.out.println("HP: " + healthPoints + "/" + healthPoints + ", MP: " + manaPoints + "/" + manaPoints);
        System.out.println("------------------");
        System.out.println();
    }

}

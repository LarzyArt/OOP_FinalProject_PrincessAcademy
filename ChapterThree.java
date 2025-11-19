public class ChapterThree extends MobNPC{
   public ChapterThree(){
        super("Eclipse Core", "Miniboss", "Melee", "Astral Magic", 510, 4);
    }

    @Override
    public void performSkill(int skill, Character target){
        int damage = 0;
        switch (skill) {
            case 1: // Dark Ooze
                damage = 30;
                target.takeDamage(damage);
                System.out.println(name + " used Dark Ooze! Deals " + damage + " damage.");
                break;
            case 2: // Stellar Absorb
                damage = (int)(Math.random() * 10) + 25;
                target.takeDamage(damage);
                System.out.println(name + " used Stellar Absorb! Deals " + damage + " damage.");
                break;
            case 3: // Eclipse
                damage = (int)(Math.random() * 11) + 30;
                target.takeDamage(damage);
                System.out.println(name + " used Eclipse! Deals " + damage + " damage.");
                break;
        }
    }
}

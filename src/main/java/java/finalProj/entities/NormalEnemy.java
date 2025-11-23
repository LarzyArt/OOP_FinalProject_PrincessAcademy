package charmees.finalproj.entities;

public class NormalEnemy extends MobNPC{
    public NormalEnemy(String name, String charClass, String type, String weapon, int healthPoints, int chapter) {
        super(name, charClass, type, weapon, healthPoints, chapter);
    }

    @Override
    public void performSkill(int skill, GameCharacter target){
        int damage = 0;
        switch (skill) {
            case 1:
                damage = (int)(Math.random() * 11) + 25;
                target.takeDamage(damage);
                System.out.println(name + " used Slash! Deals " + damage + " damage.");
                break;
            case 2:
                damage = (int)(Math.random() * 21) + 20;
                target.takeDamage(damage);
                System.out.println(name + " used Stab! Deals " + damage + " damage.");
                break;
        }
    }
}

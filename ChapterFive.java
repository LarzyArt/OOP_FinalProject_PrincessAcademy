public class ChapterFive extends MobNPC{
    public ChapterFive() {
        super("Kassundre", "Miniboss", "Ranged", "Dark Magic", 650,5);
    }
    
    @Override
    public void performSkill(int skill, Character target){
        int damage = 0;
        switch (skill) {
            case 1: // Corrupted tears
                    int hits = (int)(Math.random() * 4) + 1;
                    damage = 0;
                    for (int i = 0; i < hits; i++) {
                        damage += (int)(Math.random() * 11) + 10;
                    }
                    this.lastSkillHits = hits;
                    this.lastSkillDamage = damage;
                    target.takeDamage(damage);
                    System.out.println(name + " used Corrupted Tears! Hits " + hits + " times for " + damage + " damage.");
                break;
            case 2: // Corrupted Hug
                    damage = (int)(Math.random() * 26) + 35;
                    target.takeDamage(damage);
                    System.out.println(name + " used Corrupted Hug! Deals " + damage + " damage.");
                break;
            case 3: // Corrupted FLora
                    damage = (int)(Math.random() * 21) + 40;
                    target.takeDamage(damage);
                    System.out.println(name + " used Corrupted Flora! Deals " + damage + " damage.");
                break;
        }
    }
}

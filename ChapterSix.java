public class ChapterSix extends MobNPC{
    public ChapterSix() {
        super("Twinkle", "Boss", "Melee", "Puppet", 999,6);
    }

    @Override
    public void useSkill(int skill, Character target){
        int damage = 0;
        switch (skill) {
            case 1: // Puppet Slash
                    damage = (int)(Math.random() * 26) + 40;
                    target.takeDamage(damage);
                    System.out.println(name + " used Puppet Slash! Deals " + damage + " damage.");
            case 2: // Lazer Devastation
                    int hits = (int)(Math.random() * 4) + 1;
                    damage = 0;
                    for (int i = 0; i < hits; i++) {
                        damage += 20;
                    }
                    this.lastSkillHits = hits;
                    this.lastSkillDamage = damage;
                    target.takeDamage(damage);
                    System.out.println(name + " used Lazer Devastation! Hits " + hits + " times for " + damage + " damage.");
                break;
            case 3: // Corruption
                    damage = 80;
                    target.takeDamage(damage);
                    System.out.println(name + " used Corruption! Deals " + damage + " fixed damage.");
                break;
    
        }
    }
}

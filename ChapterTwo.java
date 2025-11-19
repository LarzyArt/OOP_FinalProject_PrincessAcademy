public class ChapterTwo extends MobNPC{
    public ChapterTwo(){
        super("Lava Beast", "Miniboss", "Melee", "Fire Magic", 520,2);
    }

    @Override
    public void performSkill(int skill, Character target){
        int damage = 0;
        switch (skill) {
            case 1: // Lava Burst
                    damage = (int)(Math.random() * 16) + 20;
                    target.takeDamage(damage);
                    System.out.println(name + " used Lava Burst! Deals " + damage + " damage.");
                break;
            case 2: // Magma Flow
                    int hits = (int)(Math.random() * 2) + 1;
                    for (int i = 0; i < hits; i++){
                    damage += (int)(Math.random() * 16) + 5;
                    }
                    target.takeDamage(damage);
                        this.lastSkillHits = hits;
                        this.lastSkillDamage = damage;
                        System.out.println(name + " used Magma Flow! Hits " + hits + " times for " + damage + " damage.");
                break;
            case 3: // Corrupted Eruption
                    damage = (int)(Math.random() * 31) + 20;
                    // AoE: should be handled by UI.enemypase print and expect caller to apply to party
                    System.out.println(name + " used Corrupted Eruption! Deals " + damage + " damage to all enemies!");
                    //UI will handle applying AoE.
                break;
        }
    }
    
}

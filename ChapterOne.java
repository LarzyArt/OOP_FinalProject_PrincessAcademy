public class ChapterOne extends MobNPC{
    public ChapterOne(){
        super("Siren Empress", "Miniboss", "Ranged", "Water Magic", 470,1);
    }
    
    @Override
    public void useSkill(int skill, Character target){
        int damage = 0;
        switch (skill) {
            case 1: // Water Vortex
                    damage = (int)(Math.random() * 16) + 20;
                    target.takeDamage(damage);
                    System.out.println(name + " used Water Vortex! Deals " + damage + " damage.");
                break;
            case 2: // Tidal Wave
                    int hits = (int)(Math.random() * 3) + 1;
                        for (int i = 0; i < hits; i++){
                        damage += (int)(Math.random() * 16) + 5;
                        }
                        this.lastSkillHits = hits;
                        this.lastSkillDamage = damage;
                        target.takeDamage(damage);
                        System.out.println(name + " used Tidal Wave! Hits " + hits + " times for " + damage + " damage.");
                break;
            case 3: // Siren's Call
                    damage = (int)(Math.random() * 21) + 25;
                    target.takeDamage(damage);
                    System.out.println(name + " used Siren's Call! Deals " + damage + " damage.");
                break;
        }
    }
}

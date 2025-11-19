package charmees.finalproj.entities;

public class ChapterFour extends MobNPC{
    public ChapterFour() {
        super("Resonara", "Miniboss", "Ranged", "Sound Magic", 550,4);
    }

    @Override
    public void performSkill(int skill, GameCharacter target){
        int damage = 0;
        int hits = 0;
        switch (skill) {
            case 1: // Dissonant Chords
                hits = (int)(Math.random() * 3) + 2; 
                for (int i = 0; i < hits; i++) {
                    damage += (int)(Math.random() * 11) + 5;
                }
                this.lastSkillHits = hits;
                this.lastSkillDamage = damage;
                target.takeDamage(damage);
                System.out.println(name + " used Dissonant Chords! Hits " + hits + " times for " + damage + " damage.");
                break;

            case 2: // Sonic Wave
                damage = 45;
                target.takeDamage(damage);
                System.out.println(name + " used Sonic Wave! Deals " + damage + " fixed damage."); 
                break;

            case 3: // Crescendo
                hits = (int)(Math.random() * 4) + 2;
                for (int i = 0; i < hits; i++) {
                    damage += (int)(Math.random() * 11) + 15;
                }
                this.lastSkillHits = hits;
                this.lastSkillDamage = damage;
                target.takeDamage(damage);
                System.out.println(name + " used Crescendo! Hits " + hits + " times for " + damage + " damage.");
                break;
        }
    }
}

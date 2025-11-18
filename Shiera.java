public class Shiera extends Character {
    public Shiera() {
        super("Shiera", "Rogue", "Physical", "Dagger", 110, 50);
    }
    
    @Override
    public void useSkill(int skill, MobNPC target, Character ally, Character[] party) {
        int damage = 0;
        switch (skill) {
            case 1: // Stone Spikes
                if (manaPoints >= 4) {
                    manaPoints -= 4;
                    int hits = (int)(Math.random() * 4) + 1;
                    damage = 0;
                    for (int i = 0; i < hits; i++) {
                        damage += (int)(Math.random() * 10) + 1;
                    }
                    this.lastSkillHits = hits;
                    this.lastSkillDamage = damage;
                    target.takeDamage(damage);
                    System.out.println(name + " used Stone Spikes! Hits " + hits + " times for " + damage + " damage.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 2: // Earth Wall
                if (manaPoints >= 6) {
                    manaPoints -= 6;
                    // apply damage reduction to self
                    this.damageReductionPercent = 0.20;
                    this.damageReductionTurns = Math.max(this.damageReductionTurns, 4);
                    System.out.println(name + " used Earth Wall! Reduces incoming damage by 20% for 4 turns.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 3: // Iron Maiden
                if (manaPoints >= 10) {
                    manaPoints -= 10;
                    damage = 50;
                    target.takeDamage(damage);
                    // stun the enemy for 1 turn
                    target.applyStun(1);
                    System.out.println(name + " used Ultimate, Iron Maiden! Deals " + damage + " fixed damage and stuns enemy for 1 turn.");
                    } else System.out.println(name + " doesn't have enough mana!");
                break;
        }
    }

    @Override
    public String[] getSkillList() {
        return new String[] {
            "Skill: Stone Spikes (Cost: 4 MP) \n\n- Hits 1-4 times for 1-10 damage each to a single enemy.",
            "Skill: Earth Wall (Cost: 6 MP) \n\n- Reduces incoming damage by 20% for 4 turns.",
            "Ultimate Skill: Iron Maiden (Cost: 10 MP) \n\n- Deals 50 fixed damage to a single enemy and stuns them for 1 turn."
        };
    }   

    @Override
    public String getSkillTargetType(int skill) {
        if(skill == 2) {
            return "SELF";
        } else {
            return "ENEMY";
        }
    }
}

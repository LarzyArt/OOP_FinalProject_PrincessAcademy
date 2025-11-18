public class Audry extends Character {
    public Audry() {
        super("Audry", "Assassin", "Melee", "Acidic Slime", 100, 25);
    }
    
    @Override
    public void useSkill(int skill, MobNPC target, Character ally, Character[] party) {
         int damage = 0;
        switch (skill) {
            case 1: // Slime Bounce
                if (manaPoints >= 3) {
                    manaPoints -= 3;
                    damage = (int)(Math.random() * 21) + 20;
                    target.takeDamage(damage);
                }
            case 2: // Acid Shot
                if (manaPoints >= 7) {
                    manaPoints -= 7;
                    int hits = (int)(Math.random() * 3) + 1;
                    damage = 0;
                    for (int i = 0; i < hits; i++) {
                        damage += (int)(Math.random() * 11) + 5;
                    }
                    // expose hit metadata for UI
                    this.lastSkillHits = hits;
                    this.lastSkillDamage = damage;
                    target.takeDamage(damage);
                    System.out.println(name + " used Acid Shot! Hits " + hits + " times for " + damage + " damage.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 3: // Beyond the Abyss
                if (manaPoints >= 15) {
                    manaPoints -= 15;
                    damage = 150;
                    target.takeDamage(damage);
                    System.out.println(name + " used Ultimate, Beyond the Abyss! Deals " + damage + " fixed damage.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
        }
    }

    @Override
    public String[] getSkillList() {
        return new String[] {
            "Skill: Slime Bounce (Cost: 3 MP) \n\n- Deals 20-40 damage to a single enemy.",
            "Skill: Acid Shot (Cost: 7 MP) \n\n- Hits 1-3 times for 5-15 damage each to a single enemy.",
            "Ultimate Skill: Beyond the Abyss (Cost: 15 MP) \n\n- Deals 150 fixed damage to a single enemy."
        };
    }

    @Override
    public String getSkillTargetType(int skill) {
        return "ENEMY";
    }

}

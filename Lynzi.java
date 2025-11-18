public class Lynzi extends Character {
    public Lynzi() {
        super("Lynzi", "Dealer", "Melee", "Star Magic", 170, 30);
    }
    
    @Override
    public void useSkill(int skill, MobNPC target, Character ally, Character[] party) {
        int damage = 0;
        
        switch (skill) {
            case 1: // Majestic Kick
                damage = (int)(Math.random() * 21) + 20;
                target.takeDamage(damage);
                System.out.println(name + " used Majestic Kick! Deals " + damage + " damage.");
                break;
            case 2: // Galactic Fist
                if (manaPoints >= 2) {
                    manaPoints -= 2;
                    damage = (int)(Math.random() * 36) + 20;
                    target.takeDamage(damage);
                    System.out.println(name + " used Galactic Fist! Deals " + damage + " damage.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 3: // Meteoric Smash
                if (manaPoints >= 10) {
                    manaPoints -= 10;
                    damage = (int)(Math.random() * 151) + 100;
                    target.takeDamage(damage);
                    // Lynzi becomes unable to act for 2 turns after using Meteoric Smash
                    this.applyStun(2);
                    System.out.println(name + " used Ultimate, Meteoric Smash! Deals " + damage + " damage.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
        }
    }

    @Override
    public String[] getSkillList() {
        return new String[] {
            "Skill: Majestic Kick (Cost: 0 MP) \n\n- Deals 20-40 damage to a single enemy.",
            "Skill: Galactic Fist (Cost: 2 MP) \n\n- Deals 20-55 damage to a single enemy.",
            "Ultimate Skill: Meteoric Smash (Cost: 10 MP) \n\n- Deals 100-250 damage to a single enemy and stuns self for 2 turns."
        };
    }

    @Override
    public String getSkillTargetType(int skill) {
        return "ENEMY";
    }
}

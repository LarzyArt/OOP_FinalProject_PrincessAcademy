public class Giantha extends Character {
    public Giantha() {
        super("Giantha", "Tank", "Melee", "World Tree Branch", 250, 20);
    }
    
    @Override
    public void useSkill(int skill, MobNPC target, Character ally, Character[] party) {
        int damage = 0;
        switch (skill) {
            case 1: // Giant Punch
                damage = (int)(Math.random() * 6) + 5;
                target.takeDamage(damage);
                System.out.println(name + " used Giant Punch! Deals " + damage + " damage.");
                break;
            case 2: // Giant's Roar
                if (manaPoints >= 1) {
                    manaPoints -= 1;
                    // Giant's Roar will taunt enemies to target Giantha (handled by BattleUI)
                    System.out.println(name + " used Giant's Roar! Enemies will target " + name + " for 2 turns.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 3: // Club Smash
                if (manaPoints >= 9) {
                    manaPoints -= 9;
                    damage = (int)(Math.random() * 21) + 20;
                    target.takeDamage(damage);
                    // stun the targeted enemy for 1 turn (they can't act)
                    target.applyStun(1);
                    System.out.println(name + " used Ultimate, Club Smash! Deals " + damage + " damage and stuns the target for 1 turn.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
        }
    }

    @Override
    public String[] getSkillList() {
        return new String[] {
            "Skill: Giant Punch (Cost: 0 MP) \n\n- Deals 5-10 damage to a single enemy.",
            "SKill: Giant's Roar (Cost: 1 MP) \n\n- Taunts enemies to target Giantha for 2 turn.",
            "Ultimate Skill: Club Smash (Cost: 9 MP) \n\n- Deals 20-40 damage to a single enemy and stuns them for 1 turn."
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

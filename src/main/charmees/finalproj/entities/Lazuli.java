package charmees.finalproj.entities;

public class Lazuli extends GameCharacter {
    public Lazuli() {
        super("Lazuli", "Healer", "Ranged", "Staff", 150, 30);
    }   
    
    @Override
    public void performSkill(int skill, MobNPC target, GameCharacter ally, GameCharacter[] party) {
        int heal = 0;
        switch (skill) {
            case 1: // Basic Heal
                if (manaPoints >= 10) {
                    manaPoints -= 10;
                    // restore ~25% of current HP (best-effort since no max HP stored) and some MP
                    heal = Math.max(15, ally.healthPoints / 4);
                    ally.healAlly(heal);
                    ally.restoreMP(10);
                    System.out.println(name + " used Basic Heal! Restores " + heal + " HP and 10 MP to " + ally.getName() + ".");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 2: // Ocean's Blessing
                if (manaPoints >= 20) {
                    manaPoints -= 20;
                    // heal and restore MP to all allies
                    for (GameCharacter c : party) {
                        if (c != null && c.isAlive()) {
                            int h = Math.max(10, c.healthPoints / 7); // ~15% of current
                            c.healAlly(h);
                            c.restoreMP(10);
                        }
                    }
                    System.out.println(name + " used Ocean's Blessing! Heals allies and restores MP to all allies.");
                } else System.out.println(name + " doesn't have enough mana!");
                break;
            case 3: // Harmonic Wave
                if (manaPoints == 0) {
                    for (GameCharacter c : party) {
                        if (c != null && c.isAlive()) {
                            int h = Math.max(20, c.healthPoints / 2);
                            c.healAlly(h);
                            c.restoreMP(100);
                        }
                    }
                    System.out.println(name + " used Ultimate, Harmonic Wave! Restores large MP and HP to all allies.");
                } else System.out.println(name + " must have 0 mana to use Harmonic Wave!");
                break;
        }
    }

    @Override
    public String[] getSkillList() {
        return new String[] {
            "Skill: Basic Heal (Cost: 10 MP) \n\n- Heals an ally for ~25% of their current HP and restores 10 MP.",
            "Skill: Ocean's Blessing (Cost: 20 MP) \n\n- Heals all allies for ~15% of their current HP and restores 10 MP each.",
            "Ultimate Skill: Harmonic Wave (Cost: 0 MP, Must have 0 MP) \n\n- Heals all allies for ~50% of their current HP and restores 100 MP each."
        };
    }

    @Override
    public String getSkillTargetType(int skill) {
        if(skill == 1) {
            return "ALLY";
        } else {
            return "ALL";
        }
    }
}

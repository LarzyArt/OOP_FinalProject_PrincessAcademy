package charmees.finalproj.entities;

import charmees.finalproj.util.Damageable;

public abstract class MobNPC implements Damageable {
    protected String name;
    protected String charClass;
    protected String type;
    protected String weapon;
    protected int healthPoints;
    public int chapter;

    //Status effects
    int tauntTurns = 0;
    // if this mob is taunted, this is the target they should focus on
    protected GameCharacter tauntTarget = null;
    int damageReductionTurns = 0;
    double damageReductionPercent = 0.0;
    int stunnedTurns = 0;
    // If true, the stun was applied this turn and will only become active next turn
    boolean stunJustApplied = false;

    // last skill metadata for UI logging
    protected int lastSkillHits = 0;
    protected int lastSkillDamage = 0;
    // store max HP for UI display
    protected int maxHealthPoints;

    public MobNPC(String name, String charClass, String type, String weapon, int healthPoints, int chapter) {
        this.name = name;
        this.charClass = charClass;
        this.type = type;
        this.weapon = weapon;
        this.healthPoints = healthPoints;
        this.maxHealthPoints = healthPoints;
        this.chapter = chapter;
    }

    @Override
    public void takeDamage(int damage){
        if (damageReductionTurns > 0 && damageReductionPercent > 0.0) {
            int reduced = (int) Math.round(damage * (1.0 - damageReductionPercent));
            damage = Math.max(0, reduced);
        }
        healthPoints -= damage;
    }
    //flag to check if character is alive
    @Override
    public boolean isAlive(){
        return healthPoints > 0;
    }

    public boolean isStunned() { return stunnedTurns > 0 && !stunJustApplied; }
    public void applyStun(int turns) { 
        // mark stun to take effect starting next turn
        stunnedTurns = Math.max(stunnedTurns, turns);
        stunJustApplied = true;
        System.out.println(name + " will be stunned for " + stunnedTurns + " turn(s) starting next turn."); 
    }
    public boolean isTaunted() { return tauntTurns > 0; }
    public void applyTaunt(int turns, GameCharacter target) { 
        // apply taunt immediately and set which character to target
        tauntTurns = Math.max(tauntTurns, turns);
        tauntTarget = target;
        System.out.println(name + " is taunted to target " + target.getName() + " for " + tauntTurns + " turn(s)."); 
    }

    public GameCharacter getTauntTarget() { return tauntTarget; }
    public void applyDamageReduction(double percent, int turns) { damageReductionPercent = percent; damageReductionTurns = Math.max(damageReductionTurns, turns); System.out.println(name + " has damage reduction for " + damageReductionTurns + " turn(s)."); }
    

    public void tickStatus(){
        // Decrement taunt timer and clear tauntTarget when expired
        if(tauntTurns > 0) {
            tauntTurns--;
            if (tauntTurns == 0) tauntTarget = null;
        }
        // If a stun was just applied this turn, make it active next turn but don't decrement yet
        if (stunJustApplied) {
            stunJustApplied = false;
        } else {
            if (stunnedTurns > 0) stunnedTurns--;
        }
        if(damageReductionTurns > 0){
            damageReductionTurns--;
            if(damageReductionTurns == 0) damageReductionPercent = 0.0;
        }
    }

    public final void useSkill(int skill, GameCharacter target) {
        // reset last-skill metadata
        lastSkillHits = 0;
        lastSkillDamage = 0;

        if (isStunned()) {
            System.out.println(name + " is stunned and cannot use any skills!");
            return;
        }

        performSkill(skill, target);
    }

    // Public getters for UI and other packages
    public String getName() { return name; }
    public int getHealthPoints() { return healthPoints; }
    public int getMaxHealthPoints() { return maxHealthPoints; }
    public int getTauntTurns() { return tauntTurns; }
    public int getStunnedTurns() { return stunnedTurns; }
    public int getLastSkillHits() { return lastSkillHits; }
    public int getLastSkillDamage() { return lastSkillDamage; }

    // actual mob skill effects here
    protected abstract void performSkill(int skill, GameCharacter target);
}
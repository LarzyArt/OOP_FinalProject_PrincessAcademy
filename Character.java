
public abstract class Character implements Damageable{
    protected String name;
    protected String charClass;
    protected String type;
    protected String weapon;
    protected int healthPoints;
    protected int manaPoints;
    protected int maxHealthPoints;
    protected int maxManaPoints;

    //Status effects
    int damageReductionTurns = 0;
    double damageReductionPercent = 0.0;
    int stunnedTurns = 0;
    // If true, the stun was applied this turn and will only become active next turn
    boolean stunJustApplied = false;

    // last skill metadata (helpful for UI logging)
    protected int lastSkillHits = 0;
    protected int lastSkillDamage = 0;

    public Character(String name, String charClass, String type, String weapon, int healthPoints, int manaPoints) {
        this.name = name;
        this.charClass = charClass;
        this.type = type;
        this.weapon = weapon;
        this.healthPoints = healthPoints;
        this.manaPoints = manaPoints;
        this.maxHealthPoints = healthPoints;
        this.maxManaPoints = manaPoints;
    }

    //simply getname
    public String getName() { return name; }

    //when character takes damage
    @Override
    public void takeDamage(int damage){
        if (damageReductionTurns > 0 && damageReductionPercent > 0.0) {
            int reduced = (int) Math.round(damage * (1.0 - damageReductionPercent));
            damage = Math.max(0, reduced);
        }
        healthPoints -= damage;
    }

    //heals character
    public void healAlly(int heal){
        healthPoints += heal;
        if (healthPoints > maxHealthPoints) {
            healthPoints = maxHealthPoints;
        }
        System.out.println(name + " healed for " + heal + " HP. Current HP: " + healthPoints);
    }

    //restores mana
    public void restoreMP(int mp){
        manaPoints += mp;
        if (manaPoints > maxManaPoints) {
            manaPoints = maxManaPoints;
        }
        System.out.println(name + " restored " + mp + " MP. Current MP: " + manaPoints);
    }

    //flag to check if character is alive
    @Override
    public boolean isAlive(){
        return healthPoints > 0;
    }

    public boolean isStunned(){
        // stun is considered active only after the turn it was applied
        return stunnedTurns > 0 && !stunJustApplied;
    }

    public void applyStun(int turns) {
        // mark stun to take effect starting next turn
        stunnedTurns = Math.max(stunnedTurns, turns);
        stunJustApplied = true;
        System.out.println(name + " will be stunned for " + stunnedTurns + " turn(s) starting next turn.");
    }

    public boolean isManaSufficient(int cost){
        return manaPoints >= cost;
    }

    //basically timer
    public void tickStatus(){
        // If a stun was just applied this turn, make it active next turn but don't decrement yet
        if (stunJustApplied) {
            // clear the just-applied flag so the stun becomes active on subsequent turns
            stunJustApplied = false;
        } else {
            if (stunnedTurns > 0) stunnedTurns--;
        }
        if(damageReductionTurns > 0){
            damageReductionTurns--;
            if(damageReductionTurns == 0) damageReductionPercent = 0.0;
        }
    }

    public final void useSkill(int skill, MobNPC target, Character ally, Character[] party) {
        // reset last skill metadata
        lastSkillHits = 0;
        lastSkillDamage = 0;

        if (isStunned()) {
            System.out.println(name + " is stunned and cannot use any skills!");
            return;
        }

        performSkill(skill, target, ally, party);
    }

    // implement actual skill effects here
    protected abstract void performSkill(int skill, MobNPC target, Character ally, Character[] party);

    // skill list per character
    public abstract String[] getSkillList();

    // target type per skill
    public abstract String getSkillTargetType(int skill);
}

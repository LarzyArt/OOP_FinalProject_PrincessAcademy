package charmees.finalproj.util;

public interface Damageable {
    // Apply damage to mobs
    void takeDamage(int damage);

    // Return true if entity's health is > 0
    boolean isAlive();
}

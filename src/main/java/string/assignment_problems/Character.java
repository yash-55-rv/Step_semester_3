public class Character {
    private int currentHealth;
    private final int maxHealth;

    public Character(int maxHealth) {
        if (maxHealth <= 0) {
            throw new IllegalArgumentException("Maximum health must be greater than 0.");
        }
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void takeDamage(int amount) {
        if (amount < 0) return;
        this.currentHealth = this.currentHealth - amount;
        if (this.currentHealth < 0) {
            this.currentHealth = 0;
        }
    }

    public void heal(int amount) {
        if (amount < 0) return;
        this.currentHealth = this.currentHealth + amount;
        if (this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        }
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    // ========================================================
    // THE MAIN METHOD (The entry point to run your code)
    // ========================================================
    public static void main(String[] args) {
        // 1. Create a character with 100 max health
        Character c = new Character(100);
        System.out.println("Starting health: " + c.getCurrentHealth()); // Output: 100

        // 2. Take 30 damage -> should be 70
        c.takeDamage(30);
        System.out.println("After 30 damage: " + c.getCurrentHealth()); // Output: 70

        // 3. Heal 50 -> should cap at 100
        c.heal(50);
        System.out.println("After 50 heal (capped): " + c.getCurrentHealth()); // Output: 100

        // 4. Take 150 damage -> should floor at 0
        c.takeDamage(150);
        System.out.println("After 150 damage (floored): " + c.getCurrentHealth()); // Output: 0
    }
}

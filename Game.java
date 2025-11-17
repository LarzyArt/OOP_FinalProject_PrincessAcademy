// Scanner removed; GUI uses Swing for input

//Everyone contributed to making this file and methods
public class Game {
    public static void main(String[] args) {
        // No console Scanner - GUI will handle input
        Character[] characters = {
            new Audry(),
            new Giantha(),
            new Lynzi(),
            new Shiera(),
            new Lazuli(),
        };
        MobNPC[] mobs = {
            //Boss
            new ChapterSix(), 
            //Minibosses
            new ChapterOne(),
            new ChapterTwo(),
            new ChapterThree(),
            new ChapterFour(),
            new ChapterFive(),
            //Mobs
            new NormalEnemy("Student Puppet", "Minion", "Melee", "Wooden Sword", 100,5),
            new NormalEnemy("Corrupted Skeleton", "Minion", "Melee", "Bone Sword", 120,2),
            new NormalEnemy("Water Sprite", "Minion", "Ranged", "Water Magic", 130,1),
            new NormalEnemy("Echo Imp", "Minion", "Ranged", "Sound Magic", 130, 4),
            new NormalEnemy("Astral Glob", "Minion", "Melee", "Astral Slime", 110, 3),
            new NormalEnemy("Princess Puppet", "Minion", "Melee", "wand", 100,5),
            new NormalEnemy("Magma Skeleton", "Minion", "Melee", "Bone Sword", 120,2),
            new NormalEnemy("Water Blob", "Minion", "Ranged", "Water Magic", 130,1),
            new NormalEnemy("Resonance Goblin", "Minion", "Ranged", "Sound Magic", 130, 4),
            new NormalEnemy("Moon Sprite", "Minion", "Melee", "Astral magic", 110, 3),
        };

        GameSystem gameSystem = new GameSystem(characters, mobs);
        
        // Create and display the main menu UI using Swing Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            new SplashScreenUI(gameSystem).setVisible(true);
        });

        // No console scanner to close
    }
}

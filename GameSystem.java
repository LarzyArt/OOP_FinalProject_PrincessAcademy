//Everyone contributed to making this file and methods
import java.util.ArrayList;

public class GameSystem {
    protected Character[] characters;
    protected MobNPC[] mobs;
    protected int currentChapter = -1;
    public Dialogue[] dialogue;
    // flag set when player requests to abort the current battle and return to the menu
    protected boolean abortBattle = false;
    // flag set when the last battle was aborted so StartGame can skip post-battle dialogues
    protected boolean lastBattleAborted = false;
    // highest chapter number that is unlocked (player can access). Start at 1 so chapter 1 is available.
    public int highestChapterUnlocked = 1;
    // maximum number of chapters in the game
    private static final int MAX_CHAPTER = 6;
    // track which chapter dialogues have been shown (index 0 unused, 1-6 for chapters)
    private boolean[] preDialoguesShown = new boolean[7];
    private boolean[] postDialoguesShown = new boolean[7];

    public GameSystem(Character[] characters, MobNPC[] mobs) {
        this.characters = characters;
        this.mobs = mobs;
    }

    // --- Accessors for other game components to use ---
    public Character[] getCharacters() { return characters; }
    public MobNPC[] getMobs() { return mobs; }
    // Note: Scanner/console input removed for GUI-only usage
    public int getCurrentChapter() { return currentChapter; }
    public void setCurrentChapter(int c) { this.currentChapter = c; }
    public boolean isAbortBattle() { return abortBattle; }
    public void setAbortBattle(boolean v) { this.abortBattle = v; }
    public boolean isLastBattleAborted() { return lastBattleAborted; }
    public void setLastBattleAborted(boolean v) { this.lastBattleAborted = v; }

    public ArrayList<MobNPC> EnemiesforChapter(int chapter) {
        ArrayList<MobNPC> enemies = new ArrayList<>();
        switch (chapter) {
            case 1:
                enemies.add(new ChapterOne());
                enemies.add(new NormalEnemy("Water Sprite", "Minion", "Ranged", "Water Magic", 130,1));
                enemies.add(new NormalEnemy("Water Blob", "Minion", "Ranged", "Water Magic", 130,1));
                break;    
            case 2:
                enemies.add(new ChapterTwo());
                enemies.add(new NormalEnemy("Magma Skeleton", "Minion", "Melee", "Bone Sword", 120,2));
                enemies.add(new NormalEnemy("Corrupted Skeleton", "Minion", "Melee", "Bone Sword", 120,2));
                break;
            case 3:
                enemies.add(new ChapterThree());
                enemies.add(new NormalEnemy("Astral Glob", "Minion", "Melee", "Astral Slime", 110, 3));
                enemies.add(new NormalEnemy("Moon Sprite", "Minion", "Melee", "Astral magic", 110, 3));
                break;
            case 4:
                enemies.add(new ChapterFour());
                enemies.add(new NormalEnemy("Echo Imp", "Minion", "Ranged", "Sound Magic", 130, 4));
                enemies.add(new NormalEnemy("Resonance Goblin", "Minion", "Ranged", "Sound Magic", 130, 4));
                break;
            case 5:
                enemies.add(new ChapterFive());
                enemies.add(new NormalEnemy("Student Puppet", "Minion", "Melee", "Wooden Sword", 100,5));
                enemies.add(new NormalEnemy("Princess Puppet", "Minion", "Melee", "wand", 100,5));
                break;
            case 6:
                enemies.add(new ChapterSix());
                break;
        }
        return enemies;
    }

    /**
     * Returns true if the given chapter is unlocked (player can access it).
     * Chapters become unlocked only sequentially; you cannot skip ahead.
     */
    public boolean isChapterUnlocked(int chapter){
        if (chapter <= 0) return false;
        return chapter <= highestChapterUnlocked;
    }

    /**
     * Attempt to unlock the specified chapter. This will only succeed if the
     * chapter is the immediate next chapter after the current highest unlocked
     * chapter (prevents skipping). Use this when unlocking the next chapter
     * after the player completes the previous one.
     */
    public void markUnlockChapter(int chapter){
        if (chapter <= highestChapterUnlocked) return; // already unlocked or lower
        if (chapter == highestChapterUnlocked + 1 && chapter <= MAX_CHAPTER) {
            highestChapterUnlocked = chapter;
        }
        // otherwise ignore: cannot unlock non-sequential chapters
    }

    /**
     * Convenience helper: call this when a chapter is completed to unlock the next one.
     */
    public void markChapterCompleted(int completedChapter){
        markUnlockChapter(completedChapter + 1);
    }

    /**
     * Check if a chapter's pre-dialogue has already been shown.
     */
    public boolean hasPreDialogueBeenShown(int chapter) {
        if (chapter < 1 || chapter > MAX_CHAPTER) return true;
        return preDialoguesShown[chapter];
    }

    /**
     * Mark a chapter's pre-dialogue as shown so it won't play again.
     */
    public void markPreDialogueAsShown(int chapter) {
        if (chapter >= 1 && chapter <= MAX_CHAPTER) {
            preDialoguesShown[chapter] = true;
        }
    }

    /**
     * Check if a chapter's post-dialogue has already been shown.
     */
    public boolean hasPostDialogueBeenShown(int chapter) {
        if (chapter < 1 || chapter > MAX_CHAPTER) return true;
        return postDialoguesShown[chapter];
    }

    /**
     * Mark a chapter's post-dialogue as shown so it won't play again.
     */
    public void markPostDialogueAsShown(int chapter) {
        if (chapter >= 1 && chapter <= MAX_CHAPTER) {
            postDialoguesShown[chapter] = true;
        }
    }
}

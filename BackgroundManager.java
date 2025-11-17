

/**
 * Abstract class for managing chapter backgrounds and text area backgrounds
 * Extend this class for each chapter to customize backgrounds
 */
public abstract class BackgroundManager {
    protected int chapter;

    public BackgroundManager(int chapter) {
        this.chapter = chapter;
    }

    /**
     * Get the main battle background image path
     */
    public abstract String getMainBackgroundPath();

    /**
     * Factory method to get the appropriate BackgroundManager for a chapter
     */
    public static BackgroundManager getManager(int chapter) {
        switch (chapter) {
            case 1:
                return new Chapter1BackgroundManager();
            case 2:
                return new Chapter2BackgroundManager();
            case 3:
                return new Chapter3BackgroundManager();
            case 4:
                return new Chapter4BackgroundManager();
            case 5:
                return new Chapter5BackgroundManager();
            case 6:
                return new Chapter6BackgroundManager();
            default:
                return new DefaultBackgroundManager(chapter);
        }
    }
}


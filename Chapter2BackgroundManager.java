import java.io.File;

public class Chapter2BackgroundManager extends BackgroundManager {
    public Chapter2BackgroundManager() {
        super(2);
    }

    @Override
    public String getMainBackgroundPath() {
        String chapterPath = "assets/backgrounds/Chapter2bg.png";
        File file = new File(chapterPath);
        if (file.exists()) {
            return chapterPath;
        } else {
            System.out.println("Warning: " + chapterPath + " not found. Using default background.");
            return "assets/backgrounds/Chapterdefaultbg.png";
        }
    }

}

import java.io.File;

public class Chapter1BackgroundManager extends BackgroundManager {
    public Chapter1BackgroundManager() {
        super(1);
    }

    @Override
    public String getMainBackgroundPath() {
        String chapterPath = "assets/backgrounds/Chapter1bg.png";
        File file = new File(chapterPath);
        if (file.exists()) {
            return chapterPath;
        } else {
            System.out.println("Warning: " + chapterPath + " not found. Using default background.");
            return "assets/backgrounds/Chapterdefaultbg.png";
        }
    }

}

import java.io.File;

public class Chapter3BackgroundManager extends BackgroundManager {
    public Chapter3BackgroundManager() {
        super(3);
    }

    @Override
    public String getMainBackgroundPath() {
        String chapterPath = "assets/backgrounds/Chapter3bg.png";
        File file = new File(chapterPath);
        if (file.exists()) {
            return chapterPath;
        } else {
            System.out.println("Warning: " + chapterPath + " not found. Using default background.");
            return "assets/backgrounds/Chapterdefaultbg.png";
        }
    }

}

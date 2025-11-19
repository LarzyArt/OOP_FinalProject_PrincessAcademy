import java.io.File;

public class Chapter4BackgroundManager extends BackgroundManager {
    public Chapter4BackgroundManager() {
        super(4);
    }

    @Override
    public String getMainBackgroundPath() {
        String chapterPath = "assets/backgrounds/Chapter4bg.png";
        File file = new File(chapterPath);
        if (file.exists()) {
            return chapterPath;
        } else {
            System.out.println("Warning: " + chapterPath + " not found. Using default background.");
            return "assets/backgrounds/Chapterdefaultbg.png";
        }
    }

}

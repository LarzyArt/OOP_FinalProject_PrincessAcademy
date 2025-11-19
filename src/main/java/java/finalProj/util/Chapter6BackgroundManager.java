import java.io.File;

public class Chapter6BackgroundManager extends BackgroundManager {
    public Chapter6BackgroundManager() {
        super(6);
    }

    @Override
    public String getMainBackgroundPath() {
        String chapterPath = "assets/backgrounds/Chapter6bg.png";
        File file = new File(chapterPath);
        if (file.exists()) {
            return chapterPath;
        } else {
            System.out.println("Warning: " + chapterPath + " not found. Using default background.");
            return "assets/backgrounds/Chapterdefaultbg.png";
        }
    }
    
}

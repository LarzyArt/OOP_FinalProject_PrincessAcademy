package charmees.finalproj.util;

import java.io.File;

public class Chapter4BackgroundManager extends BackgroundManager {
    public Chapter4BackgroundManager() {
        super(4);
    }

    @Override
    public String getMainBackgroundPath() {
        String chapterPath = "assets/backgrounds/Chapter4bg.png";
        java.net.URL res = getClass().getResource("/" + chapterPath);
        if (res != null) return chapterPath;
        File file = new File(chapterPath);
        if (file.exists()) return chapterPath;
        System.out.println("Warning: " + chapterPath + " not found in classpath or filesystem. Using default background.");
        return "assets/backgrounds/Chapterdefaultbg.png";
    }

}

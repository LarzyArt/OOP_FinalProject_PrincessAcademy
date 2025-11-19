package charmees.finalproj.util;

import java.io.File;

public class Chapter3BackgroundManager extends BackgroundManager {
    public Chapter3BackgroundManager() {
        super(3);
    }

    @Override
    public String getMainBackgroundPath() {
        String chapterPath = "assets/backgrounds/Chapter3bg.png";
        java.net.URL res = getClass().getResource("/" + chapterPath);
        if (res != null) return chapterPath;
        File file = new File(chapterPath);
        if (file.exists()) return chapterPath;
        System.out.println("Warning: " + chapterPath + " not found in classpath or filesystem. Using default background.");
        return "assets/backgrounds/Chapterdefaultbg.png";
    }

}

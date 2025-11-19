package charmees.finalproj.util;

import java.io.File;

public class Chapter5BackgroundManager extends BackgroundManager {
    public Chapter5BackgroundManager() {
        super(5);
    }

    @Override
    public String getMainBackgroundPath() {
        String chapterPath = "assets/backgrounds/Chapter5bg.png";
        java.net.URL res = getClass().getResource("/" + chapterPath);
        if (res != null) return chapterPath;
        File file = new File(chapterPath);
        if (file.exists()) return chapterPath;
        System.out.println("Warning: " + chapterPath + " not found in classpath or filesystem. Using default background.");
        return "assets/backgrounds/Chapterdefaultbg.png";
    }

}

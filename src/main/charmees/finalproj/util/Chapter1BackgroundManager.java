package charmees.finalproj.util;

import java.io.File;

public class Chapter1BackgroundManager extends BackgroundManager {
    public Chapter1BackgroundManager() {
        super(1);
    }

    @Override
    public String getMainBackgroundPath() {
        String chapterPath = "assets/backgrounds/Chapter1bg.png";
        // Check classpath first (resources in src/main/resources)
        java.net.URL res = getClass().getResource("/" + chapterPath);
        if (res != null) return chapterPath;

        // Then check filesystem relative to working dir
        File file = new File(chapterPath);
        if (file.exists()) return chapterPath;

        System.out.println("Warning: " + chapterPath + " not found in classpath or filesystem. Using default background.");
        return "assets/backgrounds/Chapterdefaultbg.png";
    }

}

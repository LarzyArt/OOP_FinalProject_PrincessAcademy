package charmees.finalproj.util;

 

public class Chapter1BackgroundManager extends BackgroundManager {
    public Chapter1BackgroundManager() {
        super(1);
    }

    @Override
    public String getMainBackgroundPath() {
        String chapterPath = "/assets/backgrounds/Chapter1bg.png";
        java.net.URL res = getClass().getResource(chapterPath);
        if (res != null) return chapterPath;
        String noSlash = chapterPath.startsWith("/") ? chapterPath.substring(1) : chapterPath;
        java.io.File f = FontManager.locateResourceFile(noSlash);
        if (f != null && f.exists()) return chapterPath;
        return "/assets/backgrounds/Chapterdefaultbg.png";
    }

}

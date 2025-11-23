package charmees.finalproj.util;

 
public class Chapter3BackgroundManager extends BackgroundManager {
    public Chapter3BackgroundManager() {
        super(3);
    }

    @Override
    public String getMainBackgroundPath() {
        String chapterPath = "/assets/backgrounds/Chapter3bg.png";
        java.net.URL res = getClass().getResource(chapterPath);
        if (res != null) return chapterPath;
        String noSlash = chapterPath.startsWith("/") ? chapterPath.substring(1) : chapterPath;
        java.io.File f = FontManager.locateResourceFile(noSlash);
        if (f != null && f.exists()) return chapterPath;
        return "/assets/backgrounds/Chapterdefaultbg.png";
    }

}

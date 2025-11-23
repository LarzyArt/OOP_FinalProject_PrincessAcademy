package charmees.finalproj.util;

 
public class Chapter4BackgroundManager extends BackgroundManager {
    public Chapter4BackgroundManager() {
        super(4);
    }

    @Override
    public String getMainBackgroundPath() {
        String chapterPath = "/assets/backgrounds/Chapter4bg.png";
        java.net.URL res = getClass().getResource(chapterPath);
        if (res != null) return chapterPath;
        String noSlash = chapterPath.startsWith("/") ? chapterPath.substring(1) : chapterPath;
        java.io.File f = FontManager.locateResourceFile(noSlash);
        if (f != null && f.exists()) return chapterPath;
        return "/assets/backgrounds/Chapterdefaultbg.png";
    }

}

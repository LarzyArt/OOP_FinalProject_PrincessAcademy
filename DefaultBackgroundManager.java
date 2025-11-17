// Default implementation - can be used for any chapter
public class DefaultBackgroundManager extends BackgroundManager {
    public DefaultBackgroundManager(int chapter) {
        super(chapter);
    }

    @Override
    public String getMainBackgroundPath() {
        return "assets/backgrounds/Chapterdefaultbg.png";
    }
}

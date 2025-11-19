import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

public final class FontManager {
    private static volatile Font pixelFont;

    private FontManager() {}

    private static void ensureLoaded() {
        if (pixelFont != null) return;
        synchronized (FontManager.class) {
            if (pixelFont != null) return;
            try (InputStream is = FontManager.class.getResourceAsStream("/assets/fonts/PressStart2P-Regular.ttf")) {
                if (is != null) {
                    pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge.registerFont(pixelFont);
                } else {
                    // Fallback to a system font if resource not found
                    pixelFont = new Font("Dialog", Font.PLAIN, 24);
                }
            } catch (Exception e) {
                // If loading fails, fall back to a plain font
                System.out.println("FontManager: failed to load pixel font: " + e.getMessage());
                pixelFont = new Font("Dialog", Font.PLAIN, 24);
            }
        }
    }

    public static Font getPixelFont(float size) {
        ensureLoaded();
        try {
            return pixelFont.deriveFont(size);
        } catch (Exception e) {
            return new Font("Dialog", Font.PLAIN, (int) size);
        }
    }

    public static Font getPixelFont() {
        return getPixelFont(24f);
    }
}

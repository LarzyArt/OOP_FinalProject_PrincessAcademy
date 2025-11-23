package charmees.finalproj.util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.net.URL;

public class FontManager {
    private static Font pixelFont = null;

    private static void ensureLoaded() {
        if (pixelFont != null) return;
        try {
            // Try a few candidate font resource names (project uses PressStart2P.ttf)
            String[] candidates = new String[] {
                "/assets/fonts/pixel_font.ttf",
                "/assets/fonts/PressStart2P-Regular.ttf",
                "/assets/fonts/PressStart2P.ttf"
            };
            URL fontUrl = null;
            for (String c : candidates) {
                fontUrl = FontManager.class.getResource(c);
                if (fontUrl != null) break;
            }

            if (fontUrl != null) {
                Font base = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
                pixelFont = base.deriveFont(Font.PLAIN, 24f);
                try {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge.registerFont(pixelFont);
                } catch (Exception ignore) {}
            } else {
                // Fallback: try to load from filesystem (useful when running from IDE without resources copied)
                try {
                    java.io.File f = new java.io.File("src/main/resources/assets/fonts/PressStart2P-Regular.ttf");
                    if (f.exists()) {
                        Font base = Font.createFont(Font.TRUETYPE_FONT, f);
                        pixelFont = base.deriveFont(Font.PLAIN, 24f);
                    } else {
                        pixelFont = new Font("Dialog", Font.PLAIN, 24);
                    }
                } catch (Exception ex) {
                    pixelFont = new Font("Dialog", Font.PLAIN, 24);
                }
            }
        } catch (Exception e) {
            System.out.println("FontManager: failed to load pixel font: " + e.getMessage());
            pixelFont = new Font("Dialog", Font.PLAIN, 24);
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

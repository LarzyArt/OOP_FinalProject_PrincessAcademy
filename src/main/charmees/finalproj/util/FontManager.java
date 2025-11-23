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

            // Also try ClassLoader lookup without leading slash (some run configs place resources on classpath differently)
            if (fontUrl == null) {
                for (String c : candidates) {
                    String noSlash = c.startsWith("/") ? c.substring(1) : c;
                    java.net.URL u = FontManager.class.getClassLoader().getResource(noSlash);
                    if (u != null) { fontUrl = u; break; }
                }
            }

            if (fontUrl != null) {
                Font base = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
                pixelFont = base.deriveFont(Font.PLAIN, 24f);
                try {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge.registerFont(pixelFont);
                } catch (Exception ignore) {}
            } else {
                // Fallback: try to locate font file on filesystem by searching up from CWD
                try {
                    // Candidate filenames to look for
                    String[] names = new String[] {"assets/fonts/PressStart2P-Regular.ttf", "assets/fonts/PressStart2P.ttf", "assets/fonts/pixel_font.ttf"};
                    java.io.File found = null;
                    for (String n : names) {
                        java.io.File f = findFileUpwards(n, 5);
                        if (f != null && f.exists()) { found = f; break; }
                    }
                    if (found != null) {
                        Font base = Font.createFont(Font.TRUETYPE_FONT, found);
                        pixelFont = base.deriveFont(Font.PLAIN, 24f);
                    } else {
                        // last resort to a system font
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

    // Search upward from current working directory for a relative path (maxDepth levels up)
    private static java.io.File findFileUpwards(String relativePath, int maxDepth) {
        try {
            java.io.File cur = new java.io.File(System.getProperty("user.dir"));
            for (int i = 0; i <= maxDepth; i++) {
                java.io.File candidate = new java.io.File(cur, relativePath);
                if (candidate.exists()) return candidate;
                cur = cur.getParentFile();
                if (cur == null) break;
            }
        } catch (Exception e) {
            // ignore
        }
        return null;
    }

    // Public helper so other UI classes can locate resource files regardless of working dir
    public static java.io.File locateResourceFile(String relativePath) {
        String rel = relativePath.startsWith("/") ? relativePath.substring(1) : relativePath;
        java.io.File cur = new java.io.File(System.getProperty("user.dir"));
        for (int depth = 0; depth < 8 && cur != null; depth++) {
            // Try several candidate prefixes at this directory
            String[] prefixes = new String[] {"", "src/main/resources/", "resources/", "assets/"};
            for (String p : prefixes) {
                java.io.File cand = new java.io.File(cur, p + rel);
                if (cand.exists()) return cand;
            }
            cur = cur.getParentFile();
        }

        // Last attempt: try relative to working dir directly
        java.io.File direct = new java.io.File(rel);
        if (direct.exists()) return direct;

        return null;
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

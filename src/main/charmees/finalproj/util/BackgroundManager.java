package charmees.finalproj.util;

/**
 * Abstract class for managing chapter backgrounds and text area backgrounds
 * Extend this class for each chapter to customize backgrounds
 */
public abstract class BackgroundManager {
    protected int chapter;

    public BackgroundManager(int chapter) {
        this.chapter = chapter;
    }

    // Get the main battle background image path 
    public abstract String getMainBackgroundPath();

    public static BackgroundManager getManager(int chapter) {
        switch (chapter) {
            case 1:
                return new Chapter1BackgroundManager();
            case 2:
                return new Chapter2BackgroundManager();
            case 3:
                return new Chapter3BackgroundManager();
            case 4:
                return new Chapter4BackgroundManager();
            case 5:
                return new Chapter5BackgroundManager();
            case 6:
                return new Chapter6BackgroundManager();
            default:
                return new DefaultBackgroundManager(chapter);
        }
    }

    public static java.awt.Image loadImage(String resourcePath) {
        try {
            java.net.URL res = BackgroundManager.class.getResource(resourcePath);
            if (res == null) {
                String noSlash = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
                res = BackgroundManager.class.getClassLoader().getResource(noSlash);
            }
            if (res != null) {
                try {
                    return javax.imageio.ImageIO.read(res);
                } catch (Exception e) {
                    System.out.println("BackgroundManager: failed to read image from classpath: " + res + " -> " + e.getMessage());
                }
            }

            try {
                java.io.File f = charmees.finalproj.util.FontManager.locateResourceFile(resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath);
                if (f != null && f.exists()) {
                    return javax.imageio.ImageIO.read(f);
                }
            } catch (Exception e) {
                // ignore
            }

            String fs = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
            java.io.File f2 = new java.io.File("out/" + fs);
            if (f2.exists()) {
                try { return javax.imageio.ImageIO.read(f2); } catch (Exception ignore) {}
            }
            java.io.File f3 = new java.io.File("bin/" + fs);
            if (f3.exists()) {
                try { return javax.imageio.ImageIO.read(f3); } catch (Exception ignore) {}
            }

            System.out.println("BackgroundManager: resource not found: " + resourcePath);
        } catch (Exception ex) {
            System.out.println("BackgroundManager: error loading " + resourcePath + " -> " + ex.getMessage());
        }
        return null;
    }

    // Load as ImageIcon (preserves animated GIFs). Returns null if not found.
    public static javax.swing.ImageIcon loadIcon(String resourcePath) {
        try {
            java.net.URL res = BackgroundManager.class.getResource(resourcePath);
            if (res == null) {
                String noSlash = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
                res = BackgroundManager.class.getClassLoader().getResource(noSlash);
            }
            if (res != null) {
                try {
                    return new javax.swing.ImageIcon(res);
                } catch (Exception e) {
                    System.out.println("BackgroundManager: failed to create ImageIcon from classpath: " + res + " -> " + e.getMessage());
                }
            }

            try {
                java.io.File f = charmees.finalproj.util.FontManager.locateResourceFile(resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath);
                if (f != null && f.exists()) {
                    return new javax.swing.ImageIcon(f.getAbsolutePath());
                }
            } catch (Exception e) {
                // ignore
            }

            String fs = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
            java.io.File f2 = new java.io.File("out/" + fs);
            if (f2.exists()) {
                try { return new javax.swing.ImageIcon(f2.getAbsolutePath()); } catch (Exception ignore) {}
            }
            java.io.File f3 = new java.io.File("bin/" + fs);
            if (f3.exists()) {
                try { return new javax.swing.ImageIcon(f3.getAbsolutePath()); } catch (Exception ignore) {}
            }

            System.out.println("BackgroundManager: icon not found: " + resourcePath);
        } catch (Exception ex) {
            System.out.println("BackgroundManager: error loading icon " + resourcePath + " -> " + ex.getMessage());
        }
        return null;
    }
}


package charmees.finalproj.ui;

import javax.swing.*;
import java.awt.*;
import charmees.finalproj.core.*;
import charmees.finalproj.util.*;

public class SplashScreenUI extends JFrame{
    private float opacity = 0f;
    private Timer FadeInTimer, FadeOutTimer, holdTimer;
    private Image BackgroundImage;
    private GameSystem gameSystem;

    public SplashScreenUI(GameSystem gameSystem){
        this.gameSystem = gameSystem;

        // Set icon image
        try {
            java.net.URL iconUrl = SplashScreenUI.class.getResource("/assets/icon/icon.png");
            if (iconUrl != null) setIconImage(new ImageIcon(iconUrl).getImage());
            else setIconImage(new ImageIcon("assets/icon/icon.png").getImage());
        } catch (Exception e) {
            System.out.println("Icon image not found.");
        }

        // Load background image
        java.net.URL splashUrl = SplashScreenUI.class.getResource("/assets/splashscreen.gif");
        if (splashUrl != null) BackgroundImage = new ImageIcon(splashUrl).getImage();
        else BackgroundImage = new ImageIcon("assets/splashscreen.gif").getImage();
        Font pixelFont = FontManager.getPixelFont(28f);
        // Frame settings
        setSize(800, 600);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0));

        // Panel to draw background image
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());

                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                
                // image centering
                int imageWidth = BackgroundImage.getWidth(this);
                int imageHeight = BackgroundImage.getHeight(this);
                int x = (getWidth() - imageWidth) / 2;
                int y = (getHeight() - imageHeight) / 2;
                g2d.drawImage(BackgroundImage, x, y, this);

                // text centering
                g2d.setColor(Color.WHITE);
                g2d.setFont(pixelFont);
                String text = "Team Charmees Presents";
                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(text);

                int textX = (getWidth() - textWidth) / 2;
                int textY = (getHeight() + imageHeight) / 2 + 40;

                g2d.drawString(text, textX, textY);
                g2d.dispose();
                
            }
        };
        setContentPane(panel);
        startAnimation();
    }

    private void startAnimation(){
        FadeInTimer = new Timer(40, e -> {
            opacity += 0.05f;
            if (opacity >= 1f) {
                opacity = 1f;
                FadeInTimer.stop();
                holdSplash();
            }
            repaint();
        });
        FadeInTimer.start();
    }

    private void holdSplash(){
        holdTimer = new Timer(1500, e -> {
            holdTimer.stop();
            startFadeOut();
        });
        holdTimer.setRepeats(false);
        holdTimer.start();
    }

    private void startFadeOut(){
        FadeOutTimer = new Timer(40, e -> {
            opacity -= 0.05f;
            if (opacity <= 0f) {
                opacity = 0f;
                FadeOutTimer.stop();
                dispose();
                // After splash closes, open the main menu
                openMainMenu();
            }
            repaint();
        });
        FadeOutTimer.start();
    }
    private void openMainMenu(){
        dispose();
        SwingUtilities.invokeLater(() -> new MainMenuUI(gameSystem).setVisible(true));
    }
}


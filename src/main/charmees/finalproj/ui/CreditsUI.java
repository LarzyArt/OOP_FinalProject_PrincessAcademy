package charmees.finalproj.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import charmees.finalproj.core.GameSystem;
import charmees.finalproj.util.FontManager;
import charmees.finalproj.util.BackgroundManager;

public class CreditsUI extends JFrame{
    private Image backgroundImage;
    private Font pixelFont = FontManager.getPixelFont(24f);

    public CreditsUI(GameSystem gameSystem){
        setTitle("Princess Academy: Credits");
        setSize(800, 600);// Larger window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set icon image
        try {
            java.net.URL iconUrl = CreditsUI.class.getResource("/assets/icon/icon.png");
            if (iconUrl != null) setIconImage(new ImageIcon(iconUrl).getImage());
            else {
                java.io.File iconFile = charmees.finalproj.util.FontManager.locateResourceFile("assets/icon/icon.png");
                if (iconFile != null && iconFile.exists()) setIconImage(new ImageIcon(iconFile.getAbsolutePath()).getImage());
            }
        } catch (Exception ignore) {}

        // background image (centralized loader)
        backgroundImage = BackgroundManager.loadImage("/assets/backgrounds/main_menubg.gif");
        
        //Main panel
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
    
        JLabel title = new JLabel("Game Credits", JLabel.CENTER);
        title.setFont(pixelFont.deriveFont(28f));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.WHITE);

        JTextArea creditsArea = new JTextArea();
        creditsArea.setFont(pixelFont.deriveFont(16f));
        creditsArea.setEditable(false);
        creditsArea.setOpaque(false);
        creditsArea.setForeground(Color.WHITE);
        creditsArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsArea.setText("""

                        === CREDITS ===

            Programmers:        BARAGA, LAURENCE
                                DAYDAYAN, KIMBERLY
                                ECARMA, MELODY
                                PEDROSA, NEILCEN
                                RODRIGO, ABIGAIL  
                                
            Art & Animation:   LARZ
            Battle Background: KAMI TANAN

            Music & Sound:     NON-EXISTENT
            
            Story & Writing:   LYNZY_002
                                (AKA: 2021-2022 LARZ)
                
            Testing:           HI MAAM!
            
                        === SPECIAL THANKS ===
                        
            Special Person 1: SIYA

            Special Person 2: SI KUAN

            Special Person 3: KATO SIYA
        """);

        JButton returnButton = new JButton("Return to Main Menu");
        returnButton.setFont(pixelFont.deriveFont(16f));
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.setBackground(new Color(255, 215, 0));

        returnButton.addActionListener(e -> {
            this.dispose(); // Close Credits UI
            MainMenuUI mainMenu = new MainMenuUI(gameSystem);
            mainMenu.setVisible(true); // Show main menu
        });

        returnButton. addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                returnButton.setBackground(new Color(255, 223, 0)); // Lighter gold on hover
            }

            public void mouseExited(MouseEvent e) {
                returnButton.setBackground(new Color(255, 215, 0)); // Original color
            }
        });

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(creditsArea);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(returnButton);

        add(panel);
    
    }
    
}

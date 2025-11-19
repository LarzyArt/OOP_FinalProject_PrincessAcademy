import javax.swing.*;
import java.awt.*;

public class MainMenuUI extends JFrame {
    private Image backgroundImage;

    public MainMenuUI(GameSystem gameSystem) {
        // Frame settings
        setTitle("Princess Academy: Worlds Collide");
        setSize(800, 600);  // Larger window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set icon image
        try {
            java.net.URL iconUrl = MainMenuUI.class.getResource("/assets/icon/icon.png");
            if (iconUrl != null) {
                setIconImage(new ImageIcon(iconUrl).getImage());
            } else {
                setIconImage(new ImageIcon("assets/icon/icon.png").getImage());
            }
        } catch (Exception e) {
            System.out.println("Icon image not found.");
        }

        // background image
        java.net.URL bgUrl = MainMenuUI.class.getResource("/assets/backgrounds/main_menubg.gif");
        if (bgUrl != null) {
            backgroundImage = new ImageIcon(bgUrl).getImage();
        } else {
            backgroundImage = new ImageIcon("assets/backgrounds/main_menubg.gif").getImage();
        }

        //Main panel
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 60));  // Increased vertical padding

        // Load custom pixel font
        Font pixelFont = FontManager.getPixelFont(24f);

        // Title label
        JLabel titleLabel = new JLabel("Princess Academy: Worlds Collide");
        titleLabel.setFont(pixelFont.deriveFont(20f));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.WHITE);  // Gold color

        // Buttons with pixel font
        JButton storyModeButton = createMenuButton("Play Story Mode", pixelFont);
        JButton statsButton = createMenuButton("Character/Mob Stats", pixelFont);
        JButton creditsButton = createMenuButton("Credits", pixelFont);
        JButton exitButton = createMenuButton("Exit Game", pixelFont);

        // Button actions
        storyModeButton.addActionListener(e -> {
            setVisible(false); // Hide menu while in story mode
            StoryModeUI storyModeUI = new StoryModeUI(gameSystem);
            storyModeUI.setVisible(true); // Show menu again when done
        });
        statsButton.addActionListener(e -> {
            setVisible(false); // Hide menu while in stats
            NPCInfoUI npcInfoUI = new NPCInfoUI(gameSystem);
            npcInfoUI.setVisible(true); // Show menu again when done
        });
        creditsButton.addActionListener(e -> {
                setVisible(false);
                CreditsUI creditsUI = new CreditsUI(gameSystem);
                creditsUI.setVisible(true);
            ;});
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        // Add components to panel
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));  // More space after title
        panel.add(storyModeButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));  // More space between buttons
        panel.add(statsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(creditsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(exitButton);
        
        // add panel to frame
        add(panel);
    }
        //button design
        private JButton createMenuButton(String text, Font font) {
            JButton button = new JButton(text);
            button.setFont(font.deriveFont(16f));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(400, 50));
            button.setFocusPainted(false);
            button.setBackground(new Color(255, 215, 0));
            button.setForeground(Color.DARK_GRAY);
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            //Hover effect
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(255, 235, 59)); // Lighter gold on hover
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(255, 215, 0)); // Original color
                }
            });

            return button;
    }
    
}

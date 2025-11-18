import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NPCInfoUI extends JFrame{
    
    private Image backgroundImage;
    private Font pixelFont = FontManager.getPixelFont(24f);

    public NPCInfoUI(GameSystem gameSystem){
        
        setTitle("Princess Academy: NPC Information");
        setSize(800, 600); // Larger window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set icon image (classpath first, then fallback to file path)
        Image icon = ResourceLoader.loadImage("/assets/icon/icon.png", "assets/icon/icon.png");
        if (icon != null) setIconImage(icon);

        // background image (classpath first)
        backgroundImage = ResourceLoader.loadImage("/assets/backgrounds/main_menubg.gif", "assets/backgrounds/main_menubg.gif");
        
        //Main panel
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        
        JLabel title = new JLabel("NPC Information", JLabel.CENTER);
        title.setFont(pixelFont.deriveFont(28f));
        title.setForeground(Color.WHITE);

        JTextArea infoArea = new JTextArea();
        infoArea.setBackground(new Color(255, 223, 0));
        infoArea.setForeground(Color.WHITE);
        infoArea.setFont(pixelFont.deriveFont(16f));
        infoArea.setEditable(false);
        infoArea.setOpaque(false);
        infoArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoArea.setText("""

                                === CHARACTERS ===

                Audry       - HP: 100 || MP: 25 || Class: Assassin
                Giantha     - HP: 250 || MP: 20 || Class: Tank
                Lazuli      - HP: 150 || MP: 30 || Class: Healer
                Lynzi       - HP: 170 || MP: 30 || Class: Dealer
                Shiera      - HP: 120 || MP: 25 || Class: Support


                                ===FINAL BOSS ===

                    Twinkle Star    - HP: 500 || Chapter: 6


                                === MINIBOSSES ===

                    Kassundre       - HP: 300 || Chapter: 5
                    Resonara        - HP: 310 || Chapter: 4
                    Eclipse Core    - HP: 350 || Chapter: 3
                    Lava Beast      - HP: 350 || Chapter: 2
                    Siren Empress   - HP: 320 || Chapter: 1


                                === MOBS ===
                    
                Student Puppet      - HP: 100 || Chapter: 5
                Princess Puppet     - HP: 100 || Chapter: 5
                Echo Imp            - HP: 130 || Chapter: 4
                Resonance Goblin    - HP: 130 || Chapter: 4
                Astral Glob         - HP: 110 || Chapter: 3
                Moon Sprite         - HP: 110 || Chapter: 3
                Magma Skeleton      - HP: 120 || Chapter: 2
                Corrupted Skeleton  - HP: 120 || Chapter: 2
                Water Sprite        - HP: 130 || Chapter: 1
                Water Blob          - HP: 130 || Chapter: 1
                
                """);

        JScrollPane scroll = new JScrollPane(infoArea);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        JButton button = new JButton("Return to Main Menu");
        button.setFont(pixelFont.deriveFont(16f));
        button.setBackground(new Color(255, 215, 0));
        button.setMaximumSize(new Dimension(450, 100));
        button.setForeground(Color.DARK_GRAY);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);

        button.addActionListener(e -> {
            this.dispose(); // Close Story Mode UI
            MainMenuUI mainMenu = new MainMenuUI(gameSystem);
            mainMenu.setVisible(true); // Show main menu
        });

        button. addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(255, 223, 0)); // Lighter gold on hover
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(255, 215, 0)); // Original color
            }
        });

        panel.add(title, BorderLayout.NORTH);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(button, BorderLayout.CENTER);

        add(panel);
    }
}

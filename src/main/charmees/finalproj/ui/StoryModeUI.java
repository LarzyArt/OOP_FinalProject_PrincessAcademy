package charmees.finalproj.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import charmees.finalproj.core.GameSystem;
import charmees.finalproj.util.FontManager;
import charmees.finalproj.core.Dialogue;

public class StoryModeUI extends JFrame {
    private GameSystem gameSystem;
    private Image backgroundImage;
    private Font pixelFont = FontManager.getPixelFont(24f);
    public int chapter;

    public StoryModeUI(GameSystem gameSystem) {
        this.gameSystem = gameSystem;

        setTitle("Princess Academy: Story Mode");
        setSize(800, 600);// Larger window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set icon image
        try {
            java.net.URL iconUrl = StoryModeUI.class.getResource("/assets/icon/icon.png");
            if (iconUrl != null) setIconImage(new ImageIcon(iconUrl).getImage());
            else {
                java.io.File iconFile = charmees.finalproj.util.FontManager.locateResourceFile("assets/icon/icon.png");
                if (iconFile != null && iconFile.exists()) setIconImage(new ImageIcon(iconFile.getAbsolutePath()).getImage());
            }
        } catch (Exception e) {
            System.out.println("Icon image not found.");
        }

        // background image (centralized loader)
        backgroundImage = charmees.finalproj.util.BackgroundManager.loadImage("/assets/backgrounds/main_menubg.gif");

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
        panel.setOpaque(false);
        // Load custom pixel font

    JLabel titleLabel = new JLabel("==== Story Mode ====", JLabel.CENTER);
    titleLabel.setFont(pixelFont.deriveFont(28f));
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    String[] Chapters = {
        "Chapter 1: Battle of the Sea",
        "Chapter 2: The Survival",
        "Chapter 3: Darkness and Void",
        "Chapter 4: Heart of the Living Forest",
        "Chapter 5: The Castle Memoir",
        "Chapter 6: The Final Show"
    };


    panel.add(titleLabel);
    panel.add(Box.createRigidArea(new Dimension(0, 20)));

    JButton IntroButton = createIntroButton();
    panel.add(IntroButton);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));

    for(int i = 0; i < Chapters.length; i++){
        JButton button = createStoryButton(Chapters[i], i + 1);  // Pass chapter number (1-6)
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }
    JButton endingButton = createEndingButton();
        panel.add(endingButton);

    JButton returnButton = createBackButton();
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(returnButton);

        add(panel);
    }

    /**
     * Dispose current StoryModeUI and open a fresh one so the buttons
     * are rebuilt (reflecting any newly unlocked chapters).
     */
    private void showRefreshed() {
        this.dispose();
        StoryModeUI refreshed = new StoryModeUI(gameSystem);
        refreshed.setVisible(true);
    }

    private JButton createIntroButton(){
        JButton button = new JButton("Intro: Prologue");
        button.setFont(pixelFont.deriveFont(16f));
        button.setBackground(new Color(255, 215, 0));
        button.setForeground(Color.DARK_GRAY);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(getMaximumSize());
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addActionListener(e -> {
            // hide story UI while dialogue runs
            StoryModeUI.this.setVisible(false);

            // fade music and show intro dialogue
            Dialogue dlg = new Dialogue();
            String[] introLines = dlg.prologueDialogue;
            new DialogueUI(introLines, () -> {
                showRefreshed();
            });
        });

        button. addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(255, 223, 0)); // Lighter gold on hover
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(255, 215, 0)); // Original color
            }
        });
        
        return button;
    }

    private JButton createStoryButton(String text, int chapterNumber){
        JButton button = new JButton(text);
        button.setFont(pixelFont.deriveFont(16f));
        button.setBackground(new Color(255, 215, 0));
        button.setForeground(Color.DARK_GRAY);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(getMaximumSize());
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));


        // If chapter is unlocked enable the button; otherwise disable and show hint
        boolean unlocked = false;
        try { unlocked = gameSystem.isChapterUnlocked(chapterNumber); } catch (Exception ex) { unlocked = true; }

        if (unlocked) {
            button.addActionListener(e -> {
                // hide story UI while dialogue and battle run
                StoryModeUI.this.setVisible(false);

                // get pre-dialogue lines based on chapter number
                Dialogue dlg = new Dialogue();
                String[] pre;
                String[] post;
                
                switch (chapterNumber) {
                    case 1:
                        pre = dlg.sirenEmpressInteraction;
                        post = dlg.sirenEmpressDefeated;
                        break;
                    case 2:
                        pre = dlg.lavaBeastInteraction;
                        post = dlg.lavaBeastDefeated;
                        break;
                    case 3:
                        pre = dlg.eclipseCoreInteraction;
                        post = dlg.eclipseCoreDefeated;
                        break;
                    case 4:
                        pre = dlg.resonaraInteraction;
                        post = dlg.resonaraDefeated;
                        break;
                    case 5:
                        pre = dlg.kassundreInteraction;
                        post = dlg.kassundreDefeated;
                        break;
                    case 6:
                        pre = dlg.twinkleInteraction;
                        post = dlg.twinkleDefeated;
                        break;
                    default:
                        pre = new String[] {};
                        post = new String[] {};
                }

                // only show pre-battle dialogue if it hasn't been shown before
                if (gameSystem.hasPreDialogueBeenShown(chapterNumber)) {
                    // pre-dialogue already shown, check post-dialogue
                    if (gameSystem.hasPostDialogueBeenShown(chapterNumber)) {
                        // both dialogues shown, skip straight to battle
                        BattleUI battle = new BattleUI(gameSystem, chapterNumber, () -> {
                            showRefreshed();
                        });
                        battle.setVisible(true);
                    } else {
                        // pre-dialogue shown but not post, only show post-dialogue after battle
                        BattleUI battle = new BattleUI(gameSystem, chapterNumber, () -> {
                            gameSystem.markPostDialogueAsShown(chapterNumber);
                            new DialogueUI(post, () -> {
                                showRefreshed();
                            });
                        });
                        battle.setVisible(true);
                    }
                } else {
                    // first time: show pre-dialogue, then battle with post-dialogue callback
                    gameSystem.markPreDialogueAsShown(chapterNumber);
                    new DialogueUI(pre, () -> {
                        BattleUI battle = new BattleUI(gameSystem, chapterNumber, () -> {
                            gameSystem.markPostDialogueAsShown(chapterNumber);
                            // run post-dialogue after battle ends
                            new DialogueUI(post, () -> {
                                showRefreshed();
                            });
                        });
                        battle.setVisible(true);
                    });
                }
            });
        } else {
            button.setEnabled(false);
            button.setToolTipText("Locked - finish previous chapters to unlock.");
            // also show a lock dialog if user somehow attempts to interact
            button.addActionListener(e -> {
                JOptionPane.showMessageDialog(this,
                    "This chapter is locked. Finish previous chapters to unlock.",
                    "Chapter Locked",
                    JOptionPane.INFORMATION_MESSAGE);
            });
        }

        button. addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(255, 223, 0)); // Lighter gold on hover
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(255, 215, 0)); // Original color
            }
        });
        
        return button;
    }

    private JButton createEndingButton(){
        JButton button = new JButton("Ending: The AfterMath");
        button.setFont(pixelFont.deriveFont(16f));
        button.setBackground(new Color(255, 215, 0));
        button.setForeground(Color.DARK_GRAY);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(getMaximumSize());
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Check if Chapter 6 is completed (unlocked) to allow access to ending
        boolean chapter6Completed = gameSystem.highestChapterUnlocked >= 6;
        
        if (chapter6Completed) {
            button.addActionListener(e -> {
                // hide story UI while dialogue runs
                StoryModeUI.this.setVisible(false);

                // fade music and show ending dialogue
                Dialogue dlg = new Dialogue();
                String[] endingLines = dlg.ending;
                new DialogueUI(endingLines, () -> {
                    showRefreshed();
                });
            });
        } else {
            button.setEnabled(false);
            button.setToolTipText("Locked - finish Chapter 6 to unlock.");
            button.addActionListener(e -> {
                JOptionPane.showMessageDialog(this,
                    "The ending is locked. Finish Chapter 6 to unlock.",
                    "Ending Locked",
                    JOptionPane.INFORMATION_MESSAGE);
            });
        }
        
        button. addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(255, 223, 0)); // Lighter gold on hover
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(255, 215, 0)); // Original color
            }
        });
       
        return button;
    }
    
    private JButton createBackButton(){
        JButton button = new JButton("Return to Main Menu");
        button.setFont(pixelFont.deriveFont(16f));
        button.setBackground(new Color(255, 215, 0));
        button.setForeground(Color.DARK_GRAY);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

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
        
        return button;
    }
}

import javax.swing.*;
import java.awt.*;

/**
 * BattleTester: A quick testing utility to run any chapter battle directly.
 * Useful for testing battle mechanics, UI customization, and enemy behavior per chapter.
 */
public class BattleTester extends JFrame {

    private GameSystem gs;
    private int selectedChapter = 1;
    private JButton startBattleButton;
    private JButton runDialoguesOnlyButton;
    private JCheckBox preDialogueCheckbox;
    private JCheckBox postDialogueCheckbox;
    private JCheckBox dialogueOnlyCheckbox;

    public BattleTester() {
        setTitle("Battle Tester");
            // Size window to half of the current screen resolution
            java.awt.Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            int w = screen.width / 2;
            int h = screen.height / 2;
            setSize(w, h);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(30, 30));

        // Initialize GameSystem with characters and enemies
        Character[] characters = {
            new Audry(),
            new Giantha(),
            new Lynzi(),
            new Shiera(),
            new Lazuli(),
        };
        MobNPC[] mobs = {
            new ChapterSix(),
            new ChapterOne(),
            new ChapterTwo(),
            new ChapterThree(),
            new ChapterFour(),
            new ChapterFive(),
            new NormalEnemy("Student Puppet", "Minion", "Melee", "Wooden Sword", 100, 5),
            new NormalEnemy("Corrupted Skeleton", "Minion", "Melee", "Bone Sword", 120, 2),
            new NormalEnemy("Water Sprite", "Minion", "Ranged", "Water Magic", 130, 1),
            new NormalEnemy("Echo Imp", "Minion", "Ranged", "Sound Magic", 130, 4),
            new NormalEnemy("Astral Glob", "Minion", "Melee", "Astral Slime", 110, 3),
            new NormalEnemy("Princess Puppet", "Minion", "Melee", "wand", 100, 5),
            new NormalEnemy("Magma Skeleton", "Minion", "Melee", "Bone Sword", 120, 2),
            new NormalEnemy("Water Blob", "Minion", "Ranged", "Water Magic", 130, 1),
            new NormalEnemy("Resonance Goblin", "Minion", "Ranged", "Sound Magic", 130, 4),
            new NormalEnemy("Moon Sprite", "Minion", "Melee", "Astral magic", 110, 3),
        };
        gs = new GameSystem(characters, mobs);

        // Header label
        JLabel header = new JLabel("Select a Chapter to Test", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        add(header, BorderLayout.NORTH);

        // Center panel with chapter buttons and options
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Chapter buttons panel (top)
        JPanel chapterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JLabel chapterLabel = new JLabel("Chapter:");
        chapterLabel.setFont(new Font("Arial", Font.BOLD, 14));
        chapterPanel.add(chapterLabel);

        for (int i = 1; i <= 6; i++) {
            final int chapter = i;
            JButton chapterButton = new JButton(String.valueOf(i));
            chapterButton.setFont(new Font("Arial", Font.BOLD, 12));
            chapterButton.setPreferredSize(new Dimension(50, 40));
            chapterButton.addActionListener(e -> {
                selectedChapter = chapter;
                // Update button styles
                for (Component comp : chapterPanel.getComponents()) {
                    if (comp instanceof JButton && !comp.equals(chapterLabel)) {
                        ((JButton) comp).setBackground(UIManager.getColor("Button.background"));
                        ((JButton) comp).setOpaque(false);
                    }
                }
                chapterButton.setOpaque(true);
                chapterButton.setBackground(new Color(100, 150, 255));
                println("Selected Chapter: " + chapter);
            });
            if (i == 1) {
                chapterButton.setOpaque(true);
                chapterButton.setBackground(new Color(100, 150, 255));
            }
            chapterPanel.add(chapterButton);
        }
        centerPanel.add(chapterPanel, BorderLayout.NORTH);

        // Options panel (center)
        JPanel optionsPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        preDialogueCheckbox = new JCheckBox("Show pre-dialogue");
        preDialogueCheckbox.setFont(new Font("Arial", Font.PLAIN, 12));
        preDialogueCheckbox.setSelected(false); // Default to unchecked
        optionsPanel.add(preDialogueCheckbox);

        postDialogueCheckbox = new JCheckBox("Show post-dialogue");
        postDialogueCheckbox.setFont(new Font("Arial", Font.PLAIN, 12));
        postDialogueCheckbox.setSelected(false); // Default to unchecked
        optionsPanel.add(postDialogueCheckbox);

        dialogueOnlyCheckbox = new JCheckBox("Run dialogues only (no battle)");
        dialogueOnlyCheckbox.setFont(new Font("Arial", Font.PLAIN, 12));
        dialogueOnlyCheckbox.setSelected(false); // Default to unchecked
        optionsPanel.add(dialogueOnlyCheckbox);

        centerPanel.add(optionsPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel with buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        startBattleButton = new JButton("Start Battle");
        startBattleButton.setFont(new Font("Arial", Font.BOLD, 12));
        startBattleButton.addActionListener(e -> startBattle());
        bottomPanel.add(startBattleButton);

        runDialoguesOnlyButton = new JButton("Run Dialogues Only");
        runDialoguesOnlyButton.setFont(new Font("Arial", Font.BOLD, 12));
        runDialoguesOnlyButton.addActionListener(e -> runDialoguesOnly());
        bottomPanel.add(runDialoguesOnlyButton);

        JButton testDefeatedButton = new JButton("Test Defeated Dialogue");
        testDefeatedButton.setFont(new Font("Arial", Font.BOLD, 12));
        testDefeatedButton.addActionListener(e -> testDefeatedDialogue());
        bottomPanel.add(testDefeatedButton);

        JButton testEndingButton = new JButton("Test Ending Dialogue");
        testEndingButton.setFont(new Font("Arial", Font.BOLD, 12));
        testEndingButton.addActionListener(e -> testEndingDialogue());
        bottomPanel.add(testEndingButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 12));
        exitButton.addActionListener(e -> System.exit(0));
        bottomPanel.add(exitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Start the selected chapter battle.
     */
    private void startBattle() {
        boolean showPreDialogue = preDialogueCheckbox.isSelected();
        boolean showPostDialogue = postDialogueCheckbox.isSelected();
        boolean dialogueOnly = dialogueOnlyCheckbox.isSelected();
        
        println("Starting battle for chapter " + selectedChapter 
            + " (Pre-dialogue: " + showPreDialogue 
            + ", Post-dialogue: " + showPostDialogue 
            + ", Dialogue only: " + dialogueOnly + ")");
        
        // Get dialogue lines for the selected chapter
        Dialogue dlg = new Dialogue();
        String[] preLines;
        String[] postLines;
        
        switch (selectedChapter) {
            case 1:
                preLines = dlg.sirenEmpressInteraction;
                postLines = dlg.sirenEmpressDefeated;
                break;
            case 2:
                preLines = dlg.lavaBeastInteraction;
                postLines = dlg.lavaBeastDefeated;
                break;
            case 3:
                preLines = dlg.eclipseCoreInteraction;
                postLines = dlg.eclipseCoreDefeated;
                break;
            case 4:
                preLines = dlg.resonaraInteraction;
                postLines = dlg.resonaraDefeated;
                break;
            case 5:
                preLines = dlg.kassundreInteraction;
                postLines = dlg.kassundreDefeated;
                break;
            case 6:
                preLines = dlg.twinkleInteraction;
                postLines = dlg.twinkleDefeated;
                break;
            default:
                preLines = new String[]{};
                postLines = new String[]{};
        }
        
        // If dialogue-only mode is enabled, just show dialogues
        if (dialogueOnly) {
            if (showPreDialogue && preLines.length > 0) {
                new DialogueUI(preLines, () -> {
                    if (showPostDialogue && postLines.length > 0) {
                        new DialogueUI(postLines, () -> returnToBattleTester());
                    } else {
                        returnToBattleTester();
                    }
                });
            } else if (showPostDialogue && postLines.length > 0) {
                new DialogueUI(postLines, () -> returnToBattleTester());
            } else {
                println("Dialogue-only mode enabled but no dialogues selected.");
            }
            setVisible(false);
        } else {
            // Normal battle mode with optional dialogues
            if (showPreDialogue && preLines.length > 0) {
                new DialogueUI(preLines, () -> startBattleAfterPre(showPostDialogue, postLines));
            } else {
                startBattleAfterPre(showPostDialogue, postLines);
            }
        }
    }
    
    /**
     * Run dialogues only without starting a battle.
     */
    private void runDialoguesOnly() {
        boolean showPreDialogue = preDialogueCheckbox.isSelected();
        boolean showPostDialogue = postDialogueCheckbox.isSelected();
        
        if (!showPreDialogue && !showPostDialogue) {
            println("Please select at least one dialogue option (pre or post).");
            return;
        }
        
        println("Running dialogues only for chapter " + selectedChapter);
        
        // Get dialogue lines for the selected chapter
        Dialogue dlg = new Dialogue();
        String[] preLines;
        String[] postLines;
        
        switch (selectedChapter) {
            case 1:
                preLines = dlg.sirenEmpressInteraction;
                postLines = dlg.sirenEmpressDefeated;
                break;
            case 2:
                preLines = dlg.lavaBeastInteraction;
                postLines = dlg.lavaBeastDefeated;
                break;
            case 3:
                preLines = dlg.eclipseCoreInteraction;
                postLines = dlg.eclipseCoreDefeated;
                break;
            case 4:
                preLines = dlg.resonaraInteraction;
                postLines = dlg.resonaraDefeated;
                break;
            case 5:
                preLines = dlg.kassundreInteraction;
                postLines = dlg.kassundreDefeated;
                break;
            case 6:
                preLines = dlg.twinkleInteraction;
                postLines = dlg.twinkleDefeated;
                break;
            default:
                preLines = new String[]{};
                postLines = new String[]{};
        }
        
        // Show dialogues in sequence
        if (showPreDialogue && preLines.length > 0) {
            new DialogueUI(preLines, () -> {
                if (showPostDialogue && postLines.length > 0) {
                    new DialogueUI(postLines, () -> returnToBattleTester());
                } else {
                    returnToBattleTester();
                }
            });
        } else if (showPostDialogue && postLines.length > 0) {
            new DialogueUI(postLines, () -> returnToBattleTester());
        }
        
        setVisible(false);
    }
    
    /**
     * Start the battle after pre-dialogue (if any) has completed.
     */
    private void startBattleAfterPre(boolean showPostDialogue, String[] postLines) {
        // Create and show the battle UI
        new BattleUI(gs, selectedChapter, () -> {
            println("Battle for chapter " + selectedChapter + " ended.");
            
            // If post-dialogue is enabled, show it after battle
            if (showPostDialogue && postLines.length > 0) {
                new DialogueUI(postLines, () -> {
                    println("Post-dialogue completed. Returning to Battle Tester.");
                    returnToBattleTester();
                });
            } else {
                returnToBattleTester();
            }
        });
        
        // Hide the tester window
        setVisible(false);
    }
    
    /**
     * Return to the Battle Tester window.
     */
    private void returnToBattleTester() {
        println("Returned to Battle Tester.");
        setVisible(true);
    }

    /**
     * Test the player defeated dialogue.
     */
    private void testDefeatedDialogue() {
        println("Testing player defeated dialogue...");
        Dialogue dlg = new Dialogue();
        new DialogueUI(dlg.playerDefeated, () -> returnToBattleTester());
        setVisible(false);
    }

    /**
     * Test the ending dialogue.
     */
    private void testEndingDialogue() {
        println("Testing ending dialogue...");
        Dialogue dlg = new Dialogue();
        new DialogueUI(dlg.ending, () -> returnToBattleTester());
        setVisible(false);
    }

    private void println(String msg) {
        System.out.println("[BattleTester] " + msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BattleTester());
    }
}

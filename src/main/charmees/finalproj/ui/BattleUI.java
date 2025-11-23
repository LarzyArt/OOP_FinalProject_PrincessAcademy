package charmees.finalproj.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import charmees.finalproj.core.GameSystem;
import charmees.finalproj.core.Dialogue;
import charmees.finalproj.entities.*;
import charmees.finalproj.util.*;
import charmees.finalproj.util.BackgroundManager;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    private javax.swing.ImageIcon backgroundIcon;

    public BackgroundPanel(String imagePath) {
        try {
            // Prefer an ImageIcon so animated GIFs keep animating. Fall back to static image.
            backgroundIcon = BackgroundManager.loadIcon(imagePath);
            if (backgroundIcon != null) {
                backgroundImage = backgroundIcon.getImage();
                System.out.println("BackgroundPanel: loaded icon: " + imagePath);
                return;
            }
            backgroundImage = BackgroundManager.loadImage(imagePath);
            if (backgroundImage != null) {
                System.out.println("BackgroundPanel: loaded image: " + imagePath);
            } else {
                System.out.println("BackgroundPanel: image not found: " + imagePath);
            }
        } catch (Exception e) {
            System.out.println("Error loading background '" + imagePath + "': " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundIcon != null) {
            // draw scaled - let ImageProducer handle animation
            g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}

public class BattleUI extends JFrame {

    GameSystem gs;
    int chapter;
    BackgroundManager bgManager;

    JTextArea partyStatus;
    JTextArea enemyStatus;
    JTextArea battleLog;

    JPanel skillPanel;      // attack / heal / buff buttons
    JPanel targetPanel;     // dynamic target buttons
    JPanel skillListPanel;   // New panel to show actual skill names


    ArrayList<GameCharacter> allies = new ArrayList<>();
    ArrayList<MobNPC> enemies = new ArrayList<>();
    private boolean skillsVisible = false;  // Track skill panel visibility
    private int currentAllyIndex = 0;  // Track which ally's turn it is
    private int turnCounter = 0;  // Track battle turn number
    private Runnable onBattleEnd = null;  // optional callback when battle finishes


    public BattleUI(GameSystem gs, int chapter) {
        this(gs, chapter, null);
    }

    public BattleUI(GameSystem gs, int chapter, Runnable onBattleEnd) {
        this.gs = gs;
        this.chapter = chapter;
        this.bgManager = BackgroundManager.getManager(chapter);
        this.onBattleEnd = onBattleEnd;

        ArrayList<MobNPC> tempEnemies = gs.EnemiesforChapter(chapter);
        this.enemies = tempEnemies;

        loadAllies();

        buildUI();
        refreshStatus();
        
        turnCounter = 1;
        println("DEBUG: Starting BattleUI for chapter " + chapter);
        println("DEBUG: EnemiesforChapter returned: " + (tempEnemies == null ? "null" : tempEnemies.size() + " enemies"));
        println("Battle Start!");
        println("Enemies for chapter " + chapter + ": " + enemies.size());
        for (MobNPC e : enemies) {
            println("  - " + e.getName() + " HP: " + e.getHealthPoints());
        }
        println("\n========== TURN " + turnCounter + " ==========");
    }

    // ---------------- LOAD DATA ------------------
    private void loadAllies() {
        GameCharacter[] roster = gs.getCharacters();
        if (roster == null) return;
        for (GameCharacter c : roster) {
            if (c != null && c.isAlive()) allies.add(c);
        }
    }

    // ---------------- BUILD UI ------------------
    private void buildUI() {
        setTitle("Princess Academy");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        try {
            java.net.URL iconUrl = BattleUI.class.getResource("/assets/icon/icon.png");
            if (iconUrl != null) setIconImage(new ImageIcon(iconUrl).getImage());
            else setIconImage(new ImageIcon("assets/icon/icon.png").getImage());
        } catch (Exception e) {
            System.out.println("Icon image not found.");
        }

        // HEADER ---------------------
        JLabel title = new JLabel("PRINCESS ACADEMY", SwingConstants.CENTER);
        title.setIconTextGap(0);
        title.setFont(FontManager.getPixelFont(21f));
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        title.setBackground(Color.YELLOW);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // CENTER PANEL (3 rows)
        BackgroundPanel center = new BackgroundPanel(bgManager.getMainBackgroundPath());
        center.setLayout(new BorderLayout());
        add(center, BorderLayout.CENTER);

        // TOP STATUS PANEL (PARTY LEFT, ENEMIES RIGHT)
        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        statusPanel.setOpaque(false);
        JPanel topSpacer = new JPanel();
        topSpacer.setOpaque(false);
        topSpacer.setPreferredSize(new Dimension(0, 20));
        JPanel statusContainer = new JPanel(new BorderLayout());
        statusContainer.setOpaque(false);
        statusContainer.add(statusPanel, BorderLayout.CENTER);
        statusContainer.add(topSpacer, BorderLayout.SOUTH);
        center.add(statusContainer, BorderLayout.NORTH);

        // PARTY STATUS (LEFT)
        partyStatus = new JTextArea();
        partyStatus.setEditable(false);
        partyStatus.setOpaque(false);
        partyStatus.setMargin(new Insets(10, 10, 10, 10));
        partyStatus.setFont(FontManager.getPixelFont(11f));
        partyStatus.setForeground(Color.WHITE);
        
        JScrollPane partyScroll = new JScrollPane(partyStatus);
        partyScroll.setOpaque(false);
        partyScroll.getViewport().setOpaque(false);
        partyScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        partyScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        partyScroll.setBorder(BorderFactory.createEmptyBorder());
        // Add party scroll directly; keep viewport transparent so main background shows through
        statusPanel.add(partyScroll);

        // ENEMY STATUS (RIGHT)
        enemyStatus = new JTextArea();
        enemyStatus.setEditable(false);
        enemyStatus.setOpaque(false);
        enemyStatus.setMargin(new Insets(10, 10, 10, 10));
        enemyStatus.setFont(FontManager.getPixelFont(11f));
        enemyStatus.setForeground(Color.WHITE);
        
        JScrollPane enemyScroll = new JScrollPane(enemyStatus);
        enemyScroll.setOpaque(false);
        enemyScroll.getViewport().setOpaque(false);
        enemyScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        enemyScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        enemyScroll.setBorder(BorderFactory.createEmptyBorder());
        statusPanel.add(enemyScroll);

        // BATTLE LOG (MIDDLE)
        battleLog = new JTextArea();
        battleLog.setEditable(false);
        battleLog.setOpaque(false);
        battleLog.setMargin(new Insets(10, 10, 10, 10));
        battleLog.setFont(FontManager.getPixelFont(10f));
        battleLog.setForeground(Color.WHITE);
        
        JScrollPane battleLogScroll = new JScrollPane(battleLog);
        battleLogScroll.setOpaque(false);
        battleLogScroll.getViewport().setOpaque(false);
        // allow vertical scrollbars when needed so user can scroll
        battleLogScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        battleLogScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        battleLogScroll.setBorder(BorderFactory.createEmptyBorder());
        
        JPanel logSpacer = new JPanel();
        logSpacer.setOpaque(false);
        logSpacer.setPreferredSize(new Dimension(0, 20));
        JPanel logContainer = new JPanel(new BorderLayout());
        logContainer.setOpaque(false);
        logContainer.add(battleLogScroll, BorderLayout.CENTER);
        logContainer.add(logSpacer, BorderLayout.SOUTH);
        center.add(logContainer, BorderLayout.CENTER);

        // BOTTOM PANELS
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        center.add(bottom, BorderLayout.SOUTH);

        // Skill panel
        skillPanel = new JPanel();
        skillPanel.setOpaque(false);
        skillPanel.setLayout(new GridLayout(1, 2, 10, 0));
        bottom.add(skillPanel, BorderLayout.NORTH);

        // Skill list (appears below buttons)
        skillListPanel = new JPanel();
        skillListPanel.setOpaque(false);
        skillListPanel.setLayout(new GridLayout(0, 1));  // Single column for skills
        skillListPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        bottom.add(skillListPanel, BorderLayout.CENTER);

        // Target panel (appears only when selecting target)
        targetPanel = new JPanel();
        targetPanel.setOpaque(false);
        targetPanel.setLayout(new GridLayout(0, 2, 10, 10));
        targetPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        bottom.add(targetPanel, BorderLayout.SOUTH);

        // Create initial buttons
        createBasicSkillButtons();

        setVisible(true);
    }

    private void createBasicSkillButtons() {
        skillPanel.removeAll();
        skillListPanel.removeAll();
        targetPanel.removeAll();

        // MAIN BUTTONS
        JButton skills = new JButton("Skills");
        JButton back = new JButton("Return");
        // style with hover effect
        applyHoverEffect(skills, new Color(255, 223, 0), new Color(255, 200, 0));
        skills.setForeground(Color.BLACK);
        applyHoverEffect(back, new Color(255, 223, 0), new Color(255, 200, 0));
        back.setForeground(Color.BLACK);

        skillPanel.add(skills);
        skillPanel.add(back);

        // RETURN BUTTON
        back.addActionListener(e -> {
            new StoryModeUI(gs).setVisible(true);
            dispose();
        });

        // SKILLS BUTTON
        skills.addActionListener(e -> toggleSkillList());

        skillPanel.revalidate();
        skillPanel.repaint();
    }

    // Helper to add a hover color effect to buttons (no extra imports required)
    private void applyHoverEffect(JButton b, Color normal, Color hover) {
        b.setBackground(normal);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (b.isEnabled()) b.setBackground(hover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (b.isEnabled()) b.setBackground(normal);
            }
        });
    }


    private void toggleSkillList() {
        if (skillsVisible) {
            skillListPanel.removeAll();
            skillsVisible = false;
        } else {
            showSkillList();
            skillsVisible = true;
        }

        skillListPanel.revalidate();
        skillListPanel.repaint();
    }



    // ---------------- STATUS UPDATE ------------------
    private void refreshStatus() {
        partyStatus.setText("\nPARTY:\n");
        for (GameCharacter c : allies) {
            if (c.isAlive()) {
                String tags = "";
                if (c.isStunned()) tags += " [Stun:" + c.getStunnedTurns() + "]";
                partyStatus.append("\n" + c.getName() + "\t||HP:" + c.getHealthPoints() + "/" + c.getMaxHealthPoints() + "   ||MP:" + c.getManaPoints() + "/" + c.getMaxManaPoints() + tags + "\n");
            } else {
                partyStatus.append("\n" + c.getName() + "\t||DEFEATED\n");
            }
        }

        enemyStatus.setText("\nENEMIES:\n");
        for (MobNPC e : enemies) {
            if (e.isAlive()) {
                String tags = "";
                if (e.isStunned()) tags += " [Stun:" + e.getStunnedTurns() + "]";
                if (e.isTaunted()) tags += " [Taunt:" + e.getTauntTurns() + "]";
                enemyStatus.append("\n" + e.getName() + " \t||HP:" + e.getHealthPoints() + "/" + e.getMaxHealthPoints() + tags + "\n\n");
            } else {
                enemyStatus.append("\n" + e.getName() + "\t||DEFEATED\n");
            }
        }
    }

    private void println(String s) {
        battleLog.append(s + "\n\n");
        // move caret to end so view scrolls to newest text (works with DefaultCaret.ALWAYS_UPDATE)
        try {
            battleLog.setCaretPosition(battleLog.getDocument().getLength());
        } catch (Exception ex) {
            // ignore if document not ready
        }
    }

    // Helper checks for alive members (we keep defeated characters in lists so names stay visible)
    private boolean anyAlliesAlive() {
        for (GameCharacter c : allies) {
            if (c != null && c.isAlive()) return true;
        }
        return false;
    }

    private boolean anyEnemiesAlive() {
        for (MobNPC m : enemies) {
            if (m != null && m.isAlive()) return true;
        }
        return false;
    }

    // ---------------- ACTIONS ------------------

    private void enemyTurn() {
        println("\n--- Enemy Turn ---");
        if (!anyAlliesAlive()) return;

        for (MobNPC enemy : enemies) {
            if (!enemy.isAlive()) continue;
                        if (enemy.isStunned()) {
                                        println(enemy.getName() + " is stunned and cannot act!");
                                        continue;
                                    }
            
            // Enemy chooses random skill (1-3 or whatever they have)
            int skillChoice = (int)(Math.random() * 3) + 1; // Random skill 1-3
            
            // Special case: skill 3 might be AoE (check for Lava Beast's Corrupted Eruption)
            if (enemy.getName().equals("Lava Beast") && skillChoice == 3) {
                // AoE attack all allies
                println(enemy.getName() + " uses Corrupted Eruption!");
                int totalDamage = 0;
                for (GameCharacter ally : allies) {
                    if (ally.isAlive()) {
                        int hpBefore = ally.getHealthPoints();
                        enemy.useSkill(skillChoice, ally);
                        int dmg = hpBefore - ally.getHealthPoints();
                        if (dmg > 0) {
                            totalDamage += dmg;
                            println("  " + ally.getName() + " takes " + dmg + " damage!");
                        }
                        if (!ally.isAlive()) {
                            println("  " + ally.getName() + " was defeated!");
                        }
                    }
                }
                println("Total damage to party: " + totalDamage);
            } else {
                // Single target attack - if this enemy is taunted, prefer its taunt target
                GameCharacter target = null;
                if (enemy.isTaunted()) {
                    GameCharacter tt = enemy.getTauntTarget();
                    if (tt != null && tt.isAlive()) {
                        target = tt;
                    }
                }
                
                // If no taunted target, pick random alive ally
                if (target == null || !target.isAlive()) {
                    java.util.ArrayList<GameCharacter> aliveAllies = new java.util.ArrayList<>();
                    for (GameCharacter ally : allies) {
                        if (ally.isAlive()) {
                            aliveAllies.add(ally);
                        }
                    }
                    if (!aliveAllies.isEmpty()) {
                        target = aliveAllies.get((int)(Math.random() * aliveAllies.size()));
                    }
                }
                
                if (target == null || !target.isAlive()) {
                    continue;
                }
                
                int hpBefore = target.getHealthPoints();
                enemy.useSkill(skillChoice, target);
                int damageDealt = hpBefore - target.getHealthPoints();

                println(enemy.getName() + " attacks " + target.getName() + "!");
                if (enemy.getLastSkillHits() > 0) {
                    println("Hits " + enemy.getLastSkillHits() + " times for " + enemy.getLastSkillDamage() + " damage!");
                } else {
                    if (damageDealt > 0) {
                        println("Deals " + damageDealt + " damage!");
                    }
                }

                if (!target.isAlive()) {
                    println(target.getName() + " was defeated!");
                    // keep defeated ally in the list so their name remains visible
                }
            }
        }

        // Do not remove dead allies here; keep them to display "DEFEATED"
        // Tick down status effects (stuns, taunt, damage reduction) for all characters
        for (GameCharacter c : allies) {
            c.tickStatus();
        }
        for (MobNPC e : enemies) {
            e.tickStatus();
        }

        // Show stun countdowns for any stunned enemies/allies after ticking
        for (MobNPC e : enemies) {
            if (e.isAlive() && e.isStunned()) {
                println(e.getName() + " stunned: " + e.getStunnedTurns() + " turn(s) remaining.");
            }
        }
        for (GameCharacter c : allies) {
            if (c.isAlive() && c.isStunned()) {
                println(c.getName() + " stunned: " + c.getStunnedTurns() + " turn(s) remaining.");
            }
        }


        refreshStatus();

        if (!anyAlliesAlive()) {
            println("\nYour party has been wiped out...");
            // Show player-defeated dialogue, then return to story when dialogue finishes
            Dialogue dlg = new Dialogue();
            new DialogueUI(dlg.playerDefeated, () -> {
                finishBattle();
            });
            return;
        }

        if (!anyEnemiesAlive()) {
            println("\nALL ENEMIES DEFEATED! YOU WIN!");
            // Unlock next chapter on victory
            try {
                gs.markChapterCompleted(chapter);
            } catch (Exception ex) {
                // ignore if GameSystem doesn't support it
            }
            finishBattle();
            return;
        }
        
        // Increment turn after enemies attack
        turnCounter++;
        println("\n========== TURN " + turnCounter + " ==========");
        
        // Loop back to player turns
        currentAllyIndex = 0;
        skillListPanel.removeAll();
        showSkillList();
    }

    private void finishBattle() {
        println("\nReturning to story...");
        if (onBattleEnd != null) {
            try {
                onBattleEnd.run();
            } catch (Exception ex) {
                // fallback if callback fails
                new StoryModeUI(gs).setVisible(true);
            }
        } else {
            new StoryModeUI(gs).setVisible(true);
        }
        dispose();
    }

    private void showSkillList() {
        skillListPanel.removeAll();
        targetPanel.removeAll();

        if (currentAllyIndex >= allies.size()) {
            // All allies have acted, reset and start enemy turn
            currentAllyIndex = 0;
            enemyTurn();
            return;
        }

        // Skip over defeated allies and stunned allies so they don't get prompted to act
        while (currentAllyIndex < allies.size() && (!allies.get(currentAllyIndex).isAlive() || allies.get(currentAllyIndex).isStunned())) {
            GameCharacter skipped = allies.get(currentAllyIndex);
            if (skipped.isStunned()) {
                println(skipped.getName() + " is stunned and cannot act!");
            }
            currentAllyIndex++;
        }

        if (currentAllyIndex >= allies.size()) {
            // All (alive) allies have acted, reset and start enemy turn
            currentAllyIndex = 0;
            enemyTurn();
            return;
        }

        GameCharacter user = allies.get(currentAllyIndex);
        println("\n\n===================================");
        println("\n" + user.getName() + "'s turn:");
        println("Choose a skill:");

        String[] skills = user.getSkillList();

        if (skills == null || skills.length == 0) {
            JLabel noSkills = new JLabel("No skills available");
            noSkills.setForeground(Color.WHITE);
            skillListPanel.add(noSkills);
            // Auto-advance to next ally
            currentAllyIndex++;
            JButton nextBtn = new JButton("Next");
            nextBtn.setForeground(Color.BLACK);
            applyHoverEffect(nextBtn, new Color(200, 200, 200), new Color(170, 170, 170));
            skillListPanel.add(nextBtn);
        } else {
            for (int i = 0; i < skills.length; i++) {
                final int idx = i;
                String skillName = skills[i];
                JButton b = new JButton(skillName);
                // Skill buttons style (steel blue -> hover darker)
                b.setForeground(Color.WHITE);
                applyHoverEffect(b, new Color(70, 130, 180), new Color(60, 110, 160));

                b.addActionListener(e -> selectSkill(user, idx));

                skillListPanel.add(b);
            }
        }

        skillListPanel.revalidate();
        skillListPanel.repaint();
    }

    private void selectSkill(GameCharacter user, int skillIndex) {
        targetPanel.removeAll();

        int skillNumber = skillIndex + 1; // Character expects 1-based skill numbers
        String targetType = user.getSkillTargetType(skillNumber);


        // if the character has 0 mana, cant use skill them use a skill
        if (user.getManaPoints() == 0) {
            println(user.getName() + " is out of mana and can't use this skill!");
            println("Choose another skill.");
            toggleSkillList();
            toggleSkillList();
            return;
        }

        String[] skills = user.getSkillList();
        String skillName = skills[skillIndex];


        // Beginner-friendly way to get mana cost from skill name
        int requiredMana = 0;
        if (skillName.contains("MP")) {
            int openParen = skillName.indexOf('(');
            int mpIndex = skillName.indexOf("MP");
            if (openParen != -1 && mpIndex > openParen) {
                String inside = skillName.substring(openParen + 1, mpIndex).trim();
                // inside should be like "10"
                String[] parts = inside.split(" ");
                if (parts.length > 0) {
                    try {
                        requiredMana = Integer.parseInt(parts[0]);
                    } catch (Exception e) {
                        requiredMana = 0;
                    }
                }
            }
        }
        // If not enough mana, show message and let player pick again
        if (requiredMana > 0 && user.getManaPoints() < requiredMana) {
            println(user.getName() + " doesn't have enough mana for this skill!");
            println("Choose another skill.");
            toggleSkillList();
            toggleSkillList();
            return;
        }

        println(user.getName() + " uses skill: " + skillName);

        switch (targetType.toUpperCase()) {
            case "ENEMY":
                // show enemy choices
                for (MobNPC enemy : enemies) {
                    String label = enemy.getName() + (enemy.isAlive() ? "" : " (DEFEATED)");
                    JButton b = new JButton(label);
                    // Enemy buttons (red-ish)
                    if (enemy.isAlive()) {
                        applyHoverEffect(b, new Color(180, 50, 50), new Color(210, 70, 70));
                        b.setForeground(Color.WHITE);
                        b.addActionListener(e -> executeSkillOnEnemy(user, skillNumber, enemy));
                    } else {
                        b.setBackground(new Color(80, 80, 80));
                        b.setForeground(Color.LIGHT_GRAY);
                        b.setOpaque(true);
                        b.setEnabled(false);
                        b.setBorderPainted(false);
                    }
                    targetPanel.add(b);
                }
                break;

            case "ALLY":
                // show ally choices
                for (GameCharacter al : allies) {
                    String label = al.getName() + (al.isAlive() ? "" : " (DEFEATED)");
                    JButton b = new JButton(label);
                    // Ally buttons (green-ish)
                    if (al.isAlive()) {
                        applyHoverEffect(b, new Color(80, 160, 80), new Color(100, 200, 100));
                        b.setForeground(Color.WHITE);
                        b.addActionListener(e -> executeSkillOnAlly(user, skillNumber, al));
                    } else {
                        b.setBackground(new Color(80, 80, 80));
                        b.setForeground(Color.LIGHT_GRAY);
                        b.setOpaque(true);
                        b.setEnabled(false);
                        b.setBorderPainted(false);
                    }
                    targetPanel.add(b);
                }
                break;

            case "SELF":
                // apply to self without selection
                executeSelfSkill(user, skillNumber);
                return;

            case "ALL":
                // Check special conditions before executing
                boolean canUse = true;
                String errorMessage = "";
                
                // Special condition for Lazuli's Harmonic Wave (skill 3)
                if (user.getName().equals("Lazuli") && skillNumber == 3) {
                    if (user.getManaPoints() != 0) {
                        canUse = false;
                        errorMessage = "Harmonic Wave requires 0 MP! Current MP: " + user.getManaPoints();
                    }
                }
                
                if (!canUse) {
                    println(errorMessage);
                    println("Choose another skill.");
                    toggleSkillList();
                    toggleSkillList();
                    return;
                }
                
                // apply immediately to whole party - no character selection needed
                int hpBefore = 0;
                for (GameCharacter c : allies) {
                    hpBefore += c.getHealthPoints();
                }
                
                user.useSkill(skillNumber, null, null, allies.toArray(new GameCharacter[0]));
                
                int hpAfter = 0;
                for (GameCharacter c : allies) {
                    hpAfter += c.getHealthPoints();
                }
                int healAmount = hpAfter - hpBefore;
                
                println(user.getName() + " used skill on all allies!");
                if (healAmount > 0) {
                    println("Heals " + healAmount + " HP total!");
                }
                targetPanel.removeAll();
                refreshStatus();
                
                // Move to next ally
                currentAllyIndex++;
                skillListPanel.removeAll();
                showSkillList();
                return;

            default:
                println("Skill target type not recognized: " + targetType);
                return;
        }

        targetPanel.revalidate();
        targetPanel.repaint();
    }

    private void executeSkillOnEnemy(GameCharacter user, int skillNumber, MobNPC enemy) {
        int hpBefore = enemy.getHealthPoints();
        user.useSkill(skillNumber, enemy, null, allies.toArray(new GameCharacter[0]));
        int damageDealt = hpBefore - enemy.getHealthPoints();

        // Log damage
        println(user.getName() + " used skill on " + enemy.getName() + "!");
        if (user.getLastSkillHits() > 0) {
            println("Hits " + user.getLastSkillHits() + " times for " + user.getLastSkillDamage() + " damage!");
        } else {
            println("Deals " + damageDealt + " damage!");
        }

        if (!enemy.isAlive()) {
            println(enemy.getName() + " was defeated!");
            // keep defeated enemy in the list so their name remains visible
        }

        targetPanel.removeAll();
        refreshStatus();

        if (!anyEnemiesAlive()) {
            println("ALL ENEMIES DEFEATED! YOU WIN!");
            try {
                gs.markChapterCompleted(chapter);
            } catch (Exception ex) {
                // ignore
            }
            finishBattle();
            return;
        }

        // Move to next ally
        currentAllyIndex++;
        skillListPanel.removeAll();
        showSkillList();
    }

    private void executeSkillOnAlly(GameCharacter user, int skillNumber, GameCharacter ally) {
        user.useSkill(skillNumber, null, ally, allies.toArray(new GameCharacter[0]));

        println(user.getName() + " used skill on " + ally.getName() + ".");

        targetPanel.removeAll();
        refreshStatus();
        
        // Move to next ally
        currentAllyIndex++;
        skillListPanel.removeAll();
        showSkillList();
    }

    private void executeSelfSkill(GameCharacter user, int skillNumber) {
        // Check special conditions before executing
        boolean canUse = true;
        String errorMessage = "";
        
        // Special condition for Lazuli's Harmonic Wave (skill 3)
                if (user.getName().equals("Lazuli") && skillNumber == 3) {
            if (user.getManaPoints() != 0) {
                canUse = false;
                errorMessage = "Harmonic Wave requires 0 MP! Current MP: " + user.getManaPoints();
            }
        }
        
        if (!canUse) {
            println(errorMessage);
            currentAllyIndex++;
            skillListPanel.removeAll();
            showSkillList();
            return;
        }
        
        // Call useSkill only if condition is met
        user.useSkill(skillNumber, null, null, allies.toArray(new GameCharacter[0]));

        // Special: Giantha's Giant's Roar taunts enemies to target her immediately
        if (user.getName().equals("Giantha") && skillNumber == 2) {
            for (MobNPC enemy : enemies) {
                if (enemy.isAlive()) {
                    enemy.applyTaunt(2, user);
                    println(enemy.getName() + " is now taunted to target " + user.getName() + " for 2 turns.");
                }
            }
            refreshStatus();
        }

        println(user.getName() + " used self skill!");

        targetPanel.removeAll();
        refreshStatus();
        
        // Move to next ally
        currentAllyIndex++;
        skillListPanel.removeAll();
        showSkillList();
    }

}

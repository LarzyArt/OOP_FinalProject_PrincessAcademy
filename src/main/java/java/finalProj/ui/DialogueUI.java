import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DialogueUI extends JFrame {

    private int index = 0;
    private String[] dialogues;
    private Runnable onComplete;

    private JTextArea text;
    private Image BackgroundImage;

    //fade in effect
    private Timer typeTimer;
    private String fullText = "";
    private int typeIndex = 0;
    private boolean isTyping = false;

    public DialogueUI(String[] lines, Runnable OnComplete){
        this.dialogues = lines;
        this.onComplete = OnComplete;

        setTitle("Story");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // Set icon image
        try {
            java.net.URL iconUrl = DialogueUI.class.getResource("/assets/icon/icon.png");
            if (iconUrl != null) setIconImage(new ImageIcon(iconUrl).getImage());
            else setIconImage(new ImageIcon("assets/icon/icon.png").getImage());
        } catch (Exception e) {
            System.out.println("Icon image not found.");
        }

        java.net.URL dlgBg = DialogueUI.class.getResource("/assets/backgrounds/Dialogue_Background.png");
        if (dlgBg != null) BackgroundImage = new ImageIcon(dlgBg).getImage();
        else BackgroundImage = new ImageIcon("assets/backgrounds/Dialogue_Background.png").getImage();

        // Create a panel to draw the background
        JPanel bgPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (BackgroundImage != null) {
                    g.drawImage(BackgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        bgPanel.setOpaque(true);

        // Top panel with instruction text and skip button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // keep it transparent so no extra background is drawn
        topPanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JLabel instructionLabel = new JLabel("Press Space to Continue");
        instructionLabel.setFont(FontManager.getPixelFont(10f));
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setOpaque(false); // do not draw any background behind the text
        topPanel.add(instructionLabel, BorderLayout.WEST);

        JButton skipButton = new JButton("Skip");
        // Make skip larger and match main menu gold styling
        skipButton.setFont(FontManager.getPixelFont(12f));
        skipButton.setForeground(Color.DARK_GRAY);
        skipButton.setOpaque(true);
        skipButton.setContentAreaFilled(true);
        skipButton.setBorderPainted(true);
        // Gold colors consistent with main buttons
        Color goldNormal = new Color(255, 215, 0);
        Color goldHover = new Color(255, 235, 59);
        skipButton.setBackground(goldNormal);
        skipButton.setBorder(BorderFactory.createLineBorder(new Color(200, 170, 0), 2));
        skipButton.setFocusPainted(false);
        // Make button bigger than text
        skipButton.setPreferredSize(new Dimension(100, 32));
        skipButton.setBorder(BorderFactory.createCompoundBorder(
            skipButton.getBorder(), BorderFactory.createEmptyBorder(4, 12, 4, 12)
        ));
        skipButton.addActionListener(e -> {
            dispose();
            if (onComplete != null) onComplete.run();
        });
        // Hover effect: change to hover gold
        skipButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (skipButton.isEnabled()) skipButton.setBackground(goldHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (skipButton.isEnabled()) skipButton.setBackground(goldNormal);
            }
        });
        topPanel.add(skipButton, BorderLayout.EAST);

        bgPanel.add(topPanel, BorderLayout.NORTH);

        //text area
        text = new JTextArea();
        text.setFont(FontManager.getPixelFont(12f));
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setMargin(new Insets(15, 15, 25, 15));
        text.setBorder(BorderFactory.createEmptyBorder(300, 25, 25, 20));
        text.setOpaque(false);
        text.setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        // Remove any default border so the dialogue area blends seamlessly with the background
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        bgPanel.add(scrollPane, BorderLayout.CENTER);
        add(bgPanel, BorderLayout.CENTER);

        //Spacebar to advance
        text.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "nextDialogue");
        text.getActionMap().put("nextDialogue", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                advance();
            }
        });

        advance(); //show first dialogue
        setVisible(true);
    }

    private void advance(){
        if (isTyping) {
            // finish current typing immediately
            if (typeTimer != null) typeTimer.stop();
            text.setText(fullText);
            isTyping = false;
            return;
        }

        if(index < dialogues.length){
            showTypewriterText(dialogues[index]);
            index++;
        } else {
            // restore music volume when dialogue completes
            MusicPlayer.setVolume(0.6);
            dispose();
            if(onComplete != null) onComplete.run();
        }
    }

    private void showTypewriterText(String line){
        // if currently typing, finish it and return
        if (isTyping) {
            if (typeTimer != null) typeTimer.stop();
            this.text.setText(fullText);
            isTyping = false;
            return;
        }

        // start typewriter effect for the given line
        fullText = (line == null) ? "" : line;
        typeIndex = 0;
        this.text.setText("");
        isTyping = true;

        // defines speed of text (milliseconds per char)
        typeTimer = new Timer(10, null);
        typeTimer.addActionListener(e -> {
            if (typeIndex < fullText.length()) {
                // append next character
                this.text.setText(fullText.substring(0, typeIndex + 1));
                typeIndex++;
            } else {
                typeTimer.stop();
                isTyping = false;
            }
        });

        typeTimer.start();
    }

}

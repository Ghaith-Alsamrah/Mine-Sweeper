import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public final class view {
    private JFrame windowFrame;
    private JPanel gamePanel;
    private JPanel startingGamePanel;
    private String gameDifficulity;
    public view() {
        createDisplay();
    }

    
    public void createDisplay () {
        windowFrame = new JFrame("My mine sweeper");
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setSize(300, 300);
        startingGamePanel = new JPanel();
        gamePanel = new JPanel();
        startScreen();
        windowFrame.setVisible(true);
    }
    private void startScreen () { 

        

        JPanel welcomPanel = new JPanel();
        welcomPanel.setBorder(new EmptyBorder(20,0,0,0));

        JLabel welcomeLabel = new JLabel("Welcome to my mine sweeper", JLabel.CENTER);

        welcomPanel.add(welcomeLabel);


        JPanel  difficultyPanel = new JPanel();
        {
            difficultyPanel.setBorder(new EmptyBorder(100,0,0,0));
            JPanel difficulityAlignmentPanel = new JPanel();

            difficulityAlignmentPanel.setLayout(new BoxLayout(difficulityAlignmentPanel, BoxLayout.Y_AXIS));

            JLabel choosingDifficulitylLabel = new JLabel("Choose difficulity", JLabel.CENTER);
            choosingDifficulitylLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            JPanel difficulityOptionsPanel = new JPanel();
            {
                JButton easyButton = new JButton("Easy");
                easyButton.addActionListener(e -> {
                    gameDifficulity = "easy";
                });
                JButton mediumButton = new JButton("Medium");
                mediumButton.addActionListener(e -> {
                    gameDifficulity = "medium";
                });
                JButton hardButton = new JButton("Hard");     
                hardButton.addActionListener(e -> {
                    gameDifficulity = "hard";
                });
                difficulityOptionsPanel.add(easyButton);
                difficulityOptionsPanel.add(mediumButton);
                difficulityOptionsPanel.add(hardButton);
            }
        

            difficulityAlignmentPanel.add(choosingDifficulitylLabel);
            difficulityAlignmentPanel.add(difficulityOptionsPanel);
            difficultyPanel.add(difficulityAlignmentPanel);
        }
        


        
        JButton startButton = new JButton("Start game");
        startButton.addActionListener(e -> {
            mineSweeper game = new mineSweeper(gameDifficulity);
            startingGamePanel.setVisible(false);
            gameScreen();
        });
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(0,0,20,0));
        windowFrame.setLayout(new BorderLayout());
        panel.add(startButton);
        startingGamePanel.add(welcomPanel, BorderLayout.NORTH);
        startingGamePanel.add(difficultyPanel, BorderLayout.CENTER);
        startingGamePanel.add(panel, BorderLayout.SOUTH);
        windowFrame.add(startingGamePanel);
    }


    private void gameScreen () {
        JPanel gameInfoPanel = new JPanel();
        {
            JLabel minesLeftLabel = new JLabel("Mines left: 10");
            JLabel timeLabel = new JLabel("Time: 0");
            gameInfoPanel.add(minesLeftLabel);
            gameInfoPanel.add(timeLabel);
        }
        JPanel gameBoardPanel = new JPanel();
        {
            gameBoardPanel.setLayout(new BoxLayout(gameBoardPanel, BoxLayout.Y_AXIS));
            for (int i = 0; i < 10; i++) {
                JPanel rowPanel = new JPanel();
                {
                    rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
                    for (int j = 0; j < 10; j++) {
                        JButton button = new JButton();
                        button.setPreferredSize(new Dimension(30,30));
                        button.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                if (SwingUtilities.isRightMouseButton(e)) {
                                    // game.flag(i, j);
                                    button.setText("F");
                                    button.setForeground(Color.RED);
                                } else {
                                // game.getGameBoard(i, j);
                                }
                            }
                        });
                        rowPanel.add(button);
                    }
                }
                gameBoardPanel.add(rowPanel);
            }
        }
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(gameInfoPanel, BorderLayout.NORTH);
        gamePanel.add(gameBoardPanel, BorderLayout.CENTER);
        windowFrame.add(gamePanel);
        windowFrame.pack();
    }

    public void settingOnPanel (JPanel panel) {
        panel.setVisible(true);
    }
    
    public void settingOffPanel (JPanel panel) {
        panel.setVisible(false);
    }


}

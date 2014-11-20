package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.*;

public class Game {
    private JFrame frame;
    private JButton moreCards;
    private JButton enoughCards;
    public static final int cards_const = 2;
    private Deck d = new Deck();
    public Player[] players;

    public static void main(String[] args) {
        new Game(2, new Deck()).StartGame();
    }

    class DrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
            Image img;
            File f;
            frame.setTitle("Денег-"+players[0].plBank+". У Вас "+players[0].playerPoints+" очков");
            try {
                    for (int j = 0; j < players[0].getPlayerCards().size(); j++) {
                        try {
                            f = new File("C:\\poker\\src\\resources\\" + players[0].getPlayerCards().get(j).getSuit() + "." +
                                    players[0].getPlayerCards().get(j).getRank() + ".gif");

                            img = ImageIO.read(f);
                            g.drawImage(img, 100*j, 0, 60, 86, this);

                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                    for (int j=0;j<players[1].getPlayerCards().size();j++) {
                        try {
                            f = new File("C:\\poker\\src\\resources\\back.gif");
                            img = ImageIO.read(f);
                            g.drawImage(img, 100*j, 150, 60, 86, this);

                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }
            } catch (Exception e) {
            }
        }
    }

    public Game(int playersCount, Deck deck) {
        players = new Player[playersCount];
        for (int i = 0; i < playersCount; i++) {
            players[i] = new Player();
        }
        d = deck;
    }

    void SetCards(Deck deck) {

        for (int i = 0; i < players.length; i++) {
            players[i].playerPoints = 0;
        }
        for (int i = 0; i < cards_const; i++) {
            for (int j = 0; j < players.length; j++) {
                players[j].addPlayerCards(deck.myCards.get((i * players.length) + j));
                deck.myCards.remove((i * players.length) + j);
            }
        }
        frame.repaint();
    }

    public void FoldCards(Player player) {
        int i = 0;
        while (player.getPlayerCards().size() > 0) {
            d.myCards.add(d.myCards.size(), player.getPlayerCards().get(i));
            player.getPlayerCards().remove(i);
        }
        player.playerPoints = 0;
        frame.repaint();
    }
    private void ActionDone() {
        Player.ComparePoints(players[0], players[1]);
        FoldCards(players[0]);
        FoldCards(players[1]);
        SetCards(d);
        players[0].plBank -= players[0].bet;
        frame.repaint();
    }
    public void StartGame() {
        DrawPanel draw = new DrawPanel();
        frame = new JFrame("Денег-" + players[0].plBank);
        frame.setSize(700, 550);
        frame.setLocation(35, 55);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, draw);
        frame.setVisible(true);
        moreCards = new JButton("more") {{
            setSize(100, 50);
            setLocation(200, 150);
            setLayout(null);
            setEnabled(true);
            setMinimumSize(this.getPreferredSize());
        }};
        frame.add(moreCards);
        enoughCards = new JButton("enough") {{
            setSize(100, 50);
            setLocation(350, 150);
            setLayout(null);
            setEnabled(true);
            setMinimumSize(this.getPreferredSize());
        }};
        frame.add(enoughCards);
        class MoreActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                players[0].addPlayerCards(d.myCards.get(0));
                d.myCards.remove(0);
                frame.repaint();
                if (players[0].playerPoints == 21) {
                    JOptionPane.showMessageDialog(null, "не ну это победа");
                    ActionDone();
                } else if (players[0].PlunkCheck()) {
                    JOptionPane.showMessageDialog(null, "Перебор");
                    ActionDone();
                }
            }
        }
        class EnoughActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String s;
                if (players[0].playerPoints > players[1].playerPoints)
                    s = "Победа ";
                else if (players[0].playerPoints < players[1].playerPoints)
                    s = "Поражение ";
                else s = "Ничья ";
                JOptionPane.showMessageDialog(null, s + "У вас " + players[0].playerPoints + " очков, у соперника - " + players[1].playerPoints);
                ActionDone();
            }
        }
        MoreActionListener moreActionListener = new MoreActionListener();
        EnoughActionListener enoughActionListener = new EnoughActionListener();
        enoughCards.addActionListener(enoughActionListener);
        moreCards.addActionListener(moreActionListener);
        SetCards(d);
        if (players[0].playerPoints == 21) {
            JOptionPane.showMessageDialog(null, "не ну это победа");
            ActionDone();
        }
    }
}

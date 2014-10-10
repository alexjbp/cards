package com.company;

import javax.smartcardio.Card;
import java.io.Console;
import java.util.Random;

class Game {
    public static final int cards_const = 6;
    private Deck d;
    public Player[] players;
    void setCards(Deck deck) {
        for (int i=0; i<cards_const;i++) {
            for (int j=0;j<players.length;j++) {
                players[j].addPlayerCards(d.myCards[(i*cards_const)+j]);
            }
        }
    }
    public Game(int playersCount, Deck deck) {
        players = new Player[playersCount];
        for (int i=0;i<playersCount;i++) {
            players[i]=new Player();
        }
        d=deck;
    }
    public void StartGame() {

    }
}

class Player {
    public int playerPoints = 0;
    public int num = 0;
    public int plBank = 5000;
    public int bet;

    private Deck.Card[] playerCards = new Deck.Card[Game.cards_const];

    public void setPlayerCards(Deck.Card[] arg_playerCards) {
        playerCards=arg_playerCards;
    }

    public Deck.Card[] getPlayerCards() {
        return playerCards;
    }


    public void addPlayerCards(Deck.Card arg) {
        playerCards[playerCards.length] = new Deck.Card();
        playerCards[playerCards.length] = arg;
    }
}

class Deck {
    public static final int deckSize = 52;
    class Card {
        private int suit; // 0- черви, 1- бубны, 2- крести, 3- пики
        private int rank; // от 2 до 14

        public void setSuit(int arg_suit) {
            suit = arg_suit;
        }

        public void setRank(int arg_rank) {
            rank = arg_rank;
        }

        public int getSuit () {
            return suit;
        }

        public int getRank () {
            return rank;
        }
    }

    Card[] myCards = new Card[deckSize];
    Deck() {
        int k=0;
        do {
            for (int i = 0; i <= 3; i++) {
                for (int j = 2; j <= 14; j++) {
                    myCards[k] = new Card();//почему нужно отдельно инициализировать поля?
                    myCards[k].setRank(j);
                    myCards[k++].setSuit(i);
                }
            }
        } while (k<deckSize);
    }
    private final <T> void Swap (T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public void MixDeck()
    {
        for (int i = 0; i < myCards.length; i++)
        {
            Random rnd=new Random();
            int j = rnd.nextInt(deckSize);
            Swap(myCards,i,j);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Deck d = new Deck();
        d.MixDeck();
        for (int i = 0; i < Deck.deckSize; i++) {
            System.out.println(d.myCards[i].getRank() + " " + d.myCards[i].getSuit());
        }
        Game g = new Game(2,d);
        g.setCards(d);
        for (int i=0;i<2;i++) {
  //          for (int j=0;j<6;j++)
//            System.out.println(g.players[i].getPlayerCards()[j]);
        }
    }
}

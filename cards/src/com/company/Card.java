package com.company;

public class Card {
    public static final int deckSize = 52;
    private int suit; // 0- черви, 1- бубны, 2- крести, 3- пики
    private int rank; // от 2 до 14

    public void setSuit(int arg_suit) {
        suit = arg_suit;
    }

    public void setRank(int arg_rank) {
        rank = arg_rank;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }
}
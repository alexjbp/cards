package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    public List<Card> myCards = new ArrayList<Card>();

    Deck() {
        int k=0;
        Card tmp = new Card();
        do {
            for (int i = 0; i <= 3; i++) {
                for (int j = 2; j <= 14; j++) {
                    tmp = new Card();
                    tmp.setRank(j);
                    tmp.setSuit(i);
                    myCards.add(tmp);
                    k++;
                }
            }
        } while (k<Card.deckSize);
        this.MixDeck();
    }
    private final <T> void Swap (List<Card> a, int i, int j) {
        Card t = a.get(i);
        a.set(i,a.get(j));
        a.set(j,t);
    }
    public void MixDeck()
    {
        for (int i = 0; i < myCards.size(); i++)
        {
            Random rnd=new Random();
            int j = rnd.nextInt(Card.deckSize);
            Swap(myCards,i,j);
        }
    }

}

package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public int playerPoints = 0;
    public int num = 0;
    public int plBank = 50;
    public int bet=20;
    private List<Card> playerCards = new ArrayList<Card>();

    public void setPlayerCards(List<Card> arg_playerCards) {
        playerCards=arg_playerCards;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }


    public void addPlayerCards(Card arg) {
        playerCards.add(arg);
        GetValue(arg);

    }

    public static void ComparePoints(Player a, Player b)
    {
        if ((a == b) || ((a.playerPoints > 21) && (b.playerPoints > 21)))
        {
            a.plBank += a.bet;
        }

        if ((a.playerPoints <= 21) && (b.playerPoints > 21) || (a.playerPoints > b.playerPoints))
        {
            a.plBank += 2 * a.bet;
        }
    }

    public Boolean PlunkCheck()
    {
        return playerPoints > 21;
    }

    public void GetValue(Card card)
    {
        if (card.getRank() == 14)
        {
            playerPoints += 11;
        }
        else
        if (card.getRank() <= 10)
        {
            playerPoints += card.getRank();
        }
        else
        {
            playerPoints += 10;
        }
    }

}

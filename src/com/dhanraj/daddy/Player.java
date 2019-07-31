package com.dhanraj.daddy;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String symbol=null,name;
    int totalCoinsOnBoard=0;
    List dadyPlaces;
    int totalCoins=9;


    public void setDadyPlaces(int[] dadyPlaces)
    {
        for(int place :dadyPlaces)
        {
            if (!this.dadyPlaces.contains(place))
            {
               this.dadyPlaces.add(place);
            }
        }
    }

    public int getDaddyPlacesLength()
    {
        return dadyPlaces.size();
    }

    public List getDaddyPlaces()
    {
        return dadyPlaces;
    }

    public int getTotalCoinsOnBoard() {
        return totalCoinsOnBoard;
    }

    public void setTotalCoinsOnBoard(int totalCoinsOnBoard) {
        this.totalCoinsOnBoard = totalCoinsOnBoard;
    }

    public Player(String symbol,String name) {
        this.symbol = symbol;
        this.name=name;
        dadyPlaces=new ArrayList();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

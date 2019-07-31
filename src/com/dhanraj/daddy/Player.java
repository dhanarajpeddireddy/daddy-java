package com.dhanraj.daddy;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String symbol=null,name;
    public ArrayList<List<Integer>> dadyPlacesList;
    public int totalCoins=constants.totalCoins,removedCoins=constants.remove_initial,coinsOnBoard=constants.onBord_initial;


    public void setDadyPlaces(int[] dadyPlaces)
    {
        List<Integer> daddyPlaces=new ArrayList();
        for(int place :dadyPlaces)
        {
            if (!this.dadyPlacesList.contains(place))
            {
                daddyPlaces.add(place);
            }
        }
        dadyPlacesList.add(daddyPlaces);

    }

    public int getDaddyPlacesLength()
    {
        int length=0;
        for (List daddyPlaces:dadyPlacesList)
        {
            length+=daddyPlaces.size();
        }
        return length;
    }

    public List getDaddyPlaces()
    {
        List<Integer> daddyplaces=new ArrayList();
        for (List<Integer> dadyPlaces:dadyPlacesList)
        {
            for(int place :dadyPlaces)
            {
               daddyplaces.add(place);
            }
        }
        return daddyplaces;
    }



    public Player(String symbol,String name) {
        this.symbol = symbol;
        this.name=name;
        dadyPlacesList=new ArrayList();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

package com.dhanraj.daddy;

import java.util.List;

public class Player {
    String symbol;
    int gamePicks=9;
    int remainPicks=9;
    int daddyCount=0;
    Bord bord;
    String name;

    public Player(String symbol, String name,Bord bord) {
        this.symbol = symbol;
        this.bord = bord;
        this.name=name;
    }

    public int getGamePicks() {
        return gamePicks;
    }

    public int getRemainPicks() {
        return remainPicks;
    }

    public void setRemainPicks(int remainPicks) {
        this.remainPicks = remainPicks;
    }

    public int getDaddyCount() {
        return daddyCount;
    }

    public void setDaddyCount(int daddyCount) {
        this.daddyCount = daddyCount;
    }

     public void placeCoin(position position)
     {
         Box box=bord.getBoxRow(position);
         box.updateSymbol(symbol);
     }


    public boolean checkDaddy(position position) {
        boolean dady=false;
        if (checkRowDaddy(position.getRow(),position.getColumn()) || checkColumnDaddy(position.getRow(),position.getColumn()))
        {
            return true;
        }
        return dady;
    }

    private boolean checkColumnDaddy(int row, int column) {
        boolean flag=false;
        List<position> rowPositionList=bord.getValidpositions(row);
        if (column!=3 || (column==3 && row<4))
        {
            if((bord.getBoxColumn(rowPositionList.get(0)).symbol==bord.getBoxColumn(rowPositionList.get(1)).symbol)
            && bord.getBoxColumn(rowPositionList.get(0)).symbol==bord.getBoxColumn(rowPositionList.get(2)).symbol)
            {
                flag=true;
            }
        }
        else if(column==3 && row>=4)
        {
            if((bord.getBoxColumn(rowPositionList.get(3)).symbol==bord.getBoxColumn(rowPositionList.get(4)).symbol)
                    && bord.getBoxColumn(rowPositionList.get(3)).symbol==bord.getBoxColumn(rowPositionList.get(5)).symbol)
            {
                flag=true;
            }
        }
        return flag;
    }

    private boolean checkRowDaddy(int row,int column) {
        boolean flag=false;
        List<position> rowPositionList=bord.getValidpositions(row);
        if (row!=3 || (row==3 && column<4))
        {
            if((bord.getBoxRow(rowPositionList.get(0)).symbol==bord.getBoxRow(rowPositionList.get(1)).symbol)
                    && bord.getBoxRow(rowPositionList.get(0)).symbol==bord.getBoxRow(rowPositionList.get(2)).symbol)
            {
                flag=true;
            }
        }
        else if(row==3 && column>=4)
        {
            if((bord.getBoxRow(rowPositionList.get(3)).symbol==bord.getBoxRow(rowPositionList.get(4)).symbol)
                    && bord.getBoxRow(rowPositionList.get(3)).symbol==bord.getBoxRow(rowPositionList.get(5)).symbol)
            {
                flag=true;
            }
        }
        return flag;
    }

    public boolean removeOpponentCoin(Player opponentPlayer, position position) {
        boolean done=false;
        if (bord.getBoxRow(position).symbol!=opponentPlayer.symbol || bord.getBoxRow(position).symbol=="0")
        {
            return false;
        }
        else if(!checkDaddy(position))
        {
           bord.getBoxRow(position).updateSymbol("0");
           done=true;
        }

        return done;

    }
}

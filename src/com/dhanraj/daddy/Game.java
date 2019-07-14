package com.dhanraj.daddy;

import java.util.Scanner;

public class Game {

static  Bord bord;static   Game game;
static Player currentPlayer,opponentPlayer,swap;
static boolean flag=true;
    public Game() {
        bord=new Bord();
        Player player1=new Player("1","dana",bord);
        Player player2=new Player("2","arjun",bord);
        currentPlayer=player1;
        opponentPlayer=player2;
    }

    public static void main(String ags[]) {
       game = new Game();
        boolean gameStatus=false;

        do {
            gameStatus=game.runGame(game);

        }while (!gameStatus);


    }

    private boolean runGame(Game game) {
        boolean flag=false;
        do {
            bord.displayBord();
            System.out.println(currentPlayer.name + " choice ");
            System.out.println(currentPlayer.remainPicks + " yours ");
            System.out.println(opponentPlayer.remainPicks + " opponents ");
            int row = game.read("Enter row position : ");
            int column = game.read("Enter column position : ");
            position position = new position(row, column);
            Box b = bord.getBoxRow(position);
            currentPlayer.placeCoin(new position(row, column));
            if (currentPlayer.remainPicks <= 7)
            {
                if (currentPlayer.checkDaddy(position)) {
                    boolean removeStatus = false;
                    do {
                        bord.displayBord();
                        System.out.println("Daddy for " + currentPlayer.name + "\n" + "Remove " + opponentPlayer.name + " Coin");
                        row = game.read("Enter opponent coin row position : ");
                        column = game.read("Enter opponent coin column position : ");
                        position = new position(row, column);
                        if (currentPlayer.removeOpponentCoin(opponentPlayer, position)) {
                            System.out.println(opponentPlayer.name + " Coin removed Success.");
                            removeStatus = true;
                        } else
                            System.out.println("you can remove only " + opponentPlayer.name + " Coins not in daddy");

                    } while (!removeStatus);

                }
        }
                currentPlayer.setRemainPicks(currentPlayer.remainPicks - 1);
                swap = currentPlayer;
                currentPlayer = opponentPlayer;
                opponentPlayer = swap;

        }while (currentPlayer.remainPicks != 0) ;


        return flag;
    }

    public  int read(String message) {
        System.out.println(message);
        Scanner s=new Scanner(System.in);
        int value=Integer.parseInt(s.next());
        return value;
    }


}

package com.dhanraj.daddy;

import java.util.Scanner;

public class Game {
 static    Board b;
 static Player p1,p2,currentPlayer;
 static Controller controller;
 static Scanner scanner;
   static Response response;

public static void main(String[] args)
{
    scanner=new Scanner(System.in);
    b=new Board();
    p1=new Player("1","arjun");
    p2=new Player("2","naga");
    controller=new Controller(p1,p2,b);
    startGame();

}

    private static void startGame() {
        b.displayBoard();
        controller.setCurrentPlayer();
        currentPlayer=controller.getCurrentPlayer();
        if (currentPlayer.totalCoins>0)
    {
       placing();
    }
        else
        {
           moving();
        }
    }

    private static boolean isGameover() {
    boolean flag=false;
    return flag;
    }

    private static void moving() {
        Controller.showMessage(currentPlayer.name+" turns\n move your coin");
        Controller.showMessage("Choose Source");
        int input_source=scanner.nextInt();
        Controller.showMessage("Choose Destination");
        int input_destination=scanner.nextInt();
        response=controller.checkMovePlaces(input_source,input_destination);
        if (response.status)
        {
          controller.makeMove(input_source,input_destination);
          checkDaddy(input_destination);

        }
        else
        {
            Controller.showMessage(currentPlayer.name+" "+response.getMsg());
            moving();
        }


    }

    private static void placing() {
        Controller.showMessage(currentPlayer.name+" turns\nChoose one place");
        int input_place=scanner.nextInt();
        response=controller.setPlace(input_place,currentPlayer);
        if (response.status)
        {
            if (currentPlayer.totalCoinsOnBoard>=3)
                checkDaddy(input_place);
            else
            {
                startGame();
            }


        }
        else
        {
            Controller.showMessage(response.getMsg());
            placing();
        }
    }

    private static void checkDaddy(int input_place) {

            response= controller.isDaddyOccure(input_place);
            if (response.status)
            {
                Controller.showMessage(response.msg +" Daay Congrats "+currentPlayer.name);
                response=  controller.checkRemoveCoins();
                if (response.status)
                {
                    removeCoin();

                }
                else
                {
                    Controller.showMessage(response.msg +"  "+currentPlayer.name);

                    startGame();
                }
            }
            else
                startGame();


    }

    private static void removeCoin() {
        Controller.showMessage("Enter opponent coin place for remove");
        int input_Removeplace=scanner.nextInt();
        response=controller.removeCoin(input_Removeplace);
        if (response.status)
        {
            Controller.showMessage("removed sucessfully");
            startGame();
        }
        else
        {
            Controller.showMessage(response.getMsg());
            removeCoin();

        }
    }

}

package com.dhanraj.daddy;

import java.util.Scanner;

public class Game {
 static  Board b;
 static Player p1,p2,currentPlayer;
 static Controller controller;
 static Scanner scanner;
 static Response response;
 static   String phase=constants.phase_place;

public static void main(String[] args)
{
    scanner=new Scanner(System.in);
    b=new Board();
    p1=new Player(constants.player1_symbol,constants.player1_name);
    p2=new Player(constants.player2_symbol,constants.player2_name);
    controller=new Controller(p1,p2,b);
    startGame();

}

    private static void startGame() {
        b.displayBoard();
        controller.setCurrentPlayer();
        currentPlayer=controller.getCurrentPlayer();
         phase =controller.getGamePhase(phase);
        if (phase.equals(constants.phase_place))
    {
       placing();
    }
        else if(phase.equals(constants.phase_move))
        {
           moving();
        }
    }


    private static void moving() {
        Controller.showMessage(currentPlayer.name+constants.chance_move);
        Controller.showMessage(constants.choose_source);
        int input_source=readValue(constants.choose_source);
        Controller.showMessage(constants.choose_destinaation);
        int input_destination=readValue(constants.choose_destinaation);
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
        Controller.showMessage(currentPlayer.name+constants.chance_place);
        int input_place=readValue(currentPlayer.name+constants.chance_place);
        response=controller.setPlace(input_place);
        if (response.status)
        {
            if (currentPlayer.coinsOnBoard>=3)
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
                Controller.showMessage(currentPlayer.name+constants.daddy_occure);
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
    private static void checkGame() {
        if (controller.isGameover())
        {
            Controller.showMessage(controller.getCurrentPlayer().name+" "+constants.game_completed);
        }
        else
            startGame();
    }

    private static void removeCoin() {
        Controller.showMessage(currentPlayer.name+" "+constants.remove_coin);
        int input_Removeplace=readValue(currentPlayer.name+" "+constants.remove_coin);
        response=controller.removeCoin(input_Removeplace);
        if (response.status)
        {
            Controller.showMessage(constants.remove_success);
            checkGame();
        }
        else
        {
            Controller.showMessage(response.getMsg());
            removeCoin();

        }
    }


    public static int readValue(String value)
    {
        int integer = 0;
        try
        {
            integer = scanner.nextInt();
        }
        catch(Exception e)
        {
            scanner.nextLine();
            Controller.showMessage(constants.choose_betweenRange);
            Controller.showMessage(value);
            integer = readValue(value);
        }
        return integer;
    }
}

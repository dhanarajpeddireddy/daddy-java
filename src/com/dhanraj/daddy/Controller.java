package com.dhanraj.daddy;

import java.util.List;

public class Controller {

    Player p1,p2,currentPlayer;
    Board board;
    Response response;
    boolean allSuccess=true;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer() {
        if (currentPlayer==null)
            this.currentPlayer=p1;
        else
            this.currentPlayer=this.getOpponentPlayer();
    }

    public Player getOpponentPlayer() {
        if (currentPlayer==p2 || currentPlayer==null)
            return p1;
        else
            return p2;
    }
    public Controller(Player p1, Player p2, Board board) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = board;
    }


    public Response setPlace(int input_place,Player currentPlayer) {
         response=new Response();
        if (input_place<24 && input_place>=0)
        {
            if (!board.getBordPlace(input_place).isOcupied) {
                Place place = board.getBordPlace(input_place);
                place.setOcupiedPlayer(currentPlayer);
                currentPlayer.totalCoinsOnBoard++;
                currentPlayer.totalCoins--;
                response.status=true;
                allSuccess=true;
                return response;
            }
            else
            {
                response.status=false;
                response.setMsg("value is already selected");
                allSuccess=false;
                return response;
            }
        }
        response.status=false;
        response.setMsg("Please choose values between 0 and 24");
        allSuccess=false;
        return response;
    }

    public static void showMessage(String value)
    {
        System.out.println(value);
    }

    public Response isDaddyOccure(int input_place) {
        response=new Response();
        checkDaddy(input_place,"v");
        if (response.status)
        {
            return response;
        }
        else
        {
            checkDaddy(input_place,"h");

        }
        return response;
    }

    private Response checkDaddy(int input_place,String tag) {
        Place place=board.getBordPlace(input_place);
        int[] places=place.getLinks(tag);
        if (board.boardPlaces[places[0]].isOcupied && board.boardPlaces[places[1]].isOcupied )
        {
            if (place.getOcupiedPlayer().symbol==board.boardPlaces[places[0]].ocupiedPlayer.symbol
                    && place.getOcupiedPlayer().symbol==board.boardPlaces[places[1]].ocupiedPlayer.symbol)
            {
              int[] daddyPlaces={input_place,places[0],places[1]};
                place.getOcupiedPlayer().setDadyPlaces(daddyPlaces);
               response.status=true;
               response.msg=tag;
               return response;
            }

        }

        response.status=false;
        return response;
    }


    public Response checkRemoveCoins() {
        response=new Response();
      Player opponent=getOpponentPlayer();
      if (opponent.totalCoinsOnBoard- opponent.getDaddyPlacesLength()>0)
        {
            response.status=true;
            return response;
        }
      response.status=false;
      response.setMsg("sorry you dont have valid opponent  coins for remove ");
        return response;
    }

    public Response removeCoin(int input_removeplace) {
        response=new Response();
        if (board.getBordPlace(input_removeplace).isOcupied) {
            if (board.getBordPlace(input_removeplace).getOcupiedPlayer().symbol == getOpponentPlayer().symbol) {
                if (!getOpponentPlayer().getDaddyPlaces().contains(input_removeplace))
                {
                    response.status = true;
                    board.getBordPlace(input_removeplace).setUnOcupied();
                    return response;
                }
                else
                {
                    response.status = false;
                    response.setMsg("Daddy place you cont remove");
                    return response;
                }
            }
        }
        else
        {
            response.setMsg("place is empty");
            response.status=false;
            return response;
        }

        response.status=false;
        response.setMsg("Remove only opponent coin");
        return response;
    }

    public Response checkMovePlaces(int input_source, int input_destination) {
        response=new Response();
        if (getPlayerAtPlace(input_source)!=null) {
            if (getPlayerAtPlace(input_source).symbol == getCurrentPlayer().symbol) {
                if (board.getBordPlace(input_source).isBeside(input_destination)) {

                    if (!board.getBordPlace(input_destination).isOcupied) {
                        response.status = true;
                        return response;
                    } else {
                        response.status = false;
                        response.setMsg("choose unocupied place");
                        return response;
                    }

                } else {
                    response.status = false;
                    response.setMsg("choose Wrong place");
                    return response;
                }
            } else {
                response.status = false;
                response.setMsg("choose your coin");
                return response;
            }
        }
        response.status = false;
        response.setMsg("choose your coin");
        return response;
    }

    private Player getPlayerAtPlace(int place) {
        Player player=board.getBordPlace(place).ocupiedPlayer;
        return player;
    }

    public void makeMove(int input_source, int input_destination) {

        board.getBordPlace(input_source).setUnOcupied();
        board.getBordPlace(input_destination).setOcupiedPlayer(getCurrentPlayer());
        if (getCurrentPlayer().getDaddyPlaces().contains(input_source))
        {
            //todo remove places from dady Places list
        }

    }
}

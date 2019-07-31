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


    public Response setPlace(int input_place) {
         response=new Response();
        if (input_place<=constants.place_ends && input_place>=constants.place_starts)
        {
            if (!board.getBordPlace(input_place).isOcupied) {
                Place place = board.getBordPlace(input_place);
                place.setOcupiedPlayer(currentPlayer);
                currentPlayer.coinsOnBoard++;
                response.status=true;
                allSuccess=true;
                return response;
            }
            else
            {
                response.status=false;
                response.setMsg(constants.place_notAvialable);
                allSuccess=false;
                return response;
            }
        }
        response.status=false;
        response.setMsg(constants.choose_betweenRange);
        allSuccess=false;
        return response;
    }

    public static void showMessage(String value)
    {
        System.out.println(value);
    }

    public Response isDaddyOccure(int input_place) {
        response=new Response();
        checkDaddy(input_place,constants.v_tag);
        if (response.status)
        {
            return response;
        }
        else
        {
            checkDaddy(input_place,constants.h_tag);

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
      if (opponent.coinsOnBoard- opponent.getDaddyPlacesLength()>0)
        {
            response.status=true;
            return response;
        }
      response.status=false;
      response.setMsg(constants.noCoinsFor_remove);
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
                    getOpponentPlayer().coinsOnBoard--;
                    getOpponentPlayer().removedCoins++;
                    return response;
                }
                else
                {
                    response.status = false;
                    response.setMsg(constants.daddy_place);
                    return response;
                }
            }
        }
        else
        {
            response.setMsg(constants.empty_place_remove);
            response.status=false;
            return response;
        }

        response.status=false;
        response.setMsg(constants.remove_opponent);
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
                        response.setMsg(constants.ocupied_place);
                        return response;
                    }

                } else {
                    response.status = false;
                    response.setMsg(constants.wrong_place);
                    return response;
                }
            } else {
                response.status = false;
                response.setMsg(constants.move_yourCoin);
                return response;
            }
        }
        response.status = false;
        response.setMsg(constants.move_yourCoin);
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
            for(List list:getCurrentPlayer().dadyPlacesList)
            {
                if (list.contains(input_source))
                {
                    getCurrentPlayer().dadyPlacesList.remove(list);
                    break;
                }
            }
        }

    }

    public String getGamePhase(String phase) {
        String gamephase=phase;
        if (gamephase.equals(constants.phase_move))
        {
            phase=constants.phase_move;
        }

       else if (gamephase.equals(constants.phase_place)&& currentPlayer.totalCoins-(currentPlayer.removedCoins+currentPlayer.coinsOnBoard)>0)
        {
            phase=constants.phase_place;
        }
        else
        {
            gamephase=constants.phase_move;
        }

        return gamephase;
    }
    public   boolean isGameover() {
        if (getOpponentPlayer().coinsOnBoard==constants.min_coins)
            return true;
        return false;
    }


}

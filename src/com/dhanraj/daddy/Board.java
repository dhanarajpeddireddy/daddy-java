package com.dhanraj.daddy;
public class Board {

    static int totalPlaces = 24;
    Place[] boardPlaces;


    public Board() {
        boardPlaces = new Place[Board.totalPlaces];
        for (int i = 0; i < Board.totalPlaces; i++) {
            boardPlaces[i] = new Place();
        }

        // first
        boardPlaces[0].setLinks(1, 2, 9, 21);
        boardPlaces[1].setLinks(0, 2, 4, 7);
        boardPlaces[1].setHorizontalMiddle(true);
        boardPlaces[2].setLinks(1, 0, 14, 23);
        boardPlaces[9].setLinks(0, 21, 10, 11);
        boardPlaces[9].setVerticleMiddle(true);
        boardPlaces[14].setLinks(13, 12,2,23);
        boardPlaces[14].setVerticleMiddle(true);
        boardPlaces[21].setLinks(22, 23, 9, 0);
        boardPlaces[22].setLinks(21, 23, 19, 16);
        boardPlaces[22].setHorizontalMiddle(true);
        boardPlaces[23].setLinks(22, 21, 14, 2);
        // second
        boardPlaces[3].setLinks(4, 5, 10, 18);
        boardPlaces[4].setLinks(3, 5, 1, 7);
        boardPlaces[4].setMiddle(true);
        boardPlaces[5].setLinks(4, 3, 13, 20);
        boardPlaces[10].setLinks(9, 11, 3, 18);
        boardPlaces[10].setMiddle(true);
        boardPlaces[13].setLinks(12, 14, 5, 20);
        boardPlaces[13].setMiddle(true);
        boardPlaces[18].setLinks(19, 20, 10, 3);
        boardPlaces[19].setLinks(18, 20, 16, 22);
        boardPlaces[19].setMiddle(true);
        boardPlaces[20].setLinks(19, 18, 13, 5);
        // third
        boardPlaces[6].setLinks(7, 8, 11, 15);
        boardPlaces[7].setLinks(6, 8, 4, 1);
        boardPlaces[7].setHorizontalMiddle(true);
        boardPlaces[8].setLinks(7, 6, 12, 17);
        boardPlaces[11].setLinks(10, 9, 6, 15);
        boardPlaces[11].setVerticleMiddle(true);
        boardPlaces[12].setLinks(13, 14, 8, 17);
        boardPlaces[12].setVerticleMiddle(true);
        boardPlaces[15].setLinks(16, 17, 11, 6);
        boardPlaces[16].setLinks(15, 17, 19, 22);
        boardPlaces[16].setHorizontalMiddle(true);
        boardPlaces[17].setLinks(16, 15, 12, 8);
    }


    public void displayBoard() {
        System.out.println(displayPlace(0) + " - - - - - " + displayPlace(1) + " - - - - - " + displayPlace(2));
        System.out.println("|           |           |");
        System.out.println("|     " + displayPlace(3) + " - - " + displayPlace(4) + " - - " + displayPlace(5) + "     |");
        System.out.println("|     |     |     |     |");
        System.out.println("|     | " + displayPlace(6) + " - " + displayPlace(7) + " - " + displayPlace(8) + " |     |");
        System.out.println("|     | |       | |     |");
        System.out.println(displayPlace(9) + " - - " + displayPlace(10) + "-" + displayPlace(11) + "       " + displayPlace(12) + "-" + displayPlace(13) + " - - " + displayPlace(14));
        System.out.println("|     | |       | |     |");
        System.out.println("|     | " + displayPlace(15) + " - " + displayPlace(16) + " - " + displayPlace(17) + " |     |");
        System.out.println("|     |     |     |     |");
        System.out.println("|     " + displayPlace(18) + " - - " + displayPlace(19) + " - - " + displayPlace(20) + "     |");
        System.out.println("|           |           |");
        System.out.println(displayPlace(21) + " - - - - - " + displayPlace(22) + " - - - - - " + displayPlace(23));
    }

    private String displayPlace(int i) {
       if (boardPlaces[i].getOcupiedPlayer()==null)
           return "0";
      return boardPlaces[i].getOcupiedPlayer().getSymbol();
    }


    public Place getBordPlace(int position)
    {
      return boardPlaces[position];
    }

}

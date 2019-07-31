package com.dhanraj.daddy;

public class Place {
    int places=2;
    boolean isOcupied;
    Player ocupiedPlayer;
    int[] verticleLinks,horizontalLinks;
    boolean isHorizontalMiddle=false,isVerticleMiddle=false,isMiddle=false;

    public boolean isHorizontalMiddle() {
        return isHorizontalMiddle;
    }

    public void setHorizontalMiddle(boolean horizontalMiddle) {
        isHorizontalMiddle = horizontalMiddle;
    }

    public boolean isVerticleMiddle() {
        return isVerticleMiddle;
    }

    public void setVerticleMiddle(boolean verticleMiddle) {
        isVerticleMiddle = verticleMiddle;
    }

    public boolean isMiddle() {
        return isMiddle;
    }

    public void setMiddle(boolean middle) {
        isMiddle = middle;
    }

    public Place() {
        isOcupied = false;
        ocupiedPlayer = null;
        verticleLinks=new int[places];
        horizontalLinks=new int[places];

    }

    public boolean isOcupied() {
        return isOcupied;
    }

    public void setUnOcupied() {
        isOcupied = false;
        this.ocupiedPlayer=null;
    }

    public Player getOcupiedPlayer() {
        return ocupiedPlayer;
    }

    public void setOcupiedPlayer(Player ocupiedPlayer) {
        isOcupied=true;
        this.ocupiedPlayer = ocupiedPlayer;
    }

    public void setLinks(int place1,int place2,int place3,int place4)
    {
        horizontalLinks[0]=place1;horizontalLinks[1]=place2;
        verticleLinks[0]=place3;verticleLinks[1]=place4;

    }

    public int[] getLinks(String tag)
    {
        if (tag=="v")
            return verticleLinks;
        else if(tag=="h")
            return  horizontalLinks;
        else
            return null;

    }

    public boolean isBeside(int position) {
        if (isMiddle) {
            for (int i = 0; i < verticleLinks.length; i++) {
                if (verticleLinks[i] == position) {
                    return true;
                } else if (horizontalLinks[i] == position) {
                    return true;
                }
            }

        } else if (!isHorizontalMiddle && !isVerticleMiddle)
        {
          if (verticleLinks[0]==position)
          {
              return true;
          }
          else if (horizontalLinks[0]==position)
              return true;

        }
        else if (isHorizontalMiddle)
        {
            for (int i=0;i<horizontalLinks.length;i++)
            {
                if (horizontalLinks[i]==position)
                {
                    return true;
                }
                else if (i==0 && verticleLinks[i]==position)
                    return true;
            }
        }
        else if (isVerticleMiddle)
        {
            for (int i=0;i<verticleLinks.length;i++)
            {
                if (verticleLinks[i]==position)
                {
                    return true;
                }
                else if (i==0 && horizontalLinks[i]==position)
                    return true;
            }
        }
        return false;
    }

}

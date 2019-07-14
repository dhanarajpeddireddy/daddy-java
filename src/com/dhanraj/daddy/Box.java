package com.dhanraj.daddy;

public class Box {
    boolean enabledBox;
    String symbol;


    public Box(boolean enabledBox, String symbol) {
        this.enabledBox = enabledBox;
        this.symbol = symbol;
    }

    public boolean isEnabledBox() {
        return enabledBox;
    }

    public String getSymbol() {
        return symbol;
    }


    public void updateSymbol(String symbol)
    {
        this.symbol=symbol;
    }

}

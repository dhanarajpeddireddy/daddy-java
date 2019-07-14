package com.dhanraj.daddy;

public class position {

    int row,column;

    public position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String toString(){//overriding the toString() method
        return "row="+row+", column="+column;
    }
}

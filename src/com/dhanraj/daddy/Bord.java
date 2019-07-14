package com.dhanraj.daddy;
import java.util.ArrayList;
import java.util.List;
public class Bord {
    int size=7;
    List<position> rowPositions ;
    ArrayList<List> validpositions=new ArrayList<>();
    Box all_boxs[][];

    public Bord() {
        setRowPositions();
        all_boxs=new Box[size][size];
        loadBoxes();
    }
    private void loadBoxes() {
        for(int row=0;row<size;row++)
        {
            List<position> box=getValidpositions(row);
            List<Integer> columnValues=new ArrayList<>();
            if (row!=3)
            {
                columnValues.add(box.get(0).getColumn());columnValues.add(box.get(1).getColumn());columnValues.add(box.get(2).getColumn());

            }
            else {
                columnValues.add(box.get(0).getColumn());columnValues.add(box.get(1).getColumn());columnValues.add(box.get(2).getColumn());
                columnValues.add(box.get(3).getColumn());columnValues.add(box.get(4).getColumn());columnValues.add(box.get(5).getColumn());

            }

            for (int column=0;column<size;column++)
            {
                if (columnValues.contains(column)) {
                    all_boxs[row][column] = new Box(true, "0");
                }
                else {
                    all_boxs[row][column] = new Box(false, "-");
                }
            }
        }
    }

    private void setRowPositions() {
        // Zero row
        rowPositions=new ArrayList<>();
        rowPositions.add(new position(0,0));
        rowPositions.add(new position(0,3));
        rowPositions.add(new position(0,6));
        validpositions.add(rowPositions);

        // first row
        rowPositions=new ArrayList<>();
        rowPositions.add(new position(1,1));
        rowPositions.add(new position(1,3));
        rowPositions.add(new position(1,5));
        validpositions.add(rowPositions);

        //secnd row
        rowPositions=new ArrayList<>();
        rowPositions.add(new position(2,2));
        rowPositions.add(new position(2,3));
        rowPositions.add(new position(2,4));
        validpositions.add(rowPositions);

        //third row
        rowPositions=new ArrayList<>();
        rowPositions.add(new position(3,0));
        rowPositions.add(new position(3,1));
        rowPositions.add(new position(3,2));
        rowPositions.add(new position(3,4));
        rowPositions.add(new position(3,5));
        rowPositions.add(new position(3,6));
        validpositions.add(rowPositions);


        // fourth row
        rowPositions=new ArrayList<>();
        rowPositions.add(new position(4,2));
        rowPositions.add(new position(4,3));
        rowPositions.add(new position(4,4));
        validpositions.add(rowPositions);

        // fifth row
        rowPositions=new ArrayList<>();
        rowPositions.add(new position(5,1));
        rowPositions.add(new position(5,3));
        rowPositions.add(new position(5,5));
        validpositions.add(rowPositions);

        // sixth row
        rowPositions=new ArrayList<>();
        rowPositions.add(new position(6,0));
        rowPositions.add(new position(6,3));
        rowPositions.add(new position(6,6));
        validpositions.add(rowPositions);
    }


    public List<position> getValidpositions(int row) {
        return validpositions.get(row);
    }

    public void displayBord()
    {
        for(int i=0;i<size;i++)
        {
            for (int k=0;k<size;k++)
            {
                Box box=all_boxs[i][k];
                System.out.print(box.getSymbol()+"  ");
            }
            System.out.println("\n");
        }
    }


    public Box getBoxRow(position position) {
        Box box = null;
        if(position.row<size || position.column<size)
        {
            box=all_boxs[position.row][position.column];
        }
        return box;
    }

    public Box getBoxColumn(position position) {
        Box box = null;
        if(position.row<size || position.column<size)
        {
            box=all_boxs[position.column][position.row];
        }
        return box;
    }

}

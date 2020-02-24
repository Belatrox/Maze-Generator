package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Cell {
    int indexOfi;
    int indexOfj;
    boolean[] walls = new boolean[]{true, true, true, true};
    Line[] linesOfCell = new Line[4];
    boolean visited = false;

    public Cell(int i, int j){
        this.indexOfi = i;
        this.indexOfj = j;
    }

    void show(int magnitude, Pane canvas){
        int x = indexOfj * magnitude;
        int y = indexOfi * magnitude;

            Line topLine = new Line(x, y, x + magnitude, y);
            //topLine.setStrokeWidth(-20);
            canvas.getChildren().add(topLine);
            linesOfCell[0] = topLine;

            Line rightLine = new Line(x + magnitude, y, x+ magnitude, y + magnitude);
            //rightLine.setStrokeWidth(-20);
            canvas.getChildren().add(rightLine);
            linesOfCell[1] = rightLine;

            Line bottomLine = new Line(x, y + magnitude, x + magnitude, y + magnitude);
             //bottomLine.setStrokeWidth(-20);
            canvas.getChildren().add(bottomLine);
            linesOfCell[2] = bottomLine;

            Line leftLine = new Line(x, y, x, y + magnitude);
               // leftLine.setStrokeWidth(-20);
            canvas.getChildren().add(leftLine);
            linesOfCell[3] = leftLine;
        // we can see that the cell is visited...

    }

    int getIndex(int i, int j){
        if(i < 0 || j < 0 || i > 9 || j > 9){
            return  -1;
        }
        return j + (i * 10);
    }

    Cell checkNeighbors(ArrayList<Cell> grid){
        ArrayList<Cell> neighbors = new ArrayList<>();
        // 10 is numberOfCols..
        System.out.println(getIndex(indexOfj,indexOfi));
        Cell topCell = getIndex(indexOfi - 1, indexOfj) != -1 ? grid.get(getIndex(indexOfi - 1, indexOfj)) : null;
        Cell rightCell = getIndex(indexOfi, indexOfj + 1) != -1 ? grid.get(getIndex(indexOfi, indexOfj + 1)) : null;
        Cell bottomCell = getIndex(indexOfi + 1, indexOfj) != -1 ? grid.get(getIndex(indexOfi + 1, indexOfj)) : null;
        Cell leftCell = getIndex(indexOfi, indexOfj - 1) != -1 ? grid.get(getIndex(indexOfi, indexOfj - 1)) : null;

        if(topCell != null && !topCell.visited){
            neighbors.add(topCell);
        }
        if(rightCell != null && !rightCell.visited){
            neighbors.add(rightCell);
        }
        if(bottomCell != null && !bottomCell.visited){
            neighbors.add(bottomCell);
        }
        if(leftCell != null && !leftCell.visited){
            neighbors.add(leftCell);
        }
        //System.out.println(neighbors.size());
        if (neighbors.size() > 0){
            int randomIndex = (int)(Math.random() * neighbors.size());
            return  neighbors.get(randomIndex);
        }else {
            return null;
        }
    }


    public static void removeWalls(Cell currentCell, Cell nextCell, Pane canvas){
        int x = currentCell.indexOfj - nextCell.indexOfj;
        if(x == 1){
            //canvas.getChildren().remove(currentCell.linesOfCell[3]);
            //canvas.getChildren().remove(nextCell.linesOfCell[1]);
            currentCell.linesOfCell[3].setStroke(Color.MEDIUMPURPLE);
            nextCell.linesOfCell[1].setStroke(Color.MEDIUMPURPLE);
        }else if(x == -1){
            //canvas.getChildren().remove(currentCell.linesOfCell[1]);
            //canvas.getChildren().remove(nextCell.linesOfCell[3]);
            currentCell.linesOfCell[1].setStroke(Color.MEDIUMPURPLE);
            nextCell.linesOfCell[3].setStroke(Color.MEDIUMPURPLE);
        }

        int y = currentCell.indexOfi - nextCell.indexOfi;
        if(y == 1){
            //canvas.getChildren().remove(currentCell.linesOfCell[0]);
            //canvas.getChildren().remove(nextCell.linesOfCell[2]);
            currentCell.linesOfCell[0].setStroke(Color.MEDIUMPURPLE);
            nextCell.linesOfCell[2].setStroke(Color.MEDIUMPURPLE);
        }else if(y == -1){
            //canvas.getChildren().remove(currentCell.linesOfCell[2]);
            //canvas.getChildren().remove(nextCell.linesOfCell[0]);
            currentCell.linesOfCell[2].setStroke(Color.MEDIUMPURPLE);
            nextCell.linesOfCell[0].setStroke(Color.MEDIUMPURPLE);
        }
    }
}

package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {
    double cols, rows;
    int w = 40;
    ArrayList<Cell> grid = new ArrayList<>();
    ArrayList<Cell> stack = new ArrayList<>();
    Cell currentCell;
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Pane canvas = new Pane();

        cols = 10;
        rows = 10;

        //System.out.println(cols + " ," + rows);
        for (int i = 0 ; i < rows; i++){
            for (int j = 0; j < cols; j++){
                Cell newCell = new Cell(i, j);
                grid.add(newCell);
                newCell.show(w, canvas);
            }
        }
        currentCell = grid.get(0);
        boolean condition = true;
        while (condition){
            if(currentCell != null){
                currentCell.visited = true;
                Rectangle visitedRect = new Rectangle(currentCell.indexOfj * w, currentCell.indexOfi * w, w-0.4, w-0.4);
                visitedRect.setFill(Color.MEDIUMPURPLE);
                canvas.getChildren().add(visitedRect);
            }
            ///////
            //STEP1
            Cell nextCell = currentCell.checkNeighbors(grid);
            if(nextCell != null){
                nextCell.visited = true;
                //STEP2
                stack.add(currentCell);
                //STEP3
                Cell.removeWalls(currentCell, nextCell, canvas);
                //STEP4
                currentCell = nextCell;
            }else if(!stack.isEmpty()){
                    currentCell = stack.remove(stack.size() - 1);
            }
            if(stack.isEmpty()){
                condition = false;
            }
        }

        primaryStage.setTitle("MazeGenerator");
        primaryStage.setScene(new Scene(canvas, 400, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package nels;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyRegularImage {
    private int width;
    private int rows;
    private int cols;
    private int ID=-1;
    private Group root;
    private int[][] destination_image;

    public MyRegularImage(int width, int cols, int rows, Group root  ){
        this.width = width;
        this.rows = rows;
        this.cols = cols;
        this.root = root;
        this.destination_image = new int[(this.rows)*width][(this.cols)*width];
        //clearing output image
        for (int i=0; i<(this.cols)*width; ++i){
            for (int j=0; j<(this.rows)*width; ++j){
                destination_image[j][i] = 0;
            }
        }
    }

    void compute_element(int ID){

        double x_start_d = (Math.ceil(ID%cols))*width;
        double y_start_d = (Math.ceil((ID-ID%cols)/cols)*width);
        int x_start = (int) x_start_d;
        int y_start = (int) y_start_d;
        for (int i=0; i<width; ++i){
            for (int j=0; j<width; ++j){
                if (i%10==0 && j%10==0) {
                    destination_image[j+y_start][i+x_start] = 1;
                }
            }
        }
    }

    //metod that gets result of computation and returns it to window
    void compute_all(){
        for (int i=0; i<width*(cols); ++i){
            for (int j=0; j<width*(rows); ++j){
                if (destination_image[j][i] == 1) {
                    Rectangle rect1 = new Rectangle(i, j, 1, 1);
                    rect1.setFill(Color.BLUE);
                    root.getChildren().add(rect1);
                }
            }
        }
    }

    //returning next ID to thread
    int get_ID(){
        ID++;
        return ID;
    }

}

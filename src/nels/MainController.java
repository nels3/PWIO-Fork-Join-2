package nels;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.util.concurrent.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private TextArea text_one_thread;
    @FXML
    private TextArea text_multi_thread;
    @FXML
    private TextArea warnings;
    @FXML
    private TextArea width;
    @FXML
    private TextArea number_of_cols;
    @FXML
    private TextArea number_of_rows;
    @FXML
    private Label width_info;
    @FXML
    private Label number_of_cols_info;
    @FXML
    private Label number_of_rows_info;
    @FXML
    private Button submit;

    //variables that were set by user
    private int set_width;
    private int set_number_of_cols;
    private int set_number_of_rows;

    private boolean good_data = true;
    public boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
    //initialization of GUI
    @FXML
    private void initialize() {
        width_info.setText("Width:");
        number_of_cols_info.setText("Columns:");
        number_of_rows_info.setText("Rows");
        width.setText("100");
        number_of_cols.setText("2");
        number_of_rows.setText("2");

        //Setting an action for the Submit button that gets input from GUI
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (isStringInt(width.getText()) && isStringInt(number_of_cols.getText()) && isStringInt(number_of_rows.getText())) {
                    good_data = true;

                }
                else{
                    text_multi_thread.clear();
                    text_one_thread.clear();
                    warnings.clear();
                    warnings.appendText("Zle dane wejsciowe. Masz zrobic typ INT");
                    good_data = false;
                    return;
                }
                set_width = Integer.valueOf(width.getText());
                set_number_of_cols = Integer.valueOf(number_of_cols.getText());
                set_number_of_rows = Integer.valueOf(number_of_rows.getText());
                if (set_width <1 || set_number_of_cols <1 || set_number_of_rows<1){
                    warnings.clear();
                    warnings.appendText("Zle dane wejsciowe. Maja byc wieksze od 0");
                    good_data = false;
                }
                else{
                    good_data = true;
                }
            }
        });
    }

    //method that started multithreating computation
    private void startMultiThreading() {

        Group root = new Group();
        ForkJoinPool pool = new ForkJoinPool();
        MyRegularImage MRI = new MyRegularImage(set_width, set_number_of_cols, set_number_of_rows, root );
        pool.invoke(new ForkThread(set_number_of_cols*set_number_of_rows, MRI));

        MRI.compute_all();

        Scene multiThreadScene = new Scene(root, set_number_of_cols*set_width, set_number_of_rows*set_width);
        Stage newWindow = new Stage();
        newWindow.setTitle("Multi Thread");
        newWindow.setScene(multiThreadScene);
        newWindow.setX(400);
        newWindow.setY(0);
        newWindow.show();

    }

    //method that start computation using only one thread
    private void startOneThread() {

        Group root = new Group();
        MyRegularImage MRI = new MyRegularImage(set_width, set_number_of_cols, set_number_of_rows, root );
        for (int i=0; i<set_number_of_cols*set_number_of_rows; ++i){
            int ID = MRI.get_ID();
            MRI.compute_element(ID);
        }

        MRI.compute_all();
        Scene oneThreadScene = new Scene(root, set_number_of_cols*set_width, set_number_of_rows*set_width);
        Stage newWindow = new Stage();
        newWindow.setTitle("One Thread");
        newWindow.setScene(oneThreadScene);
        newWindow.setX(400);
        newWindow.setY(0);
        newWindow.show();


    }

    @FXML
    //method used for searching all Women in .xlsx
    private void start_action() {
        if (good_data) {
            long multi_thread_start_time = System.currentTimeMillis();
            //method that start paining all image from multi threads
            startMultiThreading();
            long multi_thread_stop_time = System.currentTimeMillis();
            //method that start paining all image from one thread
            long one_thread_start_time = System.currentTimeMillis();
            startOneThread();
            long one_thread_stop_time = System.currentTimeMillis();
            text_one_thread.appendText("Jeden watek trwal: " + (one_thread_stop_time - one_thread_start_time) + "\n");
            text_multi_thread.appendText("Wiele watkow trwalo: " + (multi_thread_stop_time - multi_thread_start_time) + "\n");
            System.out.println("Skonczono");
        }
    }

    @FXML
    private void closeScene(){
        Platform.exit();
    }

}



package nels;

import java.util.concurrent.RecursiveAction;

//class that uses RecursiveAction for doing tasks
public class ForkThread extends RecursiveAction {
    private int width;
    private int nb_of_cols;
    private int nb_of_rows;
    private int number_of_forks;
    private MyRegularImage MRI;


    public ForkThread(int number_of_forks, MyRegularImage MRI) {

        this.number_of_forks = number_of_forks;
        this.MRI = MRI;
    }

    @Override
    protected void compute() {

        if (number_of_forks<=1){
            int ID = MRI.get_ID();
            MRI.compute_element(ID);
        }
        else {
            if(number_of_forks%2==0){
                invokeAll(new ForkThread(number_of_forks/2,MRI),
                        new ForkThread( number_of_forks/2,MRI ));
            }else{
                invokeAll(new ForkThread( number_of_forks/2+1,MRI),
                        new ForkThread( number_of_forks/2,MRI));
            }
        }
    }
}

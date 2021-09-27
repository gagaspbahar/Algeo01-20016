package Utility;
import Matrix.*;
import java.io.*;

public class Output {
    private double[] SPLans;
    private double det;
    private Matrix inverse;
    private String intFunction;

    public Output(double n){
        this.det = n;
    }

    public Output(Matrix m){
        this.inverse = m;
    }

    public Output(String s){
        this.intFunction = s;
    }

    public void detToFile(){

    }
}

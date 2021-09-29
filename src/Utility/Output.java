package Utility;
import Matrix.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.text.SimpleDateFormat;
import Main.*;

public class Output {
    private double det;
    private Matrix mat;
    private String out;
    private String function;
    private double interpolateRes;
    private double interpolateGuess;
    private String dir = "test\\result\\";
    private String path = "";
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");

    // public static void main(String[] args){
    //     Output o = new Output(2.0);
    //     o.detToFile();
    // }

    public Output(double n){
        this.det = n;
    }

    public Output(Matrix m){
        this.mat = m;
    }

    public Output(String s){
        this.out = s;
    }
    public Output(String s, double x, double guess){
        this.function = s;
        this.interpolateRes = x;
        this.interpolateGuess = guess;
    }


    public void createFile(){
        try{
            Date date = new Date();
            File file = new File(dir + formatter.format(date) + ".txt");
            if (!file.exists()){
                file.createNewFile();
            }
                // System.out.println("masuk");
            this.path = file.getAbsolutePath();
        }
        catch (IOException e){
            System.out.println("Terjadi error.");
        }
    }

    public void detToFile(){
        try{
            createFile();
            Date date = new Date();
            FileWriter wr = new FileWriter(this.path);
            wr.write("DETERMINANT RESULT " + formatter.format(date) + "\n");
            wr.write(String.valueOf(this.det));
            wr.close();
            System.out.println("Sukses menulis file.");
        }
        catch(IOException e){
            System.out.println("Terjadi error.");
        }
    }

    public void inverseToFile(){
        try{
            createFile();
            Date date = new Date();
            FileWriter wr = new FileWriter(this.path);
            String s = this.matrixToString();
            wr.write("INVERSE RESULT " + formatter.format(date) + "\n");
            wr.write(s);
            wr.close();
            System.out.println("Sukses menulis file.");
        }
        catch(IOException e){
            System.out.println("Terjadi error.");
        }
    }
    
    public void interpolateToFile(){
        try{
            createFile();
            Date date = new Date();
            FileWriter wr = new FileWriter(this.path);
            wr.write("INTERPOLATE RESULT " + formatter.format(date) + "\n");
            wr.write("Interpolated Function: " + this.function + "\n");
            wr.write("Interpolated Result from f(" + this.interpolateRes + "): " + this.interpolateGuess);
            wr.close();
            System.out.println("Sukses menulis file.");
        }
        catch(IOException e){
            System.out.println("Terjadi error.");
        }
    }

    public void SPLtoFile(){
        try{
            createFile();
            Date date = new Date();
            FileWriter wr = new FileWriter(this.path);
            wr.write("LINEAR SYSTEMS OF EQUATION RESULT " + formatter.format(date) + "\n");
            wr.write(this.out);
            wr.close();
            System.out.println("Sukses menulis file.");
        }
        catch(IOException e){
            System.out.println("Terjadi error.");
        }
    }

    public String matrixToString(){
        String s = "";
        for (int i = 0; i < this.mat.getRowLength(); i++){
            for (int j = 0; j < this.mat.getColLength(); j++){
                if(j == this.mat.getColLength()){
                    s += this.mat.getElmt(i,j);
                }
                else{
                    s += this.mat.getElmt(i,j) + " ";
                }
            }
            s += "\n";
        }
        return s;
    }

}

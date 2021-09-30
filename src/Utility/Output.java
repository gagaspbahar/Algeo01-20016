package Utility;
import Matrix.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*; 


public class Output {
    private double det;
    private Matrix mat;
    private String out;
    private String function;
    private double interpolateRes;
    private double interpolateGuess;
    private double regressionRes;
    private double[] regressionGuess; 


    
    private String dir = "test\\result\\";
    private String path = "";
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");

    // public static void main(String[] args){
    //     Output o = new Output(2.0);
    //     o.detToFile();
    // }

    
    
    // double outputs
    public Output(double n){
        this.det = n;
    }
    // Matrix outputs
    public Output(Matrix m){
        this.mat = m;
    }
    // String only outputs
    public Output(String s){
        this.out = s;
    }
    // Interpolate outputs
    public Output(String s, double x, double guess){
        this.function = s;
        this.interpolateRes = x;
        this.interpolateGuess = guess;
    }
    // Regression outputs
    public Output(String s, double x, double[] guess){
        this.function = s;
        this.regressionRes = x;
        this.regressionGuess = guess;
    }

    


    public void createFile(){
        try{
            pathMaker();
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
            JOptionPane.showMessageDialog(null,"Terjadi error. " ,"Error!", JOptionPane.ERROR_MESSAGE); 
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
            JOptionPane.showMessageDialog(null,"Sukses menulis file. " ,"SUKSES", JOptionPane.PLAIN_MESSAGE); 
        }
        catch(IOException e){
            System.out.println("Terjadi error.");
            JOptionPane.showMessageDialog(null,"Terjadi error. " ,"Error!", JOptionPane.ERROR_MESSAGE); 
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
            JOptionPane.showMessageDialog(null,"Sukses menulis file. " ,"SUKSES", JOptionPane.PLAIN_MESSAGE); 
        }
        catch(IOException e){
            System.out.println("Terjadi error.");
            JOptionPane.showMessageDialog(null,"Terjadi error. " ,"Error!", JOptionPane.ERROR_MESSAGE); 
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
            JOptionPane.showMessageDialog(null,"Sukses menulis file. " ,"SUKSES", JOptionPane.PLAIN_MESSAGE); 
        }
        catch(IOException e){
            System.out.println("Terjadi error.");
            JOptionPane.showMessageDialog(null,"Terjadi error. " ,"Error!", JOptionPane.ERROR_MESSAGE); 
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
            JOptionPane.showMessageDialog(null,"Sukses menulis file. " ,"SUKSES", JOptionPane.PLAIN_MESSAGE); 
        }
        catch(IOException e){
            System.out.println("Terjadi error.");
            JOptionPane.showMessageDialog(null,"Terjadi error. " ,"Error!", JOptionPane.ERROR_MESSAGE); 
        }
    }

    public void regressionToFile(){
        try{
            createFile();
            Date date = new Date();
            FileWriter wr = new FileWriter(this.path);
            wr.write("REGRESSION RESULT " + formatter.format(date) + "\n");
            wr.write("REGRESSION FUNCTION: " + this.function + "\n");
            String s = "";
            for(int i = 1; i < this.regressionGuess.length; i++){
                if(i == this.regressionGuess.length-1){
                    s += "X" + i + " = " + this.regressionGuess[i];
                }
                else{
                    s += "X" + i + " = " + this.regressionGuess[i] + "\n";
                }
            }
            wr.write("REGRESSION RESULTS WITH PARAMETERS:\n");
            wr.write(s + "\n");
            wr.write(this.regressionRes + "\n");
            wr.close();
            System.out.println("Sukses menulis file.");
            JOptionPane.showMessageDialog(null,"Sukses menulis file. " ,"SUKSES", JOptionPane.PLAIN_MESSAGE); 
        }
        catch(IOException e){
            System.out.println("Terjadi error.");
            JOptionPane.showMessageDialog(null,"Terjadi error. " ,"Error!", JOptionPane.ERROR_MESSAGE); 
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


    public void pathMaker(){
        String directory = System.getProperty("user.dir");
        directory = directory.substring(directory.lastIndexOf("\\")+1);
        if(directory.equals("bin")){
            dir = "..\\test\\result\\";
        }
        else{
            dir = "test\\result\\";
        }
    }
}

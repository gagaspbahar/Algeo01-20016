package Matrix;
import java.util.Scanner;
import javax.swing.*; 
import Algorithm.Operation;
import java.awt.*;
import java.io.*;

public class MatrixInput {
    
    // Input prompt
    public static Matrix Input(){
        Matrix m = new Matrix();
        // Scanner sc = new Scanner(System.in);
        System.out.println("Pilih cara input matrix:");
        System.out.println("1. Input keyboard");
        System.out.println("2. Input dari file");
        int method = 0;
        while(true){
            try{
                method = Integer.parseInt(JOptionPane.showInputDialog(null,"Pilih cara input matrix :\n1. Input keyboard\n2. Input dari file"));;
                if (method > 2 || method < 1){
                    throw new Exception();
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                System.out.println("Menu tidak valid. Ulangi input.");
                JOptionPane.showMessageDialog(null,"Menu tidak valid. Ulangi input. " ,"Error!", JOptionPane.ERROR_MESSAGE);
                continue;
            }
        }
        if(method == 1){
            m = consoleInput();
        }
        else if(method == 2){
            m = fileInput();
        }
        // sc.close();
        return m;
    }

    // Input from console
    public static Matrix consoleInput(){
        // Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan jumlah baris: ");
        int row = Integer.parseInt(JOptionPane.showInputDialog(null,"Masukkan jumlah baris :"));
        System.out.println("Masukkan jumlah kolom: ");
        int col = Integer.parseInt(JOptionPane.showInputDialog(null,"Masukkan jumlah kolom :"));
        System.out.println("Masukkan matrix: ");
        Matrix m = new Matrix(row, col);
        JOptionPane.showMessageDialog(null,"Masukkan matriks");
        JPanel panel = new JPanel();     
        panel.setLayout(new GridLayout(row,col));

        for (int a=0; a<(row*col); a++)
        {
            panel.add(new JTextField(a));
        }

        if (JOptionPane.showConfirmDialog(null, panel, "Masukkan Matriks", JOptionPane.OK_CANCEL_OPTION)
                                        == JOptionPane.OK_OPTION)
        {
            for(int a=0; a<(row*col); a++){
                for(int b=0; b<row; b++){
                    for(int c=0; c<col; c++){

                        m.setElmt(Double.parseDouble(((JTextField)panel.getComponent(b*col + c)).getText()), b, c);
                    }
                }
            }
        }
        // sc.close();
        return m;
    }

    // Input b (SPL only, from console)
    public static Matrix SPLInput(){
        // Scanner sc = new Scanner(System.in);
        Matrix m = new Matrix();
        System.out.println("Pilih cara input matrix:");
        System.out.println("1. Input keyboard");
        System.out.println("2. Input dari file");
        int method = 0;
        while(true){
            try{
                method = Integer.parseInt(JOptionPane.showInputDialog(null,"Pilih cara input matrix:\n1. Input keyboard\n2. Input dari file"));;
                if (method > 2 || method < 1){
                    throw new Exception();
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                System.out.println("Menu tidak valid. Ulangi input.");
                JOptionPane.showMessageDialog(null,"Menu tidak valid. Ulangi input. " ,"Error!", JOptionPane.ERROR_MESSAGE);
                continue;
            }
        }
        if(method == 1){
            m = consoleInput();
            int row = m.getRowLength();
            Matrix ext = new Matrix(row, 1);
            System.out.println("Masukkan hasil dari persamaan SPL: ");
            JOptionPane.showMessageDialog(null,"Masukkan hasil dari persamaan SPL");
            JPanel panel = new JPanel();     
            panel.setLayout(new GridLayout(row,1));

            for (int a=0; a<(row*1); a++)
            {
                panel.add(new JTextField(a));
            }

            if (JOptionPane.showConfirmDialog(null, panel, "Enter the matrix", JOptionPane.OK_CANCEL_OPTION)
                                            == JOptionPane.OK_OPTION)
            {
                for(int a=0; a<(row*1); a++){
                    for(int b=0; b<row; b++){
                        for(int c=0; c<1; c++){

                            ext.setElmt(Double.parseDouble(((JTextField)panel.getComponent(b + c)).getText()), b, c);
                        }
                    }
                }
            }
            m = Operation.extendMatrix(m, ext);
        }
        else if(method == 2){
            m = fileInput();
        }
        return m;
    }

    // Input Matrix from file
    public static Matrix fileInput(){
        try{
            // Scanner sc = new Scanner(System.in);
            System.out.println("Masukkan nama file berisi matriks:");
            String filename = JOptionPane.showInputDialog(null,"Masukkan nama file berisi matriks :");
            String path = "test\\" + filename;
            File file = new File(path);
            // File file = new File("test\\mat.txt");
            // System.out.println(file.getAbsolutePath());
            Scanner scfile = new Scanner(file);
            int col = 0;
            int row = 0;
            while (scfile.hasNextLine()){
                col = (scfile.nextLine().trim().split(" ")).length;
                row++;
            }
            scfile.close();
            Matrix m = new Matrix(row, col);
            scfile = new Scanner(file);
            for (int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    double x = scfile.nextDouble();
                    m.setElmt(x, i, j);
                }
            }
            scfile.close();
            // sc.close();
            return m;
        }
        catch(FileNotFoundException e){
            System.out.println("File tidak ditemukan. Mengembalikan matriks 1x1 berisi elemen 1.");
            JOptionPane.showMessageDialog(null,"File tidak ditemukan. Mengembalikan matriks 1x1 berisi elemen 1. " ,"Error!", JOptionPane.ERROR_MESSAGE);
            Matrix m = new Matrix(1,1);
            m.setElmt(1, 0, 0);
            return m;
        }        
    }
}
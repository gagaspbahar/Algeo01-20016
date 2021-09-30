package Matrix;
import java.util.Scanner;
import javax.swing.*; 
import Algorithm.Operation;
import java.awt.*;
import java.io.*;
import Utility.*;
import java.util.Objects;

public class MatrixInput {
    
    // Input prompt
    public static Matrix Input(){
        Matrix m = new Matrix();
        // Scanner sc = new Scanner(System.in);
        System.out.println("Pilih cara input matrix:");
        System.out.println("1. Input keyboard");
        System.out.println("2. Input dari file");

        int method = 0;
        String temps;
        while(true){
            try{
                temps = JOptionPane.showInputDialog(null,"Pilih cara input matrix :\n1. Input keyboard\n2. Input dari file");
                if (Objects.isNull(temps)){
                    throw new Exception();
                }
                else{
                    method = Integer.parseInt(temps);
                    if (method > 2 || method < 1){
                        throw new NumberFormatException();
                    }
                    break;
                }
            }
            catch(NumberFormatException e){
                System.out.println("Menu tidak valid. Ulangi input.");
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Menu tidak valid, Ulangi input. " ,"Error!", JOptionPane.ERROR_MESSAGE);   
                continue;
            }
            catch(Exception e){
                UI.exit();
            }
        }

        // while(true){
        //     try{
        //         method = Integer.parseInt(JOptionPane.showInputDialog(null,"Pilih cara input matrix :\n1. Input keyboard\n2. Input dari file"));
        //         if (method > 2 || method < 1){
        //             throw new Exception();
        //         }
        //         else{
        //             break;
        //         }
        //     }
        //     catch(Exception e){
        //         System.out.println("Menu tidak valid. Ulangi input.");
        //         JOptionPane.showMessageDialog(null,"Menu tidak valid. Ulangi input. " ,"Error!", JOptionPane.ERROR_MESSAGE);

        //         continue;
        //     }
        // }
        if(method == 1){
            m = consoleInput();
        }
        else if(method == 2){
            m = fileInput();
        }
        // sc.close();
        return m;
    }

    public static Matrix interpolateInput(){
        int n = Integer.parseInt(JOptionPane.showInputDialog(null,"Masukkan n :"));
        Matrix M;
        M = new Matrix(n,2);
        System.out.println("BABAI");;
        int count = 0;
        for (int i = 0; i<n; i++){
                JPanel panel = new JPanel();
                System.out.println("BABAI");;
                panel.setLayout(new GridLayout(1,2));
                System.out.println("BABAI");;
                for (int a=0; a<2; a++)
                {
                    panel.add(new JTextField(3));
                }
                if (JOptionPane.showConfirmDialog(null, panel, "Masukkan X" + count +" Y" + count,JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION); 
               {
                    M.setElmt(Double.parseDouble(((JTextField)panel.getComponent(0)).getText()), i, 0);
                    M.setElmt(Double.parseDouble(((JTextField)panel.getComponent(1)).getText()), i, 1);
               }
               count++;

        }

        return M;
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
            panel.add(new JTextField(3));
        }

        if (JOptionPane.showConfirmDialog(null, panel, "Masukkan Matriks", JOptionPane.OK_CANCEL_OPTION)
                                        == JOptionPane.OK_OPTION)
        {
            for(int a=0; a<(row*col); a++){
                for(int b=0; b<row; b++){
                    for(int c=0; c<col; c++){
                        try{
                            Component tempc = panel.getComponent(b*col + c);
                            
                            if(Objects.isNull(tempc)){
                                throw new NullPointerException();
                            }
                            else{
                                m.setElmt(Double.parseDouble(((JTextField)tempc).getText()), b, c);
                                break;
                            }
                        }
                        catch(NumberFormatException e){
                            System.out.println("Menu tidak valid. Ulangi input.");
                            System.out.println(e);
                            JOptionPane.showMessageDialog(null,"Menu tidak valid, Ulangi input. " ,"Error!", JOptionPane.ERROR_MESSAGE);   
                            continue;
                        }
                        catch(NullPointerException e){
                            UI.exit();
                        }
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
        String temps;
        while(true){
            try{
                temps = JOptionPane.showInputDialog(null,"Pilih cara input matrix :\n1. Input keyboard\n2. Input dari file");
                if (Objects.isNull(temps)){
                    throw new Exception();
                }
                else{
                    method = Integer.parseInt(temps);
                    if (method > 2 || method < 1){
                        throw new NumberFormatException();
                    }
                    break;
                }
            }
            catch(NumberFormatException e){
                System.out.println("Menu tidak valid. Ulangi input.");
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Menu tidak valid, Ulangi input. " ,"Error!", JOptionPane.ERROR_MESSAGE);   
                continue;
            }
            catch(Exception e){
                UI.exit();
            }
        }


        // while(true){
        //     try{

        //         method = Integer.parseInt(JOptionPane.showInputDialog(null,"Pilih cara input matrix:\n1. Input keyboard\n2. Input dari file"));;

        //         if (method > 2 || method < 1){
        //             throw new Exception();
        //         }
        //         else{
        //             break;
        //         }
        //     }

        //     catch(Exception e){
        //         System.out.println("Menu tidak valid. Ulangi input.");
        //         JOptionPane.showMessageDialog(null,"Menu tidak valid. Ulangi input. " ,"Error!", JOptionPane.ERROR_MESSAGE);

        //         continue;
        //     }
        // }


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
                panel.add(new JTextField(1));
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
            if(Objects.isNull(filename)){
                throw new Exception();
            }
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
            System.out.println(e);
            Matrix m = new Matrix(1,1);
            m.setElmt(1, 0, 0);
            return m;
        }
        catch(Exception e){
            System.out.println(e);
            UI.exit();
            Matrix m = new Matrix(1,1);
            m.setElmt(1, 0, 0);
            return m;
        }      
    }
}
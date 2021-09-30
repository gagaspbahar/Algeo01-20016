package Matrix;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class MatrixInput {
    
    // Input prompt
    public static Matrix Input(){
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
                exit();
            }
        }

        if(method == 1){
            m = consoleInput();
        }
        else if(method == 2){
            m = fileInput();
        }

        return m;
    }

    public static Matrix interpolateInput(){
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
                exit();
            }
        }

        if(method == 1){
            m = interpolateInt();
        }
        else if(method == 2){
            m = fileInput();
        }
        

        return m;
    }

    public static Matrix interpolateInt(){
        int n = Integer.parseInt(JOptionPane.showInputDialog(null,"Masukkan n :"));
        Matrix M;
        M = new Matrix(n,2);
    
        int count = 0;
        for (int i = 0; i<n; i++){
                JPanel panel = new JPanel();
               
                panel.setLayout(new GridLayout(1,2));
              
                for (int a=0; a<2; a++)
                {
                    panel.add(new JTextField(3));
                }
                if (JOptionPane.showConfirmDialog(null, panel, "Masukkan X" + count +" Y" + count,JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) 
               {
                    M.setElmt(Double.parseDouble(((JTextField)panel.getComponent(0)).getText()), i, 0);
                    M.setElmt(Double.parseDouble(((JTextField)panel.getComponent(1)).getText()), i, 1);
               } else {
                   exit();
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
                        m.setElmt(Double.parseDouble(((JTextField)panel.getComponent(b*col + c)).getText()), b, c);
                    }
                }
            }
        }else{

            exit();
        }
        return m;
    }

    // Input b (SPL only, from console)
    public static Matrix SPLInput(){
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
                exit();
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
            }else {
                exit();
            }
            m = extendMatrix(m, ext);
        }
        else if(method == 2){
            m = fileInput();
        }
        return m;
    }

    // Input Matrix from file
    public static Matrix fileInput(){
        try{
            String path;
            System.out.println("Masukkan nama file berisi matriks:");
            String filename = JOptionPane.showInputDialog(null,"Masukkan nama file berisi matriks :");
            if(Objects.isNull(filename)){
                throw new Exception();
            }
            String directory = System.getProperty("user.dir");
            directory = directory.substring(directory.lastIndexOf("\\")+1);
            if(directory.equals("bin")){
                path = "..\\test\\" + filename;
            }
            else{
                path = "test\\" + filename;
            }
            System.out.println(path);
            File file = new File(path);
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
            exit();
            Matrix m = new Matrix(1,1);
            m.setElmt(1, 0, 0);
            return m;
        }      
    }

    public static void exit(){
        System.exit(0);
    }

    public static Matrix extendMatrix(Matrix M1 , Matrix M2){
        Matrix extend;
        int RowsM1, ColsM1, ColsM2, i , j;
        RowsM1 = M1.getRowLength();
        ColsM1 = M1.getColLength();
        ColsM2 = M2.getColLength();

        extend = new Matrix(RowsM1,ColsM1+ColsM2);
        for (i=0; i<RowsM1; i++) {
            for (j=0; j<(ColsM1 +ColsM2); j++) {
                if (j < ColsM1) {
                
                    extend.setElmt(M1.getElmt(i, j), i, j);
                } else {
                    
                    extend.setElmt(M2.getElmt(i, j-ColsM1), i, j);
                }
            }
        }
        return extend;

    }
}
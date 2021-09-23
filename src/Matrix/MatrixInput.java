package Matrix;
import java.util.Scanner;

import java.io.*;

public class MatrixInput {
    public static Matrix consoleInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan jumlah baris: ");
        int row = sc.nextInt();
        System.out.println("Masukkan jumlah kolom: ");
        int col = sc.nextInt();
        System.out.println("Masukkan matrix: ");
        Matrix m = new Matrix(row, col);
        for(int i = 0; i<m.getRowLength(); i++){
            for(int j = 0; j<m.getColLength(); j++){
                double x = sc.nextDouble();
                m.setElmt(x, i, j);
            }
        }
        sc.close();
        return m;
    }
    public static Matrix fileInput(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Masukkan nama file berisi matriks:");
            String filename = sc.next();
            sc.close();
            String path = "test\\" + filename;
            File file = new File(path);
            // File file = new File("test\\mat.txt");
            // System.out.println(file.getAbsolutePath());
            sc = new Scanner(file);
            int col = 0;
            int row = 0;
            while (sc.hasNextLine()){
                col = (sc.nextLine().trim().split(" ")).length;
                row++;
            }
            sc.close();
            Matrix m = new Matrix(row, col);
            sc = new Scanner(file);
            for (int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    double x = sc.nextDouble();
                    m.setElmt(x, i, j);
                }
            }
            sc.close();
            return m;
        }
        catch(FileNotFoundException e){
            System.out.println("File tidak ditemukan. Mengembalikan matriks kosong.");
            Matrix m = new Matrix();
            return m;
        }        
    }
    public static Matrix Input(){
        Matrix m = new Matrix();
        Scanner sc = new Scanner(System.in);
        System.out.println("Pilih cara input matrix:");
        System.out.println("1. Input keyboard");
        System.out.println("2. Input dari file");
        int method = sc.nextInt();
        if(method == 1){
            m = consoleInput();
        }
        else if(method == 2){
            m = fileInput();
        }
        sc.close();
        return m;
    }
}

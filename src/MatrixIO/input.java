package MatrixIO;
import Matrix.*;
import java.util.Scanner;
import java.io.*;

public class input {
    public static Matrix consoleInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan jumlah baris: ");
        int row = sc.nextInt();
        System.out.println("Masukkan jumlah kolom: ");
        int col = sc.nextInt();
        Matrix m = new Matrix(row, col);
        for(int i = 0; i<row; i++){
            for(int j = 0; i <col; j++){
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
            System.out.println("Masukkan nama file berisi matriks: (tanpa .txt)");
            String filename = sc.nextLine();
            sc.close();
            String path = "..\\test\\" + filename + ".txt";
            File file = new File(path);
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
}

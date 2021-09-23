import java.util.Scanner;
import java.io.*;
import Matrix.*;
import MatrixIO.*;

public class Main {
    public static Scanner sc;
    public Matrix m;
    public static void main(String[] args){
        menu();
    }

    public static void menu(){
        System.out.println("Selamat datang di Matrix Calculator Mang DODZ. Silahkan pilih menu:\n");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi polinom");
        System.out.println("5. Regresi linier berganda");
        System.out.println("6. Keluar");
        Scanner sc = new Scanner(System.in);
        int menu = sc.nextInt();

        switch(menu){
            case 1:
            System.out.println("1. Metode eliminasi Gauss");
            System.out.println("2. Metode eliminasi Gauss-Jordan");
            System.out.println("3. Metode matriks balikan");
            System.out.println("4. Kaidah Cramer");
            int method = sc.nextInt();
            case 2:
            System.out.println("1. Metode reduksi baris (Gauss)");
            System.out.println("2. Ekspansi kofaktor");
            case 3:
            System.out.println("1. Metode eliminasi Gauss");
            System.out.println("2. Metode matriks adjoin");
            case 4:
            case 5:
            case 6:
            System.exit(0);
        }
        sc.close();
    }

    public void inputMethod(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Pilih cara input matrix: \n");
        System.out.println("1. Input keyboard");
        System.out.println("2. Input dari file");
        int method = sc.nextInt();
        if(method == 1){
            m = MatrixIO.input.consoleInput();
        }
        else if(method == 2){
            m = MatrixIO.input.fileInput();
        }
        sc.close();
    }
    // static double[][] readMatrix(){

    // }
}

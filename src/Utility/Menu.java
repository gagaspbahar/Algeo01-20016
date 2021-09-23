package Utility;

import java.util.Scanner;
import Matrix.*;

public class Menu {
    public static Scanner sc = new Scanner(System.in);

    public static void mainMenu(){
        System.out.println("Selamat datang di Matrix Calculator Mang DODZ. Silahkan pilih menu:\n");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi polinom");
        System.out.println("5. Regresi linier berganda");
        System.out.println("6. Keluar");
        System.out.println();
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                SPLMenu();
            case 2:
                determinantMenu();
            case 3:
                inverseMenu();
            case 4:
                interpolateMenu();
            case 5:
                regressionMenu();
            case 6:
                exit();
        }
        sc.close();
    }

    public static void SPLMenu(){
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println();
    }

    public static void determinantMenu(){
        System.out.println("1. Metode reduksi baris (Gauss)");
        System.out.println("2. Ekspansi kofaktor");
        System.out.println();
        int choice = sc.nextInt();
        Matrix m;
        double det;
        m = MatrixInput.Input();
        switch (choice){
            case 1:
                det = m.determinantByOBE();
                System.out.println("Determinan dari matriks adalah " + det);     
                prompt(); 
            case 2:
                det = m.determinantCofactor();
                System.out.println("Determinan dari matriks adalah " + det);     
                prompt();
        }
    }

    public static void inverseMenu(){
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode matriks adjoin");
        System.out.println();
    }

    public static void interpolateMenu(){

    }

    public static void regressionMenu(){

    }

    // BENERIN PROMPTNYA !!! MSH NoSuchElementException
    public static void prompt(){
        System.out.println("Ingin menggunakan kalkulator lagi?");
        String flag = sc.nextLine();
        if(flag == "y"){
            mainMenu();
        }
        else{
            exit();
            
        }
    }
    public static void exit(){
        System.exit(0);
    }
}

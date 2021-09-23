package Utility;

import java.io.*;
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
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                System.out.println("1. Metode eliminasi Gauss");
                System.out.println("2. Metode eliminasi Gauss-Jordan");
                System.out.println("3. Metode matriks balikan");
                System.out.println("4. Kaidah Cramer");
            case 2:
                determinantMenu();
            
            case 3:
                System.out.println("1. Metode eliminasi Gauss");
                System.out.println("2. Metode matriks adjoin");
            case 4:
            case 5:
            case 6:
                exit();
        }
        sc.close();
    }
    public static void determinantMenu(){
        
        System.out.println("1. Metode reduksi baris (Gauss)");
        System.out.println("2. Ekspansi kofaktor");
        int choice = sc.nextInt();
        Matrix m;
        double det;
        switch (choice){
            case 1:
                m = MatrixInput.Input();
                det = m.determinantByOBE();
                System.out.println("Determinan dari matriks adalah " + det);     
                prompt(); 
            case 2:
                
        }
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

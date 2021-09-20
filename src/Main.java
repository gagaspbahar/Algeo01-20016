import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        menu();
    }

    static void menu(){
        System.out.println("Selamat datang di Matrix Calculator Mang DODZ. Silahkan pilih menu:\n");
        System.out.println("1. Sistem Persamaan Linier\n2. Determinan\n3. Matriks balikan\n4. Interpolasi polinom\n5. Regresi linier berganda\n6. Keluar");
        Scanner sc = new Scanner(System.in);
        int menu = sc.nextInt();

        switch(menu){
            case 1:
            System.out.println("1. Metode eliminasi Gauss\n2. Metode eliminasi Gauss-Jordan\n3. Metode matriks balikan\n4. Kaidah Cramer");
            int method = sc.nextInt();
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            System.exit(0);
        }
    }
    // static double[][] readMatrix(){

    // }
}

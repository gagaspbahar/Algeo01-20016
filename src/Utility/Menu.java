package Utility;

import Algorithm.*;
import Matrix.*;
import Main.*;
public class Menu {
    // public static Scanner sc = new Scanner(System.in);

    // Main menu
    public static void mainMenu(){
        System.out.println("Selamat datang di Matrix Calculator Mang DODZ. Silahkan pilih menu:\n");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi polinom");
        System.out.println("5. Regresi linier berganda");
        System.out.println("6. Keluar");
        System.out.println();
        int choice = Main.sc.nextInt();

        switch(choice){
            case 1:
                SPLMenu();
                break;
            case 2:
                determinantMenu();
                break;
            case 3:
                inverseMenu();
                break;
            case 4:
                interpolateMenu();
                break;
            case 5:
                regressionMenu();
                break;
            case 6:
                exit();
        }
    //    Main.sc.close();
    }

    // SPL Menu
    public static void SPLMenu(){
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println();
        int choice = Main.sc.nextInt();
        Matrix m;
        double[] ans = {0};
        Output o;
        String out = "";
        m = MatrixInput.SPLInput();
        SPL spl = new SPL(m);
        ans = spl.solve(choice);
        out = spl.consoleOut();
        
        // switch (choice){
        //     case 1:
        //         ans = Gauss.gaussEquation(m);
        //         break;
        //     case 2:
        //         ans = GaussJordan.gaussJordanEquation(m);
        //         break;
        //     case 3:
        //         break;
        //     case 4:
        //         ans = Cramer.cramerAlgo(m);
        //         break;
               
        // }
        // for(int i = 0; i < ans.length; i++){
        //     if (ans.length-1 == i){
        //         out += "X" + (i+1) + " = " + ans[i];
        //     }
        //     else{
        //         out += "X" + (i+1) + " = " + ans[i] + "\n";
        //     }
        // }
        System.out.print(out);
        System.out.println();
        o = new Output(out);
        o.SPLtoFile();
        prompt();
    }

    // Determinant Menu
    public static void determinantMenu(){
        System.out.println("1. Metode reduksi baris (Gauss)");
        System.out.println("2. Ekspansi kofaktor");
        System.out.println();
        int choice = Main.sc.nextInt();
        Matrix m;
        double det = 0;
        m = MatrixInput.Input();
        switch (choice){
            case 1:
                det = m.determinantByOBE();
                System.out.println("Determinan dari matriks adalah " + det);     
                break; 
            case 2:
                det = m.determinantCofactor();
                System.out.println("Determinan dari matriks adalah " + det);     
                break;
        }
        Output o = new Output(det);
        o.detToFile();
        prompt();
    }

    // Inverse Menu
    public static void inverseMenu(){
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode matriks adjoin");
        System.out.println();
        int choice = Main.sc.nextInt();
        Matrix m;
        Matrix ans = new Matrix();
        m = MatrixInput.Input();
        switch (choice){
            case 1:
                ans = Invers.inversOBE(m);
                break;
            case 2:
                ans = Invers.inversCofactor(m);
                break;
        }
        System.out.println("Matriks balikan dari matriks input adalah: ");
        ans.displayMatrix();
        Output o = new Output(ans);
        o.inverseToFile();
        prompt();
    }

    // Interpolate Menu
    public static void interpolateMenu(){
        Matrix m;
        double[] ans = {0};
        m = MatrixInput.Input();
        ans = Interpolate.interpolateAlg(m);
        System.out.println("Hasil dari interpolasi adalah: ");
        String out = "";
        int len = m.getRowLength();
        out += ans[0] + " +";
        for (int i = 1; i < len; i++){
            if (i==1){
                out += " " + ans[1] + "x +";
            }
            else if(i == len-1){
                out += " " + ans[i] + "x^" + i; 
            }
            else{
                out += " " + ans[i] + "x^" + i + " +";
            }
        }
        System.out.println(out);
        System.out.println("Masukkan input nilai fungsi yang ingin ditaksir: ");
        double x = Main.sc.nextDouble();
        double guess = Interpolate.functionInterpolate(ans, x);
        System.out.println("Hasil nilai taksiran f(" + x + ") = " + guess);
        Output o = new Output(out, x, guess);
        o.interpolateToFile();
        prompt();
    }

    // Regression menu
    public static void regressionMenu(){
        Matrix m;
        double[] ans = {0};
        m = MatrixInput.Input();
        ans = Regression.regressionAlgo(m);
        System.out.println("Hasil dari regresi berganda adalah: ");
        String out = "";
        int len = m.getColLength();
        out += ans[0] + " +";
        for (int i = 1; i < len; i++){
            if(i == len-1){
                out += " " + ans[i] + "*x" + i; 
            }
            else{
                out += " " + ans[i] + "*x" + i + " +";
            }
        }
        System.out.println(out);
        prompt();
    }

    public static void prompt(){
        System.out.println("Ingin menggunakan kalkulator lagi? (y/n)");
        Main.sc.nextLine();
        String flag = Main.sc.nextLine().toLowerCase();
        if(flag.equals("y")){
            mainMenu();
        }
        else{
            exit();
            
        }
    }
    // Exit function with return code 0
    public static void exit(){
        System.exit(0);
    }
}

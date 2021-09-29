package Utility;
import javax.swing.*; 
import Algorithm.*;
import Matrix.*;
import java.awt.*;

public class UI {

    public static void mainMenu(){
        int opt = Integer.parseInt(JOptionPane.showInputDialog(null, "Selamat datang di Matrix Calculator Mang DODZ. Silahkan pilih menu :\n1. Sistem Persamaan Linier\n2. Determinan\n3. Matriks balikan\n4. Interpolasi Polinom\n5. Regresi linier berganda\n 6. Keluar","Main Menu", JOptionPane.PLAIN_MESSAGE));

        switch (opt){
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
    }

    public static void SPLMenu(){
        int choice = Integer.parseInt(JOptionPane.showInputDialog(null,"1. Metode eliminasi Gauss\n2. Metode eliminasi Gauss-Jordan\n3. Metode matriks balikan\n4. Kaidah cramer","SPL subMenu", JOptionPane.INFORMATION_MESSAGE));
        Matrix m;
        Output o;
        String[] ans = {""};
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
        //         ans = inversSPL(m);
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
        JOptionPane.showMessageDialog(null,out);
        System.out.println();
        o = new Output(out);
        o.SPLtoFile();
        prompt();
    }

    // Determinant Menu
    public static void determinantMenu(){
        int choice = Integer.parseInt(JOptionPane.showInputDialog(null,"1. Metode reduksi baris (Gauss)\n2. Ekspansi kofaktor","Determinant subMenu", JOptionPane.INFORMATION_MESSAGE));
        Matrix m;
        double det = 0;
        m = MatrixInput.Input();
        switch (choice){
            case 1:
                det = m.determinantByOBE();
                System.out.println("Determinan dari matriks adalah " + det);     
                JOptionPane.showMessageDialog(null,"Determinan dari matriks adalah " + det ,"Hasil", JOptionPane.INFORMATION_MESSAGE);
                break; 
            case 2:
                det = m.determinantCofactor();
                System.out.println("Determinan dari matriks adalah " + det);   
                JOptionPane.showMessageDialog(null,"Determinan dari matriks adalah " + det ,"Hasil", JOptionPane.INFORMATION_MESSAGE);   
                break;
        }
        Output o = new Output(det);
        o.detToFile();
        prompt();
    }

    // Inverse Menu
    public static void inverseMenu(){
        int choice = Integer.parseInt(JOptionPane.showInputDialog(null,"1. Metode eliminasi Gauss\n2. Metode matriks adjoin","Inverse subMenu", JOptionPane.INFORMATION_MESSAGE));
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
        int row = ans.getRowLength();
        int col = ans.getColLength();
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(row, col, 50, 10));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                JLabel label = new JLabel(Double.toString(ans.getElmt(i, j)));
                pane.add(label);
            }
        }
        JOptionPane.showMessageDialog(new JFrame(), pane);
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
        JOptionPane.showMessageDialog(null,"Hasil dari interpolasi adalah " + out);
        System.out.println("Masukkan input nilai fungsi yang ingin ditaksir: ");
        double x = Integer.parseInt(JOptionPane.showInputDialog(null,"Masukkan input nilai fungsi yang ingin ditaksir :","Nilai Fungsi", JOptionPane.INFORMATION_MESSAGE));
        double guess = Interpolate.functionInterpolate(ans, x);
        System.out.println("Hasil nilai taksiran f(" + x + ") = " + guess);
        JOptionPane.showMessageDialog(null,"Hasil nilai taksiran f(" + x + ") = " + guess);
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
        JOptionPane.showMessageDialog(null,"Hasil dari regresi berganda adalah: " + out);
        prompt();
    }

    public static void prompt(){
        System.out.println("Ingin menggunakan kalkulator lagi? (y/n)");

        String flag = JOptionPane.showInputDialog(null,"Ingin menggunakan kalkulator lagi? (y/n)", "prompt", JOptionPane.INFORMATION_MESSAGE);
        if(flag.equals("y") || flag.equals("Y")){
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


    
    



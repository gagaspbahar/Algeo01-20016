package Utility;
import javax.swing.*; 
import Algorithm.*;
import Matrix.*;
import java.awt.*;
import java.lang.Math;
import java.util.Objects;

public class UI {

    public static void mainMenu(){
        String temps;
        int opt = 0;
        
        while(true){
            try{
                temps = JOptionPane.showInputDialog(null, "Selamat datang di Matrix Calculator Mang DODZ. Silahkan pilih menu :\n1. Sistem Persamaan Linier\n2. Determinan\n3. Matriks balikan\n4. Interpolasi Polinom\n5. Regresi linier berganda\n 6. Keluar","Main Menu", JOptionPane.PLAIN_MESSAGE);
                if (Objects.isNull(temps)){
                    throw new Exception();
                }
                else{
                    opt = Integer.parseInt(temps);
                    break;
                }
            }

            catch(NumberFormatException e){
                System.out.println("Menu tidak valid. Ulangi input.");
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Menu tidak valid, Ulangi input. " ,"Error!", JOptionPane.ERROR_MESSAGE);   
                mainMenu();
                continue;
            }
            catch(Exception e){
                exit();
            }
        }
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
        default :
            JOptionPane.showMessageDialog(null,"Menu tidak valid, Mengembalikan ke menu awal. " ,"Error!", JOptionPane.ERROR_MESSAGE);
            mainMenu();
    }
    }

    public static void SPLMenu(){
        int choice = 0;
        String temps;
        while(true){
            try{
                temps = JOptionPane.showInputDialog(null,"1. Metode eliminasi Gauss\n2. Metode eliminasi Gauss-Jordan\n3. Metode matriks balikan\n4. Kaidah cramer","SPL subMenu", JOptionPane.INFORMATION_MESSAGE);
                if (Objects.isNull(temps)){
                    throw new Exception();
                }
                else{
                    choice = Integer.parseInt(temps);
                    if (choice > 4 || choice < 1){
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


            // try{
            //     choice = Integer.parseInt(JOptionPane.showInputDialog(null,"1. Metode eliminasi Gauss\n2. Metode eliminasi Gauss-Jordan\n3. Metode matriks balikan\n4. Kaidah cramer","SPL subMenu", JOptionPane.INFORMATION_MESSAGE));
            //     if(choice == 0){
            //         exit();
            //     }
            //     else if (choice > 4 || choice < 1){
            //         throw new Exception();
            //     }
            //     else{
            //         break;
            //     }
            // }
           
            // catch(Exception e){
            //     JOptionPane.showMessageDialog(null,"Menu tidak valid, Ulangi input. " ,"Error!", JOptionPane.ERROR_MESSAGE);
            //     continue;
            // }
        Matrix m = new Matrix();
        Output o;
        String[] ans = {""};
        String out = "";

        try{
            m = MatrixInput.SPLInput();
        }
        catch(Exception e){
            System.out.println("Terjadi eror. Input mungkin tidak valid. Mengulangi menu."); 
            JOptionPane.showMessageDialog(null,"Terjadi error. Input mungkin tidak valid. Mengulangi menu. " ,"Error!", JOptionPane.ERROR_MESSAGE);
            m = MatrixInput.SPLInput();
        }
        SPL spl = new SPL(m);
        ans = spl.solve(choice);
        out = spl.consoleOut();
        System.out.print(out);
        JOptionPane.showMessageDialog(null,out,"Hasil SPL adalah", JOptionPane.INFORMATION_MESSAGE);
        System.out.println();
        o = new Output(out);
        o.SPLtoFile();
        prompt();
    }

    // Determinant Menu
    public static void determinantMenu(){
        int choice = 0;
        String temps;
        while(true){
            try{
                temps = JOptionPane.showInputDialog(null,"1. Metode reduksi baris (Gauss)\n2. Ekspansi kofaktor","Determinant subMenu", JOptionPane.INFORMATION_MESSAGE);
                if (Objects.isNull(temps)){
                    throw new Exception();
                }
                else{
                    choice = Integer.parseInt(temps);
                    if (choice > 2 || choice < 1){
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


        // while(true){
        //     try{
        //         choice = Integer.parseInt(JOptionPane.showInputDialog(null,"1. Metode reduksi baris (Gauss)\n2. Ekspansi kofaktor","Determinant subMenu", JOptionPane.INFORMATION_MESSAGE));
        //         if (choice > 2 || choice < 1){
        //             throw new Exception();
        //         }
        //         else{
        //             break;
        //         }
        //     }
        //     catch(Exception e){
        //         System.out.println("Menu tidak valid. Ulangi input.");
        //         JOptionPane.showMessageDialog(null,"Terjadi error. Input mungkin tidak valid. Mengulangi menu. " ,"Error!", JOptionPane.ERROR_MESSAGE);
        //         continue;
        //     }
        // }


        Matrix m = new Matrix();
        double det = 0;
        try{
            m = MatrixInput.Input();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Terjadi error. Input mungkin tidak valid. Mengulangi menu. " ,"Error!", JOptionPane.ERROR_MESSAGE);
            determinantMenu();
        }
        if (!m.isSquare()){
            choice = -1;
        }
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
            case -1:
                System.out.println("Matriks input bukan matriks persegi. Mengembalikan ke menu utama. \n");
                JOptionPane.showMessageDialog(null,"Matriks input bukan matriks persegi. Mengembalikan ke menu utama. " ,"Error!", JOptionPane.ERROR_MESSAGE);
                prompt();
                return;
            default:
                System.out.println("Menu tidak valid. Mengembalikan ke menu awal.");
                JOptionPane.showMessageDialog(null,"Menu tidak valid. Mengembalikan ke menu awal. " ,"Error!", JOptionPane.ERROR_MESSAGE);
                determinantMenu();
        }
        Output o = new Output(det);
        o.detToFile();
        prompt();

    }

    // Inverse Menu
    public static void inverseMenu(){
        int choice = 0;
        String temps;
        while(true){
            try{
                temps = JOptionPane.showInputDialog(null,"1. Metode eliminasi Gauss\n2. Metode matriks adjoin","Inverse subMenu", JOptionPane.INFORMATION_MESSAGE);
                if (Objects.isNull(temps)){
                    throw new Exception();
                }
                else{
                    choice = Integer.parseInt(temps);
                    if (choice > 2 || choice < 1){
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
        
        
        
        // while(true){
        //     try{
        //         choice = Integer.parseInt(JOptionPane.showInputDialog(null,"1. Metode eliminasi Gauss\n2. Metode matriks adjoin","Inverse subMenu", JOptionPane.INFORMATION_MESSAGE));
        //         if (choice > 2 || choice < 1){
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


        Matrix m = new Matrix();
        Matrix ans = new Matrix();
        try{
            m = MatrixInput.Input();
        }
        catch(Exception e){
            System.out.println("Terjadi eror. Input mungkin tidak valid. Mengulangi menu.");
            JOptionPane.showMessageDialog(null,"Terjadi error. Input mungkin tidak valid. Mengulangi menu. " ,"Error!", JOptionPane.ERROR_MESSAGE);
            inverseMenu();
        }
        if(!m.isSquare()){
            choice = 3;
        }
        switch (choice){
            case 1:
                ans = Invers.inversOBE(m);
                break;
            case 2:
                ans = Invers.inversCofactor(m);
                break;
            case 3:
                System.out.println("Matriks input bukan matriks persegi. Mengembalikan ke menu utama. \n");
                JOptionPane.showMessageDialog(null,"Matriks input bukan matriks persegi. Mengembalikan ke menu utama. " ,"Error!", JOptionPane.ERROR_MESSAGE);
                prompt();
                return;
            default:
                System.out.println("Menu tidak valid. Mengembalikan ke menu awal.");
                JOptionPane.showMessageDialog(null,"Menu tidak valid. Mengembalikan ke menu awal. " ,"Error!", JOptionPane.ERROR_MESSAGE);
                inverseMenu();
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
        JOptionPane.showMessageDialog(new JFrame(), pane,"Matriks Balikan dari matriks input adalah ", JOptionPane.INFORMATION_MESSAGE);
        Output o = new Output(ans);
        o.inverseToFile();
        prompt();
    }
    

    // Interpolate Menu
    public static void interpolateMenu(){
        Matrix m = new Matrix();
        double[] ans = {0};
        try{
            m = MatrixInput.Input();
        }
        catch(Exception e){
            System.out.println("Terjadi eror. Input mungkin tidak valid. Mengulangi menu.");
            JOptionPane.showMessageDialog(null,"Terjadi error. Input mungkin tidak valid. Mengulangi menu. " ,"Error!", JOptionPane.ERROR_MESSAGE);
            interpolateMenu();
        }
        ans = Interpolate.interpolateAlg(m);
        System.out.println("Hasil dari interpolasi adalah: ");
        String out = "";
        String plus = " + ";
        String minus = " - ";
        boolean isNegative = false;
        int len = m.getRowLength();
        out += ans[0];
        for (int i = 1; i < len; i++){
            isNegative = false;
            if (ans[i] < 0){
                out += minus;
                ans[i] *= -1;
                isNegative = true;
            }
            else{
                out += plus;
            }
            if (i==1){
                out += ans[1] + "x";
            }
            else if(i == len-1){
                out += ans[i] + "x^" + i; 
            }
            else{
                out += ans[i] + "x^" + i;
            }
            if(isNegative){
                ans[i] *= -1;
            }
        }
        System.out.println(out);
        JOptionPane.showMessageDialog(null,"Hasil dari interpolasi adalah " + out);
        System.out.println("Masukkan input nilai fungsi yang ingin ditaksir: ");
        double x = 0;
        while(true){
            try{
                x = Double.parseDouble(JOptionPane.showInputDialog(null,"Masukkan input nilai fungsi yang ingin ditaksir :","Nilai Fungsi", JOptionPane.INFORMATION_MESSAGE));
                break;
            }
            catch(NullPointerException e){
                UI.exit();
            }
            catch(Exception e){
                System.out.println("Input tidak valid.");
                JOptionPane.showMessageDialog(null,"Input tidak valid. " ,"Error!", JOptionPane.ERROR_MESSAGE);
                continue;
            }
        }
        double guess = Interpolate.functionInterpolate(ans, x);
        System.out.println("Hasil nilai taksiran f(" + x + ") = " + guess);
        JOptionPane.showMessageDialog(null,"Hasil nilai taksiran f(" + x + ") = " + guess);
        Output o = new Output(out, x, guess);
        o.interpolateToFile();
        prompt();
    }

    // Regression menu
    public static void regressionMenu(){
        Matrix m = new Matrix();
        double[] ans = {0};
        try{
            m = MatrixInput.Input();
        }
        catch(Exception e){
            System.out.println("Terjadi eror. Input mungkin tidak valid. Mengulangi menu.");
            JOptionPane.showMessageDialog(null,"Terjadi error. Input mungkin tidak valid. Mengulangi menu. " ,"Error!", JOptionPane.ERROR_MESSAGE);
            regressionMenu();
        }
        ans = Regression.regressionAlgo(m);

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
        double x = 0;
        double[] in = new double [ans.length];
        int i = 1;
        while(i < ans.length){
            while(true){
                try{
                    in[i] = Double.parseDouble(JOptionPane.showInputDialog("Masukkan X" + i));
                    x += ans[i] * in[i];
                    break;
                }
                catch(NullPointerException e){
                    UI.exit();
                }
                catch(Exception e){
                    System.out.println("Input tidak valid." + e);
                    JOptionPane.showMessageDialog(null,"Input tidak valid. " ,"Error!", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
            }
            i++;
        }
        System.out.println("Hasil dari regresi berganda adalah: ");
        System.out.println(out);
        JOptionPane.showMessageDialog(null,"Hasil dari regresi berganda adalah: " + out);
        JOptionPane.showMessageDialog(null,"Hasil regresi dengan parameter input adalah y = " + x);
        Output o = new Output(out, x, in);
        o.regressionToFile();
        prompt();
    }

    public static void prompt(){
        System.out.println("Ingin menggunakan kalkulator lagi? (y/n)");

        String flag = JOptionPane.showInputDialog(null,"Ingin menggunakan kalkulator lagi? (y/n)", "prompt", JOptionPane.INFORMATION_MESSAGE).toLowerCase();
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


    
    



package Algorithm;
import Matrix.*;

public class SPL {
    private boolean noSolutions;
    private boolean manySolutions;
    private boolean singleSolution;
    private String[] solution;
    private Matrix m;

    public SPL(Matrix M){
        this.singleSolution = true;
        this.manySolutions = false;
        this.noSolutions = false;
        this.m = M;
    }

    public String[] solve(int choice){
        String[] ans = {""};
        switch (choice){
            case 1:
                ans = gaussEquation();
                break;
            case 2:
                ans = gaussJordanEquation();
                break;
            case 3:
                ans = inversSPL();
                break;
            case 4:
                ans = cramerAlgo();
                break;
            default:
                System.out.println("Pilihan tidak valid. Mengembalikan ke menu awal.");

        }
        this.solution = ans;
        return ans;
    }

    public String consoleOut(){
        String out = "";
        if(singleSolution){
            for(int i = 0; i < this.solution.length; i++){
                if (this.solution.length-1 == i){
                    out += "X" + (i+1) + " = " + this.solution[i];
                }
                else{
                    out += "X" + (i+1) + " = " + this.solution[i] + "\n";
                }
            }
            
        }
        else if (noSolutions){
            out = this.solution[0];
        }

        else if (manySolutions){
            for(int i = 0; i < this.solution.length; i++){
                if (this.solution.length-1 == i){
                    out += "X" + (i+1) + " = " + this.solution[i];
                }
                else{
                    out += "X" + (i+1) + " = " + this.solution[i] + "\n";
                }
            }
        }

        return out;
    }

    public void toNoSolutions(){
        this.manySolutions = false;
        this.noSolutions = true;
        this.singleSolution = false;
    }

    public void toManySolutions(){
        this.manySolutions = true;
        this.noSolutions = false;
        this.singleSolution = false;
    }

    public void toSingleSolution(){
        this.manySolutions = false;
        this.noSolutions = false;
        this.singleSolution = true;
    }

    public boolean manySolutions(){
        return this.manySolutions;
    }

    public boolean noSolutions(){
        return this.noSolutions;
    }

    public boolean singleSolution(){
        return this.singleSolution;
    }

    public String[] arrayDoubleToString(double[] d){
        int i, n; 
        String[] s; 
        n = d.length;
        s = new String[d.length];

        for (i=0; i<n; i++) {
            s[i] = Double.toString(d[i]);
        }
        
        return s;
    }


    public String[] parametric(Matrix M) {

        int i, j, row, col, count;
        double tempD;
        row = M.getRowLength();
        col = M.getColLength()-1;
        count = (col - row);

        this.m = Operation.OBETereduksi(this.m);

        // Cari nilai variabel yang bukan parametrik dulu
        int[][] tempIndex = new int[row][2];
        double[] tempSolusi = new double[col];

        for (i=0; i<col; i++) { // inisiasi nilai solusi sementara
            tempSolusi[i] = 0; 
        }

        for (i=0; i<row; i++) {
            for (j=0; j<col; j++) {
                if (this.m.getElmt(i, j) == 1) {
                    tempIndex[i][0] = i; // Indeks baris dari 1 utama 
                    tempIndex[i][1] = j;
                    break;
                }
            }
        }
        
        // Operasi yang buat non parametrik        
        for (i=row-1; i>=0; i--){
            tempD = this.m.getElmt(i, col);
            for (j=tempIndex[i][1]; j<col; j++) {
                tempD -= (tempSolusi[j]*this.m.getElmt(i, j));
            }
            tempSolusi[tempIndex[i][1]] = tempD;
        }
        
        int countZero = 0;
        for (i=0; i<col;i++) {
            if (tempSolusi[i] == 0) {
                countZero++;
            }
        }

        // Bikin nilai parametriknya
        boolean allZero = (countZero == col);
        String[] X = new String[col];
        if (!allZero) {
            for (i=0; i<col; i++) {
                if (tempSolusi[i] == 0){
                    X[i] = "x" + Integer.toString(i+1);
                } else {
                    X[i] = Double.toString(tempSolusi[i]) + " ";
                    // X[i] = "";
                }
            }
        } else {
            for (i=col-1; i>=0; i--){
                if (count > 0) {
                    X[i] = "x" + Integer.toString(i+1);
                    count--;
                } else {
                    X[i] = "";
                }
            }
        }

        String tempS;
        for (i=0; i<row; i++){
            tempS = X[tempIndex[i][1]];
            for (j=tempIndex[i][1]; j<col; j++){
                if (tempSolusi[j] == 0 && this.m.getElmt(i, j) != 0 && tempIndex[i][1] != j){
                    tempS += "-" + Double.toString(this.m.getElmt(i, j)) + "*" + X[j] + " ";
                }

            }
            X[tempIndex[i][1]] = tempS;
        }
        return X;
    }
    
    public String[] gaussEquation(){
        // Fungsi Memberikan solusi untuk Matriks M dalam bentuk array
        int i,j,n;
        
        n = this.m.getColLength() - 1;
        double[] x = new double[n];

        //Gauss Elimination
        this.m = Operation.OBE(this.m);

        //Check buat solusinya
        int row, col;
        row = this.m.getRowLength()-1;
        col = this.m.getColLength()-1;
        if (this.m.getElmt(row, col-1) != 0 && this.m.getElmt(row, col) != 0 && row == col -1) {
            toSingleSolution();
        } else if (this.m.getElmt(row, col-1) == 0 && this.m.getElmt(row, col) != 0) {
            toNoSolutions();
        } else {
            toManySolutions();
        }

        //Backward Substitution
        if (this.singleSolution) {
            x[n-1] = this.m.getElmt(n-1, n)/this.m.getElmt(n-1, n-1);

            for (i=n-2; i>=0; i--){
                x[i] = this.m.getElmt(i, n);
                
                for (j=i+1; j < n; j++) {
                    x[i] = x[i] - this.m.getElmt(i, j)*x[j];
                }
        
                x[i] = x[i]/this.m.getElmt(i, i);
            }
            return arrayDoubleToString(x);

        } else if (this.manySolutions) {
            return parametric(this.m);

        } else {
            String[] s = {"Tidak memiliki solusi"};
            return s;
        }
    }

    public String[] gaussJordanEquation() {
        // Untuk case yang hasilnya tidak parametrik
        int i, n;
        
    
        n = this.m.getColLength() - 1;
        double[] x = new double[n];
    
        // Gauss Jordan Elimination (Matriks Eselon Tereduksi)
        this.m = Operation.OBETereduksi(this.m);

        //Check buat solusinya
        int row, col;
        row = this.m.getRowLength()-1;
        col = this.m.getColLength()-1;
        if (this.m.getElmt(row, col-1) != 0 && this.m.getElmt(row, col) != 0 && row == col -1) {
            toSingleSolution();
        } else if (this.m.getElmt(row, col-1) == 0 && this.m.getElmt(row, col) != 0) {
            toNoSolutions();
        } else {
            toManySolutions();
        }
    
        if (this.singleSolution) {
            for (i=0; i<n; i++) {
                x[i] = this.m.getElmt(i, n);
            }
            return arrayDoubleToString(x);
        }else if (this.manySolutions) {
            return parametric(this.m);
        }else {
            String[] s = {"Tidak memiliki solusi"};
            return s;
        }
    } 

    public String[] cramerAlgo() {

        Matrix mTemp;
        int n, i, j;
        // Matrix n x n untuk mencari determinan
        n = this.m.getRowLength(); 
        mTemp = new Matrix(n,n);
        double[] x = new double[n];
        double[] detX = new double[n+1];

        // Isi Matrix temp dengan n x n SPL
        for (i=0; i<n; i++) {
            for (j=0; j<n; j++) {
                mTemp.setElmt(this.m.getElmt(i, j), i, j);; 
            }
        }

        //Check buat solusinya
        int row, col;
        row = this.m.getRowLength()-1;
        col = this.m.getColLength()-1;
        if (detX[0] == 0 || row != col) {
            toNoSolutions();
        } else {
            toSingleSolution();;
        }

        if (this.singleSolution) {
            // Mengganti tiap row dan insert determinan
            for (i=0; i<n; i++) {
                for (j=0; j<n; j++) {
                    mTemp.setElmt(this.m.getElmt(j, n),j,i);
                    if (i > 0) {
                        mTemp.setElmt(this.m.getElmt(j, i-1), j, i-1);
                    }
                }
                detX[i+1] = mTemp.determinantCofactor();
            }
    
            // Cari tiap x dengan determminan yang telah didapat
            for (i=0; i<n; i++){
                x[i] = (detX[i+1]/detX[0]);
            }
                
            return arrayDoubleToString(x);

        } else {
            String[] s = {"Tidak bisa menggunakan metode cramer"};
            return s;
        }
    }

    public String[] inversSPL() {
        // Inisialisasi variabel
        Matrix Mres,Mtemp,Mb;
        int row,col;
        row = this.m.getRowLength();
        col = this.m.getColLength();
        double[] res = new double[row];
        Mtemp = new Matrix(row,col-1);

        // membuat matrix b
        Mb = Operation.cutRight(this.m);

        // membuat matrix a
        for (int i = 0; i < row; i++){
            for(int j = 0; j<col-1; j++){
                Mtemp.setElmt(this.m.getElmt(i, j), i, j);
            }
        }

        // cek solusi
        if (Mtemp.determinantCofactor() == 0 || row != col) {
            toNoSolutions();
        } else {
            toSingleSolution();
        }
        
        if (this.singleSolution) {
            Mres = Operation.multiplyMatrix(Invers.inversOBE(Mtemp),Mb);
            Invers.inversOBE(Mtemp).displayMatrix();
            Mb.displayMatrix();
            Mres.displayMatrix();
            for (int i = 0; i < row; i++){
                res[i] = Mres.getElmt(i, 0);
            }
        
            return arrayDoubleToString(res);  
        } else {
            String[] s = {"Tidak bisa menggunakan metode invers balikan"};
            return s;
        }
    }
    
}

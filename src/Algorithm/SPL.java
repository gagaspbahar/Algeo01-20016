package Algorithm;
import Matrix.*;

public class SPL {
    private boolean noSolutions;
    private boolean manySolutions;
    private boolean singleSolution;
    private double[] solution;
    private Matrix m;

    public SPL(Matrix M){
        this.singleSolution = true;
        this.manySolutions = false;
        this.noSolutions = false;
        this.m = M;
    }

    public double[] solve(int choice){
        double[] ans = {0};
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

        }
        else if (manySolutions){

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
    
    public double[] gaussEquation(){
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
        x[n-1] = this.m.getElmt(n-1, n)/this.m.getElmt(n-1, n-1);

        for (i=n-2; i>=0; i--){
            x[i] = this.m.getElmt(i, n);
            
            for (j=i+1; j < n; j++) {
                x[i] = x[i] - this.m.getElmt(i, j)*x[j];
            }
    
            x[i] = x[i]/this.m.getElmt(i, i);
        }
        return x;
    }

    public double[] gaussJordanEquation() {
        // Untuk case yang hasilnya tidak parametrik
        int i, n;
        
    
        n = this.m.getColLength() - 1;
        double[] x = new double[n];
    
        // Gauss Jordan Elimination (Matriks Eselon Tereduksi)
        this.m = Operation.OBETereduksi(this.m);
    
        for (i=0; i<n; i++) {
            x[i] = this.m.getElmt(i, n);
        }

    
        return x;
    } 

    public double[] cramerAlgo() {

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

        // Inisialisasi det untuk x0
        detX[0] = mTemp.determinantCofactor();
        if (detX[0] == 0) {
            System.out.println("Tidak terdapat solusi");
            return detX;
        }

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
            
        return x;
    }

    public double[] inversSPL() {
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
        
        Mres = Operation.multiplyMatrix(Invers.inversOBE(Mtemp),Mb);
        Invers.inversOBE(Mtemp).displayMatrix();
        Mb.displayMatrix();
        Mres.displayMatrix();
        for (int i = 0; i < row; i++){
            res[i] = Mres.getElmt(i, 0);
        }
    
        return res;
    }
    
}
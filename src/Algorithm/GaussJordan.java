package Algorithm;
import Matrix.*;


public class GaussJordan {
    public static Matrix OBETereduksi(Matrix M){
        int i, j, k,row, col;
        double a, b, ratio;
        M = Gauss.OBE(M);
    
        row = M.getRowLength();
        col = M.getColLength();
    
        for (i=row-1; i>=1; i--){
    
            // Diagonal Utama ada elemen yang 0 maka tidak dilakukan operasi
            if (M.getElmt(i, i) == 0) {
                continue;
            }
    
            for (j=i-1; j>=0;j--) {
                a = M.getElmt(j, i);
                b = M.getElmt(i, i);
                ratio = a/b;
    
                for (k=0; k<col+1; k++){
                    M.setElmt(M.getElmt(j, k) - ratio*M.getElmt(i, k), j, k);
                }
                    
            }
        }    
    
        return M;
    }

    public static double[] gaussJordanEquation(Matrix M) {
        // Untuk case yang hasilnya tidak parametrik
        int i, n;
        
    
        n = M.getColLength() - 1;
        double[] x = new double[n];
    
        // Gauss Jordan Elimination (Matriks Eselon Tereduksi)
        M = OBETereduksi(M);
    
        for (i=0; i<n; i++) {
            x[i] = M.getElmt(i, n);
        }

    
        return x;
}
}

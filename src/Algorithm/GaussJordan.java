package Algorithm;
import Matrix.*;


public class GaussJordan {
    

    public static double[] gaussJordanEquation(Matrix M) {
        // Untuk case yang hasilnya tidak parametrik
        int i, n;
        
    
        n = M.getColLength() - 1;
        double[] x = new double[n];
    
        // Gauss Jordan Elimination (Matriks Eselon Tereduksi)
        M = Operation.OBETereduksi(M);
    
        for (i=0; i<n; i++) {
            x[i] = M.getElmt(i, n);
        }

    
        return x;
}
}

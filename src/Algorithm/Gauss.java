package Algorithm;
import Matrix.*;
public class Gauss {

    public static double[] gaussEquation(Matrix M){
        // Fungsi Memberikan solusi untuk Matriks M dalam bentuk array
        int i,j,n;
       
        
        n = M.getColLength() - 1;
        double[] x = new double[n];

        //Gauss Elimination
        M = Operation.OBE(M);

        //Backward Substitution
        x[n-1] = M.getElmt(n-1, n)/M.getElmt(n-1, n-1);

        for (i=n-2; i>=0; i--){
            x[i] = M.getElmt(i, n);
            
            for (j=i+1; j < n; j++) {
                x[i] = x[i] - M.getElmt(i, j)*x[j];
            }
    
            x[i] = x[i]/M.getElmt(i, i);
        }
        return x;
    }

    
}

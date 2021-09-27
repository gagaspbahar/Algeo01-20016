package Algorithm;
import Matrix.*;

public class Cramer {
    public static double[] cramerAlgo(Matrix M) {

    Matrix mTemp;
    int n, i, j;
    // Matrix n x n untuk mencari determinan
    n = M.getRowLength(); 
    mTemp = new Matrix(n,n);
    double[] x = new double[n];
    double[] detX = new double[n];
    // Isi Matrix temp dengan n x n SPL
    for (i=0; i<n; i++) {
        for (j=0; j<n; j++) {
            mTemp.setElmt(M.getElmt(i, j), i, j);; 
        }
    }

    // Inisialisasi det untuk x0
    detX[0] = mTemp.determinantByOBE();
    if (detX[0] == 0) {
        System.out.println("Tidak terdapat solusi");
        return detX;
    }

    // Mengganti tiap row dan insert determinan
    for (i=0; i<n; i++) {
        for (j=0; j<n; j++) {
            
            mTemp.setElmt(M.getElmt(j,n), j, i);
            if (i > 0) {
                
                mTemp.setElmt((M.getElmt(j,i-1)), j, i-1);
            }
        }
        detX[i+1] = mTemp.determinantByOBE();

        for (i=1; i<=n; i++){
            x[i] = (detX[i]/detX[0]);
        }

        
    }
    return x;
}


}


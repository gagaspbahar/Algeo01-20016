package Algorithm;
import Matrix.*;
import java.lang.Math;

public class Interpolate {
    public static double[] interpolateAlg(Matrix M){
        // Input dalam bentuk [[x0, y0], [x1, y1], [x2, y2]...[xn, yn]]
        Matrix mTemp;
        int n, i, j;
        n = M.getRowLength();
        mTemp = new Matrix(n,n+1);
        
        // Ubah ke bentuk SPL(Augmented Matrix)
        for (i=0; i<n; i++) {
            for (j=0; j<n+1; j++) {
                if (j == n) {
                    mTemp.setElmt(M.getElmt(i, 1), i, j);
                } else {
                    mTemp.setElmt(Math.pow(M.getElmt(i, 0), j), i, j);
                }
            }
        }

        // Lakukan operasi OBETereduksi
        mTemp = Operation.OBETereduksi(mTemp);

        // Karena gaada ADT Array, pake matrix dengan rows = 1
        double[] ans;
        ans = new double[n];
        for (i=0; i<n; i++) {
            ans[i] = mTemp.getElmt(i, n);
        }

        // mAns merupakan "array" yang bersifat sebagai koefisien dari
        // polinomial derajat COLS(mAns)
        return ans;
    }

    public static double functionInterpolate(double[] ar, double x){
         // Menghitung f(x) dari hasil interpolasi dengan
        // x sebagai parameter

        int n, i;
        double ans;
        n =  ar.length;
        ans = 0;

        for (i=0;i<n;i++){
            ans += ar[i] * Math.pow(x,i);
        }

        return ans;
    }
}

package Algorithm;
import Matrix.*;

public class Regression {
    public static double[] regressionAlgo(Matrix M) {
        // Nerima matrix dengan bentuk x1 + x2 + .. + xn = y
        Matrix mReg;
        int n ,row, col, i, j, k;
        n = M.getRowLength();
        row = M.getColLength();
        col = M.getColLength() + 1;
        mReg = new Matrix(row, col);

        // Augment matrix menjadi bentuk x0 + x1 + x2 + .. + xn = y
        Matrix x0;
        x0 = new Matrix(n, 1);
        // Inisiasi x0 dengan elemennya 1 semua
        for (i = 0; i<n; i++) {
            M.setElmt(1, i, 1);
        }
        // Augment dengan m
        M = Operation.extendMatrix(x0, M);

        // Isi Matrix dengan Normal Estimation Equation
        for (i=0; i<row; i++) {
            for (j=0; j<col; j++) {
                double temp;
                temp = 0;

                for (k=0; k<n; k++) {
                    if (i == 0 && j == 0) {
                        temp += 1;
                    } else if (i == 0) {
                        temp += M.getElmt(k, j);
                    } else if (j == 0) {
                        temp += M.getElmt(k, i);
                    } else {
                        temp += M.getElmt(k, i)*M.getElmt(k, j);
                    }
                }
                mReg.setElmt(temp, i, j);
            }
        }

        //Selesaikan dengan GaussJordan
        return GaussJordan.gaussJordanEquation(mReg);
    }

    public static double functionRegression(double[] arrX, double[] inputX) {
        // arrX merupakan array yang dikeluarkan dari fungsi regression
        // inputX sebanyak k sebagai input nilai x untuk mengecek nilai fungsi
        double ans;
        int i, n;
        n = inputX.length;

        // Inisialiasasi untuk x0 dari fungsi regresi
        ans = arrX[0];

        // Menghasilkan ans berdasarkan operasi fungsi
        for (i=0;i<n;i++) {
            ans += arrX[i+1]*inputX[i];
        }
        
        return ans;
    }
}

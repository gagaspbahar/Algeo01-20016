package Algorithm;
import Matrix.*;

public class Operation {
    public static Matrix OBE(Matrix M){
        int row = M.getRowLength();
        int col = M.getColLength();
        int i = 0;
        int j =0;

        while (j<col){
            boolean cariSatu = false;

            if(M.getElmt(i, j) == 0){
                boolean cariNol = false;
                int colCheck = i +1;

                while(colCheck<row && !cariNol){
                    if (M.getElmt(colCheck, j) != 0){
                        cariNol = true;
                        for (int x = 0; x < col; x++){
                            double temp = M.getElmt(colCheck, x);
                            M.setElmt(M.getElmt(i, x), colCheck, x);
                            M.setElmt(temp, i, x);
                        }
                    }
                    ++colCheck;
                }
            }
           
            if(M.getElmt(i, j) != 0){
                double per = M.getElmt(i, j);
                for (int a = 0; a < col; a++){
                    M.setElmt(M.getElmt(i, a)/per, i, a);
                }
                cariSatu = true;

                double factor;
                int rowLain = i+1;
                while (rowLain < row){
                    factor = M.getElmt(rowLain, j);
                    double val;
                    for (int k = 0; k < col; k++){
                        val = M.getElmt(i, k) * factor;
                        M.setElmt(M.getElmt(rowLain, k) - val, rowLain, k);
                    }
                    rowLain++;
                }

            }
            if (cariSatu){
                i++;
            }

            if (row <= i){
                break;
            }
            j++;
        }

        
        

        return M;
    }

    public static Matrix OBETereduksi(Matrix M){
        int i , j;
        int row = M.getRowLength();
        int col = M.getColLength();
        M = OBE(M);
        for (i = row - 1 ; i >= 0; i--){
            for(j = col -1 ; j >= 0; j--){
                if (M.getElmt(i, j) == 1){
                    double factor;
                    int rowLain = i -1;
                    while (rowLain >= 0){
                        factor = M.getElmt(rowLain, j);
                        double val;
                        for (int k = 0; k < col; k++){
                            val = M.getElmt(i, k) * factor;
                            M.setElmt(M.getElmt(rowLain, k) - val, rowLain, k);
                        }
                        rowLain--;

                    }
                }
            }
        }
        return M;
    }

    public static Matrix extendMatrix(Matrix M1 , Matrix M2){
        Matrix extend;
        int RowsM1, ColsM1, ColsM2, i , j;
        RowsM1 = M1.getRowLength();
        ColsM1 = M1.getColLength();
        ColsM2 = M2.getColLength();

        extend = new Matrix(RowsM1,ColsM1+ColsM2);
        for (i=0; i<RowsM1; i++) {
            for (j=0; j<(ColsM1 +ColsM2); j++) {
                if (j < ColsM1) {
                
                    extend.setElmt(M1.getElmt(i, j), i, j);
                } else {
                    
                    extend.setElmt(M2.getElmt(i, j-ColsM1), i, j);
                }
            }
        }
        return extend;

    }

    public static void pMultiplyConst(Matrix M, double k){
        int i,j, RowsM, ColsM;
        RowsM = M.getRowLength(); ColsM = M.getColLength();
        for (i=0; i<RowsM;i++) {
            for (j=0; j<ColsM;j++) {
                M.setElmt(M.getElmt(i, j)*k, i, j);
            }
        }

    }
    
    public static Matrix multiplyMatrix(Matrix m1, Matrix m2){
        int row = m1.getRowLength();
        int col = m2.getColLength();
        Matrix ans = new Matrix(row, col);
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int temp = 0;
                for(int k = 0; k < m1.getColLength(); k++){
                    temp += m1.getElmt(i,k) * m2.getElmt(k,j);
                }
                ans.setElmt(temp,i,j);
            }
        }
        return ans;
    }

    public static Matrix cutRight(Matrix m){
        Matrix ans = new Matrix(m.getRowLength(), 1);
        int col = m.getColLength();
        for(int i = 0; i < ans.getRowLength(); i++){
            ans.setElmt(m.getElmt(i, col-1), i, 0);
        }
        return ans;
    }
}



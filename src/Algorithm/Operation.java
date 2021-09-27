package Algorithm;
import Matrix.*;

public class Operation {
    public static Matrix OBE(Matrix M){
        int i,j,k,row,col,x;
        double a,b,ratio;
        row = M.getRowLength();
        col = M.getColLength();
        for(i=0;i<row;i++){
            x = i;
            //Find first non 0 index
            while(x < row && M.getElmt(x, i) == 0){
                x++;
            }
            //Main diagonal element == 0
            if(x == row){
                continue;
            }

            //Switch row between i and non-zero index
            if (x!=i){
                for (j=0; j<col;j++){
                    double temp;
                    temp = M.getElmt(x, j);
                    M.setElmt(M.getElmt(i, j), x, j);
                    M.setElmt(temp, i, j);
                }
            }

            for (j = i+1; j<row; j++){
                a = M.getElmt(j, i);
                b = M.getElmt(i, i);
                ratio = a/b;

                for (k = 0;k<col;k++){
                    M.setElmt(M.getElmt(j, k) - ratio*M.getElmt(i, k), j, k);


                }

               
            }
        }

        for (i=0;i<row;i++){
            if(M.getElmt(i, i)!= 0){
                ratio = M.getElmt(i, i);
                for (j = i; j<col; j++){
                    M.setElmt(M.getElmt(i, j)/ratio, i, j);
                }
            }
        }

        return M;
    }

    public static Matrix OBETereduksi(Matrix M){
        int i, j, k, row, col;
        double a, b, ratio;
        M = OBE(M);
    
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
    
                for (k=0; k<col; k++){
                    M.setElmt(M.getElmt(j, k) - ratio*M.getElmt(i, k), j, k);
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
}



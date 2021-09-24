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
            while(M.getElmt(x, i) == 0 && x < row){
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

            for (j = i+1; j<col; j++){
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
        int i, j, k,row, col;
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
    
                for (k=0; k<col+1; k++){
                    M.setElmt(M.getElmt(j, k) - ratio*M.getElmt(i, k), j, k);
                }
                    
            }
        }    
    
        return M;
    }
}


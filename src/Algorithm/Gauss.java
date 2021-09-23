package Algorithm;
import Matrix.*;
public class Gauss {
    
    public Matrix OBE(Matrix M){
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
    
    public double[] gaussEquation(Matrix M){
        // Fungsi Memberikan solusi untuk Matriks M dalam bentuk array
        int i,j,n;
       
        
        n = M.getColLength();
        double[] x = new double[n];

        //Gauss Elimination
        M = OBE(M);

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

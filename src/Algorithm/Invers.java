package Algorithm;

import Matrix.Matrix;


public class Invers {
    public static Matrix inversOBE(Matrix M){
        //prekondisi matriks isSquare
        Matrix extend,identity,Mres;
        int i,j,n;
        boolean flag = true;
        n = M.getRowLength();
        Mres = new Matrix(n,n);
        extend = new Matrix(n,2*n);
        identity = new Matrix(n);
        
        // Extend matrix dengan identitasnya
        extend = Operation.extendMatrix(M, identity);
        // Operasi Gauss Jordan untuk extended matriks
        extend = Operation.OBETereduksi(extend);
        
        for (i=0; i<n ; i++)
        {
            if(extend.getElmt(i, i) == 0)
            {
                System.out.println("Tidak mempunyai invers");
                flag = false;
                break;
            }
        }
        if(flag) 
        {
            
            for (i=0; i<n;i++)
            {
                for (j=0; j<n; j++)
                {
                    Mres.setElmt(extend.getElmt(i, j+n), i, j);
                }
                
                
            }
        }
        return Mres;
    }
    
    public static Matrix inversCofactor(Matrix M){
        Matrix mTemp,mRes,mCofactor;
        int i,j,n;
        double sign,det;
        
        sign = 1;
        n = M.getRowLength();
        mRes = new Matrix(n,n);
        
        
        if (M.determinantCofactor() == 0){
            System.out.println("Tidak ada invers") ;
            return null;
        }
        else{
            det = 1/M.determinantCofactor();
            mTemp = new Matrix(n, n);
            mTemp.copyMatrix(M);
            
            // Matriks Cofactor
            mCofactor = new Matrix(n-1,n-1);
            for(i=0;i<n;i++){
                for(j=0;j<n;j++){
                mCofactor = mTemp.cofactor(i, j);
                // mCofactor.displayMatrix();
                mRes.setElmt(mCofactor.determinantCofactor()*sign, i, j) ;
                sign *= -1;
            }
            sign *= -1;
        }
        }
        
        mRes = mRes.transpose();
        Operation.pMultiplyConst(mRes, det);
        
        return mRes;
    }
    
    public static double[] inversSPL(Matrix M) {
        Matrix Mres,Mtemp,Mb;
        int row,col;
        row = M.getRowLength();
        col = M.getColLength();
        Mtemp = new Matrix(row,col-1);
        Mb = Operation.cutRight(M);
        double[] res = new double[row];
        for (int i = 0; i < row; i++){
            for(int j = 0; j<col-1; j++){
                Mtemp.setElmt(M.getElmt(i, j), i, j);
            }
        }
        
        Mres = Operation.multiplyMatrix(inversOBE(Mtemp),Mb);
        for (int i = 0; i < row; i++){
            res[i] = Mres.getElmt(i, 0);
        }
    
        return res;
    }
}


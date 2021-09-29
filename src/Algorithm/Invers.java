package Algorithm;
import java.lang.Math;
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
                    sign = Math.pow(-1, i+j);
                    mCofactor = mTemp.cofactor(i, j);
                    mRes.setElmt(mCofactor.determinantCofactor()*sign, i, j);
                }
            }
        }
        
        mRes = mRes.transpose();
        Operation.pMultiplyConst(mRes, det);
        
        return mRes;
    }

}


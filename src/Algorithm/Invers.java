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
        Matrix mTemp,mRes,mcofactor;
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
            det = 1/M.determinantCofactor() ;
            mTemp = M.copyMatrix(M);
           
            // Matriks Cofactor
            mcofactor = new Matrix(n,n);
            mcofactor = mTemp.cofactor(n,n);
            for(i = 0; i <n ; i++)
                for(j=0;j<n;j++){
                    mRes.setElmt(mcofactor.determinantCofactor() * sign, i, j) ;
                    sign *= -1;
                }
                sign += 1;
            }
            mRes.transpose();
            Operation.pMultiplyConst(mRes, det);
            
            return mRes;
        }
     
    }


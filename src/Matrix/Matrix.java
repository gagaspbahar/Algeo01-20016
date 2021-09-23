package Matrix;

public class Matrix {
    private int Row, Col;
    private double[][] Contents;

    // Constructor

    public Matrix(){
        this.Row = 0;
        this.Col = 0;
    }
    public Matrix(int row, int col){
        this.Row = row;
        this.Col = col;
        this.Contents = new double[row][col];
        for (int i = 0; i < row; i++){
            for(int j = 0; j<col; j++){
                setElmt(0, i, j);
            }
        }
    }

    public void displayMatrix(){
        for (int i = 0; i < this.Row; i++){
            for (int j = 0; j < this.Col; j++){
                if(j == this.Col){
                    System.out.print(getElmt(i,j));
                }
                else{
                    System.out.print(getElmt(i,j) + " ");
                }
            }
            System.out.println();
        }
    }

    public Matrix copyMatrix(Matrix m){
        Matrix mCopy = new Matrix(m.Row, m.Col);
        for (int i = 0; i<m.Row;i++){
            for (int j = 0; j<m.Col;j++){
                setElmt(m.getElmt(i, j), i, j);
            }
        }
        return mCopy;
    }

    public double getElmt(int i, int j){
        return this.Contents[i][j];
    }

    public int getRowLength(){
        return this.Row;
    }

    public int getColLength(){
        return this.Col;
    }

    public void setElmt(double x, int row, int col){
        this.Contents[row][col] = x;
    }
        
    public double determinantByOBE(){
        int a, i, j, k, n;
        double num1, num2, num3, temp;
        double det, total;
    
        n = this.Row;
        det = 1;
        total = 1;
    
        // Switching Row
    
    
        // Gauss Elimination
        for (i = 0; i<n; i++) {
            a = i;
            
            // Find the first non 0 index
            while (a < n && getElmt(a, i) == 0) {
                a++;
            }
    
            if (a == n) {
                return 0;
            }
    
            // Row Switching (between the i and non zero index)
            if (a != i) {
                for (j=0; j<n; j++) {
                    temp = getElmt(a, j);
                    setElmt(getElmt(i, j), a, j);
                    setElmt(temp, i, j);
                }
                det *= -1;
            }
    
            // OBE
            for (j = i+1; j<n; j++) {
                num1 = getElmt(i, i);
                num2 = getElmt(j, i);
    
                for (k=0; k<n; k++) {
                    num3 = (num1*getElmt(j, k)) - (num2*getElmt(i, k));
                    setElmt(num3, j, k);
                }
    
                total *= num1;
            }
        }
    
        // Determinan
        for (i = 0; i<n;i++) {
            det *= getElmt(i, i);
        }
    
    
        return (det/total);
    }

    public boolean isSquare(Matrix m){
        return (this.Row == this.Col);
    }
    
}

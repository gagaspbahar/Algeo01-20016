package Matrix;

public class Matrix {
    private int Row, Col;
    private double[][] Contents;

    // Constructor

    public void Matrix(){
        this.Row = 0;
        this.Col = 0;
    }
    public void Matrix(int row, int col){
        this.Row = row;
        this.Col = col;
        this.Contents = new double[row][col];
    }

    public void displayMatrix(Matrix m){
        for (int i = 0; i < this.Row; i++){
            for (int j = 0; j < this.Col; j++){
                if(j == this.Col){
                    System.out.print(getElmt(i,j) + "\n");
                }
                else{
                    System.out.print(getElmt(i,j) + " ");
                }
            }
        }
    }

    public double getElmt(int i, int j){
        return this.Contents[i][j];
    }

    public boolean isSquare(Matrix m){
        return (this.Row == this.Col);
    }
}

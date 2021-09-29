package Utility;
import Algorithm.*;
import Matrix.*;

public class SPL {
    public boolean noSolutions;
    public boolean manySolutions;
    public boolean singleSolution;
    private double[] solution;
    private Matrix m;

    public SPL(Matrix M){
        this.singleSolution = true;
        this.manySolutions = false;
        this.noSolutions = false;
        this.m = M;
    }

    public double[] solve(int choice){
        double[] ans = {0};
        switch (choice){
            case 1:
                ans = Gauss.gaussEquation(m);
                break;
            case 2:
                ans = GaussJordan.gaussJordanEquation(m);
                break;
            case 3:
                break;
            case 4:
                ans = Cramer.cramerAlgo(m);
                break;
        }
        this.solution = ans;
        return ans;
    }

    public String consoleOut(){
        String out = "";
        if(singleSolution){
            for(int i = 0; i < this.solution.length; i++){
                if (this.solution.length-1 == i){
                    out += "X" + (i+1) + " = " + this.solution[i];
                }
                else{
                    out += "X" + (i+1) + " = " + this.solution[i] + "\n";
                }
            }
        }
        else if (noSolutions){

        }
        else if (manySolutions){
            
        }
        return out;
    }
}

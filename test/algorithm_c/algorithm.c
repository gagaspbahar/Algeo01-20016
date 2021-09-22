#include <stdlib.h>
#include <stdio.h>
#include "boolean.h"
#include "matrix.h"

Matrix cofactor(Matrix m, Matrix *mTemp, int p, int q, int n) {
    int i, j, a, b;
    a = 0;
    b = 0;

    for (i = 0; i<n;i++) {
        for (j = 0; j<n;j++) {
            if (i != p && j != q) {
                ELMT(*mTemp, a, b) = ELMT(m, i, j);
                b++;
                if (b == n - 1) {
                    b = 0;
                    a++;
                }
            }
        }
    }
}

float determinantCofactor(Matrix m, int n) {
    float d = 0;

    if (n == 1) {
        return ELMT(m, 0, 0);

    } else {
        Matrix mTemp;
        CreateMatrix(n, n, &mTemp);
        int sign = 1;
        int i;

        for (i=0; i<n;i++) {
            cofactor(m, &mTemp, 0, i, n);
            d += sign*ELMT(m, 0, i)*determinantMatrix(mTemp, n-1);
            sign = -sign;
        }

        return d;
    }
}

int determinantOBE(Matrix m) {
    int a, i, j, k, n, x[ROWS(m)], det, total, temp;
    int num1, num2;

    n = ROWS(m);
    det = 1;
    total = 1;

    int count = 1;
    // Switching Row


    // Gauss Elimination
    for (i = 0; i<n; i++) {
        a = i;
        
        // Find the first non 0 index
        while (ELMT(m, a, i) == 0 && a < n) {
            a++;
        }

        if (a == n) {
            return 0;
        }

        // Row Switching (between the i and non zero index)
        if (a != i) {
            for (j=0; j<n; j++) {
                temp = ELMT(m, a, j);
                ELMT(m, a, j) = ELMT(m, i, j);
                ELMT(m, i, j) = temp;
            }
            det *= -1;
        }

        // OBE
        for (j = i+1; j<n; j++) {
            num1 = ELMT(m, i, i);
            num2 = ELMT(m, j, i);

            for (k=0; k<n; k++) {
                ELMT(m, j, k) = (num1*ELMT(m, j, k)) 
                - (num2*ELMT(m, i, k));
            }

            total *= num1;
        }

        printf("%d.\n", count++);
        displayMatrix(m);
        printf("\n");
    
    }

    // Determinan
    for (i = 0; i<n;i++) {
        det *= ELMT(m, i, i);
    }


    return (det/total);
}

double gaussEquation(Matrix m) {
    // Untuk case yang hasilnya tidak parametrik
    int i, j, k, n;
    double a, b, ratio;

    n = COLS(m)-1;
    double x[n];

    // Gauss Elimination
    for (i=0; i<n; i++){

        if (ELMT(m, i, i) == 0) {
            return 0;
        }

        for (j=i+1; j < n+1;j++){
            a = ELMT(m, j, i);
            b = ELMT(m, i, i);
            ratio = a/b;

            for (k=0; k<n+1; k++){
                ELMT(m, j, k) = ELMT(m, j, k) 
                    - ratio*ELMT(m, i, k);
            }
        }
    }

    for (i=0; i<n; i++) {
        ratio = ELMT(m, i, i);
        for (j=i; j<n+1; j++) {
            ELMT(m, i, j) = ELMT(m, i, j)/ratio;
        }
    }

    // Backward Subtitution
    x[n-1] = ELMT(m, n-1, n)/ELMT(m, n-1, n-1);

    for (i=n-2; i>=0; i--){
        x[i] = ELMT(m, i, n);
        
        for (j=i+1; j < n; j++) {
            x[i] = x[i] - ELMT(m, i, j)*x[j];
        }

        x[i] = x[i]/ELMT(m, i, i);
    }


    printf("\nSolution: \n");
    for (i = 0; i<n; i++){
        printf("x[%d] = %0.2f\n", i+1, x[i]);
    }
}


double gaussJordanEquation(Matrix m) {
    // Untuk case yang hasilnya tidak parametrik
    int i, j, k, n;
    double a, b, ratio;

    n = COLS(m)-1;
    double x[n];

    // Gauss Elimination (Matriks Eselon)
    for (i=0; i<n; i++){

        if (ELMT(m, i, i) == 0) {
            return 0;
        }

        for (j=i+1; j < n+1;j++){
            a = ELMT(m, j, i);
            b = ELMT(m, i, i);
            ratio = a/b;

            for (k=0; k<n+1; k++){
                ELMT(m, j, k) = ELMT(m, j, k) 
                    - ratio*ELMT(m, i, k);
            }
        }
    }


    for (i=0; i<n; i++) {
        ratio = ELMT(m, i, i);
        for (j=i; j<n+1; j++) {
            ELMT(m, i, j) = ELMT(m, i, j)/ratio;
        }
    }


    // Gauss Jordan Elimination (Matrik Eselon Terduksi)
    for (i=n-1; i>=1; i--){

        for (j=i-1; j>=0;j--){
            a = ELMT(m, j, i);
            b = ELMT(m, i, i);
            ratio = a/b;

            for (k=0; k<n+1; k++){
                ELMT(m, j, k) = ELMT(m, j, k) 
                    - ratio*ELMT(m, i, k);
            }
        }
    }

    for (i=0; i<n; i++) {
        x[i] = ELMT(m, i, n);
    }

    printf("\nSolution: \n");
    for (i = 0; i<n; i++){
        printf("x[%d] = %0.2f\n", i+1, x[i]);
    }
}

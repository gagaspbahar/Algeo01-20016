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

double determinantCofactor(Matrix m, int n) {
    double d = 0;

    if (n == 1) {
        return ELMT(m, 0, 0);

    } else {
        Matrix mTemp;
        CreateMatrix(n, n, &mTemp);
        int sign = 1;
        int i;

        for (i=0; i<n;i++) {
            cofactor(m, &mTemp, 0, i, n);
            d += sign*ELMT(m, 0, i)*determinantCofactor(mTemp, n-1);
            sign = -sign;
        }

        return d;
    }
}

double determinantOBE(Matrix m) {
    int a, i, j, k, n;
    double num1, num2, temp;
    double det, total;

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
    }

    // Determinan
    for (i = 0; i<n;i++) {
        det *= ELMT(m, i, i);
    }


    return (det/total);
}

Matrix OBE(Matrix m){
    int i, j, k, row, col, x;
    double a, b, ratio;
    row = ROWS(m);
    col = COLS(m);

    for (i=0; i<row; i++){
        int x = i;
        
        // Find the first non 0 index
        while (ELMT(m, x, i) == 0 && x < row) {
            x++;
        }

        // Row Switching (between the i and non zero index)
        if (x != i) {
            for (j=0; j<col; j++) {
                double temp;
                temp = ELMT(m, x, j);
                ELMT(m, x, j) = ELMT(m, i, j);
                ELMT(m, i, j) = temp;
            }

        }

        for (j=i+1; j < col;j++){
            a = ELMT(m, j, i);
            b = ELMT(m, i, i);
            ratio = a/b;

            for (k=0; k<col; k++){
                ELMT(m, j, k) = ELMT(m, j, k) 
                    - ratio*ELMT(m, i, k);
            }
            displayMatrix(m);
            printf("\n");
            printf("\n");
        }
    }

    for (i=0; i<row; i++) {
        if (ELMT(m, i, i) != 0) {
            ratio = ELMT(m, i, i);
            for (j=i; j<col; j++) {
                ELMT(m, i, j) = ELMT(m, i, j)/ratio;
            }
        }
    }

    displayMatrix(m);

    return m;
}


Matrix OBETereduksi(Matrix m){
    int i, j, k, n, row, col;
    double a, b, ratio;
    m = OBE(m);

    row = ROWS(m);
    col = COLS(m);

    for (i=row-1; i>=1; i--){

        for (j=i-1; j>=0;j--){
            a = ELMT(m, j, i);
            b = ELMT(m, i, i);
            ratio = a/b;

            for (k=0; k<row+1; k++){
                ELMT(m, j, k) = ELMT(m, j, k) 
                    - ratio*ELMT(m, i, k);
            }

            displayMatrix(m);
            printf("\n");
            printf("\n");
        }
    }    

    displayMatrix(m);

    return m;
}


void gaussEquation(Matrix m) {
    // Untuk case yang hasilnya tidak parametrik
    int i, j, k, n;
    double a, b, ratio;

    n = COLS(m)-1;
    double x[n];

    // Gauss Elimination
    m = OBE(m);

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


void gaussJordanEquation(Matrix m) {
    // Untuk case yang hasilnya tidak parametrik
    int i, j, k, n;
    double a, b, ratio;

    n = COLS(m)-1;
    double x[n];

    // Gauss Jordan Elimination (Matriks Eselon Tereduksi)
    m = OBETereduksi(m);

    for (i=0; i<n; i++) {
        x[i] = ELMT(m, i, n);
    }

    printf("\nSolution: \n");
    for (i = 0; i<n; i++){
        printf("x[%d] = %0.2f\n", i+1, x[i]);
    }
}


void cramer (Matrix m) {
    Matrix mTemp;
    int n, i, j, k;
    // Matrix n x n untuk mencari determinan
    n = ROWS(m); 
    CreateMatrix(n, n, &mTemp);
    double x[n], detX[n];

    // Isi Matrix temp dengan n x n SPL
    for (i=0; i<n; i++) {
        for (j=0; j<n; j++) {
            ELMT(mTemp, i, j) = ELMT(m, i, j); 
        }
    }

    // Inisialisasi det untuk x0
    detX[0] = determinantOBE(mTemp);
    if (detX[0] == 0) {
        return 0;
    }

    // Mengganti tiap row dan insert determinan
    for (i=0; i<n; i++) {
        for (j=0; j<n; j++) {
            ELMT(mTemp, j, i) = ELMT(m, j, n);
            if (i > 0) {
                ELMT(mTemp, j, i-1) = ELMT(m, j, i-1);
            }
        }
        detX[i+1] = determinantOBE(mTemp);
    }

    // for (i=0; i<=n; i++) {
    //     printf("Det x[%d] = %.1f\n", i, detX[0]);
    // }

    // Cari tiap x dengan determminan yang telah didapat
    for (i=1; i<=n; i++){
        x[i] = (detX[i]/detX[0]);
    }

    for (i=1; i<=n; i++) {
        printf("x[%d] = %.1f\n", i, x[i]);
    }
}

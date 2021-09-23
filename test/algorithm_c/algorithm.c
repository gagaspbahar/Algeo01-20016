#include <stdlib.h>
#include <stdio.h>
#include "boolean.h"
#include "matrix.h"

Matrix cofactor(Matrix m, int p, int q) {
    // p, q baris dan kolom dari cofactor
    Matrix mTemp;
    int i, j, a, b, n;
    n = ROWS(m);
    CreateMatrix(n-1, n-1, &mTemp);
    a = 0;
    b = 0;

    for (i = 0; i<n;i++) {
        for (j = 0; j<n;j++) {
            if (i != p && j != q) {
                ELMT(mTemp, a, b) = ELMT(m, i, j);
                b++;
                if (b == n - 1) {
                    b = 0;
                    a++;
                }
            }
        }
    }

    return mTemp;
}

void CreateMatrixIdentity(Matrix *m, int n) {
    int i, j;
    ROWS(*m) = n;
    COLS(*m) = n;

    for (i = 0; i<n; i++) {
        for (j = 0; j<n; j++) {
            if (i == j){
                ELMT(*m, i, j) = 1;
            } else {
                ELMT(*m, i, j) = 0;
            }
        }
    }
}

Matrix extendMatrix(Matrix m1, Matrix m2) {
    // Prekondisi ROWS(m1) = ROWS(m2)
    Matrix extend;
    CreateMatrix(ROWS(m1), COLS(m1) + COLS(m2), &extend);
    int i, j;

    for (i=0; i<ROWS(m1); i++) {
        for (j=0; j<(COLS(m1)+COLS(m2)); j++) {
            if (j < COLS(m1)) {
                ELMT(extend, i, j) = ELMT(m1, i, j);
            } else {
                ELMT(extend, i, j) = ELMT(m2, i, j-COLS(m1));
            }
        }
    }
    return extend;
}

double determinantCofactor(Matrix m) {
    double d = 0;
    int n = ROWS(m);

    if (n == 1) {
        return ELMT(m, 0, 0);

    } else {
        Matrix mTemp;
        CreateMatrix(n-1, n-1, &mTemp);
        int sign = 1;
        int i;

        for (i=0; i<n;i++) {
            mTemp = cofactor(m, 0, i);
            d += ELMT(m, 0, i)*determinantCofactor(mTemp)*sign;
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
        while (a < n && ELMT(m, a, i) == 0) {
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

        // Elemen di diagonal utama == 0
        if (x == row) {
            continue;
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
                ELMT(m, j, k) = ELMT(m, j, k)  - ratio*ELMT(m, i, k);
            }
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

    return m;
}


Matrix OBETereduksi(Matrix m){
    int i, j, k, n, row, col;
    double a, b, ratio;
    m = OBE(m);

    row = ROWS(m);
    col = COLS(m);

    for (i=row-1; i>=1; i--){

        // Diagonal Utama ada elemen yang 0 maka tidak dilakukan operasi
        if (ELMT(m, i, i) == 0) {
            continue;
        }

        for (j=i-1; j>=0;j--) {
            a = ELMT(m, j, i);
            b = ELMT(m, i, i);
            ratio = a/b;

            for (k=0; k<col+1; k++){
                ELMT(m, j, k) = ELMT(m, j, k) 
                    - ratio*ELMT(m, i, k);
            }
                
        }
    }    

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
        printf("Tidak ada solusi\n");
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

    // Cari tiap x dengan determminan yang telah didapat
    for (i=1; i<=n; i++){
        x[i] = (detX[i]/detX[0]);
    }

    for (i=1; i<=n; i++) {
        printf("x[%d] = %.1f\n", i, x[i]);
    }
}

void inversOBE(Matrix *m) {
    // Prekondisi isSquare
    Matrix extend, identity;
    int i, j, n;
    boolean flag = true;
    n = ROWS(*m);
    CreateMatrix(n, 2*n, &extend);
    CreateMatrixIdentity(&identity, n);

    // Extend matrix dengan identitasnya
    extend = extendMatrix(*m, identity);

    // Operasi Gauss Jordan terhadap extended matrix
    extend = OBETereduksi(extend);


    for (i=0; i<n;i++){
        if (ELMT(extend, i, i) == 0) {
            printf("Tidak mempunyai invers");
            flag = false;
            break;
        }
    }

    // Input hasil invers ke matrix m
    if (flag) {
        for (i=0; i<n;i++){
            for (j=0; j<n; j++){
                ELMT(*m, i, j) = ELMT(extend, i, j+n);
            }
        }
    }

    displayMatrix(*m);
}


void inversCofactor(Matrix *m) {
    Matrix mTemp;
    int i, j, n;
    float sign, det;
    sign = 1;
    n = ROWS(*m);

    if (determinantCofactor(*m)==0) {
        printf("Tidak ada invers");
    } else {

    det = 1/(determinantCofactor(*m));
    copyMatrix(*m, &mTemp);

    // Matriks Cofactor
    for (i=0; i<n; i++) {
        for (j=0; j<n; j++){
            ELMT(*m, i, j) = determinantCofactor(cofactor(mTemp, i, j))*sign;
            sign *= -1;
            // displayMatrix(cofactor(mTemp, i, j));
            // printf("\n\n");
        }
        sign *= -1;
    }

    // Matriks Adjoin
    transpose(m);
    
    // 1/det * adjoin
    pMultiplyConst(m, det);
    }

}



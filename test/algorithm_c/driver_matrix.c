#include <stdlib.h>
#include <stdio.h>
#include "matrix.h"
#include "matrix.c"
#include "algorithm.c"

int main() {
    /* KAMUS */
    int m, n;
    Matrix m1;

    /* ALGORITMA */

    scanf("%d %d", &m, &n);
    readMatrix(&m1, m, n);
    printf("\n");
    gaussEquation(m1);
    // double det = determinantOBE(m1);
    // printf("%.1f", det);
    
    // Matrix mOBE, mOBET;
    // mOBE = OBE(m1);
    // mOBET = OBETereduksi(m1);

    // displayMatrix(mOBE);
    // printf("\n");
    // printf("\n");
    // displayMatrix(mOBET);

}
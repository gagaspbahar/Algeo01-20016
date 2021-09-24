#include <stdlib.h>
#include <stdio.h>
#include "matrix.h"
#include "matrix.c"
#include "algorithm.c"

int main() {
    /* KAMUS */
    int m, n;
    Matrix m1, m2;

    /* ALGORITMA */

    scanf("%d %d", &m, &n);
    readMatrix(&m1, m, n);
    printf("\n");
    // displayMatrix(OBE(m1));
    m2 = interpolate(m1);
    printf("%.2f\n", functionInterpolate(m2, 9.2));

}
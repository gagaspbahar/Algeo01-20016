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
    inversOBE(&m1);

}
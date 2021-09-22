#include <stdlib.h>
#include <stdio.h>
#include "boolean.h"
#include "matrix.h"

/* ********** DEFINISI PROTOTIPE PRIMITIF ********** */
/* *** Konstruktor membentuk Matrix *** */
void CreateMatrix(int nRow, int nCol, Matrix *m) {
/* Membentuk sebuah Matrix "kosong" yang siap diisi berukuran nRow x nCol di "ujung kiri" memori */
/* I.S. nRow dan nCol adalah valid untuk memori matriks yang dibuat */
/* F.S. Matriks m sesuai dengan definisi di atas terbentuk */
    ROWS(*m) = nRow;
    COLS(*m) = nCol;
}

/* ********** KELOMPOK BACA/TULIS ********** */
void readMatrix(Matrix *m, int nRow, int nCol) {
/* I.S. isIdxValid(nRow,nCol) */
/* F.S. m terdefinisi nilai elemen efektifnya, berukuran nRow x nCol */
/* Proses: Melakukan CreateMatrix(m,nRow,nCol) dan mengisi nilai efektifnya */
/* Selanjutnya membaca nilai elemen per baris dan kolom */
/* Contoh: Jika nRow = 3 dan nCol = 3, maka contoh cara membaca isi matriks :
1 2 3
4 5 6
8 9 10 
*/
    int i, j;
    CreateMatrix(nRow, nCol, m);
    for (i=0; i<nRow;i++) {
        for (j=0; j<nCol;j++) {
            scanf("%lf", &ELMT(*m, i, j));
        }
    }
}

void displayMatrix(Matrix m) {
/* I.S. m terdefinisi */
/* F.S. Nilai m(i,j) ditulis ke layar per baris per kolom, masing-masing elemen per baris 
   dipisahkan sebuah spasi */
/* Proses: Menulis nilai setiap elemen m ke layar dengan traversal per baris dan per kolom */
/* Contoh: Menulis matriks 3x3 (ingat di akhir tiap baris, tidak ada spasi)
1 2 3
4 5 6
8 9 10
*/  
    int i,j;
    for (i=0; i<ROWS(m);i++) {
        for (j=0; j<COLS(m);j++) {
            if (j == COLS(m) - 1 && i == ROWS(m) - 1) {
                printf("%.1lf", ELMT(m, i, j));
            } else if (j == COLS(m) - 1) {
                printf("%.1lf\n", ELMT(m, i, j));
            } else {
                printf("%.1lf ", ELMT(m, i, j));
            }
        }
    }
}


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

float determinantMatrix (Matrix m, int n) {
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


float determinant(Matrix m) {
/* Prekondisi: isSquare(m) */
/* Menghitung nilai determinan m */
    return determinantMatrix(m, ROWS(m));
    // int a, b, n, i, j, k, index, total;
    // float det = 1;
    // total = 1;
    // n = ROWS(m);

    // Matrix temp;
    // CreateMatrix(1, n+1, &temp);

    // for (i = 0; i<n; i++) {

    //     index = i;

    //     while (ELMT(m, index, i) == 0 && index < n) {
    //         index++;
    //     }

    //     if (index == n) {
    //         return 0;
    //     }

    //     if (i != index) {
            
    //         for (j = 0; j < n; j++) {
    //             int temp = ELMT(m, index, j);
    //             ELMT(m, index, j) = ELMT(m, i, j);
    //             ELMT(m, i, j) = temp;
    //         }

    //         det *= -1;
    //     }

    //     for (j = 0; j<n; j++) {
    //         ELMT(temp, 1, j) = ELMT(m, i, j);
    //     }

    //     for (j = i + 1; j<n; j++) {
    //         a = ELMT(temp, 1, i);
    //         b = ELMT(m, j, i);

    //         for (k=0; k<n; k++) {
    //             ELMT(m, j, k) = (a*ELMT(m, j, k)) - (b*ELMT(temp, 1, k));
    //         }

    //         total = total * a;
    //     }
    // }

    // for (i = 0; i < n; i++){
    //     det *= ELMT(m, i, i);
    // }

    // displayMatrix(m);
    // // displayMatrix(temp);

    // return (det/total);

}

void transpose(Matrix *m) {
/* I.S. m terdefinisi dan isSquare(m) */
/* F.S. m "di-transpose", yaitu setiap elemen m(i,j) ditukar nilainya dengan elemen m(j,i) */
    Matrix mTemp;
    CreateMatrix(COLS(*m), ROWS(*m), &mTemp);

    int i, j;
    for (i = 0; i<ROWS(*m);i++) {
        for (j = 0; j<COLS(*m);j++) {
            ELMT(mTemp, i, j)  = ELMT(*m, j, i);
        }
    }
    copyMatrix(mTemp, m);
}
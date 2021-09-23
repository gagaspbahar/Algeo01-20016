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
                printf("%.3lf", ELMT(m, i, j));
            } else if (j == COLS(m) - 1) {
                printf("%.3lf\n", ELMT(m, i, j));
            } else {
                printf("%.3lf ", ELMT(m, i, j));
            }
        }
    }
}

void pMultiplyConst(Matrix *m, float k) {
/* I.S. m terdefinisi, k terdefinisi */
/* F.S. Mengalikan setiap elemen m dengan k */
    int i,j;
    for (i=0; i<ROWS(*m);i++) {
        for (j=0; j<COLS(*m);j++) {
            ELMT(*m, i, j) = ELMT(*m, i, j)*k;
        }
    }
}

void copyMatrix(Matrix mIn, Matrix *mRes) {
/* Melakukan assignment MRes = MIn */
    CreateMatrix(ROWS(mIn), COLS(mIn), mRes);
    int i, j;
    for (i=0; i<ROWS(mIn);i++) {
        for (j=0; j<COLS(mIn);j++) {
            ELMT(*mRes, i, j) = ELMT(mIn, i, j);
        }
    }
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

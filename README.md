# Algeo01-20016 - Matrix Calculator

<!-- ## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Screenshots](#screenshots)
* [Setup](#setup)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Acknowledgements](#acknowledgements)
* [Contact](#contact)
* [License](#license) -->


## General Information
A matrix calculator made in Java with simple GUI using java.swing library. This repository contains files needed for Tubes 1 Algeo. 
The project we're making is a Java library for Linear Algebra problems (specifically matrices). This includes SPL, Determinant, Inverse, and others.
Contributors:
- 13520016 Gagas Praharsa Bahar
- 13520044 Adiyansa Prasetya Wicaksana
- 13520081 Andhika Arta Aryanto


## Technologies Used
- Java language


## Features
- Solving linear systems of equations using Gauss, Gauss-Jordan, Inverse, and Cramer methods
- Solving determinants using Cofactor and Inverse matrix methods
- Making inverse matrices using Gauss elimination and Adjoint matrix methods
- Solving interpolation problems
- Solving double regression problems


## Structure
```bash
.
│   README.md
│
├───bin                                     # Bytecode-containing folder
│   ├───Algorithm
│   │       Interpolate.class
│   │       Invers.class
│   │       Operation.class
│   │       Regression.class
│   │       SPL.class
│   │
│   ├───Main
│   │       Main.class
│   │
│   ├───Matrix
│   │       Matrix.class
│   │       MatrixInput.class
│   │
│   └───Utility
│           Output.class
│           UI.class
│
├───doc                             # documentation
├───src                             # Source code
│   ├───Algorithm                   # Algorithm package
│   │       Interpolate.java
│   │       Invers.java
│   │       Operation.java
│   │       Regression.java
│   │       SPL.java
│   │
│   ├───Main                        # Main package   
│   │       Main.java
│   │
│   ├───Matrix                      # Matrix package
│   │       Matrix.java
│   │       MatrixInput.java
│   │
│   └───Utility                     # Utility package
│           Output.java
│           UI.java
│
└───test                            # Testing cases
    │   mat.txt
    │   matrix_test_case.txt
    │   spl1.txt
    │   spl2.txt
    │   spl3.txt
    │   spl4.txt
    │   studikasus1a.txt
    │   studikasus1b.txt
    │   studikasus1c.txt
    │   studikasus1d10.txt
    │   studikasus1d6.txt
    │   studikasus1dd10.txt
    │   studikasus2a.txt
    │   studikasus2b.txt
    │   studikasus3a.txt
    │   studikasus3b.txt
    │   studikasus4.txt
    │   studikasus5.txt
    │   studikasus6a.txt
    │   studikasus6b.txt
    │   studikasus6c.txt
    │   studikasus7.txt
    │
    └───result                      # Result file directory
```


## How to run our matrix calculator?
(if already compiled go to step 3)
1. Go to src folder
2. Compile all source code files with this command: `javac -d ..\bin Matrix/*.java Algorithm/*.java Utility/*.java Main/*.java`
3. Go to bin folder
4. Run the Main.Main program using the command : `java Main.Main`

- note: if UnsupportedClassVersionError, recompile the code using step 2. this means your java version doesnt support.


## Acknowledgements

- Thanks to Allah SWT
- Thanks to Mr. Rinaldi Munir, Mr. Jodhi, and Mr. Rila as our Lecturers
- Thanks to academic assistants
- This project was created to fulfill our Big Project for IF2123 Linear and Geometric Algebra


## Contact
Created by SEI SAPI MANG DODZ Group.
2021
All rights reserved
![](https://media.discordapp.net/attachments/881054776048123937/892754016843493396/image0.jpg)

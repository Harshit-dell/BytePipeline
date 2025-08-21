# BytePipeline

## Overview
BytePipeline is a Java-based interpreter for arithmetic expressions and variable assignments. It supports basic operations (`+`, `-`, `*`, `/`), parentheses for precedence, and variable storage with print statements.

## Features
- Lexer: Tokenizes input code
- Parser: Builds an Abstract Syntax Tree (AST)
- Interpreter: Evaluates expressions and executes statements
- Supports variables and arithmetic expressions with proper precedence

## Getting Started
1. Clone the repository:
   
   git clone https://github.com/Harshit-dell/BytePipeline.git

    Navigate to the project directory:

cd BytePipeline

Compile and run:

    javac -d out src/**/*.java
    java -cp out Main

    Provide your code in a text file for the interpreter to execute.

Syntax Examples

    Variable assignment: x = 5;

    Print statement: print x + 3;

    Arithmetic operations: (1 + 2) * 3


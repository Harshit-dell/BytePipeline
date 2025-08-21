# BytePipeline

## Project Overview
**BytePipeline** is a lightweight Java interpreter for evaluating arithmetic expressions and handling variable assignments. It demonstrates the core concepts of building a lexer, parser, and interpreter with proper operator precedence and variable management.

## Features
- **Lexer:** Converts raw input into tokens.
- **Parser:** Constructs an Abstract Syntax Tree (AST) from tokens.
- **Interpreter:** Evaluates expressions and executes statements.
- **Variable Support:** Store and use variables within expressions.
- **Arithmetic Operations:** Handles `+`, `-`, `*`, `/` with proper precedence.
- **Parentheses:** Supports expressions with brackets for grouping.

## Getting Started
1. Clone the repository:
   ```bash
   git clone https://github.com/Harshit-dell/BytePipeline.git

    Navigate to the project directory:

cd BytePipeline

Compile the project:

javac -d out src/**/*.java

Run the interpreter:

java -cp out Main

# BytePipeline

## IDEA-Configurable Syntax Compiler Framework (Experimental)

BytePipeline is an experimental module and tooling designed to help you quickly test grammar changes and run small programs. Its purpose is to prototype language features faster using IDE aids (such as token visualizers and small test harnesses).

**Status:** Experimental — intended as a developer tool for iterating on language syntax and parser behavior. This is **not** yet a polished product.

**Goal:** Make it easy to experiment with syntax changes and evaluate parser behavior without repeatedly editing core code.

---

## Project Overview

BytePipeline is a lightweight interpreter written in pure Java. It demonstrates the core building blocks of a programming language — from lexing and parsing to AST construction and interpretation.

Originally designed for arithmetic evaluation, BytePipeline has evolved into a mini-language that supports:

- **Variables**
- **Functions**
- **Control flow** (if/else, spin loops)
- **Blocks & scoping**
- **Function calls**
- **Recursion**
- **IDE-integrated tooling** (experimental) via an IDEA-configurable syntax framework

> This project is not about frameworks — it’s about understanding how languages work from scratch.

---

## Features

### Lexer
- Tokenizes raw input into identifiers, numbers, operators, keywords, and symbols.

### Parser
- Builds an Abstract Syntax Tree (AST) for expressions and statements.
- Handles operator precedence, grouping, and nested structures.

### Interpreter
- Walks the AST and executes code.
- Supports variable binding, function execution, and condition evaluation.

### Statements
- Variable declaration & assignment
- Print statements
- Blocks `{ ... }`
- If/Else branching
- Spin loops (like `while`)

### Functions
- Function declarations with parameters
- Function calls with arguments
- Function return the value

### Expressions
- Arithmetic (`+`, `-`, `*`, `/`)
- Unary (`+`, `-`)
- Variables & numbers
- Function call as expression

---

## Design Overview

**Flow:**  
`Source → Lexer → Tokens → Parser → AST → Interpreter → Program Output`

- **Lexer:** Splits text into tokens (numbers, identifiers, operators)
- **Parser:** Builds AST nodes (statements & expressions)
- **Interpreter:** Evaluates AST nodes, managing a variable store and function store

---

## Personal Note

BytePipeline began as a learning project — a way to understand how languages work under the hood. A few days later, I decided to push beyond simple arithmetic and make it more capable. I also started an IDEA-Configurable Syntax Compiler Framework as an experimental tool so I could iterate grammar changes quickly inside IntelliJ IDEA.

This repository is a learning playground — not a production language — but it’s a great way to build deep, transferable knowledge.

This is Part of Some other Big Project.

---

## To Run

- Fork the repository
- Run the `Main` file in your Java compiler or IDE

// Base class for expressions
public abstract class Expr { }

// Numbers like 1, 2, 4
class NumberExpr extends Expr {
    int value;
    NumberExpr(int value) { this.value = value; }
}

// Binary operations like +, -, *, /
class BinaryExpr extends Expr{
    Expr left;
    String operator;
    Expr right;
    BinaryExpr(Expr left, String operator, Expr right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}

// Print statement
class PrintStmt {
    Expr expression;
    PrintStmt(Expr expression) {
        this.expression = expression;
    }

}

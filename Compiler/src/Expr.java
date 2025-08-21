// Base class for expressions
abstract class Expr { }
abstract  class Stmt {}
abstract  class Block{}
// Numbers like 1, 2, 42

class VariableStmt extends Stmt {
    String name;
    Expr value;
    VariableStmt(String name, Expr value) {
        this.name = name;
        this.value = value;
    }
}

// Print statement
//last stage
class PrintStmt extends Stmt {
    Expr expr;
    PrintStmt(Expr expr) { this.expr = expr; }
}



class UnaryExpr extends  Expr{
    String operator;
    Expr value;
    UnaryExpr(String operator,Expr value){
        if(operator.equals("-") || operator.equals("+")){
            this.operator=operator;
        }
        else{
            throw  new RuntimeException("This univary method sucks cahnge it -Expr");
        }
        this.value=value;
    }

}

class NumberExpr extends Expr {
    int value;
    NumberExpr(int value) { this.value = value; }
}


//made this just becuase return type of the adddition was EXPR
class VariableExpr extends Expr {
    String name;
    VariableExpr(String name) { this.name = name; }
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

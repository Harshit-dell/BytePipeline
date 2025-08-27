import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

class Pair <V,K>{
    V First;
    K Second;
    Pair(V first,K second){
        this.First=first;
        this.Second=second;
    }
}

// Base class for expressions
abstract  class Stmt {}
abstract class Expr {}
// Numbers like 1, 2, 42



class ExprStmt extends Stmt {
    Expr expr;
    ExprStmt(Expr expr) { this.expr = expr; }
}

class FunctionDelc extends  Stmt{
    String name;
    List<String> parameter;
    List<Stmt> block;
    FunctionDelc(String name,List<String > a,List<Stmt> b){
        this.name=name;
        this.parameter=a;
        this.block=b;
    }

}



class SpinStmt extends  Stmt{
    final Expr Condtion;
    final Stmt spinBlock;
    SpinStmt(Expr condtion,Stmt spinBlock){
        this.Condtion=condtion;
        this.spinBlock=spinBlock;
    }
}



class IfElse extends  Stmt{
    final Expr Condition;
    final Stmt ifBlock;
    final Stmt elseBlock;
    IfElse(Expr a,Stmt b,Stmt c){
        this.Condition=a;
        this.ifBlock=b;
        this.elseBlock=c;

    }

}


class VariableStmt extends Stmt {
    String name;
    Expr value;
    VariableStmt(String name, Expr value) {
        this.name = name;
        this.value = value;
    }
    VariableStmt(String name) {
        this.name = name;
        this.value =null;
    }
}

// Print statement
//last stage
class PrintStmt extends Stmt {
    Expr expr;
    PrintStmt(Expr expr) { this.expr = expr; }
}
class BlockStmt extends Stmt{
    public  final  List<Stmt> statement;
    BlockStmt(List<Stmt> statements) {
        this.statement= statements;
    }
}

    class FunctionCall extends Expr {
    String name;
    List<Expr> arguments;
    FunctionCall(String name, List<Expr> args) {
        this.name = name;
        this.arguments = args;
    }
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

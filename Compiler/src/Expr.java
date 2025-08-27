import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;// Base class for expressions
    abstract  class Expr{}


//made this just becuase return type of the adddition was EXPR
class VariableExpr extends Expr {
    String name;
    VariableExpr(String name) { this.name = name; }
}

class NumberExpr extends Expr {
    int value;
    NumberExpr(int value) { this.value = value; }
}

class StringExpr extends Expr {
    String value;
    StringExpr(String value) { this.value = value; }
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

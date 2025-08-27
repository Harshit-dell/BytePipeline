import java.util.List;

abstract  class Stmt {}

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
public class  Interpreter {
    public int evaluate(Expr expr) {
        if (expr instanceof NumberExpr) {
            return ((NumberExpr) expr).value;
        }
        int left=evaluate(((BinaryExpr)expr).left);
        int right=evaluate(((BinaryExpr)expr).right);
        switch (((BinaryExpr)expr).operator){
                case "+":
                    return  left+right;
                    case "-":
                        return  left-right;
            case "*":
                return left*right;
            case "/":
                return  left/right;
        }
        throw new RuntimeException("dikkat hai interpretor mein ");
    }
    public void execute(PrintStmt stmt) {
        System.out.println(evaluate(stmt.expression));
    }
}

import java.util.HashMap;

public class Interpreter {
    private final HashMap<String, Integer> variableStore = new HashMap<>();

    // Evaluate expressions recursively
    public int evaluate(Expr expr) {
        if (expr instanceof NumberExpr) {
            return ((NumberExpr) expr).value;
        }
        else if (expr instanceof VariableExpr) {
            String name = ((VariableExpr) expr).name;
            if (!variableStore.containsKey(name)) {
                throw new RuntimeException("Variable -> " + name + " is not defined");
            }
            return variableStore.get(name);
        }
        else if (expr instanceof UnaryExpr) {
            UnaryExpr unary = (UnaryExpr) expr;
            int value=evaluate(unary.value);
            String operator= unary.operator;
            if(operator.equals("-")) return -value;
            return value;

        }

        else if (expr instanceof BinaryExpr) {
            BinaryExpr bin = (BinaryExpr) expr;
            int leftVal = evaluate(bin.left);
            int rightVal = evaluate(bin.right);
            switch (bin.operator) {
                case "+": return leftVal + rightVal;
                case "-": return leftVal - rightVal;
                case "*": return leftVal * rightVal;
                case "/": return leftVal / rightVal;
                default:
                    throw new RuntimeException("Unknown operator: " + bin.operator);
            }
        }
        throw new RuntimeException("Interpreter error: unknown expression type");
    }

    // Execute statements (VariableStmt or PrintStmt)
    public void execute(Stmt stmt) {
        if (stmt instanceof VariableStmt) {
            int value = evaluate(((VariableStmt) stmt).value);
            variableStore.put(((VariableStmt) stmt).name, value);
        }
        else if (stmt instanceof PrintStmt) {
            int value = evaluate(((PrintStmt) stmt).expr);
            System.out.println(value);
        }
        else {
            throw new RuntimeException("Interpreter fault: unknown statement");
        }
    }
}
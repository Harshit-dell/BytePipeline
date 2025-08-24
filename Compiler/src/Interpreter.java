
import java.util.*;

public class Interpreter {
    private  HashMap<String, Integer> variableStore = new HashMap<>();

    public void execute(List<Stmt> ListStmt ) {
        for(Stmt stmt:ListStmt){
            check(stmt);
        }
    }

    private  void check(Stmt stmt){
        if (stmt instanceof VariableStmt) {
            int value = evaluate(((VariableStmt) stmt).value);
            variableStore.put(((VariableStmt) stmt).name, value);
        }
        else if (stmt instanceof PrintStmt) {
            int value = evaluate(((PrintStmt) stmt).expr);
            System.out.print("Output is :");
            System.out.println(value);
        }
        else if(stmt instanceof BlockStmt){
            executeBlock((BlockStmt)stmt);
        }
        else  if(stmt instanceof IfElse){
            executeFlipFlop((IfElse)stmt);
        }
        else {
            throw new RuntimeException("Check-Interpreter fault");
        }
    }
    private  void executeFlipFlop(IfElse stmt){
              int condValue = evaluate(stmt.Condition);
              if (condValue != 0) {
                  check(stmt.ifBlock);
              } else if (stmt.elseBlock != null) {
                  check(stmt.elseBlock);
              }
    }
    private void executeBlock(BlockStmt stmt) {
        HashMap<String, Integer> previous = new HashMap<>(variableStore);

        try {
            variableStore = new HashMap<>(previous);

            for (Stmt qwe : stmt.statement) {
                check(qwe);
            }
        } finally {
            variableStore = previous;
        }
    }


    // Execute statements (VariableStmt or PrintStmt)


    // Evaluate expressions recursively
    private int evaluate(Expr expr) {
        if (expr instanceof NumberExpr) {
            return ((NumberExpr) expr).value;
        }
        else if (expr instanceof VariableExpr) {
            String name = ((VariableExpr) expr).name;
            if (!variableStore.containsKey(name)) {
                throw new RuntimeException("Variable -> " + name + " is not defined  evalute-interpreter");
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
            int left = evaluate(bin.left);
            int right = evaluate(bin.right);
            return switch (bin.operator) {
                case "+" -> left + right;
                case "-" -> left - right;
                case "*" -> left * right;
                case "/" -> left / right;
                case "<" -> (left < right) ? 1 : 0;
                case "<=" -> (left <= right) ? 1 : 0;
                case ">" -> (left > right) ? 1 : 0;
                case ">=" -> (left >= right) ? 1 : 0;
                case "==" -> (left == right) ? 1 : 0;
                case "!=" -> (left != right) ? 1 : 0;
                default -> throw new RuntimeException("Unknown operator " + bin.operator);
            };
        }

        throw new RuntimeException("Interpreter error: unknown expression type");
    }

}
import java.util.*;

public class Interpreter {
    private HashMap<String, Object> variableStore = new HashMap<>();
    private HashMap<String, Pair<List<String>, List<Stmt>>> functionStore = new HashMap<>();
    public void execute(List<Stmt> ListStmt) {
        for (Stmt stmt : ListStmt) {
            check(stmt);
        }
    }

    private void check(Stmt stmt) {
        if (stmt instanceof VariableStmt) {
            Object value = evaluate(((VariableStmt) stmt).value);
            variableStore.put(((VariableStmt) stmt).name, value);
        }

        else if (stmt instanceof PrintStmt) {
            Object value = evaluate(((PrintStmt) stmt).expr);
            System.out.print("Output is : ");
            System.out.println(value);
        }

        else if (stmt instanceof BlockStmt) {
            executeBlock((BlockStmt) stmt);
        }

        else if (stmt instanceof IfElse) {
            executeFlipFlop((IfElse) stmt);
        }

        else if (stmt instanceof SpinStmt) {
            executeSpin((SpinStmt) stmt);
        }

        else if (stmt instanceof FunctionDelc) {
            FunctionDelc function = ((FunctionDelc) stmt);
            if (functionStore.containsKey(function.name)) {
                throw new RuntimeException("Function already defined: " + function.name);
            } else {
                functionStore.put(function.name, new Pair<>(function.parameter, function.block));
            }
        }

        else if (stmt instanceof ExprStmt) {
            evaluate(((ExprStmt) stmt).expr);
        }

        else {
            throw new RuntimeException("Check-Interpreter fault");
        }
    }

    private void executeSpin(SpinStmt stmt) {
        try {
            while (toInt(evaluate(stmt.Condtion)) != 0) {
                executeBlock_Varibalechange((BlockStmt) stmt.spinBlock);
            }
        } catch (Exception e) {
            System.out.printf("new code" + e.getMessage());
        }
    }

    private void executeBlock_Varibalechange(BlockStmt stmt) {
        for (Stmt qwe : stmt.statement) {
            check(qwe);
        }
    }

    private void executeFlipFlop(IfElse stmt) {
        int condValue = toInt(evaluate(stmt.Condition));
        if (condValue != 0) {
            check(stmt.ifBlock);
        } else if (stmt.elseBlock != null) {
            check(stmt.elseBlock);
        }
    }

    private void executeBlock(BlockStmt stmt) {
        HashMap<String, Object> previous = new HashMap<>(variableStore);
        try {
            variableStore = new HashMap<>(previous);
            for (Stmt qwe : stmt.statement) {
                check(qwe);
            }
        } finally {
            variableStore = previous;
        }
    }

    private Object evaluate(Expr expr) {
        if (expr instanceof NumberExpr) {
            return ((NumberExpr) expr).value;
        }

        else if (expr instanceof StringExpr) {
            return ((StringExpr) expr).value;
        }

        else if (expr instanceof FunctionCall) {
            FunctionCall temp = (FunctionCall) expr;
            String name = temp.name;
            List<Expr> argument = temp.arguments;

            if (!functionStore.containsKey(name)) {
                throw new RuntimeException("Function -> " + name + " not defined");
            }

            Pair<List<String>, List<Stmt>> function = functionStore.get(name);
            List<String> parameter = function.First;
            List<Stmt> block = function.Second;

            if (parameter.size() != argument.size()) {
                throw new RuntimeException("Argument count mismatch in function call -> " + name);
            }

            HashMap<String, Object> tempStorage = new HashMap<>(variableStore);

            for (int i = 0; i < parameter.size(); i++) {
                Object value = evaluate(argument.get(i));
                variableStore.put(parameter.get(i), value);
            }

            executeBlock(new BlockStmt(block));

            variableStore = tempStorage;

            return 0;
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
            int value = toInt(evaluate(unary.value));
            if (unary.operator.equals("-")) return -value;
            return value;
        }
        else if (expr instanceof BinaryExpr) {
            BinaryExpr bin = (BinaryExpr) expr;
            Object leftObj = evaluate(bin.left);
            Object rightObj = evaluate(bin.right);

            // String concatenation
            if (bin.operator.equals("+") && (leftObj instanceof String || rightObj instanceof String)) {
                return String.valueOf(leftObj) + String.valueOf(rightObj);
            }

            int left = toInt(leftObj);
            int right = toInt(rightObj);

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

    private int toInt(Object obj) {
        //just rechecking agr phele se int aya ho toh
        if (obj instanceof Integer) return (Integer) obj;
        //here the problem is , and sometime i need the int as string as sometime int as string
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                return ((String) obj).isEmpty() ? 0 : 1; // non-empty string treated as true
            }
        }
        throw new RuntimeException("Cannot convert to int: " + obj);
    }
}

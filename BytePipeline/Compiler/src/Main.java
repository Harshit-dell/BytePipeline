//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer("tell 1 + 2 + 3;");
        Parser parser = new Parser(lexer);

        PrintStmt stmt = parser.parsePrint();

        Interpreter interpreter=new Interpreter();
        interpreter.execute(stmt);
    }
}

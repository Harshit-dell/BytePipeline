import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("write code here:)");
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String value=reader.readLine();

        Lexer lexer = new Lexer(value);
        Parser parser = new Parser(lexer);

        PrintStmt stmt = parser.parsePrint();

        Interpreter interpreter=new Interpreter();
        interpreter.execute(stmt);
    }
}

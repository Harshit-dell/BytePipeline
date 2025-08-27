import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String file_location = "/home/deadpool/Working_Project/BytePipeline/Program_code.txt";
        StringBuilder sb = new StringBuilder();
         try (BufferedReader reader = new BufferedReader(new FileReader(file_location))) {
             String line;
             while ((line = reader.readLine()) != null) {
                 sb.append(line).append('\n');
             }
         }
         String source = sb.toString();
        //from the last time i have taken the whole string becuse making the lexer inside loop make the
        Lexer lexer = new Lexer(source);
        Parser parser = new Parser(lexer);
        List<Stmt> program = parser.parseProgram();

        Interpreter interp = new Interpreter();
        interp.execute(program);
    }
}
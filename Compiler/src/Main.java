import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("write code here:)");
        String file_location="/home/deadpool/Working_Project/first_program.txt";
        BufferedReader reader=new BufferedReader(new FileReader(file_location));
        String value=reader.readLine();
        //interepreter hashmap was getting formated every time because it was in loop
        Interpreter interpreter=new Interpreter();
        while(value!=null){
            if(value.trim().isEmpty()){
                value=reader.readLine();
                continue;
            }
            Lexer lexer = new Lexer(value);
            Parser parser = new Parser(lexer);

            Stmt stmt = parser.starter();

            interpreter.execute(stmt);
            value=reader.readLine();


        }

        reader.close();
    }
}
